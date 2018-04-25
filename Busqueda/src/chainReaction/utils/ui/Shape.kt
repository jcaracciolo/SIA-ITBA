package chainReaction.utils.ui

import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color

enum class Shape {
    LINER {
        override fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext) {
            gc.stroke = ColorPicker.getColor(c)
            gc.strokeLine(x * 50 + 45.0, y * 50 + 5.0, x * 50 + 5.0, y * 50 + 45.0)
        }
    }, LINEL {
        override fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext) {
            gc.stroke = ColorPicker.getColor(c)
            gc.strokeLine(x * 50 + 5.0, y * 50 + 5.0, x * 50 + 45.0, y * 50 + 45.0)
        }
    }, CIRCLEF {
        override fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext) {
            gc.fill = ColorPicker.getColor(c)
            gc.fillOval(x * 50 + 5.0, y * 50 + 5.0, 40.0, 40.0)
        }
    }, SQUAREF {
        override fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext) {
            gc.fill = ColorPicker.getColor(c)
            gc.fillRect(x * 50 + 5.0, y * 50 + 5.0, 40.0, 40.0)
        }
    }, RECTVERF {
        override fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext) {
            gc.fill = ColorPicker.getColor(c)
            gc.fillRect(x * 50 + 15.0, y * 50 + 5.0, 20.0, 40.0)
        }
    }, RECTHORF {
        override fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext) {
            gc.fill = ColorPicker.getColor(c)
            gc.fillRect(x * 50 + 5.0, y * 50 + 15.0, 40.0, 20.0)
        }
    }, TRIANGLEUF {
        override fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }, TRIANGLEDF {
        override fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }, TRIANGLELF {
        override fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }, TRIANGLERF {
        override fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }, DIAMONDF {
        override fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }, OVALVERF {
        override fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext) {
            gc.fill = ColorPicker.getColor(c)
            gc.fillOval(x * 50 + 25.0, y * 50 + 25.0, 10.0, 20.0)
        }
    }, OVALHORF {
        override fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext) {
            gc.fill = ColorPicker.getColor(c)
            gc.fillOval(x * 50 + 25.0, y * 50 + 25.0, 20.0, 10.0)
        }
    }, POLYGONF {
        override fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext) {
            gc.fillPolygon(doubleArrayOf(x * 50 + x * 50 + 45.0, x * 50 + 5.0, x * 50 + 45.0),
                    doubleArrayOf(y * 50 + 5.0, y * 50 + 5.0, y * 50 + 45.0, y * 50 + 45.0), 4)
        }
    }, CIRCLES {
        override fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext) {
            gc.stroke = ColorPicker.getColor(c)
            gc.strokeOval(x * 50 + 5.0, y * 50 + 5.0, 40.0, 40.0)
        }
    }, SQUARES {
        override fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext) {
            gc.stroke = ColorPicker.getColor(c)
            gc.strokeRect(x * 50 + 5.0, y * 50 + 5.0, 40.0, 40.0)
        }
    }, RECTVERS {
        override fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext) {
            gc.stroke = ColorPicker.getColor(c)
            gc.strokeRect(x * 50 + 15.0, y * 50 + 5.0, 20.0, 40.0)
        }
    }, RECTHORS {
        override fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext) {
            gc.stroke = ColorPicker.getColor(c)
            gc.strokeRect(x * 50 + 5.0, y * 50 + 15.0, 40.0, 20.0)
        }
    }, TRIANGLEUS {
        override fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }, TRIANGLEDS {
        override fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }, TRIANGLELS {
        override fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }, TRIANGLERS {
        override fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }, DIAMONDS {
        override fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }, OVALVERS {
        override fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext) {
            gc.stroke = ColorPicker.getColor(c)
            gc.strokeOval(x * 50 + 25.0, y * 50 + 25.0, 10.0, 20.0)
        }
    }, OVALHORS {
        override fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext) {
            gc.stroke = ColorPicker.getColor(c)
            gc.strokeOval(x * 50 + 25.0, y * 50 + 25.0, 20.0, 10.0)
        }
    }, POLYGONS {
        override fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext) {
            gc.strokePolygon(doubleArrayOf(x * 50 + x * 50 + 45.0, x * 50 + 5.0, x * 50 + 45.0),
                    doubleArrayOf(y * 50 + 5.0, y * 50 + 5.0, y * 50 + 45.0, y * 50 + 45.0), 4)
        }
    };

    abstract fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext)

    fun touch(x: Int, y: Int, shapeColor: Int, gc: GraphicsContext){
        backGround(x, y, Color.BLACK, gc)
        this.draw(x, y, shapeColor, gc)
    }

    fun unTouch(x: Int, y: Int, shapeColor: Int, gc: GraphicsContext){
        backGround(x, y, Color.WHITE, gc)
        this.draw(x, y, shapeColor, gc)
    }

    private fun backGround(x: Int, y: Int, backgroundColor: Color, gc: GraphicsContext){
        gc.setFill(backgroundColor)
        gc.fillRect(x * 50.0, y * 50.0, 50.0, 50.0)
    }
}