package chainReaction.game

import chainReaction.utils.BooleanMatrix
import chainReaction.utils.CharMatrix

data class CRState(val game: CRGame, val touched: BooleanMatrix, val neighbours: CharMatrix, val last: Pair<Int, Int>){

    override fun hashCode(): Int {
        return touched.hashCode().xor(last.hashCode())
    }
}