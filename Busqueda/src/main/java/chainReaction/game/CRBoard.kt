package chainReaction.game

import chainReaction.utils.CharMatrix

class CRBoard(
        val maxShape: Int,
        val maxColor: Int,
        val shapes: CharMatrix,
        val colors: CharMatrix) {

    companion object {
        val EMPTY = Character.MAX_VALUE
    }

    val rows: Int
    val cols: Int
    val size: Int
    val tokens: Int

    init {
        if(colors.rows != shapes.rows || colors.cols != shapes.rows) throw IllegalStateException("Matrix sizes do not match")
        if(maxShape > EMPTY.toInt() || maxColor > EMPTY.toInt()) throw IllegalStateException("Shapes and Colors must be bound to ${EMPTY.toInt()} ")
        rows = colors.rows
        cols = colors.cols
        size = rows*cols

        var countTokens = 0
        for(i in 0..rows) {
            for (j in 0..rows) {
                when {
                    shapes[i, j] == EMPTY && colors[i, j] != EMPTY -> throw IllegalStateException("Color and shape matrix does not match")
                    shapes[i, j] != EMPTY && colors[i, j] == EMPTY -> throw IllegalStateException("Color and shape matrix does not match")
                    shapes[i,j].toInt() > maxShape ||
                            colors[i,j].toInt() > maxColor -> throw IllegalStateException("There is a color greater than ${maxColor} or a shape greater than ${maxShape}")
                    shapes[i, j] != EMPTY && colors[i, j] != EMPTY -> countTokens++
                }
            }
        }

        tokens = countTokens

        if( tokens <= 0 ){
            throw IllegalArgumentException("There are no tokens in the board")
        }
    }
}