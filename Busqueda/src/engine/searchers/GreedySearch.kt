package engine.searchers

import ar.com.itba.sia.Heuristic
import chainReaction.game.CRState
import engine.Node
import java.util.*

class GreedySearch <E> (val heuristic: Heuristic<E>)  : Searcher<E>{

    private val visitedNodes: HashSet<Node<E>> = HashSet()
    private val comparator = kotlin.Comparator<Node<E>> { n1, n2 ->
        val d1 = heuristic.getValue(n1.state)
        val d2 = heuristic.getValue(n2.state)
        return@Comparator when{
            d1 > d2 -> 1
            d1 == d2 -> 0
            else -> -1
        }
    }

    private val openNodes = PriorityQueue<Node<E>>(comparator)

    private val openSet = HashMap<Node<E>,Node<E>>()

    override fun nextNode(): Node<E> {
        val ans = openNodes.poll()
        openSet.remove(ans)
        visitedNodes.add(ans)
        return ans
    }

    override fun addNodes(nodes: List<Node<E>>, from: Node<E>) {
        nodes
                .filter{ !visitedNodes.contains(it) }
                .filter{ heuristic.getValue(it.state)<Double.POSITIVE_INFINITY }.forEach {
            val lastNode = openSet[it]

            if(lastNode!=null) {
                if(lastNode.cost > it.cost) {
                    openNodes.remove(lastNode)
                    openSet.remove(lastNode)
                    openSet[it] = it
                    openNodes.add(it)
                }
            } else {
                openSet[it] = it
                openNodes.add(it)
            }
        }
    }

    override fun addNode(node: Node<E>) {
        openNodes.add(node)
        openSet[node] = node
    }

    override fun isEmpty(): Boolean = openNodes.isEmpty()

    override fun frontierNodes(): Int {
        return openNodes.size
    }

}