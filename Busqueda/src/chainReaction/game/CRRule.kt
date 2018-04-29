package chainReaction.game

import ar.com.itba.sia.Rule
import chainReaction.utils.BooleanMatrix
import chainReaction.utils.CharMatrix
import chainReaction.utils.PairCache

data class CRRule(val direction: Direction, val steps: Int) : Rule<CRState> {

    override var cost: Double = 1.0

    override fun applyToState(state: CRState): CRState {
        val newMap = BooleanMatrix(state.touched)
        val next = nextPosition(state)
        newMap[next] = true

        val newNeighbours = CharMatrix(state.neighbours)
        val board = state.game.board
        (0 until board.rows).map { PairCache[it, next.second] }
                .filter { board.areNeighbours(next, it) && newNeighbours[it].toInt() > 0 && newNeighbours[it] != CRBoard.EMPTY }
                .forEach { newNeighbours[it]-- }

        (0 until board.cols).map { PairCache[next.first, it] }
                .filter { board.areNeighbours(next, it) && newNeighbours[it].toInt() > 0 && newNeighbours[it] != CRBoard.EMPTY }
                .forEach { newNeighbours[it]-- }


        newNeighbours[state.last] = CRBoard.EMPTY

        return CRState(state.game, newMap, newNeighbours, next)
    }

    private fun nextPosition(state: CRState): Pair<Int, Int> {
        val next = direction.move(state.last, steps)
        val board = state.game.board

        when {
            next.first < 0 -> throw IllegalStateException("Trying to move left of 0")
            next.first >= board.rows -> throw IllegalStateException("Trying to move right outside")
            next.second < 0 -> throw IllegalStateException("Trying to move up of 0")
            next.second >= board.cols -> throw IllegalStateException("Trying to move down outside")
        }

        return next
    }

}