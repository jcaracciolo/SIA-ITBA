package engine

import ar.com.itba.sia.Problem
import chainReaction.game.CRState
import engine.searchers.Searcher
import java.util.*
import kotlin.collections.HashSet

class Engine<E>{

    var externalCurNode: Node<E>? = null

    fun solve (problem: Problem<E>, searcher: Searcher<E>, processing: StateProcessor<E>? = null): List<Node<E>>? {

        var idCounter = 1
        var nodesTaken = 0
        var solved = false

        val visitedNodes: HashSet<Node<E>> = HashSet()
        var curNode = Node(idCounter++, problem, null, problem.getInitialState(), 0.0, 0)

        searcher.addNode(curNode)

        while(!solved  &&  !searcher.isEmpty()){
            curNode = searcher.nextNode()
            externalCurNode = curNode
            nodesTaken++

            processing?.let { it.proces(curNode.state) }

            solved = curNode.problem.isResolved(curNode.state)

            if(!solved) {
                val nextNodes = curNode.possibleRules.map {
                    Node(idCounter++, problem, curNode, it.applyToState(curNode.state),
                            curNode.cost + it.cost, curNode.level + 1)
                }.filter{ !visitedNodes.contains(it) }
                searcher.addNodes(nextNodes, curNode)
                visitedNodes.add(curNode)
            }

        }

        if(solved){
            println("It took ${nodesTaken} nodes")
            println(curNode)
           return getSolution(curNode)
        }

        return null
    }

    fun getSolution(node: Node<E>): List<Node<E>>{
        var curNode = node
        val solutions: Stack<Node<E>> = Stack()
        while(curNode.previous != null){
            solutions.add(curNode)
            curNode = curNode.previous!!
        }
        solutions.add(curNode)
        return solutions
    }

}