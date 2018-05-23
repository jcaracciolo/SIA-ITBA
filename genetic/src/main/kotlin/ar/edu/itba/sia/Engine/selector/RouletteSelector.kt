package ar.edu.itba.sia.Engine.selector

import ar.edu.itba.sia.evolutionable.Evolutionable
import kotlin.collections.ArrayList

class RouletteSelector : Selector {
    override fun select(generation: List<Evolutionable>, amount: Int): List<Evolutionable> {
        val accumulative = Array(generation.size, {0.0})
        accumulative[0] = generation[0].getPerformance()

        for (i in 1 until generation.size){
            accumulative[i] += generation[i].getPerformance() + accumulative[i-1]
        }

        val randoms = Array(amount, {Math.random() * accumulative[generation.size - 1]})
        var selected = ArrayList<Evolutionable>(0)

        for (i in 0 until amount){
            selected.add(generation[getSelectedIndex(randoms[i], accumulative)])
        }

        return selected
    }

    private fun getSelectedIndex(rand: Double, accumulative: Array<Double>): Int {
        for (i in 0 until accumulative.size){
            if(rand <= accumulative[i]) return i
        }
        return accumulative.size - 1
    }
}