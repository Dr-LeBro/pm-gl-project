package pacman.GUI.menu;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import pacman.engine.graphism.GUIElements.ButtonManager;
import pacman.gameplay.Game;

public class MainMenu {
    GridPane mainRoot;
    GridPane rootOfMenu;

    public MainMenu(GridPane root){
        mainRoot = root;
        rootOfMenu = new GridPane();
        ButtonManager launchGameB = new ButtonManager("Lancer", (EventHandler<ActionEvent>) actionEvent -> {
            System.out.println("test");
            launchGame();
        });
        rootOfMenu.getChildren().add(launchGameB.getButton());
        mainRoot.getChildren().add(rootOfMenu);



    }

    public void launchGame(){
        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount( Timeline.INDEFINITE );

        Game game = new Game(rootOfMenu);
        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),                // 60 FPS
                ae -> game.gameLoop());

        gameLoop.getKeyFrames().add( kf );
        gameLoop.play();
    }


}
