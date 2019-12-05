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
import pacman.gameplay.Bonus.advantageBonus.PowerUp;
import pacman.gameplay.GameEvent;

import java.util.ArrayList;

public class Pacman extends MovableEntity {
    private int nbLives;
    private int displayedPoints;    //points displayed on screen
    private int nbPoints;           //points used to get lives
    private PowerUp resize = new PowerUp(2000, 5000, "resize");
    private PowerUp superpacgomme = new PowerUp(10000, 10000, "superpacgomme");

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
        superPowerUp(false);
    }

    public void superPowerUp(boolean use) {
        if(use){
            superpacgomme.forceUse();
            invulnerable = true;
        }
        else
        {
            int currentState = superpacgomme.checkState();
            if(!superpacgomme.isEndedTargeted()){
                invulnerable = false;
            }
        }
    }

    private void resizePowerUp(boolean keyPressed){
        if(keyPressed && resize.canBeUsed()){
            resize.use();
            resize(Map.ArrayUnit, Map.ArrayUnit);
        }else{
            int currentState = resize.checkState();
            if(!resize.isEndedTargeted()){
                if(HitBox.canBePlaced(((int)getX()/Map.ArrayUnit)*Map.ArrayUnit, ((int)getY()/Map.ArrayUnit)*Map.ArrayUnit,this, 30, 30)) {
                    respawn(((int) getX() / Map.ArrayUnit) * Map.ArrayUnit, ((int) getY() / Map.ArrayUnit) * Map.ArrayUnit);
                    resize(3 * Map.ArrayUnit, 3 * Map.ArrayUnit);
                    return;
                }
                if(HitBox.canBePlaced(((int)getX()/Map.ArrayUnit)*Map.ArrayUnit + Map.ArrayUnit, ((int)getY()/Map.ArrayUnit)*Map.ArrayUnit,this, 30, 30)) {
                    respawn(((int) getX() / Map.ArrayUnit) * Map.ArrayUnit + Map.ArrayUnit, ((int) getY() / Map.ArrayUnit) * Map.ArrayUnit);
                    resize(3 * Map.ArrayUnit, 3 * Map.ArrayUnit);
                    return;
                }
                if(HitBox.canBePlaced(((int)getX()/Map.ArrayUnit)*Map.ArrayUnit - Map.ArrayUnit, ((int)getY()/Map.ArrayUnit)*Map.ArrayUnit,this, 30, 30)) {
                    respawn(((int) getX() / Map.ArrayUnit) * Map.ArrayUnit - Map.ArrayUnit, ((int) getY() / Map.ArrayUnit) * Map.ArrayUnit);
                    resize(3 * Map.ArrayUnit, 3 * Map.ArrayUnit);
                    return;
                }
                if(HitBox.canBePlaced(((int)getX()/Map.ArrayUnit)*Map.ArrayUnit, ((int)getY()/Map.ArrayUnit)*Map.ArrayUnit + Map.ArrayUnit,this, 30, 30)) {
                    respawn(((int) getX() / Map.ArrayUnit) * Map.ArrayUnit, ((int) getY() / Map.ArrayUnit) * Map.ArrayUnit + Map.ArrayUnit);
                    resize(3 * Map.ArrayUnit, 3 * Map.ArrayUnit);
                    return;
                }
                if(HitBox.canBePlaced(((int)getX()/Map.ArrayUnit)*Map.ArrayUnit, ((int)getY()/Map.ArrayUnit)*Map.ArrayUnit - Map.ArrayUnit,this, 30, 30)) {
                    respawn(((int) getX() / Map.ArrayUnit) * Map.ArrayUnit, ((int) getY() / Map.ArrayUnit) * Map.ArrayUnit - Map.ArrayUnit);
                    resize(3 * Map.ArrayUnit, 3 * Map.ArrayUnit);
                    return;
                }
                if(HitBox.canBePlaced(((int)getX()/Map.ArrayUnit)*Map.ArrayUnit - Map.ArrayUnit, ((int)getY()/Map.ArrayUnit)*Map.ArrayUnit - Map.ArrayUnit,this, 30, 30)) {
                    respawn(((int) getX() / Map.ArrayUnit) * Map.ArrayUnit - Map.ArrayUnit, ((int) getY() / Map.ArrayUnit) * Map.ArrayUnit - Map.ArrayUnit);
                    resize(3 * Map.ArrayUnit, 3 * Map.ArrayUnit);
                    return;
                }
                if(HitBox.canBePlaced(((int)getX()/Map.ArrayUnit)*Map.ArrayUnit + Map.ArrayUnit, ((int)getY()/Map.ArrayUnit)*Map.ArrayUnit - Map.ArrayUnit,this, 30, 30)) {
                    respawn(((int) getX() / Map.ArrayUnit) * Map.ArrayUnit + Map.ArrayUnit, ((int) getY() / Map.ArrayUnit) * Map.ArrayUnit - Map.ArrayUnit);
                    resize(3 * Map.ArrayUnit, 3 * Map.ArrayUnit);
                    return;
                }
                if(HitBox.canBePlaced(((int)getX()/Map.ArrayUnit)*Map.ArrayUnit - Map.ArrayUnit, ((int)getY()/Map.ArrayUnit)*Map.ArrayUnit + Map.ArrayUnit,this, 30, 30)) {
                    respawn(((int) getX() / Map.ArrayUnit) * Map.ArrayUnit - Map.ArrayUnit, ((int) getY() / Map.ArrayUnit) * Map.ArrayUnit + Map.ArrayUnit);
                    resize(3 * Map.ArrayUnit, 3 * Map.ArrayUnit);
                    return;
                }
                if(HitBox.canBePlaced(((int)getX()/Map.ArrayUnit)*Map.ArrayUnit + Map.ArrayUnit, ((int)getY()/Map.ArrayUnit)*Map.ArrayUnit + Map.ArrayUnit,this, 30, 30)) {
                    respawn(((int) getX() / Map.ArrayUnit) * Map.ArrayUnit + Map.ArrayUnit, ((int) getY() / Map.ArrayUnit) * Map.ArrayUnit + Map.ArrayUnit);
                    resize(3 * Map.ArrayUnit, 3 * Map.ArrayUnit);
                    return;
                }

                resize.setEnded(0);
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
