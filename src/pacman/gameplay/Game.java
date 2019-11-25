package pacman.gameplay;

import javafx.event.Event;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import pacman.engine.core.KeyboardInput;
import pacman.engine.core.Map.Map;
import pacman.engine.graphism.ResizableCanvas;
import pacman.engine.graphism.Sprite;
import pacman.fileManager.LabyrinthFile;
import pacman.gameplay.pacman.Pacman;

public class Game {

    private Pacman pacman;
    private KeyboardInput kI;
    private ResizableCanvas canvas;
    public static Map labyrynth = null;
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
        Sprite[][] staticMap = labyrynth.getStaticMapVisual();
        for (int i = 0; i < staticMap.length; i++){
            for (int j = 0; j < staticMap[0].length; j++){
                if (staticMap[i][j] != null)
                    canvas.addDrawingElement(staticMap[i][j]);
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
