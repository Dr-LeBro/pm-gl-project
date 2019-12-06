package pacman;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import pacman.GUI.menu.MainMenu;

/**
 * Main of program
 * Launch graphical interface
 */
public class Main extends Application {

    static public GridPane root;
    static public Stage primaryStage;

    /**
     * launch graphical interface
     * @param primaryStage main stage
     */
    @Override
    public void start(Stage primaryStage){
        Main.primaryStage = primaryStage;
        primaryStage.resizableProperty().setValue(false); //block resize
        root = new GridPane(); //set root
        primaryStage.setTitle("DARK PACMAN"); //Title of primary stage
        primaryStage.setScene(new Scene(root, 848, 480)); //set scene with values

        new MainMenu(root);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
