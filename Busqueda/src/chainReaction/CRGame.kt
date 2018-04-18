package chainReaction

import ar.com.itba.sia.Problem
import ar.com.itba.sia.Rule
import utils.BooleanMatrix
import utils.PairCache
import java.lang.reflect.Array

data class CRGame(
        val board: CRBoard,
        val starting: Pair<Int,Int>): Problem<CRState> {


    override fun getInitialState(): CRState {
        return CRState(game = this, touched = BooleanMatrix(board.rows,board.cols), last = starting)
    }

    override fun getRules(state: CRState): List<Rule<CRState>> {
        val board = state.game.board

        val currentX = state.last.second
        val currentY = state.last.first

        val up = currentY
        val down = board.rows - currentY - 1
        val left = currentX
        val right = board.cols - currentX - 1

        val list = ArrayList<Rule<CRState>>()
        checkMovement(state, Direction.UP, up, list)
        checkMovement(state, Direction.DOWN, down, list)
        checkMovement(state, Direction.LEFT, left, list)
        checkMovement(state, Direction.RIGHT, right, list)

        return list
    }

    private fun checkMovement(state: CRState, direction: Direction, steps: Int, list: ArrayList<Rule<CRState>>) {
        val y = state.last.first
        val x = state.last.second
        for(i in 0..steps) {
            if(canMove(state, direction.move(state.last, steps), state.last)) {
                list.add(CRRule(direction, steps))
            }
        }
    }

    private fun canMove(state: CRState, position: Pair<Int, Int>, from: Pair<Int, Int>): Boolean {
        val y = position.first
        val x = position.second
        val board = state.game.board
        return when {
            x < 0 || y < 0 || y >= board.rows || x >= board.cols -> false
            state.touched[y,x] -> false
            board.shapes[y,x] != board.shapes[from.first, from.second]
                    && board.colors[y,x] != board.colors[from.first, from.second] -> false
            else -> true
        }
    }


    override fun isResolved(state: CRState): Boolean  =
            when {
                state.touched.count(true) < state.game.board.tokens -> false
                state.touched.count(true) == state.game.board.tokens -> checkFinalIsCorrect(state)
                else -> throw IllegalStateException("You cant touch more tokens than there are available in the game")
            }

    private fun checkFinalIsCorrect(state: CRState): Boolean {
        val board = state.game.board
        for(i in 0..board.rows) {
            for(j in 0..board.cols) {
                val present = board.shapes[i,j] != CRBoard.EMPTY
                if(present == state.touched[i,j]) {
                    throw IllegalStateException("Final state is not final")
                }
            }
        }

        return true
    }
}
