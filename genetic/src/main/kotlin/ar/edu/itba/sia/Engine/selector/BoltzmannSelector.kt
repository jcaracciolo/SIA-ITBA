package ar.edu.itba.sia.Engine.selector

import ar.edu.itba.sia.evolutionable.Evolutionable

class BoltzmannSelector(val other: Selector, var temperature: Double, val decrement: Double) : Selector {
    override fun select(generation: List<Evolutionable>, amount: Int): List<Evolutionable> {
        val newPerformances = generation.map {
            Math.exp(it.getPerformance() / temperature)
        }
        val average = newPerformances.average()

        this.temperature *= decrement

        return other.select(generation.mapIndexed { index, evolutionable ->
            AdaptedEvolutionable(newPerformances[index] / average, evolutionable)
        }, amount).map { (it as AdaptedEvolutionable).specimen }
    }
}