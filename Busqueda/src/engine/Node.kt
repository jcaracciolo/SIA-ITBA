package engine

import ar.com.itba.sia.Problem
import ar.com.itba.sia.Rule

class Node<E> (val id: Int, val problem: Problem<E>, val previous: Node<E>?, val state: E, val cost: Double,
                    val level: Int){



    override fun hashCode(): Int {
        if (state==null){
            return 0
        }
        return state.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Node<*>

        if (state != other.state) return false

        return true
    }

    var possibleRules : List<Rule<E>> = problem.getRules(state)
}