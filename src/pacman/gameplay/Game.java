package pacman.gameplay;

import javafx.scene.layout.GridPane;
import pacman.engine.core.KeyboardInput;
import pacman.engine.graphism.ResizableCanvas;
import pacman.gameplay.pacman.Pacman;

public class Game {

    Pacman pacman;
    KeyboardInput kI;
    ResizableCanvas canvas;

    public Game(GridPane root){
        ResizableCanvas canvas = new ResizableCanvas();
        root.getChildren().add(canvas);
        canvas.setWidth(800);
        canvas.setHeight(875);

        pacman = new Pacman(3);
        kI = new KeyboardInput(root.getScene());
    }

    public void gameLoop(){

    }

}
