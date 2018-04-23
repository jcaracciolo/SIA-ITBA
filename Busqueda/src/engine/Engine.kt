package engine

import ar.com.itba.sia.Heuristic
import ar.com.itba.sia.Problem
import engine.searchers.BreadthFirstSearcher
import engine.searchers.Searcher
import java.util.*

class Engine<E> (val problem: Problem<E>, val heuristic: Heuristic<E>) {

    var idCounter: Int = 1;
    var solved: Boolean = false;
    var nodeMap: Set<Node<E>> = HashSet()

    fun solve (problem: Problem<E>): List<Node<E>>? {

        var curNode: Node<E> = Node(idCounter++, problem, null, problem.getInitialState(), 0.0, 0)
        var newNode: Node<E>

        var searcher: Searcher<E> = BreadthFirstSearcher()
        searcher.addNode(curNode)

        //TODO( Consultar si debemos quedarnos con la primer solucion )
        //TODO 2 chequear si el control x hash alcanza
        while(!solved  &&  !searcher.isEmpty()){

            curNode = searcher.nextNode()

            val nextNodes = curNode.possibleRules.map {
                Node(idCounter++, problem, curNode, it.applyToState(curNode.state),
                        curNode.cost + it.cost, curNode.level + 1)
            }

            searcher.addNodes(nextNodes)
            solved = curNode.problem.isResolved(curNode.state)

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