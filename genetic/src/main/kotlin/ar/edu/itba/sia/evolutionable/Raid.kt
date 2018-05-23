package ar.edu.itba.sia.evolutionable

import ar.edu.itba.sia.evolutionable.characters.Character
import ar.edu.itba.sia.evolutionable.characters.CharacterType
import java.util.*
import kotlin.collections.ArrayList

class Raid(private val innerGens: Array<Character>): Evolutionable {

    override val gens: Array<Any>
        get() = innerGens as Array<Any>

    override fun getDescendant(): Evolutionable {
        return Raid(innerGens.copyOf())
    }

    override fun getPerformance(): Double {
        val performance = innerGens.map{ it.getPerformance() }.sum()
        val differentClasses = innerGens.map { it.characterClass }.distinct().count()
        return performance * Math.pow(1.05, differentClasses.toDouble())
    }

    override fun mutateGen(n: Int) {
        var validClasses: MutableList<CharacterType> = ArrayList()
        validClasses.add(CharacterType.DEFENDER)
        if(n != 0) {
            validClasses.addAll(CharacterType.values())
        }
        innerGens[n] = validClasses[Random().nextInt(validClasses.size)].getRandom()
    }

}