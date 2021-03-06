package chainReaction.game

import ar.com.itba.sia.Problem
import ar.com.itba.sia.Rule
import chainReaction.utils.BooleanMatrix
import chainReaction.utils.CharMatrix
import chainReaction.utils.PairCache

data class CRGame(
        val board: CRBoard,
        val starting: Pair<Int,Int>): Problem<CRState> {

    init {
        if(board.colors[starting]==CRBoard.EMPTY || board.shapes[starting]==CRBoard.EMPTY) {
            throw IllegalStateException("Starting at an empty place")
        }
    }

    override fun getInitialState(): CRState {
        val initial = CRState(game = this,
                touched = BooleanMatrix(board.rows, board.cols),
                last = starting,
                neighbours = calculateNeighbours()
        )
        initial.touched[starting.first,starting.second] = true
        return initial
    }

    private fun calculateNeighbours(): CharMatrix {
        val neighbours = CharMatrix(board.rows, board.cols)
        (0 until board.rows).forEach { i ->
            (0 until board.cols).forEach { j ->
                var thisNeighbours = 0
                if(board.shapes[i,j] != CRBoard.EMPTY) {
                    thisNeighbours += (0 until board.rows).filter {
                        i != it && PairCache[it, j] != starting && board.areNeighbours(PairCache[i, j], PairCache[it, j])
                    }.count()

                    thisNeighbours += (0 until board.cols).filter {
                        j != it && PairCache[i, it] != starting && board.areNeighbours(PairCache[i, j], PairCache[i, it])
                    }.count()
                    neighbours[i, j] = thisNeighbours.toChar()
                } else {
                    neighbours[i,j] = CRBoard.EMPTY
                }
            }
        }
        
        return neighbours
    }

    override fun getRules(state: CRState): List<Rule<CRState>> {
        val board = state.game.board

        val currentRow = state.last.first
        val currentCol = state.last.second

        val up = currentRow
        val down = board.rows - currentRow - 1
        val left = currentCol
        val right = board.cols - currentCol - 1

        val list = ArrayList<Rule<CRState>>()
        checkMovement(state, Direction.UP, up, list)
        checkMovement(state, Direction.DOWN, down, list)
        checkMovement(state, Direction.LEFT, left, list)
        checkMovement(state, Direction.RIGHT, right, list)

        return list
    }

    private fun checkMovement(state: CRState, direction: Direction, steps: Int, list: ArrayList<Rule<CRState>>) {
        for(i in 1 .. steps) {
            if(canMove(state, direction.move(state.last, i), state.last)) {
                list.add(CRRule(direction, i))
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
        for(i in 0 until board.rows) {
            for(j in 0 until board.cols) {
                val present = board.shapes[i,j] != CRBoard.EMPTY
                if(present != state.touched[i,j]) {
                    throw IllegalStateException("Final state is not final")
                }
            }
        }

        return true
    }
}
