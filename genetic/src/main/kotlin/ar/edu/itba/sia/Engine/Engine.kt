package ar.edu.itba.sia.Engine

import ar.edu.itba.sia.evolutionable.Evolutionable
import ar.edu.itba.sia.utils.ConfigurationFile
import java.util.*
import kotlin.collections.ArrayList

class Engine {

    fun <G> NaturalSelection(configurationFile: ConfigurationFile, initial: List<Evolutionable<G>>){
        val crosser = configurationFile.crosser
        val mutator = configurationFile.mutator
        val genMutator = configurationFile.genMutator
        val cutter = configurationFile.cutter
        val replacer = configurationFile.replacer

        var generation = 0
        var currentGeneration: List<Evolutionable<G>> = initial
        var greatestChild: Evolutionable<G>

        greatestChild = currentGeneration.maxBy { it.getPerformance() }!!

        while(!cutter.shouldCut(currentGeneration)) {
            val parents = replacer.parentsToCross<G>()
            val children = ArrayList<Evolutionable<G>>()

            while(children.size < parents.size) {
                val fatherIndex = Random().nextInt(parents.size)
                val motherIndex = Random().nextInt(parents.size)

                for (child : Evolutionable<G> in crosser.crossOver(parents[fatherIndex], parents[motherIndex])){
                    val currentChild = mutator.mutate(child, generation, genMutator)
                    if (currentChild.getPerformance() > greatestChild.getPerformance()){
                        greatestChild = currentChild
                    }
                    children.add(child)

                }
            }
            currentGeneration = replacer.replace(parents, children).toMutableList()
            generation++

        }
    }
}