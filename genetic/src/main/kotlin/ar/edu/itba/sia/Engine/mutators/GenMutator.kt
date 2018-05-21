package ar.edu.itba.sia.Engine.mutators

import ar.edu.itba.sia.evolutionable.characters.Evolutionable

interface GenMutator<G> {

    fun mutate(specimen: Evolutionable<G>)

}