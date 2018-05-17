package ar.edu.itba.sia.Engine.replacer

import ar.edu.itba.sia.characters.Character

interface Replacer {

    fun replace(parents: List<Character>, children: List<Character>): List<Character>

}