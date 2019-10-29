package pacman;

import javafx.scene.image.Image;

public class Ghost extends Controller {
    /* We need 4 images : eyes position are specific according to the ghost is looking */
    private Image upSprite, downSprite, leftSprite, rightSprite;
    private int behaviour; //to differentiate ghosts according to their color (à voir) ?
    private float spawnTime; //every ghost has a spawntime before he hunts Pacman

    public Ghost(Image upSprite, Image downSprite, Image leftSprite, Image rightSprite) {
        this.upSprite = upSprite;
        this.downSprite = downSprite;
        this.leftSprite = leftSprite;
        this.rightSprite = rightSprite;
    }

    public Image getUpSprite() {
        return upSprite;
    }

    public Image getDownSprite() {
        return downSprite;
    }

    public Image getLeftSprite() {
        return leftSprite;
    }

    public Image getRightSprite() {
        return rightSprite;
    }

    public void move()
    {

    }
}
