package ar.edu.itba.sia.utils

import ar.edu.itba.sia.Engine.crossOver.*
import ar.edu.itba.sia.Engine.cutter.*
import ar.edu.itba.sia.Engine.mutators.*
import ar.edu.itba.sia.Engine.replacer.*
import ar.edu.itba.sia.Engine.selector.*
import ar.edu.itba.sia.evolutionable.Evolutionable
import ar.edu.itba.sia.evolutionable.characters.*
import org.json.JSONException
import org.json.JSONObject
import java.io.File
import java.nio.charset.Charset
import kotlin.system.exitProcess

enum class Crossers(val string: String, val crosser: Crosser) {
    ANULAR("anular", AnularCrosser()),
    SINGLE_POINT("single point", SinglePointCrosser()),
    DOUBLE_POINT("double point", DoublePointCrosser()),
    UNIFORM("uniform", UniformCrosser());

    companion object {
        fun fromSting(string: String): Crosser = Crossers.values().firstOrNull { it.string == string }
                .let { it?.crosser ?: "$string is not a valid crosser".andExit() }
    }
}

enum class Cutters(val string: String) {
    GENERATION("generation"),
    STAYS_SAME("stay same"),
    NO_PROGRESS("no progress"),
    OPTIMAL_CUTTER("optimal");

    companion object {
        fun fromString(string: String, parameters: JSONObject): Cutter =
                when(string) {
                    GENERATION.string -> GenerationCutter(
                            parameters.tryWithError("generations", Int::class.java)
                    )

                    STAYS_SAME.string -> GenerationStaysSame(
                            parameters.tryWithError("generations", Int::class.java),
                            parameters.tryWithError("amount", Int::class.java)
                    )

                    NO_PROGRESS.string -> NoProgressCutter(
                            parameters.tryWithError("generations", Int::class.java),
                            parameters.tryWithError("percentage", Double::class.java)
                    )

                    OPTIMAL_CUTTER.string -> OptimalCutter(
                            parameters.tryWithError("amount", Double::class.java)
                    )

                    else -> "$string is not a valid Cutter".andExit()

                }
    }

}

enum class Mutators(val string: String) {
    NOT_UNIFORM("not uniform"),
    UNIFORM("uniform");

    companion object {
        fun fromString(string: String, parameters: JSONObject): Mutator =
                when(string) {
                    NOT_UNIFORM.string -> NotUniformMutator(
                            parameters.tryWithError("percentage", Double::class.java)
                    )

                    UNIFORM.string -> UniformMutator(
                            parameters.tryWithError("percentage", Double::class.java)
                    )

                    else -> "$string is not a valid Mutator".andExit()

                }
    }

}

enum class GenMutators(val string: String) {
    MULTIPLE("multiple"),
    UP_TO("up to");

    companion object {
        fun fromString(string: String, parameters: JSONObject): GenMutator =
                when(string) {
                    MULTIPLE.string -> MultiGenMutator(
                            parameters.tryWithError("amount", Int::class.java)
                    )

                    UP_TO.string -> UpToGenMutator(
                            parameters.tryWithError("amount", Int::class.java)
                    )

                    else -> "$string is not a valid Mutator".andExit()

                }
    }

}

enum class Replacers(val string: String) {
    LESS_CHILDREN("less children"),
    ONLY_CHILDREN("only children"),
    MIX_MATCH_REPLACER("mix and match"),
    COMBINED("combined");

    companion object {
        fun fromSting(string: String, selector: Selector, parameters: JSONObject?): Replacer =
                when (string) {
                    LESS_CHILDREN.string -> {
                        LessChildrenReplacer(
                                parameters?.tryWithNull("amount", Int::class.java),
                                selector
                        )
                    }

                    ONLY_CHILDREN.string -> OnlyChildrenReplacer(selector)

                    MIX_MATCH_REPLACER.string -> {
                        MixAndMatchReplacer(
                                parameters?.tryWithNull("amount", Int::class.java),
                                selector
                        )
                    }

                    COMBINED.string -> {
                        if (parameters == null) {
                            "Combined replacer needs parameters".andExit()
                        }
                        CombinedReplacer(
                                parameters.tryWithError("percentage", Double::class.java),
                                parameters.tryWithError("first",JSONObject::class.java).toReplacer(selector),
                                parameters.tryWithError("second", JSONObject::class.java).toReplacer(selector)
                        )
                    }

                    else -> "$string is not a valid Replacer".andExit()
                }
    }
}

enum class Selectors(val string: String) {
    BOLTZMANN("boltzmann"),
    COMBINED("combined"),
    ELITE("elite"),
    RANKING("ranking"),
    ROULETTE("roulette"),
    TOURNAMENT_DETERMINISTIC("tournament_deterministic"),
    TOURNAMENT_PROBABILISTIC("tournament_probabilistic"),
    UNIVERSAL("universal");

