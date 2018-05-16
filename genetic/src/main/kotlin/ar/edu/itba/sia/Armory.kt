package ar.edu.itba.sia

import ar.edu.itba.sia.equipables.Equipment
import ar.edu.itba.sia.equipables.EquipmentType
import ar.edu.itba.sia.utils.ItemParser

class Armory {

    companion object {

        private val weaponsFileName = "WeaponsFileName"
        private val headGearFileName = "WeaponsFileName"
        private val glovesFileName = "WeaponsFileName"
        private val bootsFileName = "WeaponsFileName"
        private val bodyArmorFileName = "WeaponsFileName"

        val weapons: Map<Double,Equipment>
        val headgear: Map<Double,Equipment>
        val gloves: Map<Double,Equipment>
        val boots: Map<Double,Equipment>
        val bodyArmor: Map<Double,Equipment>

        init {
            weapons = ItemParser.parseItems(weaponsFileName)
            headgear = ItemParser.parseItems(headGearFileName)
            gloves = ItemParser.parseItems(glovesFileName)
            boots = ItemParser.parseItems(bootsFileName)
            bodyArmor = ItemParser.parseItems(bodyArmorFileName)
        }

    }

}