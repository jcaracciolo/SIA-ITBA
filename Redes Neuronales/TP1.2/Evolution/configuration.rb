rand = Random.new
threads = 4
generations = 10
sample = 16
number_of_chosens = 5

class Object
  def clamp(bounds)
    [bounds[0], self, bounds[1]].sort[1]
  end
end

class Configuration
  #   Variables
  @layersSize
  @learningRate
  @alphaMomentum
  @etaPositiveAdjustment
  @etaNegativeAdjustment
  @maxEtaErrorReductions

  @survival_instinct
  @error
  @thread_index

  attr_reader :layersSize
  attr_reader :learningRate
  attr_reader :alphaMomentum
  attr_reader :etaPositiveAdjustment
  attr_reader :etaNegativeAdjustment
  attr_reader :maxEtaErrorReductions
  attr_accessor :survival_instinct
  attr_accessor :error
  attr_accessor :thread_index

  def layersSize=(layers)
    if layers.empty?
      @layersSize = [2];
    else
      @layersSize = layers
    end
  end

  def learning_rate_bounds
    [0.005,0.1]
  end

  def learningRate=(rate)
    @learningRate = rate.clamp(learning_rate_bounds)
  end

  def alpha_momentum_bounds
    [0.8,0.95]
  end

  def alphaMomentum=(momentum)
    @alphaMomentum = momentum.clamp(alpha_momentum_bounds)
  end

  def eta_bounds
    [0.005,0.2]
  end

  def etaNegativeAdjustment=(eta_n)
    @etaNegativeAdjustment = eta_n.clamp(eta_bounds)
  end

  def etaPositiveAdjustment=(eta_p)
    @etaPositiveAdjustment = eta_p.clamp(eta_bounds)
  end

  def eta_reduction_bounds
    [1,6]
  end

  def maxEtaErrorReductions=(reductions)
    @maxEtaErrorReductions = reductions.clamp(eta_reduction_bounds)
  end

  def to_s
    "global inputSize = 2;
global outputSize = 1;
global layersSize = #{@layersSize};
global learningRate = #{@learningRate};
global alphaMomentum = #{@alphaMomentum};
global etaPositiveAdjustment = #{@etaPositiveAdjustment};
global etaNegativeAdjustment = #{@etaNegativeAdjustment};
global maxEtaErrorReductions = #{@maxEtaErrorReductions};
global validateEpsilon = 0.1;
global trainerPath = 'Trainer/TerrainTrainer';
global activationFunctionPath = 'ActivationFunction/tanH';
global trainingPercentage = 0.8;
global randomSeed = #{rand + @thread_index}
"
  end


# FUNCIONALITY
  def initialize_random
    @layersSize = Array.new(rand(1..3)) { rand(2..4)}
    @learningRate = rand(learning_rate_bounds[0]..learning_rate_bounds[1])
    @alphaMomentum = rand(alpha_momentum_bounds[0]..alpha_momentum_bounds[1])
    @etaPositiveAdjustment = rand(eta_bounds[0]..eta_bounds[1])
    @etaNegativeAdjustment = rand(eta_bounds[0]..eta_bounds[1])
    @maxEtaErrorReductions = rand(eta_reduction_bounds[0]..eta_reduction_bounds[1])
    self
  end

  def breed(other)
    conf = Configuration.new
    conf.learningRate= (@learningRate+other.learningRate)/2*rand(0.8..1.2)
    conf.alphaMomentum= (@alphaMomentum+other.alphaMomentum)/2*rand(0.8..1.2)
    conf.etaPositiveAdjustment= (@etaPositiveAdjustment+other.etaPositiveAdjustment)/2*rand(0.8..1.2)
    conf.etaNegativeAdjustment= (@etaNegativeAdjustment+other.etaNegativeAdjustment)/2*rand(0.8..1.2)
    conf.maxEtaErrorReductions= (@maxEtaErrorReductions+other.maxEtaErrorReductions)/2*rand(0.8)

    layer_size = ((@layersSize.size + other.layersSize.size)*rand(0.8..1.2)/2).clamp([1,3]).round
    conf.layersSize = Array.new(layer_size) {
                  ((@layersSize.sample + other.layersSize.sample)*rand(0.8..1.2)/2).clamp([2,5]).round
              }
    conf
  end

  def calculateError
    acumulator = 0
    3.times do
      f = File.new("../config", "w")
      f.write(to_s)
      f.close

      cmd = "cd .. && octave MultiLayerNeuralNetwork > errorOutput#{@thread_index} && cd Evolution"
      system(cmd)

      f = File.open("../errorOutput#{@thread_index}", "r")
      acumulator += 1/(f.readlines[0].strip.to_f)
      f.close
    end

    @error = acumulator
    puts @error
    @error
  end


end


next_confs = []
chosens = []

sample.times do
  next_confs << Configuration.new.initialize_random
end

while true
  generations.times do |g|
    puts "GENERATION #{g}"

    confs = next_confs

    (0...sample).each_slice(threads) do |bucket|
        bucket.each do |i|
          confs[i].thread_index = i
          Thread.start { confs[i].calculateError }
          begin
            sleep 1
          rescue Interrupt
          end
        end

        bucket.each do |i|
          while confs[i].error.nil?
            begin
              sleep 1
            rescue Interrupt
            end
          end
        end

    end

    avrg = 0
    for c in confs
      c.survival_instinct= rand(c.error*0.9..c.error)
      avrg += c.error

      if chosens.size < number_of_chosens
        chosens << c
      elsif c.error > chosens[0].error
        chosens = chosens[1...chosens.size]
        chosens << c
        chosens.sort_by! {|x| x.error }
      end

    end

    puts "NEW GENERATION AVRG #{avrg}"

    confs.sort_by! {|x| x.survival_instinct }

    next_confs = []
    (sample/2).times do |i|
      next_confs << confs[i].breed(confs[rand(0...sample/2)])
      next_confs << confs[i].breed(confs[rand(0...sample/2)])
    end
  end

  for c in chosens
    open("chosens", "a") do |f|
      f << c.to_s
      f << "-----------------------------------\n"
    end
  end
end
