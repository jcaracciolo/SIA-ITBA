package ar.edu.itba.sia.Engine.selector

import ar.edu.itba.sia.evolutionable.Evolutionable

interface Selector {

    fun <G> select(generation: List<Evolutionable<G>>, amount: Int): List<Evolutionable<G>>

}