package ar.edu.itba.sia.Engine.selector

import ar.edu.itba.sia.evolutionable.Evolutionable

class EliteSelector: Selector {

    override fun select(generation: List<Evolutionable>, amount: Int): List<Evolutionable> =
            generation.sortedBy { it.getPerformance() }.subList(0, amount)
}