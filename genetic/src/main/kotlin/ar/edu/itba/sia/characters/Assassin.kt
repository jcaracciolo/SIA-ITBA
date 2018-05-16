package ar.edu.itba.sia.characters

import ar.edu.itba.sia.equipables.*

class Assassin(override val gens: Array<Double>): Character(gens) {

    override fun getPerformance(): Double {
        return 0.7 * getAttack() + 0.3 * getDefense()
    }

    override fun getDescendant(): Character {
        return Assassin(gens)
    }
}