package ar.edu.itba.sia.characters

import ar.edu.itba.sia.equipables.*

class Warrior (override val gens: DoubleArray) : Character(gens) {

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
            this(gens = DoubleArray(6,{0.0})) {

        equip(weaponId, EquipmentType.WEAPON)
        equip(headGearId, EquipmentType.HEADGEAR)
        equip(bodyArmorId, EquipmentType.BODYARMOR)
        equip(glovesId, EquipmentType.GLOVES)
        equip(bootsId, EquipmentType.BOOTS)
        alterHeight(height)
    }
    override fun getPerformance(): Double {
        return 0.6 * getAttack() + 0.4 * getDefense()
    }

    override fun getDescendant(): Character {
        return Warrior(gens.copyOf())
    }
}