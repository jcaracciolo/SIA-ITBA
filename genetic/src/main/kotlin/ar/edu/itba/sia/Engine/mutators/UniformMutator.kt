package ar.edu.itba.sia.Engine.mutators

import ar.edu.itba.sia.evolutionable.characters.Evolutionable
import java.util.*

class UniformMutator () : Mutator {


    val mutatingProbability = 0.5

    override fun <G> mutate(specimen: Evolutionable<G>, generation: Int, genMutator: GenMutator<G>): Evolutionable<G> {


        if( Random().nextDouble() > mutatingProbability + generation * generationProbabilityMaxIncrease){
            genMutator.mutate(specimen)
        }

        return specimen
    }

}