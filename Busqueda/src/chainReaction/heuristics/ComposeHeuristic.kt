package chainReaction.heuristics

import ar.com.itba.sia.Heuristic
import chainReaction.game.CRState

class ComposeHeuristic {
    companion object {
        fun composite(vararg heuristics: CRHeuristic): Heuristic<CRState> {
            return object: Heuristic<CRState> {
                override fun getValue(state: CRState): Double {
                    val total = state.game.board.tokens.toDouble()
                    val x = state.touched.count(true)
                    var lastSteps = state.game.board.tokens
                    var lastMax = state.game.board.tokens.toDouble()

                    var answer = total - x

                    for (h in heuristics) {
                        val thisMax = h.maxValue(state)
                        val windowSize = lastMax/lastSteps
                        val modifier = windowSize/thisMax
                        answer -= h.getValue(state)*modifier

                        lastMax = thisMax*modifier
                        lastSteps = h.steps(state)
                    }

                    return answer
                }
            }

        }
    }

}