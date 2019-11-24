package pacman;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import pacman.GUI.menu.MainMenu;
import pacman.gameplay.Game;

public class Main extends Application {

    static public GridPane root;

    @Override
    public void start(Stage primaryStage){
        GridPane root = new GridPane();
        primaryStage.setTitle("DARK PACMAN");
        primaryStage.setScene(new Scene(root, 800, 875));



        MainMenu menu = new MainMenu(root);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);

    }
}
