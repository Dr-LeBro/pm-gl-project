package pacman.GUI.inGameGUI;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.Duration;
import pacman.gameplay.Game;
import pacman.gameplay.GameEvent;

public class MainGameGUI {

    private GridPane gameRoot = new GridPane();
    private GridPane upGrid = new GridPane();
    private GridPane downGrid = new GridPane();
    private GridPane middleGrid = new GridPane();

    private Text scoreDisplay = new Text();
    private Text lifeDisplay = new Text();
    private Game currentGame;
    private int mapId;

    public static Node eventHandler = new Node() {};

    public MainGameGUI(int mapId) {
        scoreDisplay.setText("Score: 0");
        lifeDisplay.setText("vies: 0");
        gameRoot.add(upGrid,0,1);
        gameRoot.add(middleGrid,0,2);
        gameRoot.add(downGrid,0,3);

        upGrid.add(lifeDisplay, 0, 0);
        upGrid.add(scoreDisplay, 0, 1);
        this.mapId = mapId;

        eventHandler.addEventHandler(GameEvent.GAME_SCORE_UPDATED, event -> updateScoreDisplay());
        eventHandler.addEventHandler(GameEvent.GAME_LIFE_UPDATED, event -> updateLifesDisplay());

    }

    public void launch(){
        Timeline gameLoop = new Timeline();
        //TODO verifier le moyen d'arreter la boucle
        gameLoop.setCycleCount( Timeline.INDEFINITE );


        currentGame = new Game(middleGrid, Integer.toString(mapId));
        updateLifesDisplay();

        GridPane.setHgrow(gameRoot, Priority.ALWAYS);
        GridPane.setVgrow(gameRoot, Priority.ALWAYS);
        GridPane.setHgrow(middleGrid, Priority.ALWAYS);
        GridPane.setVgrow(middleGrid, Priority.ALWAYS);
        //GridPane.setHgrow(currentGame.getCanvas(), Priority.ALWAYS);
        //GridPane.setVgrow(currentGame.getCanvas(), Priority.ALWAYS);

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),                // 60 FPS
                ae -> {
                    currentGame.gameLoop();
                });
        gameLoop.getKeyFrames().add( kf );



        gameLoop.play();


    }

    public GridPane getCurrentGUI(){
        return gameRoot;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    private void updateScoreDisplay(){
        scoreDisplay.setText("Score: " + currentGame.getScore());
    }

    private void updateLifesDisplay(){
        lifeDisplay.setText("Vies: " + currentGame.getPacmanRemainingLifes());
    }
}