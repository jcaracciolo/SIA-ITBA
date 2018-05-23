package ar.edu.itba.sia.visuals

import ar.edu.itba.sia.Armory
import ar.edu.itba.sia.Engine.Engine
import ar.edu.itba.sia.evolutionable.Evolutionable
import ar.edu.itba.sia.evolutionable.characters.Assassin
import ar.edu.itba.sia.evolutionable.characters.Character
import javafx.animation.AnimationTimer
import javafx.application.Application
import javafx.geometry.Pos
import javafx.scene.Group
import javafx.scene.Node
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.stage.Stage
import java.util.Collections.addAll
import javafx.scene.chart.XYChart
import javafx.scene.chart.LineChart
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.CategoryAxis
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.GridPane
import javafx.scene.layout.VBox
import javafx.scene.layout.BorderPane
import javafx.scene.paint.Color
import javafx.scene.text.Font
import java.lang.Thread.sleep


class Drawer : Application() {

    lateinit var primaryStage: Stage
    lateinit var bPane: BorderPane

    lateinit var seriesAvrg: XYChart.Series<Number, Number>
    lateinit var seriesMax: XYChart.Series<Number, Number>

    lateinit var leftGC: GraphicsContext
    lateinit var rightGC: GraphicsContext
    lateinit var centerGC: GraphicsContext

    var index = 0

    val refreshRateMS: Long = 1000/20

    companion object {

        lateinit var app: Drawer

        @JvmStatic
        fun main(args: Array<String>) {
            Application.launch(Drawer::class.java, *args)
        }
    }

    init {
        Drawer.app = this
    }

    override fun start(primaryStage: Stage?) {
        Armory.initialze("./src/Resources/testdata")
        this.primaryStage = primaryStage!!
        this.primaryStage.title = "Evolutionable"

        this.init(600.0,1800.0)

        object : AnimationTimer() {
            override fun handle(currentNanoTime: Long) {

                Engine.currentGen = (0 until 10).map{ Assassin.random() }


                if(currentNanoTime%100 == 0L) {
                    Engine.generations++
                }

                val currentGen = Engine.currentGen as List<Character>
                val generations = Engine.generations

                val character = currentGen.maxBy { it.getPerformance() } as Character?

                character?.let {
                    if(index<generations) {
                        drawHeight(it)
                        drawValues(it, generations, currentGen)
                        seriesMax.addData(index, it.getPerformance())
                        seriesAvrg.addData(index, currentGen.map { it.getPerformance() }.average())
                        index++
                    }
                }

                return
            }
        }.start()

        primaryStage.show()
    }

    fun init(height: Double, width: Double) {
        val canvasCenter = Canvas(width/3, height)
        centerGC = canvasCenter.graphicsContext2D

        val canvasLeft = Canvas(width/3, height)
        leftGC = canvasLeft.graphicsContext2D

        val canvasRight = Canvas(width/3, height)
        rightGC = canvasRight.graphicsContext2D
        //Instantiating the BorderPane class
        val bPane = BorderPane()

        //Setting the top, bottom, center, right and left nodes to the pane
        bPane.center = canvasCenter
        bPane.left = canvasLeft
        bPane.right = canvasRight

        centerGC.fill = Color.BLACK
        centerGC.fillRect(0.0,0.0,width/3,height)

        this.bPane = bPane
        drawChart()
        primaryStage.setScene(Scene(bPane))

        primaryStage.show()
    }

    fun drawHeight(character: Character) {
        val height = centerGC.canvas.height - 10
        val width = centerGC.canvas.width

        centerGC.clearRect(0.0,0.0, centerGC.canvas.height, width)

        centerGC.fill = Color.BLACK
        centerGC.font = Font.font(15.0)
        centerGC.fillText("Height: ${"%.2f".format(character.height)}",width/2 - 50,100.0)

        val humanWidth = width/5
        val humanHeight = height*0.8 * (character.height / 2.0)
        val p1 = width/2 - humanWidth/2
        val p2 = height - humanHeight

        centerGC.fill = Color.PINK
        centerGC.fillRect(p1,p2,humanWidth,humanHeight)
    }

    fun drawValues(best: Character, generations: Int, generation: List<Character>) {
        leftGC.clearRect(0.0,0.0, leftGC.canvas.height, leftGC.canvas.width)

        leftGC.fill = Color.BLACK
        leftGC.font = Font.font(30.0)
        val base = 100.0
        val spacing = 100.0
        leftGC.fillText("Type: ${best.characterClass.string.capitalize()}", 100.0,base)
        leftGC.fillText("Generation: $generations", 100.0, base*2)
        leftGC.fillText("MaxPerformance: ${"%.2f".format(best.getPerformance())}", 100.0,base*3)
        leftGC.fillText("AvrgPerformance: ${"%.2f".format(generation.map{it.getPerformance()}.average())}", 100.0, base*4)

    }

    fun drawChart() {
        val xAxis = NumberAxis()
        val yAxis = NumberAxis()
        xAxis.label = "Generations"
        val lineChart = LineChart(xAxis, yAxis)

        lineChart.title = "Average and Max performance"

        seriesAvrg = XYChart.Series<Number, Number>()
        seriesAvrg.name = "Average"

        seriesMax = XYChart.Series<Number, Number>()
        seriesMax.name = "Max"

        lineChart.data.addAll(seriesAvrg, seriesMax)
        bPane.bottom = lineChart
    }

}

fun XYChart.Series<Number, Number>.addData(x: Number, y: Number) =
    this.data.add(XYChart.Data(x,y))
