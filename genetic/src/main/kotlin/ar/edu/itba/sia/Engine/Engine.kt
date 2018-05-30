package ar.edu.itba.sia.Engine

import ar.edu.itba.sia.evolutionable.Evolutionable
import ar.edu.itba.sia.utils.ConfigurationFile
import ar.edu.itba.sia.utils.ConfigurationParser
import java.lang.Thread.sleep
import java.io.File
import java.util.*
import kotlin.collections.ArrayList



class Engine {

    companion object {
        lateinit var currentGen: List<Evolutionable>
        var generations: Int = 0

        @JvmStatic
        fun main(args: Array<String>) {
            val conf = ConfigurationParser.parseFile("./src/resources/config.json")!!
            val best = naturalSelection(conf, { sleep(1000)})
            print(best)
            println()
            print(best.getPerformance())
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
                var parents = replacer.parentsToCross(currentGeneration)
                var children = ArrayList<Evolutionable>()

                while(children.size < parents.size) {
                    val fatherIndex = Random().nextInt(parents.size)
                    val motherIndex = (fatherIndex + 1 + Random().nextInt(parents.size-2)) % parents.size

                    for (child : Evolutionable in crosser.crossOver(parents[fatherIndex], parents[motherIndex])){
                        val currentChild = mutator.mutate(child, generations, genMutator)
                        if (currentChild.getPerformance() > greatestSpecimen.getPerformance()){
                            greatestSpecimen = currentChild
                        }
                        children.add(child)

                    }
                }


                var newChildren = children.filter { it.isValid() }
                newChildren = newChildren.plus(
                     (newChildren.size until children.size).map {
                        configurationFile.initialGeneration.first().random()
                    }
                 )


                currentGeneration = replacer.replace(parents, newChildren)

                generations++
                processor()

                if(generations % 50 == 0){
                    val output = File("./500Generations200000-DoublePoint-uniform0.4-UpTo5-LessChildren180-RankingFalse-TournamentDeterministic6True.txt")
                    output.appendText("${generations}\t${greatestSpecimen.getPerformance()}\t${currentGeneration.map { it.getPerformance() }.average()}\t${currentGeneration.distinct().size}\n")
                }
            }

            return greatestSpecimen
        }
    }
}