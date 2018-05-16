package ar.edu.itba.sia

import ar.edu.itba.sia.equipables.Equipment
import ar.edu.itba.sia.equipables.EquipmentType
import ar.edu.itba.sia.utils.ItemParser

class Armory {

    var weapons: MutableMap<Long,Equipment>
    var headgear: MutableMap<Long,Equipment>
    var gloves: MutableMap<Long,Equipment>
    var boots: MutableMap<Long,Equipment>
    var bodyArmor: MutableMap<Long,Equipment>

    init {
        weapons = HashMap<Long,Equipment>()
        headgear = HashMap<Long,Equipment>()
        gloves = HashMap<Long,Equipment>()
        boots = HashMap<Long,Equipment>()
        bodyArmor = HashMap<Long,Equipment>()

        loadItems()
    }

    fun loadItems(){
        ItemParser.parseItem("WeaponsFileName", weapons, EquipmentType.WEAPON)
        ItemParser.parseItem("HeadGearFileName", headgear, EquipmentType.HEADGEAR)
        ItemParser.parseItem("GlovesFileName", gloves, EquipmentType.GLOVES)
        ItemParser.parseItem("BootFileName", boots, EquipmentType.BOOTS)
        ItemParser.parseItem("BodyArmorFileName", bodyArmor, EquipmentType.BODYARMOR)
    }
}