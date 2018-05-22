package ar.edu.itba.sia.Engine.mutators

import ar.edu.itba.sia.evolutionable.Evolutionable

interface GenMutator {

    fun <G> mutate(specimen: Evolutionable<G>)

}