package engine.searchers

import ar.com.itba.sia.Heuristic
import engine.Node
import java.util.*
import kotlin.collections.HashSet

class AStar <E> (val heuristic: Heuristic<E>)  : Searcher<E>{


    private val openNodes = PriorityQueue<Node<E>>( kotlin.Comparator({ n1,n2 ->
        val h1 = heuristic.getValue(n1.state)
        val h2 = heuristic.getValue(n2.state)
        return@Comparator when{
            n1.cost + h1 < n2.cost + h2 -> -1
            n1.cost + h1 == n2.cost + h2 -> 0
            else -> 1
        }
    }))

    private val openSet = HashMap<Node<E>,Node<E>>()

    override fun nextNode(): Node<E> {
        val ans = openNodes.poll()
        openSet.remove(ans)
        return ans
    }

    override fun addNodes(nodes: List<Node<E>>, from: Node<E>) {
        println(openNodes.size)
       nodes.filter { heuristic.getValue(it.state)<Double.POSITIVE_INFINITY }.forEach {
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

}