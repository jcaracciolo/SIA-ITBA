package utils

import chainReaction.CRCell
import chainReaction.CRGame
import java.io.File
import kotlin.collections.ArrayList

class CRParser {

    companion object {
        fun parseBoard(file: String): CRGame? {
            val input = File(file).bufferedReader()

            val firstLine = intSplit(input.readLine())
            val col = firstLine[0]
            val row = firstLine[1]

            val secondLine = intSplit(input.readLine())
            val shapes = secondLine[0]
            val colors = secondLine[1]

            val thirdLine = intSplit(input.readLine())
            val startX = thirdLine[0]
            val startY = thirdLine[1]

            val board:ArrayList<List<CRCell?>> = ArrayList()

            for (i in 0..row) {
                board.add(readRow(input.readLine()))
            }

            return CRGame(board = board.map { it.toList()},
                    shapes = shapes,
                    colors = colors,
                    starting = Pair(startX, startY))
        }

        private fun intSplit(string: String, delimiter: String = ","): List<Int> =
                string.split(delimiter).map { n -> Integer.valueOf(n) }

        private fun readRow(line: String): List<CRCell?> = line.split(",").map { cell ->
                when(cell.isEmpty()){
                    true-> null
                    false -> CRCell(color = intSplit(cell, " ")[0], shape = intSplit(cell, " ")[1])
                }
            }
        }
}