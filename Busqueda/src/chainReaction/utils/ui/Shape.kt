package chainReaction.utils.ui

import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color

enum class Shape {
    LINER {
        override fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext) {
            gc.stroke = ColorPicker.getColor(c)
            gc.strokeLine(x * 50 + 45.0, y * 50 + 5.0, x * 50 + 5.0, y * 50 + 45.0)
        }
    },
    STAR {
        override fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext) {
            gc.fill = ColorPicker.getColor(c)
            val left = x*50.0 + 5
            val top = y*50.0 + 5
            val step = 40.0/4

            val xs = doubleArrayOf(left, left+1.5*step, left+2*step, left+2.5*step, left+4*step, left+2.5*step, left+2*step, left+1.5*step)
            val ys = doubleArrayOf(top+2*step, top+step, top, top+step, top+2*step, top+3*step, top+4*step, top+3*step)
            gc.fillPolygon(xs,ys,8)
        }
    }, TRIANGLEUF {
        override fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext) {
            gc.fill = ColorPicker.getColor(c)
            val left = x*50.0 + 5
            val top = y*50.0 + 5
            val size = 40.0
            val xs = doubleArrayOf(left, left+size/2, left+size)
            val ys = doubleArrayOf(top+size, top, top+size)
            gc.fillPolygon(xs,ys,3)
        }
    },
     CIRCLEF {
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
    }, LINEL {
        override fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext) {
            gc.stroke = ColorPicker.getColor(c)
            gc.strokeLine(x * 50 + 5.0, y * 50 + 5.0, x * 50 + 45.0, y * 50 + 45.0)
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
    };

    abstract fun draw(x: Int, y: Int, c: Int, gc: GraphicsContext)

    fun touch(i: Int, j: Int, shapeColor: Int, gc: GraphicsContext){
        backGround(j, i, Color.BLACK, gc)
        this.draw(j, i, shapeColor, gc)
    }

    fun unTouch(i: Int, j: Int, shapeColor: Int, gc: GraphicsContext){
        backGround(j, i, Color.WHITE, gc)
        this.draw(j, i, shapeColor, gc)
    }

    fun selected(i: Int, j: Int, shapeColor: Int, gc: GraphicsContext){
        backGround(j, i, Color.RED, gc)
        this.draw(j, i, shapeColor, gc)
    }

    companion object {
        fun from(i: Int) = Shape.values().filter { it.ordinal == i}.first()
    }

    private fun backGround(x: Int, y: Int, backgroundColor: Color, gc: GraphicsContext){
        gc.setFill(backgroundColor)
        gc.fillRect(x * 50.0, y * 50.0, 50.0, 50.0)
    }
}