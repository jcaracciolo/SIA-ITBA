package ar.edu.itba.sia.Engine.mutators

import java.util.*
import ar.edu.itba.sia.evolutionable.Evolutionable

class UniformMutator(val mutatingProbability: Double) : Mutator {

    override fun mutate(specimen: Evolutionable, generation: Int, genMutator: GenMutator): Evolutionable {

        if( Random().nextDouble() > mutatingProbability){
            genMutator.mutate(specimen)
        }

        return specimen
    }

}