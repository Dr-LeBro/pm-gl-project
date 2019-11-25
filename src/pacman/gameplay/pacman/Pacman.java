package pacman.gameplay.pacman;

import pacman.engine.core.Entity.EntityType;
import pacman.engine.core.Entity.MovableEntity;
import pacman.engine.graphism.ResizableCanvas;
import pacman.engine.graphism.Sprite;
import pacman.engine.graphism.StaticSprite;

public class Pacman extends MovableEntity {
    private int nbLives;
    private int displayedPoints;    //points displayed on screen
    private int nbPoints;           //points used to get lives
    private static double initialX = 20, initialY = 20;

    public Pacman(int nbLives) {
        super(EntityType.PACMAN, new StaticSprite("file:sprites/pacman02_right.png", "pacmanR"), initialX, initialY, 4, 0.32);
        Sprite[] sprites = new Sprite[4];
        sprites[0] = new StaticSprite("file:sprites/pacman01_up.png", "pacmanU");
        sprites[1] = new StaticSprite("file:sprites/pacman01_down.png", "pacmanD");
        sprites[2] = new StaticSprite("file:sprites/pacman01_right.png", "pacmanR");
        sprites[3] = new StaticSprite("file:sprites/pacman01_left.png", "pacmanL");
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
            respawn(canvas, initialX, initialY);
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
