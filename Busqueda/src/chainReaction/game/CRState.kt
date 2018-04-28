package chainReaction.game

import chainReaction.utils.BooleanMatrix
import chainReaction.utils.CharMatrix

class CRState(val game: CRGame, val touched: BooleanMatrix, val neighbours: CharMatrix, val last: Pair<Int, Int>){


    override fun hashCode(): Int {
        return touched.hashCode().xor(last.hashCode())
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CRState

        if (touched != other.touched) return false
        if (last != other.last) return false

        return true
    }

    override fun toString(): String {
        return "CRState(last=${last} touched=$touched) "
    }


}