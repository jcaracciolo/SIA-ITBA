package ar.edu.itba.sia.Engine.crossOver

import ar.edu.itba.sia.evolutionable.characters.Evolutionable
import java.util.*

class AnularCrosser: Crosser {
    override fun <G> crossOver(father: Evolutionable<G>, mother: Evolutionable<G>): List<Evolutionable<G>> {
        val firstChild = father.getDescendant()
        val secondChild = mother.getDescendant()
        val children: MutableList<Evolutionable<G>> = ArrayList()
        val locus = Random().nextInt(father.genSize() - 1)
        val length = Random().nextInt(father.genSize() / 2)
        var index = locus
        for (i in locus..locus+length) {
            firstChild.setGen(index, mother.getGen(index))
            secondChild.setGen(index, father.getGen(index))
            index ++
            if (index > father.genSize() - 1){
                index = 0
            }
        }
        children.add(firstChild)
        children.add(secondChild)
        return children
    }
}