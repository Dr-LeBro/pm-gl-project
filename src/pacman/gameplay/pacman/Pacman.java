package pacman.gameplay.pacman;

import pacman.engine.core.Entity.EntityType;
import pacman.engine.core.Entity.MovableEntity;
import pacman.engine.graphism.Sprite;
import pacman.engine.graphism.StaticSprite;

public class Pacman extends MovableEntity {
    private int nbLives;
    private int displayedPoints;    //points displayed on screen
    private int nbPoints;           //points used to get lives

    public Pacman(int nbLives) {
        //TODO look size And try to make it more logique (and add a ratio for graphic)
        super(EntityType.PACMAN, new StaticSprite("file:sprites/pacman02_up.png", "pacman"), 20, 20, 50);
        Sprite sprites[] = new Sprite[4];
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

    public void getDamaged()
    {
        if(nbLives > 1) {
            nbLives--;
            kill();
        }

        else {
            kill();
            delete();
        }
    }

    /* From 100 to 5.000 points for fruits, and 200-400-800-1600 points for ghosts */
    public void eat() {}
}
