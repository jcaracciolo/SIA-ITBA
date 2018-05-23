package ar.edu.itba.sia.Engine.mutators

import ar.edu.itba.sia.evolutionable.Evolutionable

interface Mutator {

    fun mutate(specimen: Evolutionable, generation: Int, genMutator: GenMutator): Evolutionable

}