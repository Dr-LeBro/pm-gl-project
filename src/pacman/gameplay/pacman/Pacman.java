package pacman.gameplay.pacman;

import pacman.engine.core.Entity.EntityType;
import pacman.engine.core.Entity.MovableEntity;
import pacman.engine.graphism.*;
import pacman.engine.core.Map.Map;

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
    }

    public void getDamaged(ResizableCanvas canvas)
    {
        /* If PacMan get hit even though he has at least 1 life, then he dies and respawn */
        if(nbLives > 1) {
            nbLives--;
            kill(canvas);
            respawn(canvas, this.x, this.y);
        }

        /* Else, he is deleted, and the game ends */
        else {
            kill(canvas);
            delete(canvas);
        }
    }

    /* TODO : From 100 to 5.000 points for fruits, and 200-400-800-1600 points for ghosts */
    public void eat() {}
}
