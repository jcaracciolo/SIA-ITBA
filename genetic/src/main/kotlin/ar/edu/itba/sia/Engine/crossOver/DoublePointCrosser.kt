package ar.edu.itba.sia.Engine.crossOver

import ar.edu.itba.sia.characters.Character
import java.util.*

class DoublePointCrosser: Crosser{
    override fun crossOver(father: Character, mother: Character): List<Character> {
        val firstChild = father.getDescendant()
        val secondChild = mother.getDescendant()
        val childs: MutableList<Character> = ArrayList()
        val minLocus = Random().nextInt(father.gens.size - 2)
        val maxLocus = Random().nextInt(father.gens.size - 1 - minLocus) + minLocus

        for(i in minLocus..maxLocus) {
            firstChild.gens[i] = mother.gens[i]
            secondChild.gens[i] = father.gens[i]
        }
        childs.add(firstChild)
        childs.add(secondChild)
        return childs
    }
}