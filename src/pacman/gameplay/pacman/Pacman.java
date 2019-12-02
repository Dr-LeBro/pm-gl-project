package pacman.gameplay.pacman;

import javafx.scene.input.KeyCode;
import pacman.GUI.inGameGUI.MainGameGUI;
import pacman.engine.core.Entity.Entity;
import pacman.engine.core.Entity.EntityType;
import pacman.engine.core.Entity.MovableEntity;
import pacman.engine.core.GameState;
import pacman.engine.graphism.*;
import pacman.engine.core.Map.Map;
import pacman.gameplay.GameEvent;

import java.util.ArrayList;

public class Pacman extends MovableEntity {
    private int nbLives;
    private int displayedPoints;    //points displayed on screen
    private int nbPoints;           //points used to get lives

    public Pacman(int nbLives, int x, int y) {
        super(EntityType.PACMAN, new StaticSprite("file:sprites/pacman02_right.png", "pacmanR"), x * Map.ArrayUnit , y  * Map.ArrayUnit, 3 * Map.ArrayUnit, 1);
        Sprite sprites[] = new Sprite[4];
        ArrayList<String> tempSprites = new ArrayList<>();
        AnimationSyncrhonizer animSync = new AnimationSyncrhonizer(0.30);
        tempSprites.add("file:sprites/pacman01_up.png");
        tempSprites.add("file:sprites/pacman02_up.png");
        sprites[0] = new AnimatedSprite(tempSprites, "pacmanU", animSync);

        tempSprites = new ArrayList<>();
        tempSprites.add("file:sprites/pacman01_down.png");
        tempSprites.add("file:sprites/pacman02_down.png");
        sprites[1] = new AnimatedSprite(tempSprites, "pacmanD", animSync);

        tempSprites = new ArrayList<>();
        tempSprites.add("file:sprites/pacman01_right.png");
        tempSprites.add("file:sprites/pacman02_right.png");
        sprites[2] = new AnimatedSprite(tempSprites, "pacmanR", animSync);

        tempSprites = new ArrayList<>();
        tempSprites.add("file:sprites/pacman01_left.png");
        tempSprites.add("file:sprites/pacman02_left.png");
        sprites[3] = new AnimatedSprite(tempSprites, "pacmanL", animSync);

        if(setMovingSprites(sprites)){
            System.out.println("pacman Sprites loaded");
        }

        this.nbLives = nbLives;
    }

    public int getNbLives() {
        return nbLives;
    }

    public void getLive() {
        nbLives++;

        //call GUI
        MainGameGUI.eventHandler.fireEvent(new GameEvent(this, MainGameGUI.eventHandler, GameEvent.GAME_LIFE_UPDATED));
    }

    public void kill()
    {
        /* If PacMan get hit even though he has at least 1 life, then he dies and respawn */
        if(nbLives > 1) {
            nbLives--;
            super.kill();
            respawn( this.x, this.y);

            //call GUI
            MainGameGUI.eventHandler.fireEvent(new GameEvent(this, MainGameGUI.eventHandler, GameEvent.GAME_LIFE_UPDATED));
        }

        /* Else, he is deleted, and the game ends */
        else {
            super.kill();
            delete();
        }
    }

    @Override
    public void move(KeyCode keyPressed) {
        super.move(keyPressed);
        Entity[][] staticEntities = GameState.getInstance().getCurrMap().getSurroundingStaticEntityMap((int)(x/ Map.ArrayUnit),(int)(y / Map.ArrayUnit));
        for (int i = 0; i < staticEntities.length ; i++){
            for (int j = 0; j < staticEntities[i].length ; j++){
                if (staticEntities[i][j] != null && this.hitBox.isInContact(sizeX, sizeY, x, y, staticEntities[i][j])) {
                    staticEntities[i][j].kill();
                }
            }
        }
    }

    /* TODO : From 100 to 5.000 points for fruits, and 200-400-800-1600 points for ghosts */
    public void eat() {}
}
