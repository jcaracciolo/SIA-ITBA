package ar.edu.itba.sia.Engine.cutter

import ar.edu.itba.sia.evolutionable.characters.Evolutionable

class GenerationCutter(val max: Int): Cutter{

    private var currentCount = 0

    override fun <G> shouldCut(newGeneration: List<Evolutionable<G>>): Boolean {
        currentCount++
        return currentCount>=max
    }

}