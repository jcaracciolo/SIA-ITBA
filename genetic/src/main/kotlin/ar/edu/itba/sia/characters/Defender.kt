package ar.edu.itba.sia.characters

import ar.edu.itba.sia.equipables.*

class Defender (override val gens: Array<Double>): Character(gens) {

    override fun getPerformance(): Double {
        return 0.1 * getAttack() + 0.9 * getDefense()
    }

    override fun getDescendant(): Character {
        return Defender(gens)
    }
}