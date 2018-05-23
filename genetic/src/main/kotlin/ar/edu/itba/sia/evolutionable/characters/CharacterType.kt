package ar.edu.itba.sia.evolutionable.characters

import ar.edu.itba.sia.utils.andExit

enum class CharacterType(val string: String) {
    ARCHER("archer") {
        override fun getRandom(): Character = Archer.random()
    },
    ASSASSIN("assassin") {
        override fun getRandom(): Character = Assassin.random()
    },
    DEFENDER("defender") {
        override fun getRandom(): Character = Defender.random()
    },
    WARRIOR("warrior") {
        override fun getRandom(): Character = Warrior.random()
    };

    abstract fun getRandom(): Character

    companion object {
        fun fromSting(string: String): CharacterType = CharacterType.values().firstOrNull { it.string == string }
                ?: "$string is not a valid character".andExit()
    }
}