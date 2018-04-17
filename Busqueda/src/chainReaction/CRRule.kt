package chainReaction

import ar.com.itba.sia.Rule
import utils.BooleanMatrix

data class CRRule(val direction: Direction, val steps: Int) : Rule<CRState> {

    override var cost: Double = 1.0

    override fun applyToState(state: CRState): CRState {
        val newMap = BooleanMatrix(state.touched)
        val next = nextPosition(state)
        newMap[next.first, next.second] = true

        return CRState(state.game, newMap, next)
    }

    private fun nextPosition(state: CRState): Pair<Int, Int> {
        val next = direction.move(state.last, steps)

        when {
            next.first < 0 -> throw IllegalStateException("Trying to move left of 0")
            next.first >= state.game.rows -> throw IllegalStateException("Trying to move right outside")
            next.second < 0 -> throw IllegalStateException("Trying to move up of 0")
            next.second >= state.game.cols -> throw IllegalStateException("Trying to move down outside")
        }

        return next
    }


}