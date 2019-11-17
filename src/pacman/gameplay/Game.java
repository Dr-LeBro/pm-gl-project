package pacman.gameplay;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import pacman.engine.core.KeyboardInput;
import pacman.engine.core.Map.Map;
import pacman.engine.graphism.ResizableCanvas;
import pacman.gameplay.pacman.Pacman;

public class Game {

    private Pacman pacman;
    private KeyboardInput kI;
    private ResizableCanvas canvas;
    private Map labyrynth;
    public static double ratioX;
    public static double ratioY;

    public Game(GridPane root){
        canvas = new ResizableCanvas();
        root.getChildren().add(canvas);
        canvas.setWidth(800);
        canvas.setHeight(875);
        labyrynth = new Map(100,100);
        //TODO: make ratio dynamical, and move it to core engine
        ratioX = canvas.getWidth() / 100;
        ratioY = canvas.getHeight() / 100;
        pacman = new Pacman(3);
        pacman.spawn();
        canvas.addDrawingElement(pacman.getSprite());
        //TODO add a map caller to add entity
        kI = new KeyboardInput(root.getScene());
    }

    public void gameLoop(){
        gameUpdate();
        graphicalUpdate();
    }

    private void gameUpdate(){
        //TODO make that better
        KeyCode lastKeyPressed = kI.getLastKeyPressed();
        canvas.removeDrawingElement(pacman.getSprite());
        pacman.setCurrentDir(lastKeyPressed);
        canvas.addDrawingElement(pacman.getSprite());
        pacman.move();

    }

    private void graphicalUpdate(){
        canvas.clear();
        canvas.draw();
    }

}
