package ar.edu.itba.sia.Engine.mutators

import java.util.*
import ar.edu.itba.sia.evolutionable.Evolutionable

class UniformMutator(val mutatingProbability: Double) : Mutator {

    override fun <G> mutate(specimen: Evolutionable<G>, generation: Int, genMutator: GenMutator): Evolutionable<G> {

        if( Random().nextDouble() > mutatingProbability){
            genMutator.mutate(specimen)
        }

        return specimen
    }

}