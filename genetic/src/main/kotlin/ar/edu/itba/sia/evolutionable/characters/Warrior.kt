package ar.edu.itba.sia.evolutionable.characters

import ar.edu.itba.sia.equipables.*

class Warrior (override val innerGens: Array<Double>, override val type: Int) : Character(innerGens, CharacterType.WARRIOR, type) {

    companion object {
        fun random(type: Int): Warrior =
                Warrior(
                        height = Math.random()* (2.0 - 1.3) + 1.3,
                        weaponId = EquipmentType.WEAPON.randId,
                        headGearId = EquipmentType.HEADGEAR.randId,
                        bodyArmorId = EquipmentType.BODYARMOR.randId,
                        glovesId = EquipmentType.GLOVES.randId,
                        bootsId = EquipmentType.BOOTS.randId,
                        type = type
                )
    }

    constructor(height: Double, weaponId: Double, headGearId: Double, bodyArmorId: Double, glovesId: Double, bootsId: Double, type: Int):
            this(Array<Double>(6,{0.0}), type) {

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
        return Warrior(innerGens.copyOf(), type)
    }

    override val strengthModifier: Double
        get() = when(type) {
            1 -> 1.1
            2 -> 1.2
            3 -> 0.8
            else -> throw IllegalStateException("Type $type has no strengthModifier")
        }

    override val agilityModifier: Double
        get() = when(type) {
            1 -> 0.9
            2 -> 1.0
            3 -> 0.9
            else -> throw IllegalStateException("Type $type has no agilityModifier")
        }

    override val expertiseModifier: Double
        get() = when(type) {
            1 -> 0.8
            2 -> 0.8
            3 -> 0.9
            else -> throw IllegalStateException("Type $type has no expertiseModifier")
        }

    override val resistanceModifier: Double
        get() = when(type) {
            1 -> 1.0
            2 -> 0.8
            3 -> 1.2
            else -> throw IllegalStateException("Type $type has no resistanceModifier")
        }

    override val vitalityModifier: Double
        get() = when(type) {
            1 -> 0.9
            2 -> 0.8
            3 -> 1.1
            else -> throw IllegalStateException("Type $type has no vitalityModifier")
        }
}