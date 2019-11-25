package pacman.gameplay;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import pacman.engine.core.Block.Block;
import pacman.engine.core.KeyboardInput;
import pacman.engine.core.Map.Map;
import pacman.engine.graphism.ResizableCanvas;
import pacman.fileManager.LabyrinthFile;
import pacman.gameplay.pacman.Pacman;

import java.util.Iterator;

public class Game {

    private Pacman pacman;
    private KeyboardInput kI;
    private ResizableCanvas canvas;
    private Map labyrynth;
    private GridPane root;

    public Game(GridPane root, String mapId){
        LabyrinthFile.loadMaps();
        labyrynth = LabyrinthFile.getMapById(mapId);

        this.root = root;
        int labX = labyrynth.getMaxX()*Map.ArrayUnit;
        int labY = labyrynth.getMaxY()*Map.ArrayUnit;
        canvas = new ResizableCanvas(labX, labY, 800, 875);
        root.getChildren().add(canvas);

        pacman = (Pacman)labyrynth.getPacMan();
        pacman.spawn(canvas);

        System.out.println("Labyrynth");
        Block[][] block = labyrynth.getStaticMap();
        for (int i = 0; i < labyrynth.getMaxX(); i++){
            for (int j = 0; j < labyrynth.getMaxY(); j++){
                if (block[i][j] != null)
                    block[i][j].spawn(canvas);
            }
        }

        //TODO add a map caller to add entity
        kI = new KeyboardInput(root.getScene());
        root.widthProperty().addListener(evtW -> canvas.setWidth(root.getWidth()));
        root.heightProperty().addListener(evtH -> canvas.setHeight(root.getHeight()));
    }

    public void gameLoop(){
        gameUpdate();
        graphicalUpdate();
        //TODO make game condition end here
        if(false){
            Event myEvent = new GameEvent(this, root, GameEvent.GAME_END);
            this.root.fireEvent(myEvent);
        }

    }

    private void gameUpdate(){
        KeyCode lastKeyPressed = kI.getLastKeyPressed();
        pacman.move(lastKeyPressed, canvas);

    }

    private void graphicalUpdate(){
        canvas.clear();
        canvas.draw();
    }

    public void removeHandlers(){
        //root.widthProperty().removeListener();
        //root.heightProperty().addListener();
        kI.removeListener();
    }

}
