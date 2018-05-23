package ar.edu.itba.sia.Engine.crossOver

import ar.edu.itba.sia.evolutionable.Evolutionable
import java.util.*

class AnularCrosser: Crosser {
    override fun crossOver(father: Evolutionable, mother: Evolutionable): List<Evolutionable> {
        val firstChild = father.getDescendant()
        val secondChild = mother.getDescendant()
        val children: MutableList<Evolutionable> = ArrayList()
        val locus = Random().nextInt(father.gens.size - 1)
        val length = Random().nextInt(father.gens.size / 2)
        var index = locus
        for (i in locus..locus+length) {
            firstChild.gens[index] = mother.gens[index]
            secondChild.gens[index] = mother.gens[index]
            index ++
            index %= father.gens.size
        }

        children.add(firstChild)
        children.add(secondChild)
        return children
    }
}