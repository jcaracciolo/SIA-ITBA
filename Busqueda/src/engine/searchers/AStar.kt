package engine.searchers

import ar.com.itba.sia.Heuristic
import engine.Node
import java.util.*

class AStar <E> (val heuristic: Heuristic<E>)  : Searcher<E>{


    var nodes = PriorityQueue<Node<E>>(3, kotlin.Comparator({ n1,n2 ->
        var ret = 0
        var h1 = heuristic.getValue(n1.state)
        var h2 = heuristic.getValue(n2.state)
        when{
            n1.cost + h1 < n2.cost + h2 -> ret = -1
            n1.cost + h1 == n2.cost + h2 -> ret = 0
            n1.cost + h1 > n2.cost + h2 -> ret = 1
        }
        return@Comparator ret
    }))

    override fun nextNode(): Node<E> {
        return nodes.poll()
    }

    override fun addNodes(nodes: List<Node<E>>) {
        this.nodes.addAll(nodes)
    }

    override fun addNode(node: Node<E>) {
        nodes.add(node)
    }

    override fun isEmpty(): Boolean {
        return nodes.isEmpty()
    }


}