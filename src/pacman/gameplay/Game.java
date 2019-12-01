package pacman.gameplay;

import javafx.event.Event;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import pacman.GUI.inGameGUI.MainGameGUI;
import pacman.Main;
import pacman.engine.core.Entity.StaticEntity;
import pacman.engine.core.KeyboardInput;
import pacman.engine.core.Map.Map;
import pacman.engine.graphism.AnimationSyncrhonizer;
import pacman.engine.graphism.ResizableCanvas;
import pacman.engine.graphism.Sprite;
import pacman.fileManager.LabyrinthFile;
import pacman.gameplay.ghost.Blinky;
import pacman.gameplay.ghost.Ghost;
import pacman.gameplay.pacman.Pacman;
import pacman.gameplay.scoreManager.Score;

import java.util.ArrayList;

public class Game {

    private Pacman pacman;
    private Ghost blinky;
    private KeyboardInput kI;
    private ResizableCanvas canvas;
    public static Map labyrinth = null;
    private GridPane root;

    public Game(GridPane root, String mapId){
        LabyrinthFile.loadMaps();
        labyrinth = LabyrinthFile.getMapById(mapId);

        this.root = root;
        int labX = labyrinth.getMaxX()*Map.ArrayUnit;
        int labY = labyrinth.getMaxY()*Map.ArrayUnit;
        canvas = new ResizableCanvas(labX, labY, root.getWidth(), root.getHeight());
        root.getChildren().add(canvas);

        pacman = (Pacman)labyrinth.getPacMan();
        pacman.spawn(canvas);

        blinky = new Blinky();
        //blinky = (Ghost) labyrynth.getPacMan();
        //blinky.spawn(canvas);

        System.out.println("Labyrinth");
        ArrayList<Sprite> staticMap = labyrinth.getStaticMapVisual();
        for (int i = 0; i < staticMap.size(); i++){
                    canvas.addDrawingElement(staticMap.get(i));
        }

        StaticEntity[][] entities = labyrinth.getStaticEntityMap();
        for(int x = 0; x < entities.length; x++)
        {
            for(int y = 0; y < entities[0].length; y++)
            {
                if(entities[x][y] != null) {
                    entities[x][y].spawn(canvas);
                }
            }
        }

        //TODO add a map caller to add entity
        kI = new KeyboardInput(Main.root);
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
        AnimationSyncrhonizer.incrementFrame();
        canvas.draw();
    }

    public void removeHandlers(){
        //root.widthProperty().removeListener();
        //root.heightProperty().addListener();
        kI.removeListener();
    }

    public int getScore(){
        return Score.getInstance().getScore();
    }

    public int getPacmanRemainingLifes(){
        return pacman.getNbLives();
    }

    public ResizableCanvas getCanvas() {
        return canvas;
    }
}