    companion object {
        fun fromSting(string: String, parameters: JSONObject?): Selector =
                when (string) {
                    BOLTZMANN.string -> {
                        if (parameters == null) {
                            "Boltzmann selector needs parameters".andExit()
                        }
                        BoltzmannSelector(
                                parameters.tryWithError("other",JSONObject::class.java).toSelector(),
                                parameters.tryWithError("temperature", Double::class.java),
                                parameters.tryWithError("decrement", Double::class.java)
                        )
                    }

                    COMBINED.string -> {
                        if (parameters == null) {
                            "Combined selector needs parameters".andExit()
                        }
                        CombinedSelector(
                                parameters.tryWithError("percentage", Double::class.java),
                                parameters.tryWithError("first",JSONObject::class.java).toSelector(),
                                parameters.tryWithError("second",JSONObject::class.java).toSelector()
                        )
                    }

                    ELITE.string -> EliteSelector()

                    RANKING.string ->{
                        if (parameters == null) {
                            "Ranking selector needs parameters".andExit()
                        }
                        RankingSelector(
                                parameters.tryWithError("other",JSONObject::class.java).toSelector()
                        )
                    }

                    ROULETTE.string -> RouletteSelector()

                    TOURNAMENT_DETERMINISTIC.string ->{
                        if (parameters == null) {
                            "Ranking selector needs parameters".andExit()
                        }
                        TournamentDeterministicSelector(
                                parameters.tryWithError("amount", Int::class.java)
                        )
                    }

                    TOURNAMENT_PROBABILISTIC.string -> TournamentProbabilisticSelector()

                    UNIVERSAL.string -> UniversalSelector()

                    else -> "$string is not a valid Selector".andExit()
                }
    }
}

enum class EvolutionTypes(val string: String) {
    CHARACTERS("character"),
    GUILDS("guild");

    companion object {
        fun fromSting(string: String): EvolutionTypes = EvolutionTypes.values().firstOrNull { it.string == string }
                ?: "$string is not a valid Evolution Type".andExit()
    }
}

enum class Characters(val string: String) {
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
        fun fromSting(string: String): Characters = Characters.values().firstOrNull { it.string == string }
                ?: "$string is not a valid character".andExit()
    }
}

data class ConfigurationFile(
    val initialGeneration: List<Evolutionable>,
    val crosser: Crosser,
    val cutter: Cutter,
    val mutator: Mutator,
    val genMutator: GenMutator,
    val replacer: Replacer
)

class ConfigurationParser {
    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
//            ConfigurationFile(jsonObject.tryWithError("crosser"))

            println(parseFile("./src/Resources/config.json"))

        }

        fun parseFile(path: String): ConfigurationFile? {
            val jsonString = File(path).readText(Charset.defaultCharset())
            val jsonObject = JSONObject(jsonString)

            val crosser = jsonObject.tryWithError("crosser", String::class.java).toCrosser()
            val cutter = jsonObject.tryWithError("cutter", JSONObject::class.java).toCutter()
            val mutator = jsonObject.tryWithError("mutator", JSONObject::class.java).toMutator()
            val genMutator = jsonObject.tryWithError("genmutator", JSONObject::class.java).toGenMutator()
            val selector = EliteSelector()
            val replacer = jsonObject.tryWithError("replacer", JSONObject::class.java).toReplacer(selector)

            val initialSize = jsonObject.tryWithError("generation_size", Int::class.java)
            val type = jsonObject.tryWithError("type", String::class.java).toEvolutionType()
            val initialGen = when(type) {
                EvolutionTypes.CHARACTERS -> {
                    val character = jsonObject.tryWithError("character", String::class.java).toCharacter()
                    List(initialSize, { character.getRandom() })
                }
                EvolutionTypes.GUILDS -> {
                    List(0, { Assassin.random() })
                }
            }


            return ConfigurationFile(initialGen, crosser, cutter, mutator, genMutator, replacer)
        }
    }
}

inline fun <reified T> JSONObject.tryWithError(key: String, clazz: Class<T>): T =
    this.tryWithNull(key, clazz) ?:  "[ERROR] Key ($key) not found on the JSON Object".andExit()

inline fun <reified T> JSONObject.tryWithNull(key: String, clazz: Class<T>): T? =
        try {
            try {
                val value = this[key]
                when(value) {
                    JSONObject.NULL -> null
                    else -> value as T
                }
            } catch (e: ClassCastException) {
                "[ERROR] Expected value for key ($key) to be of type ${T::class.java.simpleName} but was of type ${this[key]::class.java.simpleName}".andExit()
            }
        } catch (e: JSONException) {
            null
        }

fun String.toCrosser(): Crosser = Crossers.fromSting(this)
fun String.toEvolutionType(): EvolutionTypes = EvolutionTypes.fromSting(this)
fun String.toCharacter(): Characters = Characters.fromSting(this)

fun JSONObject.toMutator(): Mutator = Mutators.fromString(
        this.tryWithError("type", String::class.java),
        this.tryWithError("parameters", JSONObject::class.java)
)

fun JSONObject.toGenMutator(): GenMutator = GenMutators.fromString(
        this.tryWithError("type", String::class.java),
        this.tryWithError("parameters", JSONObject::class.java)
)

fun JSONObject.toCutter(): Cutter = Cutters.fromString(
        this.tryWithError("type", String::class.java),
        this.tryWithError("parameters", JSONObject::class.java)
        )

fun JSONObject.toReplacer(selector: Selector): Replacer = Replacers.fromSting(
        this.tryWithError("type", String::class.java),
        selector,
        this.tryWithNull("parameters", JSONObject::class.java)
)
fun JSONObject.toSelector(): Selector = Selectors.fromSting(
        this.tryWithError("type", String::class.java),
        this.tryWithNull("parameters", JSONObject::class.java)
)

fun String.andExit(): Nothing {
    println(this)
    exitProcess(-1)
}