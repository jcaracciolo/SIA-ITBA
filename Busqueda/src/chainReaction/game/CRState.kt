package chainReaction.game

import chainReaction.utils.BooleanMatrix

data class CRState(val game: CRGame, val touched: BooleanMatrix, val last: Pair<Int, Int>){

    override fun hashCode(): Int {
        return touched.hashCode()*10+ last.hashCode()
    }
}