package ar.edu.itba.sia

import ar.edu.itba.sia.equipables.Equipment
import ar.edu.itba.sia.equipables.EquipmentType
import ar.edu.itba.sia.utils.ItemParser

class Armory {

    companion object {

        private val weaponsFileName = "/armas.tsv"
        private val headGearFileName = "/botas.tsv"
        private val glovesFileName = "/cascos.tsv"
        private val bootsFileName = "/guantes.tsv"
        private val bodyArmorFileName = "/pecheras.tsv"

        lateinit var weapons: Map<Double,Equipment>
        lateinit var headgear: Map<Double,Equipment>
        lateinit var gloves: Map<Double,Equipment>
        lateinit var boots: Map<Double,Equipment>
        lateinit var bodyArmor: Map<Double,Equipment>

        fun initialze(baseFile: String) {
            weapons = ItemParser.parseItems(baseFile + weaponsFileName)
            headgear = ItemParser.parseItems(baseFile + headGearFileName)
            gloves = ItemParser.parseItems(baseFile + glovesFileName)
            boots = ItemParser.parseItems(baseFile + bootsFileName)
            bodyArmor = ItemParser.parseItems(baseFile + bodyArmorFileName)

            EquipmentType.recalculateMax()
        }

    }

}