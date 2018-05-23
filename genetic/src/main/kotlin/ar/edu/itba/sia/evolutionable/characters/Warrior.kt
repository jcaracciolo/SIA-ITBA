package ar.edu.itba.sia.evolutionable.characters

import ar.edu.itba.sia.equipables.*

class Warrior (override val innerGens: Array<Double>) : Character(innerGens, CharacterType.WARRIOR) {

    companion object {
        fun random(): Warrior =
                Warrior(
                        height = Math.random()* (2.0 - 1.3) + 1.3,
                        weaponId = EquipmentType.WEAPON.randId,
                        headGearId = EquipmentType.HEADGEAR.randId,
                        bodyArmorId = EquipmentType.BODYARMOR.randId,
                        glovesId = EquipmentType.GLOVES.randId,
                        bootsId = EquipmentType.BOOTS.randId
                )
    }

    constructor(height: Double, weaponId: Double, headGearId: Double, bodyArmorId: Double, glovesId: Double, bootsId: Double):
            this(Array<Double>(6,{0.0})) {

        equip(EquipmentType.WEAPON, weaponId)
        equip(EquipmentType.HEADGEAR, headGearId)
        equip(EquipmentType.BODYARMOR, bodyArmorId)
        equip(EquipmentType.GLOVES, glovesId)
        equip(EquipmentType.BOOTS, bootsId)
        alterHeight(height)
    }
    override fun getPerformance(): Double {
        return 0.6 * getAttack() + 0.4 * getDefense()
    }

    override fun getDescendant(): Character {
        return Warrior(innerGens.copyOf())
    }
}