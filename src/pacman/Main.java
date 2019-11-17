package pacman;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pacman.gameplay.Game;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        GridPane root = new GridPane();
        primaryStage.setTitle("DARK PACMAN");
        primaryStage.setScene(new Scene(root, 800, 875));
        primaryStage.show();
        Game game = new Game(root);
        game.gameLoop();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
