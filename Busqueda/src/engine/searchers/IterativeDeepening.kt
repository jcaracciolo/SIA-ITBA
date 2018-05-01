package engine.searchers

import engine.Node
import java.util.*

class IterativeDeepening <E> (val depthIncrease: Int) : Searcher<E> {

    var initialNode: Node<E>? = null
    var maxDepth: Int = 3
    var fNodes: Stack<Node<E>> = Stack()
    var cont: Boolean = false


    override fun nextNode(): Node<E> {
        if (!fNodes.isEmpty()){
            return fNodes.pop()
        }else{
            cont = false
            maxDepth += depthIncrease
            return initialNode!!
        }
    }

    override fun addNodes(nodes: List<Node<E>>, from: Node<E>) {
        for (n in nodes){
            if(n.level<= maxDepth){
                this.fNodes.add(n)
            }else{
                cont=true
            }

        }
    }

    override fun addNode(node: Node<E>) {
        if (initialNode == null){
            initialNode = node
        }
        if (node.level <= maxDepth){
            this.fNodes.add(node)
        }else{
            cont=true
        }
    }

    override fun isEmpty(): Boolean {
        return fNodes.isEmpty() && !cont
    }

}