package ar.edu.itba.sia.utils

import ar.edu.itba.sia.equipables.Equipment
import ar.edu.itba.sia.equipables.EquipmentType
import java.io.File

class ItemParser {
    companion object {
        fun parseItem( file: String, map: MutableMap<Long,Equipment>, type: EquipmentType) {
            val input = File(file).bufferedReader()
            var id: Long;
            var strength: Double
            var agility: Double
            var expertise: Double
            var resistance: Double
            var vitality: Double
            var values: List<String>
            var line = input.readLine()

            line = input.readLine()

            while(line != null){
                values = line.split( "\t");

                id = values[0].toLong()
                strength = values[1].toDouble()
                agility = values[2].toDouble()
                expertise = values[3].toDouble()
                resistance = values[4].toDouble()
                vitality = values[5].toDouble()

                map[id] = type.create(id,strength,agility,expertise,resistance,vitality)

                line =input.readLine()
            }

            input.close()
        }
    }
}