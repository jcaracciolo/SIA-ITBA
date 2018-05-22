package ar.edu.itba.sia.Engine.cutter

import ar.edu.itba.sia.evolutionable.Evolutionable

class GenerationStaysSame(private val generationsSame: Int, private val amountToConsider: Int): Cutter{

    private var currentCount = 0
    private var lastGeneration: List<Evolutionable<Any>> = ArrayList()

    override fun <G> shouldCut(newGeneration: List<Evolutionable<G>>): Boolean {
        val mix = newGeneration.intersect(lastGeneration).size

        if(mix >= amountToConsider) {
            currentCount++
        } else {
            currentCount = 0
        }

        lastGeneration = newGeneration as List<Evolutionable<Any>>
        return currentCount>=generationsSame
    }
}