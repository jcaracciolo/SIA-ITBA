package ar.edu.itba.sia.evolutionable.characters

import ar.edu.itba.sia.equipables.*
import java.util.*

abstract class Character(override val gens: Array<Double>): Evolutionable<Double> {

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

    constructor(height: Double, weaponId: Double, headGearId: Double, bodyArmorId: Double, glovesId: Double, bootsId: Double):
            this(gens = Array<Double>(6,{0.0})) {

        equip(EquipmentType.WEAPON, weaponId)
        equip(EquipmentType.HEADGEAR, headGearId)
        equip(EquipmentType.BODYARMOR, bodyArmorId)
        equip(EquipmentType.GLOVES, glovesId)
        equip(EquipmentType.BOOTS, bootsId)
        alterHeight(height)
    }

    override abstract fun getDescendant(): Character

    override abstract fun getPerformance(): Double

    override fun mutateGen(n: Int) {
        if(n == gens.size -1) {
            mutateHeight()
        } else {
            mutateEquipment(n)
        }
    }

    fun getAttack(): Double{
        return (getEffectiveResistance() + getEffectiveExpertise()) * getEffectiveVitality() * getDEM()
    }

    fun getDefense(): Double{
        return (getEffectiveAgility() + getEffectiveExpertise()) * getEffectiveStrength() * getATM()
    }

    fun equip(type: EquipmentType, equipmentId: Double) {
        equip(type.index, equipmentId)
    }

    fun equip(index: Int, equipmentId: Double) {
        gens[index] = equipmentId
    }

    fun mutateEquipment(type: EquipmentType) {
        equip(type,type.randId)
    }

    fun mutateEquipment(index: Int) {
        mutateEquipment(EquipmentType.fromIndex(index))
    }

    fun alterHeight(newHeight: Double) {
        gens[gens.size - 1] = newHeight
    }

    fun mutateHeight() {
        gens[gens.size - 1] = Math.random() * (2.0 - 1.3) + 1.3
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Character

        if (!Arrays.equals(gens, other.gens)) return false

        return true
    }

    override fun hashCode(): Int {
        return Arrays.hashCode(gens)
    }


}