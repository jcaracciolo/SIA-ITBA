package ar.edu.itba.sia.Engine.mutators

import ar.edu.itba.sia.evolutionable.Evolutionable
import java.util.*

class NotUniformMutator(val generationProbabilityMaxIncrease: Double): Mutator {

    val baseMutatingProbability = 0.5

    override fun mutate(specimen: Evolutionable, generation: Int, genMutator: GenMutator): Evolutionable {

        if (Random().nextDouble() > baseMutatingProbability + generation * generationProbabilityMaxIncrease){
            genMutator.mutate(specimen)
        }

        return specimen
    }

}