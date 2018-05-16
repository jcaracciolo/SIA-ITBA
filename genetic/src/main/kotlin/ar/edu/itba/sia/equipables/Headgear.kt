package ar.edu.itba.sia.equipables

class Headgear(
        override val id: Long,
        override val strength: Double,
        override val agility: Double,
        override val expertise: Double,
        override val resistance: Double,
        override val vitality: Double) : Equipment()  {
}