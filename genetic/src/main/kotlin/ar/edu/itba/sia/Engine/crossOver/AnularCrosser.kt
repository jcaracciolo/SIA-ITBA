package ar.edu.itba.sia.Engine.crossOver

import ar.edu.itba.sia.characters.Character
import java.util.*

class AnularCrosser: Crosser {
    override fun crossOver(father: Character, mother: Character): List<Character> {
        val firstChild = father.getDescendant()
        val secondChild = mother.getDescendant()
        val childs: MutableList<Character> = ArrayList()
        val locus = Random().nextInt(father.gens.size - 1)
        val length = Random().nextInt(father.gens.size / 2)
        var index = locus
        for (i in locus..locus+length) {
            firstChild.gens[index] = mother.gens[index]
            secondChild.gens[index] = father.gens[index]
            index ++
            if (index > father.gens.size - 1){
                index = 0
            }
        }
        childs.add(firstChild)
        childs.add(secondChild)
        return childs
    }
}