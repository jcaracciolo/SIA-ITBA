package ar.edu.itba.sia.characters

import ar.edu.itba.sia.equipables.*

class Defender (
        override var height: Double,
        override var weapon : Weapon,
        override var headgear : Headgear,
        override var bodyArmor : BodyArmor,
        override var gloves : Gloves,
        override var boots : Boots) : Character(height, weapon, headgear, bodyArmor, gloves, boots) {

    override fun getPerformance(): Double {
        return 0.1 * getAttack() + 0.9 * getDefense()
    }

    override fun getDescendant(): Character {
        return Defender(height, weapon, headgear, bodyArmor, gloves, boots)
    }
}