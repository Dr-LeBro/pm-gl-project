package pacman.gameplay.pacman;

import pacman.engine.core.Entity.MovableEntity;

public class Pacman extends MovableEntity {
    private int nbLives;
    private int displayedPoints;    //points displayed on screen
    private int nbPoints;           //points used to get lives

    public Pacman(int nbLives) {
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
            //respawn();
        }

        /*else
            gameover();*/
    }

    /* From 100 to 5.000 points for fruits, and 200-400-800-1600 points for ghosts */
    public void eat() {}
}
