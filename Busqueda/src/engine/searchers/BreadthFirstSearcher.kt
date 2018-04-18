package engine.searchers

import engine.Node
import java.util.*

class BreadthFirstSearcher <E> : Searcher<E> {


    var nodes:Stack<Node<E>> = Stack()

    override fun nextNode(): Node<E> {
       return nodes.pop()
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
