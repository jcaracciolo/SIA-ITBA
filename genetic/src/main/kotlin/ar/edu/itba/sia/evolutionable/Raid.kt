package ar.edu.itba.sia.evolutionable

import ar.edu.itba.sia.evolutionable.characters.Character
import ar.edu.itba.sia.evolutionable.characters.CharacterType
import java.util.*
import kotlin.collections.ArrayList

class Raid(open protected  val innerGens: Array<Character>): Evolutionable {

    override val gens: Array<Any>
        get() = innerGens as Array<Any>

    override fun getDescendant(): Evolutionable {
        return Raid(innerGens.copyOf())
    }

    override fun getPerformance(): Double {
        var characterClasses: MutableSet<CharacterType> = HashSet()
        var classCount = 0
        var performance = 0.0
        for (character in innerGens){
            if(!characterClasses.contains(character.characterClass)) {
                classCount+=1
                characterClasses.add(character.characterClass)
            }
            performance += character.getPerformance()
        }
        performance *= Math.pow(1.05, classCount.toDouble())
        return performance
    }

    override fun mutateGen(n: Int) {
        var validClasses: MutableList<CharacterType> = ArrayList()
        validClasses.add(CharacterType.DEFENDER)
        if(n != 0) {
            validClasses.addAll(CharacterType.values())
        }
        innerGens[n] = validClasses[Random().nextInt(validClasses.size)].randomChar
    }

}