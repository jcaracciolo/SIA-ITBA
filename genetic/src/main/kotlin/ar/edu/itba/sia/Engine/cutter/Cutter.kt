package ar.edu.itba.sia.Engine.cutter

import ar.edu.itba.sia.characters.Character

interface Cutter {
    fun shouldCut(newGeneration: List<Character>): Boolean
}