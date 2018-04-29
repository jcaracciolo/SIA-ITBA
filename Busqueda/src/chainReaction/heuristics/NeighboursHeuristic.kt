package chainReaction.heuristics;

import ar.com.itba.sia.Heuristic
import chainReaction.game.CRState
import chainReaction.utils.PairCache

class NeighboursHeuristic(): CRHeuristic {

    override fun maxValue(state: CRState): Double = (state.game.board.rows + state.game.board.cols - 2).toDouble()

    override fun steps(state: CRState): Int = (state.game.board.rows + state.game.board.cols - 2)

    override fun getValue(state: CRState): Double = state.neighbours[state.last].toDouble()
}
