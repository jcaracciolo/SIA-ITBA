package chainReaction.heuristics

import ar.com.itba.sia.Heuristic
import chainReaction.game.CRState

@Deprecated(message = "Use ComposeHeuristic with Closer first the neighbours")
class NormalHeuristic: Heuristic<CRState> {


    override fun getValue(state: CRState): Double {
        val total = state.game.board.tokens.toDouble()
        val x = state.touched.count(true)
        val y = total-x
        val reduction = (-y+total)/total
        val newMax = 1/total
        val maxSR = state.game.board.rows-1+state.game.board.cols-1
        val secondReduction = (state.neighbours[state.last].toDouble()*newMax)/maxSR
        return y - reduction - secondReduction
    }
}