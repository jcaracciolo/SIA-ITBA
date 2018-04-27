//package chainReaction.utils.ui;
//
//import javafx.application.Application;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.canvas.Canvas;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.ArcType;
//import javafx.stage.Stage;
//import sun.security.provider.SHA;
//
//public class Drawer extends Application {
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Drawing Operations Test");
//        Group root = new Group();
//        Canvas canvas = new Canvas(300, 250);
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        drawShapes(gc);
//        root.getChildren().add(canvas);
//        primaryStage.setScene(new Scene(root));
//        primaryStage.show();
//        gc.setLineWidth(5);
//    }
//
//    private void drawShapes(GraphicsContext gc) {
//        Shape s1 = Shape.CIRCLES;
//        Shape s2 = Shape.CIRCLEF;
//        Shape s3 = Shape.SQUAREF;
//        Shape s4 = Shape.SQUARES;
//        s1.draw(0,0,1,gc);
//        s2.draw(0,1,3,gc);
//        s3.draw(1,0,2,gc);
//        s4.draw(1,1,4,gc);
//        s1.touch(0, 0, 1, gc);
//        s3.touch(1, 0, 1, gc);
//
//    }
//}