package pacman;

import javafx.scene.image.Image;

public class Pacman extends Controller {
    private Image closedMouth, openedMouth; //we need two images : we can rotate them whenever
    private int nbLives;

    public Pacman(Image closedMouth, Image openedMouth, int nbLives) {
        this.closedMouth = closedMouth;
        this.openedMouth = openedMouth;
        this.nbLives = nbLives; //here 'cause difficulty
    }

    public Image getClosedMouth() {
        return closedMouth;
    }

    public Image getOpenedMouth() {
        return openedMouth;
    }

    public void move()
    {

    }
}
