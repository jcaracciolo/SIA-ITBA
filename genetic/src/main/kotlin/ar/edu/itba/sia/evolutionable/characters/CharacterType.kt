package ar.edu.itba.sia.evolutionable.characters

import ar.edu.itba.sia.utils.andExit
import java.util.*

enum class CharacterType(val string: String) {
    ARCHER("archer") {
        override fun getRandom(type: Int): Character = Archer.random(type)
    },
    ASSASSIN("assassin") {
        override fun getRandom(type: Int): Character = Assassin.random(type)
    },
    DEFENDER("defender") {
        override fun getRandom(type: Int): Character = Defender.random(type)
    },
    WARRIOR("warrior") {
        override fun getRandom(type: Int): Character = Warrior.random(type)
    };

    abstract fun getRandom(type: Int): Character

    companion object {
        fun fromSting(string: String): CharacterType = CharacterType.values().firstOrNull { it.string == string }
                ?: "$string is not a valid character".andExit()

        fun randomCharacter() = CharacterType.values()[Random().nextInt(CharacterType.values().size)].getRandom()
    }
}