package ar.edu.itba.sia.evolutionable.characters

enum class CharacterType {
    ARCHER{
        override val randomChar : Character
            get() {
                val array: Array<Double> = arrayOf(0.0,0.0,0.0,0.0,0.0,0.0)
                val archer = Archer(array)
                for (i in 0..6){
                    archer.mutateGen(i)
                }
                return archer
            }
    },
    ASSASSIN{
        override val randomChar : Character
            get() {
                val array: Array<Double> = arrayOf(0.0,0.0,0.0,0.0,0.0,0.0)
                val assassin = Assassin(array)
                for (i in 0..6){
                    assassin.mutateGen(i)
                }
                return assassin
            }
    },
    DEFENDER{
        override val randomChar : Character
            get() {
                val array: Array<Double> = arrayOf(0.0,0.0,0.0,0.0,0.0,0.0)
                val defender = Defender(array)
                for (i in 0..6){
                    defender.mutateGen(i)
                }
                return defender
            }
    },
    WARRIOR{
        override val randomChar : Character
            get() {
                val array: Array<Double> = arrayOf(0.0,0.0,0.0,0.0,0.0,0.0)
                val archer = Archer(array)
                for (i in 0..6){
                    archer.mutateGen(i)
                }
                return archer
            }
    };

    abstract val randomChar: Character
}