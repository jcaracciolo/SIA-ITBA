package ar.edu.itba.sia.Engine.cutter

import ar.edu.itba.sia.evolutionable.characters.Evolutionable

interface Cutter {
    fun <G> shouldCut(newGeneration: List<Evolutionable<G>>): Boolean
}