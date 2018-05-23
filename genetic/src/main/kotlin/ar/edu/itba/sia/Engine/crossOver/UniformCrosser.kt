package ar.edu.itba.sia.Engine.crossOver

import ar.edu.itba.sia.evolutionable.Evolutionable
import java.util.*

class UniformCrosser: Crosser {
    override fun crossOver(father: Evolutionable, mother: Evolutionable): List<Evolutionable> {
        val firstChild = father.getDescendant()
        val secondChild = mother.getDescendant()
        val children: MutableList<Evolutionable> = ArrayList()

        for(i in 0 until father.gens.size) {
            if(Random().nextDouble() > 0.5) {
                firstChild.gens[i] = mother.gens[i]
                secondChild.gens[i] = father.gens[i]
            }
        }
        children.add(firstChild)
        children.add(secondChild)
        return children
    }
}