package pacman.gameplay.pacman;

import javafx.scene.input.KeyCode;
import pacman.GUI.inGameGUI.MainGameGUI;
import pacman.engine.core.Entity.Entity;
import pacman.engine.core.Entity.EntityType;
import pacman.engine.core.Entity.MovableEntity;
import pacman.engine.core.GameState;
import pacman.engine.core.Map.Map;
import pacman.engine.graphism.AnimatedSprite;
import pacman.engine.graphism.AnimationSyncrhonizer;
import pacman.engine.graphism.Sprite;
import pacman.engine.graphism.StaticSprite;
import pacman.engine.physic.hitBox.HitBox;
import pacman.engine.physic.movement.Direction;
import pacman.gameplay.Bonus.advantageBonus.PowerUp;
import pacman.gameplay.GameEvent;

import java.util.ArrayList;

public class Pacman extends MovableEntity {
    private int nbLives;
    private int displayedPoints;    //points displayed on screen
    private int nbPoints;           //points used to get lives
    private PowerUp resize = new PowerUp(2000, 5000, "resize");

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
        sprites[2] = new AnimatedSprite(tempSprites, "pacmanD", animSync);

        tempSprites = new ArrayList<>();
        tempSprites.add("file:sprites/pacman01_right.png");
        tempSprites.add("file:sprites/pacman02_right.png");
        sprites[1] = new AnimatedSprite(tempSprites, "pacmanR", animSync);

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

    @Override
    public void kill()
    {
        /* If PacMan get hit even though he has at least 1 life, then he dies and respawn */
        if(nbLives > 1) {
            nbLives--;
            super.kill();
            respawn(spawnX, spawnY);
            //call GUI
            MainGameGUI.eventHandler.fireEvent(new GameEvent(this, MainGameGUI.eventHandler, GameEvent.GAME_LIFE_UPDATED));
        }

        /* Else, he is deleted, and the game ends */
        else {
            delete();
        }
    }

    public void action(KeyCode keyPressed){
        move(keyPressed);
        if (keyPressed == KeyCode.A){
            resizePowerUp(true);
        }else{
            resizePowerUp(false);
        }
    }

    private void resizePowerUp(boolean keyPressed){
        if(keyPressed && resize.canBeUsed()){
            resize.use();
            resize(Map.ArrayUnit, Map.ArrayUnit);
        }else{

            //TODO: erreur de tableau
            int currentState = resize.checkState();
            if(!resize.isEndedTargeted()){
                Entity[][] walls = GameState.getInstance().getCurrMap().getStaticEntityMap();
                boolean neighborsClear = true;
                boolean[][] canPlaceOn;


                if (walls != null) {
                    //delimitation de la zone a regarder
                    int xIndexBegin = Math.max(0, (int)x/Map.ArrayUnit-3);
                    int yIndexBegin = Math.max(0, (int)y/Map.ArrayUnit-3);
                    int xIndexEnd = Math.min(walls.length, (int)x/Map.ArrayUnit+3);
                    int yIndexEnd = Math.min(walls.length, (int)y/Map.ArrayUnit+3);

                    //init du tableau des possibilitées
                    canPlaceOn = new boolean[xIndexEnd-xIndexBegin][yIndexEnd-yIndexBegin];
                    for(int i=xIndexBegin; i<xIndexEnd; i++){
                        for(int j=yIndexBegin; j<yIndexEnd; j++){
                            if(walls[i][j] != null){
                                //si mur alors imposable
                                canPlaceOn[i-xIndexBegin][j-yIndexBegin] = false;
                            }else{

                                //sinon regarde les vosiins pour etre sur que c'est vide
                                int tempJ = j;
                                int tempI = i;
                                if(j>0) tempJ -= 1;
                                if(i>0) tempI -= 1;
                                neighborsClear = true;
                                for(int subI = tempI; subI<=i+1 && subI<walls.length; subI++){
                                    for(int subJ = tempJ; subJ<=j+1 && subJ<walls.length; subJ++){
                                        if(walls[subI][subJ] != null){
                                            neighborsClear = false;
                                        }
                                    }
                                }
                                if(neighborsClear){
                                    // si c'est bien vide alors cette case peut acceuillir pacman
                                    canPlaceOn[i-xIndexBegin][j-yIndexBegin] = true;
                                    System.out.println("I:" +(i-xIndexBegin) + " J: "+ (j-yIndexBegin));
                                }else{
                                    //sinon non
                                    canPlaceOn[i-xIndexBegin][j-yIndexBegin] = false;
                                }
                            }
                        }
                    }

                    boolean flag = true;
                    //parcours toutes les cases potentieles.
                    /*Pour le moment j'essaye juste de poser pacman a la premiere case potentielle vue, mais il serait bien de le faire ne fonction des coo de pacman
                        a la base et de comparer la meilleure possible
                     */

                    for (int i=0; i<canPlaceOn.length && flag; i++){
                        for (int j=0; j<canPlaceOn.length && flag; j++){
                            System.out.print(canPlaceOn[i][j] + " | ");
                            if(canPlaceOn[i][j]){
                                x = (i+xIndexBegin)*Map.ArrayUnit;
                                y = (j+yIndexBegin)*Map.ArrayUnit;
                                resize(3* Map.ArrayUnit, 3* Map.ArrayUnit);
                                //flag = false;
                            }else{
                            }

                        }
                        System.out.println();
                    }
                }
            }
        }

    }

    @Override
    public void move(KeyCode keyPressed) {
        super.move(keyPressed);
        Entity[][] staticEntities = GameState.getInstance().getCurrMap().getSurroundingStaticEntityMap((int)(x/ Map.ArrayUnit),(int)(y / Map.ArrayUnit));
        if(staticEntities != null)
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
