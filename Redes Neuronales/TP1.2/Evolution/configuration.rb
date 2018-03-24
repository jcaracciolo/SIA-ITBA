require 'distribution'

threads = 4
generations = 2
sample = 8
chosens = 5


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

  attr_reader :layersSize
  attr_reader :learningRate
  attr_reader :alphaMomentum
  attr_reader :etaPositiveAdjustment
  attr_reader :etaNegativeAdjustment
  attr_reader :maxEtaErrorReductions
  attr_accessor :error
  attr_accessor :thread_index

  @layersSize
  @learningRate
  @alphaMomentum
  @etaPositiveAdjustment
  @etaNegativeAdjustment
  @maxEtaErrorReductions
  @error
  @thread_index

  def layer_size_bounds
    [2,80]
  end

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
    @maxEtaErrorReductions = reductions.clamp(eta_reduction_bounds).round
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
global trainerPath = 'Trainer/XOR';
global activationFunctionPath = 'ActivationFunction/tanH';
global trainingPercentage = 0.65;
global randomSeed = #{@thread_index + Random.new.rand}
global visual=false;
"
  end


# FUNCIONALITY
  def initialize_random
    @layersSize = Array.new(1) { Random.rand(layer_size_bounds[0]..layer_size_bounds[1])}
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
                  ((@layersSize[0] + other.layersSize[0])*Random.rand(0.7..1.3)/2).clamp(layer_size_bounds).round
              }
    conf
  end

  

  def calculateError
    accumulator = 0
    tries = 3

    tries.times do
      f = File.new("../config", "w")
      f.write(to_s)
      f.close

      cmd = "cd .. && octave MultiLayerNeuralNetwork > errorOutput#{@thread_index} && cd Evolution"
      system(cmd)

      f = File.open("../errorOutput#{@thread_index}", "r")
      accumulator += f.readlines[0].strip.to_f
      f.close
    end

    @error = accumulator/tries
    puts @error
    @error
  end


end


next_confs = []
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
            begin sleep 1 rescue Interrupt end
            running_threads+=1

            if running_threads == threads

              (i+1).times do |j|
                while confs[j].error.nil?
                  begin sleep 1 rescue Interrupt end
                end
              end

              running_threads=0
            end
          end
        end
    end

    avrg = 0
    for c in confs
      avrg += c.error
    end

    puts "NEW GENERATION AVRG #{avrg/confs.size}"

    confs.sort_by! {|x| x.error }

    next_confs = []
    (sample/2).times do |i|
      next_confs << confs[i]
      next_confs << confs[i].breed(confs[more_prob_less(sample)])
    end
  end

  for c in confs[0...chosens]
    open("chosens", "a") do |f|
      f << c.to_s
      f << "-----------------------------------\n"
    end
  end
end