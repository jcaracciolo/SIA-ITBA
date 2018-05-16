package ar.edu.itba.sia.characters

import ar.edu.itba.sia.equipables.*

class Assassin (
        override var height: Double,
        override var weapon : Weapon,
        override var headgear : Headgear,
        override var bodyArmor : BodyArmor,
        override var gloves : Gloves,
        override var boots : Boots) : Character(height, weapon, headgear, bodyArmor, gloves, boots) {

    override fun getPerformance(): Double {
        return 0.7 * getAttack() + 0.3 * getDefense()
    }

    override fun getDescendant(): Character {
        return Assassin(height, weapon, headgear, bodyArmor, gloves, boots)
    }
}