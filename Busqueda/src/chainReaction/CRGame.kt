package chainReaction

import ar.com.itba.sia.Problem
import ar.com.itba.sia.Rule

data class CRGame(
        val board: List<List<CRCell?>>,
        val shapes: Int,
        val colors: Int,
        val starting: Pair<Int,Int>): Problem<CRState> {

    val cols = board.size
    val rows = board[0].size
    val tokens: Int

    init {
        var countTokens = 0
        board.forEach {
            if(rows == it.size) {
                throw IllegalArgumentException("Board is not a matrix, size does not match")
            }
            countTokens += it.filterNotNull().count()
        }
        tokens = countTokens
        if( tokens <= 0 ){
            throw IllegalArgumentException("There are no tokens in the board")
        }

    }

    override fun getInitialState(): CRState {
        return CRState(game = this, touched = HashMap(), last = starting)
    }

    override fun getRules(state: CRState): List<Rule<CRState>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isResolved(state: CRState): Boolean  =
            when {
                state.touched.size < state.game.tokens -> false
                state.touched.size == state.game.tokens -> checkFinalIsCorrect(state)
                else -> throw IllegalStateException("You cant touch more tokens than there are available in the game")
            }

    private fun checkFinalIsCorrect(state: CRState): Boolean {
        val board = state.game.board
        for(i in 0..state.game.rows) {
            for(j in 0..state.game.cols) {
                val cell = board[i][j]
                val correct =  when(cell) {
                    null -> state.touched[Pair(i,j)] == null
                    else -> state.touched[Pair(i,j)] == true
                }

                if(!correct) { throw IllegalStateException("Final state is not final") }
            }
        }

        return true
    }
}
