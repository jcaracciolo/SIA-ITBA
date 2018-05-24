package ar.edu.itba.sia.Engine.selector

import ar.edu.itba.sia.evolutionable.Evolutionable

class TournamentDeterministicSelector(val tourneySize: Int) : Selector {

    override fun select(generation: List<Evolutionable>, amount: Int): List<Evolutionable> {
        val selected = ArrayList<Evolutionable>(amount)
        for (i in 0 until amount){
            val tourney = generation.shuffled().subList(0, tourneySize-1).sortedByDescending { it.getPerformance() }
            selected.add(tourney.first())
        }
        return selected
    }
}