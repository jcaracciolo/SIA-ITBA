package ar.edu.itba.sia.Engine.selector

import ar.edu.itba.sia.evolutionable.Evolutionable

class RankingSelector(val other: Selector) : Selector {
    override fun select(generation: List<Evolutionable>, amount: Int): List<Evolutionable> {
        val ordered = generation.sortedBy { it.getPerformance() }

        return other.select(ordered.mapIndexed { index, evolutionable ->
            AdaptedEvolutionable(index.toDouble(), evolutionable)
        }, amount)
    }
}