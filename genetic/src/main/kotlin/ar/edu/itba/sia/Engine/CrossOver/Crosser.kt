package ar.edu.itba.sia.Engine.CrossOver

import ar.edu.itba.sia.characters.Character
interface Crosser {
    fun crossOver(father: Character, mother: Character): List<Character>?
}