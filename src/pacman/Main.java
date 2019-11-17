package pacman;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pacman.engine.graphism.ImageManager;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane root = new GridPane();
        Canvas test = new Canvas();
        root.getChildren().add(test);
        test.setWidth(800);
        test.setHeight(875);
        GraphicsContext gc = test.getGraphicsContext2D();
        System.out.println(System.getProperties().get("user.dir"));
        ImageManager sprite = new ImageManager("file:yellow.png");
        sprite.setHeight(100);
        sprite.setWidth(100);
        sprite.loadImage();
        gc.drawImage(sprite.getImage(), 0, 0);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 875));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
