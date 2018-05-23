package ar.edu.itba.sia.Engine.selector

import ar.edu.itba.sia.evolutionable.Evolutionable

class UniversalSelector : Selector {
    override fun <G> select(generation: List<Evolutionable<G>>, amount: Int): List<Evolutionable<G>> {
        val accumulative = Array(generation.size, { 0.0 })
        accumulative[0] = generation[0].getPerformance()

        for (i in 1 until generation.size) {
            accumulative[i] += generation[i].getPerformance() + accumulative[i - 1]
        }
        val randoms = Array(amount, {index -> ((Math.random() + index - 1 ) / amount)})
        var selected = ArrayList<Evolutionable<G>>(0)

        for (i in 0 until amount) {
            selected.add(generation[getSelectedIndex(randoms[i], accumulative)])
        }

        return selected
    }

    private fun getSelectedIndex(rand: Double, accumulative: Array<Double>): Int {
        for (i in 0 until accumulative.size) {
            if (rand <= accumulative[i]) return i
        }
        return accumulative.size - 1
    }
}