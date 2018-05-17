package ar.edu.itba.sia.Engine.CrossOver

import ar.edu.itba.sia.characters.Character
import java.util.*

class UniformCrosser: Crosser {
    override fun crossOver(father: Character, mother: Character): List<Character>? {
        var firstChild = father.getDescendant()
        var secondChild = mother.getDescendant()
        var childs: MutableList<Character> = ArrayList()

        for(i in 0..father.gens.size-1) {
            if(Random().nextDouble() > 0.5) {
                firstChild.gens[i] = mother.gens[i]
                secondChild.gens[i] = father.gens[i]
            }
        }
        childs.add(firstChild)
        childs.add(secondChild)
        return childs
    }
}