package chainReaction.heuristics

import ar.com.itba.sia.Heuristic
import chainReaction.game.CRState

interface CRHeuristic: Heuristic<CRState> {
    // To implement heuristic just make sure it goes from (0-MaxValue)
    // Define steps as how many possible different values will the heuristic generate
    // And make sure it returns greater the better it is
    // (This is because to differentiate between many "same distance" states)
    fun maxValue(state: CRState): Double
    fun steps(state: CRState): Int
}