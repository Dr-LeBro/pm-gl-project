package pacman;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import pacman.GUI.menu.MainMenu;


public class Main extends Application {

    static public GridPane root;

    @Override
    public void start(Stage primaryStage){
        root = new GridPane();
        primaryStage.setTitle("DARK PACMAN");
        primaryStage.setScene(new Scene(root, 800, 875));

        MainMenu menu = new MainMenu(root);
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);

    }
}
