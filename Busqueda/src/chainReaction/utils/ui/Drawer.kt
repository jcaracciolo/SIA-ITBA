package chainReaction.utils.ui

import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.stage.Stage

class Drawer : Application() {

    override fun start(primaryStage: Stage) {
        primaryStage.title = "Drawing Operations Test"
        val root = Group()
        val canvas = Canvas(300.0, 250.0)
        val gc = canvas.graphicsContext2D
        drawShapes(gc)
        root.children.add(canvas)
        primaryStage.scene = Scene(root)
        primaryStage.show()
        gc.lineWidth = 5.0
    }

    private fun drawShapes(gc: GraphicsContext) {
        val s1 = Shape.CIRCLES
        val s2 = Shape.CIRCLEF
        val s3 = Shape.SQUAREF
        val s4 = Shape.SQUARES
        s1.draw(0, 0, 1, gc)
        s2.draw(0, 1, 3, gc)
        s3.draw(1, 0, 2, gc)
        s4.draw(1, 1, 4, gc)
        s1.touch(0, 0, 1, gc)
        s3.touch(1, 0, 1, gc)

    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            Application.launch(Drawer::class.java, *args)
        }
    }
}