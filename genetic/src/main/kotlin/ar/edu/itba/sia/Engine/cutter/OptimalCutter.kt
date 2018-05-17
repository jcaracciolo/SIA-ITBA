package ar.edu.itba.sia.Engine.cutter

import ar.edu.itba.sia.characters.Character

class OptimalCutter(val optimal: Double): Cutter{

    override fun shouldCut(newGeneration: List<Character>): Boolean {
        return newGeneration.map{ it.getPerformance() }.any{ it > optimal }
    }
}