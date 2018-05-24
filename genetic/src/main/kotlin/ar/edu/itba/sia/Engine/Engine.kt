package ar.edu.itba.sia.Engine

import ar.edu.itba.sia.Armory
import ar.edu.itba.sia.evolutionable.Evolutionable
import ar.edu.itba.sia.evolutionable.characters.Character
import ar.edu.itba.sia.utils.ConfigurationFile
import ar.edu.itba.sia.utils.ConfigurationParser
import java.util.*
import javax.security.auth.login.Configuration
import kotlin.collections.ArrayList

class Engine {

    companion object {
        lateinit var currentGen: List<Evolutionable>
        var generations: Int = 0

        @JvmStatic
        fun main(args: Array<String>) {
            Armory.initialze("/home/juanfra/Downloads/fulldata")
            val conf = ConfigurationParser.parseFile("./src/Resources/config.json")!!
            print(naturalSelection(conf))
        }

        fun naturalSelection(
                configurationFile: ConfigurationFile,
                processor: ()->Unit = { }): Evolutionable{
            val crosser = configurationFile.crosser
            val mutator = configurationFile.mutator
            val genMutator = configurationFile.genMutator
            val cutter = configurationFile.cutter
            val replacer = configurationFile.replacer

            generations = 0
            var currentGeneration: List<Evolutionable> = configurationFile.initialGeneration
            var greatestSpecimen: Evolutionable

            greatestSpecimen = currentGeneration.maxBy { it.getPerformance() }!!

            while(!cutter.shouldCut(currentGeneration)) {
                Engine.currentGen = currentGeneration
                val parents = replacer.parentsToCross(currentGeneration)
                val children = ArrayList<Evolutionable>()

                while(children.size < parents.size) {
                    val fatherIndex = Random().nextInt(parents.size)
                    val motherIndex = Random().nextInt(parents.size)

                    for (child : Evolutionable in crosser.crossOver(parents[fatherIndex], parents[motherIndex])){
                        val currentChild = mutator.mutate(child, generations, genMutator)
                        if (currentChild.getPerformance() > greatestSpecimen.getPerformance()){
                            greatestSpecimen = currentChild
                        }
                        children.add(child)

                    }
                }
                parents.forEach { mutator.mutate(it, generations, genMutator) }
                currentGeneration = replacer.replace(parents, children).toMutableList()
                generations++
                processor()

                println("MAX: ${greatestSpecimen.getPerformance()} AVG: ${currentGeneration.map { it.getPerformance() }.average()} - ${currentGeneration.distinct().size}")

            }

            return greatestSpecimen
        }
    }
}