package ar.edu.itba.sia.Engine.cutter

import ar.edu.itba.sia.evolutionable.Evolutionable

interface Cutter {
    fun shouldCut(newGeneration: List<Evolutionable>): Boolean
}