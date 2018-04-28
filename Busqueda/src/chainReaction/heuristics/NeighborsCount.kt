package chainReaction.heuristics;

import ar.com.itba.sia.Heuristic
import chainReaction.game.CRState
import chainReaction.utils.PairCache

class NeighborsCount() : Heuristic<CRState> {

    override fun getValue(state: CRState): Double {
        val neighbors = state.neighbours.cols * state.neighbours.rows
        if (neighbors == 0) return Double.MAX_VALUE
        return 1.0/neighbors
    }
}
