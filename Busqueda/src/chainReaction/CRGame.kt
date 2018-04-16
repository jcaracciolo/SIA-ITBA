package chainReaction

data class CRGame(
        val board: List<List<CRCell?>>,
        val shapes: Int,
        val colors: Int,
        val starting: Pair<Int,Int>) {

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

    fun isFinal(state: CRState): Boolean =
            when {
                state.touched.size < state.game.tokens -> false
                state.touched.size == state.game.tokens -> checkFinalIsCorrect(state)
                else -> throw IllegalStateException("You cant touch more tokens than there are available in the game")
            }

    private fun checkFinalIsCorrect(state: CRState): Boolean {
        //TODO check if is not valid, explode
        return true
    }
}
