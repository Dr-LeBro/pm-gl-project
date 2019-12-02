package pacman.GUI.menu;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import pacman.GUI.inGameGUI.MainGameGUI;
import pacman.engine.graphism.GUIElements.ButtonManager;
import pacman.engine.graphism.GUIElements.SliderManager;
import pacman.gameplay.GameEvent;

public class SecondaryMenuSingle extends SecondaryMenu {
    private ObservableList<Node> menuMemory;
    private int mapId;
    private SliderManager mapChooser;

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
        mapId = 1; // Default value of the Slider
    }


    public void selectMapId(){
        System.out.println(mapChooser.getValue());
        mapId = mapChooser.getValue();
    }

    public void launchGame() {
        menuMemory = FXCollections.observableArrayList(mainRoot.getChildren());
        mainRoot.getChildren().clear();
        MainGameGUI gameGui = new MainGameGUI(mapId);
        mainRoot.getChildren().add(gameGui.getCurrentGUI());
        gameGui.launch();
        mainRoot.addEventHandler(GameEvent.GAME_END, event -> {
            System.out.println("Fini");
            mainRoot.getChildren().clear();
            mainRoot.getChildren().addAll(menuMemory);
            gameGui.getCurrentGame().removeHandlers();
        });
    }
}
