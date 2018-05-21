package ar.edu.itba.sia.evolutionable.characters

import ar.edu.itba.sia.equipables.*

class Assassin(override val gens: Array<Double>): Character(gens) {

    companion object {
        fun random(): Assassin =
                Assassin(
                    height = Math.random()* (2.0 - 1.3) + 1.3,
                    weaponId = EquipmentType.WEAPON.randId,
                    headGearId = EquipmentType.HEADGEAR.randId,
                    bodyArmorId = EquipmentType.BODYARMOR.randId,
                    glovesId = EquipmentType.GLOVES.randId,
                    bootsId = EquipmentType.BOOTS.randId
                )
    }

    constructor(height: Double, weaponId: Double, headGearId: Double, bodyArmorId: Double, glovesId: Double, bootsId: Double):
            this(gens = Array<Double>(6,{0.0})) {

        equip(EquipmentType.WEAPON, weaponId)
        equip(EquipmentType.HEADGEAR, headGearId)
        equip(EquipmentType.BODYARMOR, bodyArmorId)
        equip(EquipmentType.GLOVES, glovesId)
        equip(EquipmentType.BOOTS, bootsId)
        alterHeight(height)
    }


    override fun getPerformance(): Double {
        return 0.7 * getAttack() + 0.3 * getDefense()
    }

    override fun getDescendant(): Character {
        return Assassin(gens.copyOf())
    }
}