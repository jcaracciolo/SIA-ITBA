package chainReaction.utils.ui

import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.stage.Stage

class Drawer : Application(){
    var root = Group()
    var canvas = Canvas(300.0, 250.0)
    var gc = canvas.graphicsContext2D
    var primaryStage : Stage = null!!

    override fun start(primaryStage: Stage?) {
        this.primaryStage = primaryStage!!
        this.primaryStage.setTitle("Chain Reaction")

    }

    fun init(height: Double, width: Double){
        root = Group()
        canvas = Canvas(width, height)
        gc = canvas.graphicsContext2D
        root.children.add(canvas)
        primaryStage.setScene(Scene(root))
        primaryStage.show()
        gc.lineWidth = 5.0
    }
}