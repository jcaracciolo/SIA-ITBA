package ar.edu.itba.sia.Engine.crossOver

import ar.edu.itba.sia.evolutionable.Evolutionable
import java.util.*

class DoublePointCrosser: Crosser{

    override fun crossOver(father: Evolutionable, mother: Evolutionable): List<Evolutionable> {
        val firstChild = father.getDescendant()
        val secondChild = mother.getDescendant()
        val children: MutableList<Evolutionable> = ArrayList()
        val minLocus = Random().nextInt(father.gens.size - 2)
        val maxLocus = 1 + Random().nextInt(father.gens.size - minLocus - 1)


        for(i in minLocus..(minLocus + maxLocus)) {
            firstChild.gens[i] = mother.gens[i]
            secondChild.gens[i] = father.gens[i]
        }

        children.add(firstChild)
        children.add(secondChild)
        return children
    }

}