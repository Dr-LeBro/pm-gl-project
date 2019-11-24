package pacman.GUI.menu;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import pacman.engine.graphism.GUIElements.ButtonManager;
import pacman.engine.graphism.GUIElements.SliderManager;
import pacman.gameplay.Game;
import pacman.gameplay.GameEvent;

public class SecondaryMenuSingle extends SecondaryMenu {
    private ObservableList<Node> menuMemory;
    private int mapId;
    SliderManager mapChooser;

    public SecondaryMenuSingle(GridPane root) {
        super(root);

        ButtonManager launchGameSingle = new ButtonManager("Launch", actionEvent -> launchGame());
        ChangeListener<Number> selectMapIdListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                selectMapId();
            }
        };
        mapChooser = new SliderManager(selectMapIdListener, 0, 10, 1);
        rootOfMenu.add(launchGameSingle.getComponent(), 0, 0);
        rootOfMenu.add(mapChooser.getComponent(), 0, 1);
    }


    public void selectMapId(){
        System.out.println(mapChooser.getValue());
        mapId = mapChooser.getValue();
    }

    public void launchGame(){

        Timeline gameLoop = new Timeline();
        //TODO trouver un moyen d'arreter la boucle
        gameLoop.setCycleCount( Timeline.INDEFINITE );

        menuMemory = FXCollections.observableArrayList(mainRoot.getChildren()) ;
        mainRoot.getChildren().clear();
        Game game = new Game(mainRoot);
        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),                // 60 FPS
                ae -> {
                    game.gameLoop();
                });
        gameLoop.getKeyFrames().add( kf );

        mainRoot.addEventHandler(GameEvent.GAME_END, event -> {
            System.out.println("Fini");
            mainRoot.getChildren().clear();
            mainRoot.getChildren().addAll(menuMemory);
            game.removeHandlers();
        });

        gameLoop.play();


    }
}
