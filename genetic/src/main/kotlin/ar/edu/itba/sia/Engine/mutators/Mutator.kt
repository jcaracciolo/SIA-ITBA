package ar.edu.itba.sia.Engine.mutators

import ar.edu.itba.sia.characters.Character

interface Mutator {
    fun mutate(specimen: Character, generation: Int): Character

}