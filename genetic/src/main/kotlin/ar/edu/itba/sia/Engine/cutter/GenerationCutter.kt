package ar.edu.itba.sia.Engine.cutter

import ar.edu.itba.sia.characters.Character

class GenerationCutter(val max: Int): Cutter{

    private var currentCount = 0

    override fun shouldCut(newGeneration: List<Character>): Boolean {
        currentCount++
        return currentCount>=max
    }
}