package pacman.GUI.inGameGUI;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import pacman.gameplay.Game;
import pacman.gameplay.GameEvent;


import java.util.HashMap;

public class MainGameGUI {

    private AnchorPane gameRoot = new AnchorPane();
    private GridPane leftGrid = new GridPane();
    private StackPane rightGrid = new StackPane();
    private Pane middleElem = new Pane();
    private ListView powerUpsList = new ListView();

    private Text scoreDisplay = new Text();
    private Text lifeDisplay = new Text();
    private Game currentGame;
    private int mapId;
    private Timeline gameLoop;

    private HashMap<String, PowerUpGUI> powerUpsGUI = new HashMap<>();

    public static Node eventHandler = new Node() {};


    public MainGameGUI(int mapId) {
        GridPane.setHgrow(gameRoot, Priority.ALWAYS);
        GridPane.setVgrow(gameRoot, Priority.ALWAYS);
        gameRoot.getChildren().addAll(rightGrid, leftGrid, middleElem);

        gameRoot.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));


        BorderPane.setAlignment(middleElem, Pos.CENTER);

        scoreDisplay.setText("Score: 0");
        lifeDisplay.setText("vies: 0");
        scoreDisplay.setFont(Font.font("Verdana", 30));
        lifeDisplay.setFont(Font.font("Verdana", 30));
        leftGrid.add(lifeDisplay, 0, 0);
        leftGrid.add(scoreDisplay, 0, 1);

        powerUpsList.getStylesheets().add("file:css/listView.css");
        leftGrid.add(powerUpsList, 0, 2);

        System.out.println(powerUpsList.getCssMetaData());


        this.mapId = mapId;

        /*event handlers*/
        eventHandler.addEventHandler(GameEvent.GAME_SCORE_UPDATED, event -> updateScoreDisplay());
        eventHandler.addEventHandler(GameEvent.GAME_LIFE_UPDATED, event -> updateLifesDisplay());

        eventHandler.addEventHandler(GameEvent.GAME_POWERUP_READY, event -> showPUEvent(event.getAgrs(), "PR"));
        eventHandler.addEventHandler(GameEvent.GAME_POWERUP_USED, event -> showPUEvent(event.getAgrs(), "PU"));
        eventHandler.addEventHandler(GameEvent.GAME_POWERUP_IN_COOLDOWN, event -> showPUEvent(event.getAgrs(), "PICD"));


    }

    private void showPUEvent(String[] args, String event){
        if(args.length<1) return;
        if(powerUpsGUI.get(args[0]) == null){
            powerUpsGUI.put(args[0], new PowerUpGUI(args[0]));
            powerUpsList.getItems().add(powerUpsGUI.get(args[0]).getPowerUpDisplay());
        }
        PowerUpGUI currentPowerUp = powerUpsGUI.get(args[0]);
        switch (event){
            case "PR":
                currentPowerUp.setCooldown(-1);
                currentPowerUp.setDuration(-1);
                powerUpsList.getItems().remove(powerUpsGUI.get(args[0]).getPowerUpDisplay());
                powerUpsGUI.remove(args[0]);
                break;
            case "PU":
                if(args.length<2) return;
                currentPowerUp.setDuration(Double.parseDouble(args[1]));
                break;
            case "PICD":
                if(args.length<2) return;
                currentPowerUp.setCooldown(Double.parseDouble(args[1]));
                currentPowerUp.setDuration(0.0);
                break;
        }
    }

    public void launch(){
        gameLoop = new Timeline();
        gameLoop.setCycleCount( Timeline.INDEFINITE );

        currentGame = new Game(middleElem, Integer.toString(mapId));
        updateLifesDisplay();

        Platform.runLater(() -> {
            double size = Math.min(gameRoot.getHeight(), gameRoot.getWidth());
            double sideX = (gameRoot.getWidth()-size)/2;
            double sideY = (gameRoot.getHeight()-size)/2;

            AnchorPane.setLeftAnchor(leftGrid, 0.0);
            AnchorPane.setRightAnchor(leftGrid, gameRoot.getWidth()-sideX);
            AnchorPane.setTopAnchor(leftGrid, 0.0);
            AnchorPane.setBottomAnchor(leftGrid, 0.0);

            AnchorPane.setLeftAnchor(rightGrid, gameRoot.getWidth()-sideX);
            AnchorPane.setRightAnchor(rightGrid, 0.0);
            AnchorPane.setTopAnchor(rightGrid, 0.0);
            AnchorPane.setBottomAnchor(rightGrid, 0.0);

            AnchorPane.setLeftAnchor(middleElem, sideX);
            AnchorPane.setRightAnchor(middleElem, sideX);
            AnchorPane.setTopAnchor(middleElem, sideY);
            AnchorPane.setBottomAnchor(middleElem, sideY);

            currentGame.getCanvas().myResize(size, size);
            middleElem.getChildren().add(currentGame.getCanvas());
        });

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),                // 60 FPS
                ae -> {
                    currentGame.gameLoop();
                });
        gameLoop.getKeyFrames().add( kf );



        gameLoop.play();


    }

    public void stop(){
        gameLoop.stop();
    }

    public AnchorPane getCurrentGUI(){
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
