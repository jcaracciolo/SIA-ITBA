package ar.edu.itba.sia.characters

import ar.edu.itba.sia.equipables.*

class Archer(override val gens: Array<Double>): Character(gens) {

    override fun getPerformance(): Double {
        return 0.9 * getAttack() + 0.1 * getDefense()
    }

    override fun getDescendant(): Character {
        return Archer(gens)
    }
}