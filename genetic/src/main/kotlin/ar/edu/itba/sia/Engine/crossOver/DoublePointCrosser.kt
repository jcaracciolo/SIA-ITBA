package ar.edu.itba.sia.Engine.crossOver

import ar.edu.itba.sia.evolutionable.Evolutionable
import java.util.*

class DoublePointCrosser: Crosser{

    override fun <G> crossOver(father: Evolutionable<G>, mother: Evolutionable<G>): List<Evolutionable<G>> {
        val firstChild = father.getDescendant()
        val secondChild = mother.getDescendant()
        val children: MutableList<Evolutionable<G>> = ArrayList()
        val minLocus = Random().nextInt(father.gens.size - 2)
        val maxLocus = Random().nextInt(father.gens.size - 1 - minLocus) + minLocus

        for(i in minLocus..maxLocus) {
            firstChild.gens[i] = mother.gens[i]
            secondChild.gens[i] = father.gens[i]
        }
        children.add(firstChild)
        children.add(secondChild)
        return children
    }

}