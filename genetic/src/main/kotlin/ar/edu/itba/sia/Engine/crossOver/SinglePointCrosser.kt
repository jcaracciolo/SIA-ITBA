package ar.edu.itba.sia.Engine.crossOver

import ar.edu.itba.sia.evolutionable.characters.Evolutionable
import java.util.*


class SinglePointCrosser: Crosser {

    override fun <G> crossOver(father: Evolutionable<G>, mother: Evolutionable<G>): List<Evolutionable<G>> {
        val firstChild = father.getDescendant()
        val secondChild = mother.getDescendant()
        val children: MutableList<Evolutionable<G>> = ArrayList()
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