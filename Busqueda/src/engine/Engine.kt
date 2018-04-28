package engine

import ar.com.itba.sia.Heuristic
import ar.com.itba.sia.Problem
import chainReaction.game.CRState
import chainReaction.heuristics.NeighborFilter
import engine.searchers.BreadthFirstSearcher
import engine.searchers.AStar
import engine.searchers.DepthFirstSearcher
import engine.searchers.Searcher
import java.util.*
import kotlin.collections.HashSet

class Engine<E>{

    fun solve (problem: Problem<E>, heuristic: Heuristic<E>): List<Node<E>>? {

        var idCounter = 1
        var nodesTaken = 0
        var solved = false

        val visitedNodes: HashSet<Node<E>> = HashSet()

        var curNode: Node<E> = Node(idCounter++, problem, null, problem.getInitialState(), 0.0, 0)

        val searcher: Searcher<E> =
                AStar(NeighborFilter(object: Heuristic<CRState>{
            override fun getValue(state: CRState): Double = (-state.touched.count(true)).toDouble()
        })) as Searcher<E>

        searcher.addNode(curNode)

        while(!solved  &&  !searcher.isEmpty()){

            curNode = searcher.nextNode()
            nodesTaken++
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