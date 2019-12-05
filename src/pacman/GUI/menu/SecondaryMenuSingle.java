package pacman.GUI.menu;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import pacman.GUI.inGameGUI.MainGameGUI;
import pacman.engine.core.GameState;
import pacman.engine.graphism.GUIElements.ButtonManager;
import pacman.engine.graphism.GUIElements.SliderManager;
import pacman.gameplay.GameEvent;
import pacman.gameplay.scoreManager.Score;
import pacman.gameplay.scoreManager.ScoreBoard;

/**
 * Extends of secondaryMenu
 * Show single
 */
public class SecondaryMenuSingle extends SecondaryMenu {
    private ObservableList<Node> menuMemory; //keep this menu in memory for keep up whil play
    private int mapId; //map ID
    private SliderManager mapChooser; //slider to choose id
    private Label mapIdDisplay = new Label(); //display of map ID
    private TextArea pseudoArea;//pseudo for the score

    /**
     * menu single constructor
     * @param root root of main menu
     */
    public SecondaryMenuSingle(GridPane root) {
        super(root);

        //display and select mapID
        ChangeListener<Number> selectMapIdListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                selectMapId();
                showMapId();
            }
        };

        /* set up game option and launch*/
        pseudoArea = new TextArea("default");
        pseudoArea.setPrefSize(100,10);
        ButtonManager launchGameSingle = new ButtonManager("Launch", actionEvent -> launchGame());
        mapChooser = new SliderManager(selectMapIdListener, 1, 10, 1); //slider
        mapId = 1; // Default value of the Slider
        mapIdDisplay.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        showMapId();

        /*add game option and set up to secondary menu */
        rootOfMenu.add(launchGameSingle.getComponent(), 0, 0);
        rootOfMenu.add(mapChooser.getComponent(), 0, 1);
        rootOfMenu.add(mapIdDisplay, 1, 1);
        rootOfMenu.add(pseudoArea, 0, 2);

    }

    /**
     * put slider value to map id
     */
    private void selectMapId(){
        mapId = mapChooser.getValue();
    }

    /**
     * show id of map on label
     */
    private void showMapId(){
        mapIdDisplay.setText("Map: " + mapChooser.getValue());
    }

    /**
     * Launch game
     */
    private void launchGame() {
        GameState.getInstance().setPseudo(pseudoArea.getText());
        menuMemory = FXCollections.observableArrayList(mainRoot.getChildren()); //keep menu in memory
        mainRoot.getChildren().clear(); //clear menu

        /*setup gameGUI*/
        MainGameGUI gameGui = new MainGameGUI(mapId);
        mainRoot.getChildren().add(gameGui.getCurrentGUI());
        gameGui.launch(); //launch GUI

        //handle event to GAME finish
        mainRoot.addEventHandler(GameEvent.GAME_END, event -> {
            System.out.println("Fini");
            mainRoot.getChildren().clear();
            mainRoot.getChildren().addAll(menuMemory);
            gameGui.getCurrentGame().removeHandlers();
            gameGui.stop();

            ScoreBoard.getInstance().saveScore(GameState.getInstance().getPseudo(), Score.getInstance().getScore());
            Score.getInstance().resetScore();
        });
    }
}
