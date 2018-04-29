package chainReaction.heuristics

import chainReaction.game.CRState

class CloserHeuristic: CRHeuristic {
    override fun maxValue(state: CRState): Double = state.game.board.tokens.toDouble()

    override fun steps(state: CRState): Int = state.game.board.tokens

    override fun getValue(state: CRState): Double {
        return state.touched.count(true).toDouble()
    }
}