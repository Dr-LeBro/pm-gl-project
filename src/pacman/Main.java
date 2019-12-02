package pacman;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import pacman.GUI.menu.MainMenu;


public class Main extends Application {

    static public GridPane root;
    static public Stage primaryStage;

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        primaryStage.resizableProperty().setValue(false);
        root = new GridPane();
        primaryStage.setTitle("DARK PACMAN");
        primaryStage.setScene(new Scene(root, 848, 480));

        MainMenu menu = new MainMenu(root);
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);

    }
}
