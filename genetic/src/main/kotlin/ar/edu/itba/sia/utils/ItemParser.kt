package ar.edu.itba.sia.utils

import ar.edu.itba.sia.Armory
import ar.edu.itba.sia.equipables.Equipment
import ar.edu.itba.sia.equipables.EquipmentType
import java.io.File

class ItemParser {
    companion object {

        fun parseAll(file: String) {
            Armory
        }


        fun parseItems(file: String, delimiter: String = "\t"): Map<Double, Equipment> {
            val map = HashMap<Double, Equipment>()

            val input = File(file).bufferedReader()
            var line = input.readLine()
            line = input.readLine()

            while(line != null){
                val values = line.split(delimiter)
                val id = values[0].toDouble()
                val strength = values[1].toDouble()
                val agility = values[2].toDouble()
                val expertise = values[3].toDouble()
                val resistance = values[4].toDouble()
                val vitality = values[5].toDouble()

                map[id] = Equipment(id,strength,agility,expertise,resistance,vitality)

                line =input.readLine()
            }

            input.close()
            return map
        }
    }
}

fun Double.floor(): Int = Math.floor(this).toInt()