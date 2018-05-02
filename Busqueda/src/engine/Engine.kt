package engine

import ar.com.itba.sia.Problem
import engine.searchers.Searcher
import java.util.*


class Engine<E>{

    var externalCurNode: Node<E>? = null

    fun solve (problem: Problem<E>, searcher: Searcher<E>, processing: StateProcessor<E>? = null): Solution<E>? {

        var idCounter = 1
        var nodesTaken = 0
        var solved = false
        var solution: Solution<E> = Solution()

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
                }
                searcher.addNodes(nextNodes, curNode)
            }

        }

        if(solved){
            solution.solved = true
            solution.nodesCreated = idCounter
            solution.expandedNodes = nodesTaken
            solution.frontierNodes = searcher.frontierNodes()
            solution.solutionDepth = curNode.level
            solution.solutionCost = curNode.cost
            solution.solution = getSolution(curNode)
            return solution
        }
        println(solution)
        //TODO: Change to return an empty solution (must modify tests)
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