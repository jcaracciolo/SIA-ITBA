package ar.edu.itba.sia.characters

import ar.edu.itba.sia.Armory
import ar.edu.itba.sia.equipables.*
import ar.edu.itba.sia.exceptions.NotValidEquipmentException

abstract class Character(open val gens: Array<Double>) {
    val height
        get() = gens.last()
    val weapon
        get() = EquipmentType.WEAPON.getEquipment(gens)
    val headgear
        get() = EquipmentType.HEADGEAR.getEquipment(gens)
    val bodyArmor
        get() = EquipmentType.BODYARMOR.getEquipment(gens)
    val gloves
        get() = EquipmentType.GLOVES.getEquipment(gens)
    val boots
        get() = EquipmentType.BOOTS.getEquipment(gens)

    abstract fun getDescendant(): Character

    abstract fun getPerformance(): Double

    fun getAttack(): Double{
        return (getEffectiveResistance() + getEffectiveExpertise()) * getEffectiveVitality() * getDEM()
    }

    fun getDefense(): Double{
        return (getEffectiveAgility() + getEffectiveExpertise()) * getEffectiveStrength() * getATM()
    }

    fun equip(equipment: Equipment){
        throw NotValidEquipmentException()
    }

    fun equip(equipmentId: Double, type: EquipmentType) {
        type.replace(gens, equipmentId)
    }

    private fun getATM(): Double{
        return 0.5 - Math.pow(3 * height - 5, 4.0) + Math.pow(3 * height - 5, 2.0) + height/2
    }
    private fun getDEM(): Double{
        return 2 + Math.pow(3 * height - 5, 4.0) - Math.pow(3 * height - 5, 2.0) - height/2
    }

    private fun getEffectiveStrength(): Double {
        return  100 * Math.tanh(0.01 * getTotalStrength())
    }
    private fun getEffectiveAgility(): Double {
        return  Math.tanh(0.01 * getTotalAgility())
    }
    private fun getEffectiveExpertise(): Double {
        return  0.6 * Math.tanh(0.01 * getTotalExpertise())
    }
    private fun getEffectiveResistance(): Double {
        return  Math.tanh(0.01 * getTotalResistance())
    }
    private fun getEffectiveVitality(): Double {
        return  100 * Math.tanh(0.01 * getTotalVitality())
    }

    private fun getTotalStrength(): Double {
        return weapon.strength + headgear.strength + bodyArmor.strength + gloves.strength + boots.strength
    }
    private fun getTotalAgility(): Double {
        return weapon.agility + headgear.agility + bodyArmor.agility + gloves.agility + boots.agility
    }
    private fun getTotalExpertise(): Double {
        return weapon.expertise + headgear.expertise + bodyArmor.expertise + gloves.expertise + boots.expertise
    }
    private fun getTotalResistance(): Double {
        return weapon.resistance + headgear.resistance + bodyArmor.resistance + gloves.resistance + boots.resistance
    }
    private fun getTotalVitality(): Double {
        return weapon.vitality + headgear.vitality + bodyArmor.vitality + gloves.vitality + boots.vitality
    }
}