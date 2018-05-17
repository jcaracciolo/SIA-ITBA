package ar.edu.itba.sia.Engine.cutter

import ar.edu.itba.sia.characters.Character

class GenerationStaysSame(private val generationsSame: Int, private val amountToConsider: Int): Cutter{

    private var currentCount = 0
    private var lastGeneration: List<Character> = ArrayList()

    override fun shouldCut(newGeneration: List<Character>): Boolean {
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