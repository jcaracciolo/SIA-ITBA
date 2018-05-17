package ar.edu.itba.sia.Engine.selector

import ar.edu.itba.sia.characters.Character

interface Selector {

    fun select(generation: List<Character>, amount: Int): List<Character>

}