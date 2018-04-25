package engine

import ar.com.itba.sia.Heuristic
import ar.com.itba.sia.Problem
import engine.searchers.BreadthFirstSearcher
import engine.searchers.AStar
import engine.searchers.Searcher
import java.util.*
import kotlin.collections.HashSet

class Engine<E>{

    fun solve (problem: Problem<E>, heuristic: Heuristic<E>): List<Node<E>>? {

        var idCounter: Int = 1;
        var solved: Boolean = false;
        var visitedNodes: HashSet<Node<E>> = HashSet()

        var curNode: Node<E> = Node(idCounter++, problem, null, problem.getInitialState(), 0.0, 0)
        var newNode: Node<E>

        val searcher: Searcher<E> = AStar(heuristic)
        searcher.addNode(curNode)

        //TODO( Consultar si debemos quedarnos con la primer solucion )
        //TODO 2 chequear si el control x hash alcanza
        while(!solved  &&  !searcher.isEmpty()){

            curNode = searcher.nextNode()

            if(!visitedNodes.contains(curNode)){

                visitedNodes.add(curNode)
                val nextNodes = curNode.possibleRules.map {
                    Node(idCounter++, problem, curNode, it.applyToState(curNode.state),
                            curNode.cost + it.cost, curNode.level + 1)
                }
                searcher.addNodes(nextNodes)

                solved = curNode.problem.isResolved(curNode.state)
            }

        }

        if(solved){
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