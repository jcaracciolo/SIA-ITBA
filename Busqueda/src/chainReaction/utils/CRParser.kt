package chainReaction.utils

import chainReaction.game.CRBoard
import chainReaction.game.CRGame
import java.io.File

class CRParser {

    companion object {
        fun parseBoard(file: String): CRGame? {
            val input = File(file).bufferedReader()

            val firstLine = intSplit(input.readLine())
            val row = firstLine[0]
            val col = firstLine[1]

            val secondLine = intSplit(input.readLine())
            val shapes = secondLine[0]
            val colors = secondLine[1]

            val thirdLine = intSplit(input.readLine())
            val startRow = thirdLine[0]
            val startCol = thirdLine[1]

            if(startRow<0 || startRow>=row || startCol<0 || startCol>=col) {
                throw IllegalStateException("Starting Point outside board")
            }


            val boardColors = CharMatrix(rows = row, cols = col)
            val boardShapes = CharMatrix(rows = row, cols = col)

            for (i in 0 until row) {
                readRow(line = input.readLine(), boardColors = boardColors, boardShapes = boardShapes, i = i)
            }
            val board = CRBoard(maxColor = colors, maxShape = shapes, shapes = boardShapes, colors = boardColors)
            PairCache.initialize(rows = row, cols = col)
            return CRGame(board = board,
                    starting = PairCache[startRow, startCol]
            )
        }

        private fun intSplit(string: String, delimiter: String = ","): List<Int> =
                string.split(delimiter).map { n -> Integer.valueOf(n) }

        private fun readRow(line: String, boardColors: CharMatrix, boardShapes: CharMatrix, i: Int) {
            var j = 0
            line.split(",").map { cell ->
                when(cell.isEmpty()){
                    true-> {
                        boardColors[i, j] = CRBoard.EMPTY
                        boardShapes[i, j] = CRBoard.EMPTY
                    } false -> {
                        val inputCell = intSplit(cell, " ")
                        boardColors[i, j] = inputCell[1].toChar()
                        boardShapes[i, j] = inputCell[0].toChar()
                    }
                }
                j++
            }
        }
        private fun charSplit(string: String, delimiter: String = ","): List<Char> =
                string.split(delimiter).map { n -> n[0] }
    }
}