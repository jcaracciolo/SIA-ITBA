package engine.searchers

import ar.com.itba.sia.Heuristic
import engine.Node
import java.util.*

class GreedySearch <E> (val heuristic: Heuristic<E>) : Searcher<E> {

    private val comparator = kotlin.Comparator<Node<E>> { n1, n2 ->
        val h1 = heuristic.getValue(n1.state)
        val h2 = heuristic.getValue(n2.state)
        //println("${n1.level} - ${n2.level} ---- ${h1} - ${h2} ------ IDS ${n1.id} - ${n2.id}")
        var result = 0

        //TODO check if node level should be taken into account
        /*when{
            n1.level < n2.level -> result = -1
            n1.level > n2.level -> result = 1
            else -> when{
                h1 < h2 -> result = 1
                h1 == h2 -> result = 0
                else -> result = -1
            }
        }*/
        //Better performance with no node level checkup
        when{
            h1 < h2 -> result = 1
            h1 == h2 -> result = 0
            else -> result = -1
        }
        //println("Result is ${result}")
        return@Comparator result
    }

    private val openNodes = PriorityQueue<Node<E>>(comparator)

    override fun nextNode(): Node<E> {
        return openNodes.poll()
    }

    override fun addNodes(nodes: List<Node<E>>, from: Node<E>) {
        openNodes.addAll(nodes)
    }

    override fun addNode(node: Node<E>) {
        openNodes.add(node)
    }

    override fun isEmpty(): Boolean {
        return openNodes.isEmpty()
    }


}