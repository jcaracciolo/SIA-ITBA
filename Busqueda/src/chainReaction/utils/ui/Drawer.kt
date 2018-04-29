package chainReaction.utils.ui

import chainReaction.game.CRBoard
import chainReaction.game.CRState
import chainReaction.heuristics.CloserHeuristic
import chainReaction.heuristics.ComposeHeuristic
import chainReaction.heuristics.NeighbourFilter
import chainReaction.heuristics.NeighboursHeuristic
import chainReaction.utils.CRParser
import engine.Engine
import engine.StateProcessor
import engine.searchers.AStar
import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.stage.Stage
import javafx.animation.AnimationTimer
import javafx.scene.canvas.GraphicsContext


class Drawer : Application(), StateProcessor<CRState>{
    lateinit var primaryStage: Stage
    lateinit var gc: GraphicsContext
    val path = "./test/resources/here20x20"
    val engine =  Engine<CRState>()
    val heuristic = NeighbourFilter(ComposeHeuristic.composite(CloserHeuristic(), NeighboursHeuristic()))

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
        this.primaryStage.title = "Chain Reaction"

        val problem = CRParser.parseBoard(path)!!
        this.init(50.0*problem.board.rows,50.0*problem.board.cols)


        val engine = Engine<CRState>()

        Thread({ engine.solve(problem, AStar(heuristic), this) }).start()

        object : AnimationTimer() {
            override fun handle(currentNanoTime: Long) {
                engine.externalCurNode?.let { Drawer.app.print(it.state) }
            }
        }.start()

        primaryStage.show()
    }

    fun init(height: Double, width: Double) {
        val root = Group()
        val canvas = Canvas(width, height)
        gc = canvas.graphicsContext2D
        root.children.add(canvas)
        primaryStage.setScene(Scene(root))
        primaryStage.show()
        gc.lineWidth = 5.0
    }

    override fun proces(state: CRState) {
        Thread.sleep(1000)
    }

    fun print(state: CRState) {
        val board = state.game.board

        (0 until board.rows).forEach { i->
            (0 until board.cols).forEach { j ->
                if (board.shapes[i, j] < CRBoard.EMPTY) {
                    val shape = Shape.from(board.shapes[i, j].toInt())
                    if (state.touched[i, j]) {
                        shape.touch(i, j, board.colors[i, j].toInt(), gc)
                    } else {
                        shape.unTouch(i, j, board.colors[i, j].toInt(), gc)
                    }
                }
            }
        }

    }
}