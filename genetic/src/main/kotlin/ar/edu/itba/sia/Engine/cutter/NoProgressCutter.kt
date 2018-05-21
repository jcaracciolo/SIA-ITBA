package ar.edu.itba.sia.Engine.cutter

import ar.edu.itba.sia.evolutionable.characters.Evolutionable

class NoProgressCutter(private val generations: Int, private val percentage: Double): Cutter{

    private var currentCount = 0
    private var lastBest: Double = 0.0

    override fun shouldCut(newGeneration: List<Evolutionable>): Boolean {
        val best = newGeneration.maxBy { it.getPerformance() }!!.getPerformance()
        if(lastBest * (1+percentage) > best) {
            currentCount++
        }

        lastBest = if(lastBest > best) { lastBest } else { best }

        return currentCount>=generations
    }
}