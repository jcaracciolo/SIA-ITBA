package ar.edu.itba.sia.Engine.cutter

import ar.edu.itba.sia.evolutionable.Evolutionable

class OptimalCutter(val optimal: Double): Cutter{

    override fun <G> shouldCut(newGeneration: List<Evolutionable<G>>): Boolean  =
            newGeneration.map{ it.getPerformance() }.any{ it > optimal }

}