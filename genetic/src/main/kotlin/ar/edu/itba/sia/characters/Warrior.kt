package ar.edu.itba.sia.characters

import ar.edu.itba.sia.equipables.*

class Warrior (override val gens: Array<Double>) : Character(gens) {

    override fun getPerformance(): Double {
        return 0.6 * getAttack() + 0.4 * getDefense()
    }

    override fun getDescendant(): Character {
        return Warrior(gens)
    }
}