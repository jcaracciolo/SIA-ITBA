package ar.edu.itba.sia.equipables

enum class EquipmentType {
    WEAPON {
        override fun create(id: Long, strength: Double, agility: Double, expertise: Double, resistance: Double, vitality: Double) : Equipment {
            return Weapon(id, strength, agility, expertise, resistance, vitality)
        }
    },
    HEADGEAR {
        override fun create(id: Long, strength: Double, agility: Double, expertise: Double, resistance: Double, vitality: Double): Equipment {
            return Headgear(id, strength, agility, expertise, resistance, vitality)
        }
    },
    BODYARMOR {
        override fun create(id: Long, strength: Double, agility: Double, expertise: Double, resistance: Double, vitality: Double): Equipment {
            return BodyArmor(id, strength, agility, expertise, resistance, vitality)
        }
    },
    GLOVES {
        override fun create(id: Long, strength: Double, agility: Double, expertise: Double, resistance: Double, vitality: Double): Equipment {
            return Gloves(id, strength, agility, expertise, resistance, vitality)
        }
    },
    BOOTS {
        override fun create(id: Long, strength: Double, agility: Double, expertise: Double, resistance: Double, vitality: Double): Equipment {
            return Boots(id, strength, agility, expertise, resistance, vitality)
        }
    };

    abstract fun create(id: Long,
                        strength: Double,
                        agility: Double,
                        expertise: Double,
                        resistance: Double,
                        vitality: Double) : Equipment
}