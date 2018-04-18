package chainReaction.utils

import chainReaction.game.CRBoard
import chainReaction.game.CRGame
import java.io.File

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


            var boardColors = CharMatrix(rows = row, cols = col)
            var boardShapes = CharMatrix(rows = row, cols = col)

            for (i in 0..row-1) {
                readRow(line = input.readLine(), boardColors = boardColors, boardShapes = boardShapes, i = i)
            }
            val board = CRBoard(maxColor = colors, maxShape = shapes, shapes = boardShapes, colors = boardColors)
            PairCache.initialize(rows = row, cols = col)
            return CRGame(board = board,
                    starting = PairCache[startX, startY]
            )
        }

        private fun intSplit(string: String, delimiter: String = ","): List<Int> =
                string.split(delimiter).map { n -> Integer.valueOf(n) }

        private fun readRow(line: String, boardColors: CharMatrix, boardShapes: CharMatrix, i: Int) {
            var j = 0
            line.split(",").map { cell ->
                when(cell.isEmpty()){
                    true-> null
                    false -> {
                        val inputCell = charSplit(cell, " ")
                        boardColors[i, j] = inputCell[0]
                        boardShapes[i, j] = inputCell[1]
                    }
                }
                j++
            }
        }
        private fun charSplit(string: String, delimiter: String = ","): List<Char> =
                string.split(delimiter).map { n -> n[0] }
    }
}