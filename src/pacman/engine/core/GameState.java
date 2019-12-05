package pacman.engine.core;

import javafx.scene.Node;
import pacman.engine.core.Map.Map;
import pacman.engine.graphism.ResizableCanvas;

public class GameState {
    private static GameState INSTANCE = new GameState();

    private Map currMap = null;
    private KeyboardInput kI = null;
    private ResizableCanvas canvas = null;
    private Node root;
    private String pseudo = null;

    private GameState(){}
    public static GameState getInstance(){
        return INSTANCE;
    }

    public Map getCurrMap() {
        return currMap;
    }

    public void setCurrMap(Map currMap) {
        this.currMap = currMap;
    }

    public KeyboardInput getkI() {
        return kI;
    }

    public void setkI(KeyboardInput kI) {
        this.kI = kI;
    }

    public ResizableCanvas getCanvas() {
        return canvas;
    }

    public void setCanvas(ResizableCanvas canvas) {
        this.canvas = canvas;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void setPseudo(String text) {
        pseudo = text;
    }
    public String getPseudo()
    {
        return pseudo;
    }
}
