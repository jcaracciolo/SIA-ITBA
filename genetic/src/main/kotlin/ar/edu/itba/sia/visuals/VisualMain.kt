package ar.edu.itba.sia.visuals

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
        this.primaryStage = primaryStage!!
        this.primaryStage.title = "Evolutionable"

        this.init(600.0,1800.0)

        object : AnimationTimer() {
            override fun handle(currentNanoTime: Long) {
                if(currentNanoTime%100 == 0L) {
                    seriesMax.addData(index, Math.random() * 10)
                    seriesAvrg.addData(index, Math.random() * 10)
                    index++
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

        centerGC.fill = Color.RED
        centerGC.fillRect(0.0,0.0,500.0,500.0)

        leftGC.fill = Color.BLACK
        leftGC.fillRect(0.0,0.0,500.0,500.0)

        this.bPane = bPane
        drawChart()
        primaryStage.setScene(Scene(bPane))

        primaryStage.show()
    }

    fun drawChart() {
        val xAxis = NumberAxis()
        val yAxis = NumberAxis()
        xAxis.label = "Generations"
        val lineChart = LineChart(xAxis, yAxis)

        lineChart.title = "Stock Monitoring, 2010"

        seriesAvrg = XYChart.Series<Number, Number>()
        seriesAvrg.name = "Average"
        seriesAvrg.addData(0,10)
        seriesAvrg.addData(1,15)

        seriesMax = XYChart.Series<Number, Number>()
        seriesMax.name = "Average"
        seriesMax.addData(0,5)
        seriesMax.addData(1,8)

        index = 2


        lineChart.data.addAll(seriesAvrg, seriesMax)
        bPane.bottom = lineChart


    }

}

fun XYChart.Series<Number, Number>.addData(x: Number, y: Number) =
    this.data.add(XYChart.Data(x,y))
