package ar.edu.itba.sia.equipables

import ar.edu.itba.sia.Armory

enum class EquipmentType {
    WEAPON {
        override var maxId = 0.0

        override val armory: Map<Double, Equipment>
            get() = Armory.weapons

        override val index: Int
            get() = 0
    },
    HEADGEAR {
        override var maxId = 0.0

        override val armory: Map<Double, Equipment>
            get() = Armory.headgear

        override val index: Int
            get() = 1
    },
    BODYARMOR {
        override var maxId = 0.0

        override val armory: Map<Double, Equipment>
            get() = Armory.bodyArmor

        override val index: Int
            get() = 2
    },
    GLOVES {
        override var maxId = 0.0

        override val armory: Map<Double, Equipment>
            get() = Armory.gloves

        override val index: Int
            get() = 3
    },
    BOOTS {
        override var maxId = 0.0

        override val armory: Map<Double, Equipment>
            get() = Armory.boots

        override val index: Int
            get() = 4
    };

    abstract val index: Int

    abstract val armory: Map<Double, Equipment>

    abstract var maxId: Double

    val randId: Double
        get() = Math.random() * maxId

    fun getEquipment(gens: Array<Double>) =  armory[gens[index]]!!

    fun replace(gens: Array<Double>, equipmentId: Double) = run { gens[index] = equipmentId }

    companion object {
        fun recalculateMax() {
            EquipmentType.values().forEach {
                it.maxId = it.armory.maxBy { it.key }!!.key
            }
        }
    }
}