package ar.edu.itba.sia.Engine.selector

import ar.edu.itba.sia.evolutionable.Evolutionable

class TournamentProbabilisticSelector : Selector {

    override fun select(generation: List<Evolutionable>, amount: Int): List<Evolutionable> {
        val selected = ArrayList<Evolutionable>(amount)
        for (i in 0 until amount){
            val duel = generation.shuffled().subList(0, 1).sortedByDescending { it.getPerformance() }
            val result = Math.random()
            when {
                result < 0.75 -> selected.add(duel.first())
                else -> selected.add(duel.last())
            }
        }
        return selected
    }
}