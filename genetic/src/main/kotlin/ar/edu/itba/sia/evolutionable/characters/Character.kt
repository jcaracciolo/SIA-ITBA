package ar.edu.itba.sia.evolutionable.characters

import ar.edu.itba.sia.equipables.*
import ar.edu.itba.sia.evolutionable.Evolutionable
import java.util.*

abstract class Character(open protected val innerGens: Array<Double>, val characterClass: CharacterType,
                            open val type: Int): Evolutionable {

    val height
        get() = innerGens.last()
    val weapon
        get() = EquipmentType.WEAPON.getEquipment(innerGens)
    val headgear
        get() = EquipmentType.HEADGEAR.getEquipment(innerGens)
    val bodyArmor
        get() = EquipmentType.BODYARMOR.getEquipment(innerGens)
    val gloves
        get() = EquipmentType.GLOVES.getEquipment(innerGens)
    val boots
        get() = EquipmentType.BOOTS.getEquipment(innerGens)

    constructor(height: Double,
                weaponId: Double,
                headGearId: Double,
                bodyArmorId: Double,
                glovesId: Double,
                bootsId: Double,
                characterClass: CharacterType,
                type: Int):
            this(Array<Double>(6,{0.0}), characterClass, type) {

        equip(EquipmentType.WEAPON, weaponId)
        equip(EquipmentType.HEADGEAR, headGearId)
        equip(EquipmentType.BODYARMOR, bodyArmorId)
        equip(EquipmentType.GLOVES, glovesId)
        equip(EquipmentType.BOOTS, bootsId)
        alterHeight(height)
    }

    override val gens: Array<Any>
        get() = innerGens as Array<Any>

    override abstract fun getDescendant(): Character

    override abstract fun getPerformance(): Double

    override fun random(): Evolutionable = characterClass.getRandom(type)

    override fun mutateGen(n: Int) {
        if(n == innerGens.size -1) {
            mutateHeight()
        } else {
            mutateEquipment(n)
        }
    }

    fun getDefense(): Double{
        return (getEffectiveResistance() + getEffectiveExpertise()) * getEffectiveVitality() * getDEM()
    }

    fun getAttack(): Double{
        return (getEffectiveAgility() + getEffectiveExpertise()) * getEffectiveStrength() * getATM()
    }

    fun equip(type: EquipmentType, equipmentId: Double) {
        equip(type.index, equipmentId)
    }

    fun equip(index: Int, equipmentId: Double) {
        innerGens[index] = equipmentId
    }

    fun mutateEquipment(type: EquipmentType) {
        equip(type,type.randId)
    }

    fun mutateEquipment(index: Int) {
        mutateEquipment(EquipmentType.fromIndex(index))
    }

    fun alterHeight(newHeight: Double) {
        innerGens[innerGens.size - 1] = newHeight
    }

    fun mutateHeight() {
        innerGens[innerGens.size - 1] = Math.random() * (2.0 - 1.3) + 1.3
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

    abstract val strengthModifier: Double
    private fun getTotalStrength(): Double {
        return strengthModifier * (weapon.strength + headgear.strength + bodyArmor.strength + gloves.strength + boots.strength)
    }

    abstract val agilityModifier: Double
    private fun getTotalAgility(): Double {
        return agilityModifier * (weapon.agility + headgear.agility + bodyArmor.agility + gloves.agility + boots.agility)
    }

    abstract val expertiseModifier: Double
    private fun getTotalExpertise(): Double {
        return expertiseModifier* (weapon.expertise + headgear.expertise + bodyArmor.expertise + gloves.expertise + boots.expertise)
    }

    abstract val resistanceModifier: Double
    private fun getTotalResistance(): Double {
        return resistanceModifier*(weapon.resistance + headgear.resistance + bodyArmor.resistance + gloves.resistance + boots.resistance)
    }

    abstract val vitalityModifier: Double
    private fun getTotalVitality(): Double {
        return vitalityModifier*(weapon.vitality + headgear.vitality + bodyArmor.vitality + gloves.vitality + boots.vitality)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Character

        if (!Arrays.equals(innerGens, other.innerGens)) return false

        return true
    }

    override fun hashCode(): Int {
        return Arrays.hashCode(innerGens)
    }

    override fun isValid(): Boolean = true

}