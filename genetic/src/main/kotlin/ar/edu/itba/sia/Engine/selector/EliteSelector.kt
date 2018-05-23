package ar.edu.itba.sia.Engine.selector

import ar.edu.itba.sia.evolutionable.Evolutionable

class EliteSelector: Selector {

    override fun <G> select(generation: List<Evolutionable<G>>, amount: Int): List<Evolutionable<G>> =
            generation.sortedBy { it.getPerformance() }.subList(0, amount)
}