package pacman.gameplay;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import pacman.engine.core.KeyboardInput;
import pacman.engine.core.Map.Map;
import pacman.engine.graphism.ResizableCanvas;
import pacman.engine.graphism.Sprite;
import pacman.gameplay.pacman.Pacman;

public class Game {

    private Pacman pacman;
    private KeyboardInput kI;
    private ResizableCanvas canvas;
    private Map labyrynth;

    public Game(GridPane root){
        int labX = 100;
        int labY = 100;
        canvas = new ResizableCanvas(labX, labY, 800, 875);
        root.getChildren().add(canvas);
        labyrynth = new Map(100,100);

        pacman = new Pacman(3);
        pacman.spawn();
        canvas.addDrawingElement(pacman.getSprite());

        //TODO add a map caller to add entity
        kI = new KeyboardInput(root.getScene());
        root.widthProperty().addListener(evtW ->{
            canvas.setWidth(root.getWidth());
        });
        root.heightProperty().addListener(evtH ->{
            canvas.setHeight(root.getHeight());
        });
    }

    public void gameLoop(){
        gameUpdate();
        graphicalUpdate();
    }

    private void gameUpdate(){
        //TODO make that better
        Sprite lastSprite = pacman.getSprite();
        KeyCode lastKeyPressed = kI.getLastKeyPressed();
        if(pacman.setCurrentDir(lastKeyPressed)){
            canvas.removeDrawingElement(lastSprite);
            canvas.addDrawingElement(pacman.getSprite());
        }

        pacman.move();

    }

    private void graphicalUpdate(){
        canvas.clear();
        canvas.draw();
    }

}
