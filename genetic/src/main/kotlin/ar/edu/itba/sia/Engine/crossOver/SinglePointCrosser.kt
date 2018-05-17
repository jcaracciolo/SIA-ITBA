package ar.edu.itba.sia.Engine.crossOver

import ar.edu.itba.sia.characters.Character
import java.util.*


class SinglePointCrosser: Crosser {
    override fun crossOver(father: Character, mother: Character): List<Character> {
        val firstChild = father.getDescendant()
        val secondChild = mother.getDescendant()
        val childs: MutableList<Character> = ArrayList()
        val locus = Random().nextInt(father.gens.size - 1)


        for(i in locus..father.gens.size-1) {
                firstChild.gens[i] = mother.gens[i]
                secondChild.gens[i] = father.gens[i]
        }
        childs.add(firstChild)
        childs.add(secondChild)
        return childs
    }

}