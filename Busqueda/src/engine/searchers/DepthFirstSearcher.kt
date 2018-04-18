package engine.searchers

import engine.Node
import java.util.*

class DepthFirstSearcher<E>: Searcher<E> {

    var nodes: Queue<Node<E>> = ArrayDeque()

    override fun addNodes(nodes: List<Node<E>>) {
        this.nodes.addAll(nodes)
    }

    override fun isEmpty(): Boolean {
       return nodes.isEmpty()
    }

    override fun addNode(node: Node<E>) {
        nodes.add(node)
    }

    override fun nextNode(): Node<E> {
       return nodes.poll()
    }
}