package ar.edu.itba.sia.Engine.mutators

import ar.edu.itba.sia.evolutionable.characters.Evolutionable

interface Mutator<G> {

    val genMutator : GenMutator<G>
    fun <G> mutate(specimen: Evolutionable<G>, generation: Int, genMutator: GenMutator<G>): Evolutionable<G>

}