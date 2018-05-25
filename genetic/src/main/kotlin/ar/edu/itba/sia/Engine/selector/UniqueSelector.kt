package ar.edu.itba.sia.Engine.selector

import ar.edu.itba.sia.evolutionable.Evolutionable

class UniqueSelector(val selector: Selector): Selector {

    override fun select(generation: List<Evolutionable>, amount: Int): List<Evolutionable> {
        val mutableGeneration = generation.toMutableList()
        val selected = HashSet<Evolutionable>()

        while(selected.size<amount && mutableGeneration.size>0) {
            val next = selector.select(mutableGeneration, 1)
            mutableGeneration.remove(next.first())
            selected.addAll(next)
        }

        while (selected.size<amount) {
            selected.add(selected.first().random())
        }

        return selected.toList()
    }
}