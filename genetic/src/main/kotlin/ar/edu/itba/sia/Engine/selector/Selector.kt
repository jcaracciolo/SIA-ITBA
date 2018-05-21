package ar.edu.itba.sia.Engine.selector

import ar.edu.itba.sia.evolutionable.characters.Evolutionable

interface Selector {

    fun select(generation: List<Evolutionable>, amount: Int): List<Evolutionable>

}