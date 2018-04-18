package chainReaction

import ar.com.itba.sia.Problem
import ar.com.itba.sia.Rule

data class Node<E> (val previous: Node<E>?, val problem: Problem<E>, val state: E, val cost: Double){

    var possibleRules : List<Rule<E>> = problem.getRules(state)


}