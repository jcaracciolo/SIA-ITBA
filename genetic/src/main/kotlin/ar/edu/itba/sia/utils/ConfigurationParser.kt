package ar.edu.itba.sia.utils

import ar.edu.itba.sia.Engine.crossOver.*
import ar.edu.itba.sia.Engine.cutter.*
import ar.edu.itba.sia.Engine.mutators.Mutator
import ar.edu.itba.sia.Engine.mutators.NotUniformMutator
import ar.edu.itba.sia.Engine.mutators.UniformMutator
import ar.edu.itba.sia.Engine.replacer.*
import ar.edu.itba.sia.Engine.selector.Selector
import org.json.JSONException
import org.json.JSONObject
import kotlin.system.exitProcess

enum class Crossers(val string: String, val crosser: Crosser) {
    ANULAR("anular", AnularCrosser()),
    SINGLE_POINT("single point", SinglePointCrosser()),
    DOUBLE_POINT("double point", DoublePointCrosser()),
    UNIFORM("uniform", UniformCrosser());

    fun fromSting(string: String): Crosser = Crossers.values().firstOrNull { it.string == string }
            .let { it ?: println("$string is not a valid crosser");exitProcess(-1) }
}

enum class Cutters(val string: String) {
    GENERATION("generation"),
    STAYS_SAME("stay same"),
    NO_PROGRESS("no progress"),
    OPTIMAL_CUTTER("optimal");

    fun fromSting(string: String, parameters: JSONObject): Cutter =
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
                    parameters.tryWithError("optimal", Double::class.java)
            )

            else -> {
                println("$string is not a valid Cutter")
                exitProcess(-1)
            }

        }

    }

enum class Mutators(val string: String, val mutator: Mutator) {
    NOT_UNIFORM("not uniform", NotUniformMutator()),
    UNIFORM("uniform", UniformMutator());

    fun fromSting(string: String): Mutator = Mutators.values().firstOrNull { it.string == string }
            .let { it ?: println("$string is not a valid mutator"); exitProcess(-1) }
}

enum class Replacers(val string: String) {
    LESS_CHILDREN("less children"),
    ONLY_CHILDREN("only children"),
    MIX_MATCH_REPLACER("mix and match"),
    COMBINED("combined");

    fun fromSting(string: String, selector: Selector, parameters: JSONObject?): Replacer =
            when(string) {
                LESS_CHILDREN.string -> {
                    if(parameters==null) { println("Less children replacer needs amount parameter"); exitProcess(-1) }
                    LessChildrenReplacer(
                            parameters.tryWithError("amount", Int::class.java),
                            selector
                    )
                }

                ONLY_CHILDREN.string -> OnlyChildrenReplacer(selector)

                MIX_MATCH_REPLACER.string -> {
                    if(parameters==null) { println("Mix and Match replacer needs amount parameter"); exitProcess(-1) }
                    MixAndMatchReplacer(
                            parameters.tryWithError("amount", Int::class.java),
                            selector
                    )
                }

                COMBINED.string -> {
                    if(parameters==null) { println("Combined replacer needs parameters"); exitProcess(-1) }
                    CombinedReplacer(
                            parameters.tryWithError("percentage", Double::class.java),

                            fromSting(
                                    parameters.tryWithError("first", String::class.java),
                                    selector,
                                    parameters.tryWithError("parameters", JSONObject::class.java)
                            ),

                            fromSting(
                                    parameters.tryWithError("first", String::class.java),
                                    selector,
                                    parameters.tryWithError("parameters", JSONObject::class.java)
                            )
                    )
                }

                else -> {
                    println("$string is not a valid Replacer")
                    exitProcess(-1)
                }

            }

}

data class ConfigurationFile(
    val crosser: Crossers
)

class ConfigurationParser {
    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val jsonString = "{ Crosser:\"AAA\", king: 1 }"
            val jsonObject = JSONObject(jsonString)
//            ConfigurationFile(jsonObject.tryWithError("crosser"))

            print(jsonObject.tryWithError("king", String::class.java))
        }

        fun parseFile(path: String): ConfigurationFile? {
//            val jsonString = File(path).readText(Charset.defaultCharset())






            return null
        }
    }
}

inline fun <reified T> JSONObject.tryWithError(key: String, clazz: Class<T>): T =
    try {
        try {
            val value = this[key]
            value as T
        } catch (e: ClassCastException) {
            println("[ERROR] Expected value for key ($key) to be of type ${T::class.java.simpleName} but was of type ${this[key]::class.java.simpleName} ")
            exitProcess(-1)
        }
    } catch (e: JSONException) {
        println("[ERROR] Key ($key) not found on the JSON Object")
        exitProcess(-1)
    }

fun String.toCrossers(): Crossers = Crossers.valueOf(this)
