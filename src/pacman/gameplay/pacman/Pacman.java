package pacman.gameplay.pacman;

import javafx.geometry.Point2D;
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
import pacman.engine.sound.Music;
import pacman.gameplay.Bonus.advantageBonus.PowerUp;
import pacman.gameplay.GameEvent;

import java.util.ArrayList;

public class Pacman extends MovableEntity {
    private int nbLives;
    private boolean ghosting = false;
    private PowerUp resize = new PowerUp(2000, 5000, "resize");
    private PowerUp superpacgomme = new PowerUp(10000, 10000, "super");
    private PowerUp ghostingPower = new PowerUp(1000, 10000, "ghosting");

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
        /* If PacMan get hit even though he has only 1 life, then he dies and respawn */
        if(nbLives >= 1) {
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
        //Music.getInstance().getSounds().get(1).play();
        move(keyPressed);
        if (keyPressed == KeyCode.A){
            resizePowerUp(true);
        }else if (keyPressed == KeyCode.Z){
            MagicalJourney(true);
        }
        else{
            resizePowerUp(false);
        }
        superPowerUp(false);
        ghostingPower.checkState();
    }

    public void superPowerUp(boolean use) {
        if(use){
            superpacgomme.forceUse();
            invulnerable = true;
        }
        else
        {
            superpacgomme.checkState();
            if(!superpacgomme.isEndedTargeted()){
                invulnerable = false;
            }
        }
    }

    public void MagicalJourney(boolean use) {
        ghostingPower.checkState();
        if(use && ghostingPower.canBeUsed()){
            ghostingPower.use();
            ghosting = true;
        }
    }

    private void resizePowerUp(boolean keyPressed){
        if(keyPressed && resize.canBeUsed()){
            resize.use();
            resize(Map.ArrayUnit, Map.ArrayUnit);
        }else{
            resize.checkState();
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
        if(ghosting)
        {
            Point2D pointDir;
            if(wishedDirection != Direction.STANDING) {
                actualDir = wishedDirection;
                pointDir = moveManager.move(x, y, wishedDirection);
            }
            else {
                pointDir = moveManager.move(x, y, actualDir);
            }
            x = ((pointDir.getX()%(GameState.getInstance().getCurrMap().getMaxX()*Map.ArrayUnit))+(GameState.getInstance().getCurrMap().getMaxX()*Map.ArrayUnit))%(GameState.getInstance().getCurrMap().getMaxX()*Map.ArrayUnit);
            y = ((pointDir.getY()%(GameState.getInstance().getCurrMap().getMaxY()*Map.ArrayUnit))+(GameState.getInstance().getCurrMap().getMaxY()*Map.ArrayUnit))%(GameState.getInstance().getCurrMap().getMaxY()*Map.ArrayUnit);
            if(!ghostingPower.isEndedTargeted() && HitBox.canBePlaced(((int)getX()/Map.ArrayUnit)*Map.ArrayUnit, ((int)getY()/Map.ArrayUnit)*Map.ArrayUnit,this, getSizeX(), getSizeY())) {
                respawn(((int) getX() / Map.ArrayUnit) * Map.ArrayUnit, ((int) getY() / Map.ArrayUnit) * Map.ArrayUnit);
                ghosting = false;
            }
            else
            {
                ghostingPower.setEnded(0);
            }
            if(isVisible()){
                getSprite().setPoint(x, y);
                drawCurrentSprite();
            }
        }
        else {
            super.move(keyPressed);
            Entity[][] staticEntities = GameState.getInstance().getCurrMap().getSurroundingStaticEntityMap((int) (x / Map.ArrayUnit), (int) (y / Map.ArrayUnit));
            if (staticEntities != null)
                for (int i = 0; i < staticEntities.length; i++) {
                    for (int j = 0; j < staticEntities[i].length; j++) {
                        if (staticEntities[i][j] != null && this.hitBox.isInContact(sizeX, sizeY, x, y, staticEntities[i][j])) {
                            staticEntities[i][j].kill();
                        }
                    }
                }
        }
    }
}
