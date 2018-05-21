package ar.edu.itba.sia.Engine.mutators

import ar.edu.itba.sia.evolutionable.characters.Evolutionable

interface Mutator {
    fun <G> mutate(specimen: Evolutionable<G>, generation: Int, genMutator: GenMutator<G>): Evolutionable<G>

}