package ar.edu.itba.sia.Engine.cutter

import ar.edu.itba.sia.evolutionable.characters.Evolutionable

class GenerationStaysSame(private val generationsSame: Int, private val amountToConsider: Int): Cutter{

    private var currentCount = 0
    private var lastGeneration: List<Evolutionable> = ArrayList()

    override fun shouldCut(newGeneration: List<Evolutionable>): Boolean {
        val mix = newGeneration.intersect(lastGeneration).size

        if(mix >= amountToConsider) {
            currentCount++
        } else {
            currentCount = 0
        }

        lastGeneration = newGeneration
        return currentCount>=generationsSame
    }
}