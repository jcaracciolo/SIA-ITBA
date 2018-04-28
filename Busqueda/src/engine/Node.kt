package engine

import ar.com.itba.sia.Problem
import ar.com.itba.sia.Rule

data class Node<E> (val id: Int, val problem: Problem<E>, val previous: Node<E>?, val state: E, val cost: Double,
                    val level: Int){

    override fun hashCode(): Int {
        if (state==null){
            return 0
        }
        return state.hashCode()
    }

    var possibleRules : List<Rule<E>> = problem.getRules(state)
}