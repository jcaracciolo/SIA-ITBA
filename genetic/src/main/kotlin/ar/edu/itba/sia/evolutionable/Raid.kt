package ar.edu.itba.sia.evolutionable

import ar.edu.itba.sia.evolutionable.characters.Character
import ar.edu.itba.sia.evolutionable.characters.CharacterType
import ar.edu.itba.sia.evolutionable.characters.Defender
import java.util.*
import kotlin.collections.ArrayList

class Raid(private val innerGens: Array<Character>): Evolutionable {

    override val gens: Array<Any>
        get() = innerGens as Array<Any>

    override fun getDescendant(): Evolutionable {
        return Raid(innerGens.copyOf())
    }

    companion object {
        fun random(): Evolutionable {
            val randomRaid = ArrayList<Character>()
            randomRaid.add(Defender.random(Random().nextInt(3) + 1))
            while (randomRaid.size < 10) {
                var randomCharacter = CharacterType.randomCharacter()
                while (randomRaid.filter { it.characterClass == randomCharacter.characterClass }.count()
                        >= getMaxByClass(randomCharacter.characterClass)) {
                    randomCharacter = CharacterType.randomCharacter()
                }
                randomRaid.add(randomCharacter)
            }
            return Raid(Array(10, { randomRaid[it] }))
        }

        fun getMaxByClass(characterClass : CharacterType): Int{
            if(characterClass == CharacterType.DEFENDER) return 2
            else return 4
        }
    }

    override fun random(): Evolutionable = Raid.random()

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
        val rand = Random()
        innerGens[n] = validClasses[rand.nextInt(validClasses.size)].getRandom(rand.nextInt(3) + 1)
    }

    override fun isValid(): Boolean {
        if(innerGens.groupBy { it.characterClass }.any { it.value.size > 4 }){
            return false
        }
        if(innerGens.groupBy { it }.any{it.value.size > 2}) return false
        val defenders = innerGens.filter{it.characterClass == CharacterType.DEFENDER}.size
        if(defenders < 1 && defenders > 2) return false
        return true
    }
}