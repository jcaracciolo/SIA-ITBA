package ar.edu.itba.sia

import ar.edu.itba.sia.equipables.*
import ar.edu.itba.sia.exceptions.NotValidEquipmentException

abstract class Character(
        var height: Double,
        var weapon : Weapon,
        var headgear : Headgear,
        var bodyArmor : BodyArmor,
        var gloves : Gloves,
        var boots : Boots
        ) {

    abstract fun getDescendant()

    fun equip(equipment: Equipment){
        throw NotValidEquipmentException()
    }

    fun equip(weapon: Weapon){
        this.weapon = weapon
    }

    fun equip(headgear: Headgear){
        this.headgear = headgear
    }

    fun equip(bodyArmor: BodyArmor){
        this.bodyArmor = bodyArmor
    }

    fun equip(gloves: Gloves){
        this.gloves = gloves
    }

    fun equip(boots: Boots) {
        this.boots = boots
    }
}