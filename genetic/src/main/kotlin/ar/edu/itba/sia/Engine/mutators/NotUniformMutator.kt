package ar.edu.itba.sia.Engine.mutators

import ar.edu.itba.sia.evolutionable.characters.Evolutionable
import java.util.*

class NotUniformMutator (val generationProbabilityMaxIncrease: Double): Mutator {

    val baseMutatingProbability = 0.5

    override fun <G> mutate(specimen: Evolutionable<G>, generation: Int, genMutator: GenMutator<G>): Evolutionable<G> {

        if (Random().nextDouble() > baseMutatingProbability + generation * generationProbabilityMaxIncrease){
            genMutator.mutate(specimen)
        }

        return specimen

    }

}