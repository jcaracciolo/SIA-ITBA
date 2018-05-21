package ar.edu.itba.sia.Engine.cutter

import ar.edu.itba.sia.evolutionable.characters.Evolutionable

class OptimalCutter(val optimal: Double): Cutter{

    override fun shouldCut(newGeneration: List<Evolutionable>): Boolean {
        return newGeneration.map{ it.getPerformance() }.any{ it > optimal }
    }
}