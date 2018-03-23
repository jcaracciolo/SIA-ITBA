require 'distribution'

rand = Random.new
threads = 3
generations = 12
sample = 6
number_of_chosens = 5

class Object
  def clamp(bounds)
    [bounds[0], self, bounds[1]].sort[1]
  end
end

def more_prob_less(bound)
  (Distribution::Normal.rng(mean = 0, sigma =bound/2, seed = nil).call.abs.floor + (Random.rand(0..20)/19).to_i).clamp ([0,bound-1])
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
    [2,6]
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
global randomSeed = #{@thread_index + Random.new.rand}
"
  end


# FUNCIONALITY
  def initialize_random
    @layersSize = Array.new(1) { Random.rand(2..50)}
    @learningRate = Random.rand(learning_rate_bounds[0]..learning_rate_bounds[1])
    @alphaMomentum = Random.rand(alpha_momentum_bounds[0]..alpha_momentum_bounds[1])
    @etaPositiveAdjustment = Random.rand(eta_bounds[0]..eta_bounds[1])
    @etaNegativeAdjustment = Random.rand(eta_bounds[0]..eta_bounds[1])
    @maxEtaErrorReductions = Random.rand(eta_reduction_bounds[0]..eta_reduction_bounds[1])
    self
  end

  def breed(other)
    conf = Configuration.new
    conf.learningRate= (@learningRate+other.learningRate)/2*Random.rand(0.8..1.2)
    conf.alphaMomentum= (@alphaMomentum+other.alphaMomentum)/2*Random.rand(0.8..1.2)
    conf.etaPositiveAdjustment= (@etaPositiveAdjustment+other.etaPositiveAdjustment)/2*Random.rand(0.8..1.2)
    conf.etaNegativeAdjustment= (@etaNegativeAdjustment+other.etaNegativeAdjustment)/2*Random.rand(0.8..1.2)
    conf.maxEtaErrorReductions= (@maxEtaErrorReductions+other.maxEtaErrorReductions)/2 + Random.rand(-1..1)

    conf.layersSize = Array.new(1) {
                  ((@layersSize[0] + other.layersSize[0])*Random.rand(0.7..1.3)/2).clamp([2,50]).round
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
      iterationError = f.readlines[0].strip.to_f
      if iterationError < 0.00000001
        puts "wtf"
      end
      acumulator += 1/iterationError
      f.close
    end

    @error = acumulator
    puts @error
    @error
  end


end


next_confs = []
chosens = []
confs = []


while true
  confs = []
  next_confs = []
  sample.times do
    next_confs << Configuration.new.initialize_random
  end
  generations.times do |g|
    puts "GENERATION #{g}"

    confs = next_confs

    running_threads = 0
    sample.times do |i|
        if running_threads < threads
          if confs[i].error.nil?
            confs[i].thread_index = i
            Thread.start { confs[i].calculateError }
            begin
              sleep 1
            rescue Interrupt
            end
            running_threads+=1

            if running_threads == threads
              (i+1).times do |j|
                while confs[j].error.nil?
                  begin
                    sleep 1
                  rescue Interrupt
                  end
                end
              end
              running_threads=0
            end
          end
        end
    end

    avrg = 0
    for c in confs
      c.survival_instinct= c.error
      avrg += c.error/1000

      if chosens.size < number_of_chosens
        chosens << c
      elsif c.error > chosens[0].error
        chosens = chosens[1...chosens.size]
        chosens << c
        chosens.sort_by! {|x| -x.error }
      end

    end

    puts "NEW GENERATION AVRG #{avrg}"

    confs.sort_by! {|x| -x.survival_instinct }

    next_confs = []
    (sample/2).times do |i|
      next_confs << confs[i]
      next_confs << confs[i].breed(confs[more_prob_less(sample)])
    end
  end

  for c in chosens
    open("chosens", "a") do |f|
      f << c.to_s
      f << "-----------------------------------\n"
    end
  end
end
