package ar.edu.itba.sia.Engine.cutter

import ar.edu.itba.sia.evolutionable.Evolutionable

class OptimalCutter(val optimal: Double): Cutter{

    override fun shouldCut(newGeneration: List<Evolutionable>): Boolean  =
            newGeneration.map{ it.getPerformance() }.any{ it > optimal }

}