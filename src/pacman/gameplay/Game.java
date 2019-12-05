package pacman.gameplay;

import javafx.event.Event;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import pacman.Main;
import pacman.engine.core.Entity.StaticEntity;
import pacman.engine.core.GameState;
import pacman.engine.core.KeyboardInput;
import pacman.engine.core.Map.Map;
import pacman.engine.graphism.AnimationSyncrhonizer;
import pacman.engine.graphism.ResizableCanvas;
import pacman.engine.graphism.Sprite;
import pacman.fileManager.LabyrinthFile;
import pacman.gameplay.ghost.*;
import pacman.gameplay.pacman.Pacman;
import pacman.gameplay.scoreManager.Score;

import java.util.ArrayList;

public class Game {
    private ArrayList<Ghost> ghosts;
    GameState pGame = GameState.getInstance();

    public Game(Pane root, String mapId){
        LabyrinthFile.loadMaps();
        pGame.setCurrMap(LabyrinthFile.getMapById(mapId));

        pGame.setRoot(root);
        int labX = pGame.getCurrMap().getMaxX()*Map.ArrayUnit;
        int labY = pGame.getCurrMap().getMaxY()*Map.ArrayUnit;
        pGame.setCanvas(new ResizableCanvas(labX, labY, 0, 0));


        System.out.println("Labyrinth");
        ArrayList<Sprite> staticMap = pGame.getCurrMap().getStaticMapVisual();
        for (int i = 0; i < staticMap.size(); i++){
            pGame.getCanvas().addDrawingElement(staticMap.get(i));
        }

        StaticEntity[][] entities = pGame.getCurrMap().getStaticEntityMap();
        for(int x = 0; x < entities.length; x++)
        {
            for(int y = 0; y < entities[0].length; y++)
            {
                if(entities[x][y] != null) {
                    entities[x][y].spawn();
                }
            }
        }

        ghosts = pGame.getCurrMap().getGhosts();
        for (int i = 0; i < ghosts.size(); i++){
            ghosts.get(i).spawn();
        }

        pGame.getCurrMap().getPacMan().spawn();

        pGame.setkI(new KeyboardInput(Main.root));
    }

    public void gameLoop(){
        gameUpdate();
        graphicalUpdate();

        if(GameState.getInstance().getCurrMap().isWon() || getPacmanRemainingLifes() < 1){
            Event myEvent = new GameEvent(this, pGame.getRoot(), GameEvent.GAME_END);
            this.pGame.getRoot().fireEvent(myEvent);
        }

    }

    private void gameUpdate(){
        KeyCode lastKeyPressed = pGame.getkI().getLastKeyPressed();
        KeyCode keyGhost = null;
        ((Pacman)pGame.getCurrMap().getPacMan()).action(lastKeyPressed);
        for (int i = 0; i < ghosts.size(); i++){
            switch(ghosts.get(i).getBehaviour()){
                case 0 : // Blinky
                    keyGhost = ((Blinky) (ghosts.get(i))).chase();
                    break;
                case 1 : // Pinky
                    keyGhost = ((Pinky) (ghosts.get(i))).chase();
                    break;
                case 2 : // Inky
                    keyGhost = ((Inky) (ghosts.get(i))).chase();
                    break;
                case 3 : // Clyde
                    keyGhost = ((Clyde) (ghosts.get(i))).chase();
                    break;
                default : // Default
                    keyGhost = null;
                    break;
            }
            ghosts.get(i).move(keyGhost);
        }
    }

    private void graphicalUpdate(){
        pGame.getCanvas().clear();
        AnimationSyncrhonizer.incrementFrame();
        pGame.getCanvas().draw();
    }

    public void removeHandlers(){
        pGame.getkI().removeListener();
    }

    public int getScore(){
        return Score.getInstance().getScore();
    }

    public int getPacmanRemainingLifes(){ return ((Pacman)pGame.getCurrMap().getPacMan()).getNbLives(); }

    public ResizableCanvas getCanvas(){
        return GameState.getInstance().getCanvas();
    }
}
