package ar.edu.itba.sia.Engine

import ar.edu.itba.sia.evolutionable.Evolutionable
import ar.edu.itba.sia.utils.ConfigurationFile
import java.util.*
import kotlin.collections.ArrayList

class Engine<G> {

    fun NaturalSelection(configurationFile: ConfigurationFile){
        val crosser = configurationFile.crosser
        val mutator = configurationFile.mutator
        val genMutator = configurationFile.genMutator
        val cutter = configurationFile.cutter
        val replacer = configurationFile.replacer
        val selector = configurationFile.selector
        val generationSize = configurationFile.generationSize

        var generation = 0
        var currentGeneration: MutableList<Evolutionable<G>> = ArrayList()

        var children: MutableList<Evolutionable<G>>
        var parents: List<Evolutionable<G>>
        var greatestChild: Evolutionable<G>
        var currentChild: Evolutionable<G>

        for (i in 0 until generationSize){
            //TODO: Create first generation
        }

        greatestChild = currentGeneration.maxBy { it.getPerformance() }!!

        while(!cutter.shouldCut(currentGeneration)) {
            parents = replacer.parentsToCross(selector)
            children = ArrayList()

            while(children.size < parents.size) {
                val fatherIndex = Random().nextInt(parents.size)
                val motherIndex = Random().nextInt(parents.size)

                for (child : Evolutionable<G> in crosser.crossOver(parents[fatherIndex], parents[motherIndex])){
                    currentChild = mutator.mutate(child, generation, genMutator)
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