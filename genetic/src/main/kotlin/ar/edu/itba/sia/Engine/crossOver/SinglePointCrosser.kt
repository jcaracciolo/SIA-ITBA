package ar.edu.itba.sia.Engine.crossOver

import ar.edu.itba.sia.evolutionable.Evolutionable
import java.util.*


class SinglePointCrosser: Crosser {

    override fun crossOver(father: Evolutionable, mother: Evolutionable): List<Evolutionable> {
        val firstChild = father.getDescendant()
        val secondChild = mother.getDescendant()
        val children: MutableList<Evolutionable> = ArrayList()
        val locus = Random().nextInt(father.gens.size - 1)


        for(i in locus..father.gens.size -1) {
            firstChild.gens[i] = mother.gens[i]
            secondChild.gens[i] = father.gens[i]
        }
        children.add(firstChild)
        children.add(secondChild)
        return children
    }

}