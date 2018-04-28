package chainReaction.heuristics

import ar.com.itba.sia.Heuristic
import chainReaction.game.CRBoard
import chainReaction.game.CRState
import chainReaction.utils.PairCache

class NeighborFilter(val heuristic: Heuristic<CRState>): Heuristic<CRState> {

    override fun getValue(state: CRState): Double {
        if(invalid(state)) {
            return Double.MAX_VALUE
        }

        return heuristic.getValue(state)
    }

    private fun invalid(state: CRState): Boolean {
        val board = state.game.board

        var zeros = 0
        for (i: Int in 0 until board.rows) {
            for(j: Int in 0 until board.cols) {
                if(state.neighbours[i,j].toInt() == 0) {
                    if(board.areNeighbours(PairCache[i,j],state.last)) {
                        zeros++
                        if(zeros>1) {
                            return true
                        }
                    } else {
                        return true
                    }
                }
            }
        }

        return false
    }

}