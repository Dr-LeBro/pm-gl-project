package pacman.gameplay.ghost;

import pacman.engine.graphism.Sprite;
import pacman.engine.graphism.StaticSprite;

/* This is the red ghost */
public class Blinky extends IAGhost {
    private Sprite[] sprites;

    /* Blinky starts outside the ghost house */
    public Blinky() {
        super(0, 0);
        sprites = new Sprite[4];
        sprites[0] = new StaticSprite("file:sprites/red_up.png", "redU");
        sprites[1] = new StaticSprite("file:sprites/red_right.png", "redR");
        sprites[2] = new StaticSprite("file:sprites/red_down.png", "redD");
        sprites[3] = new StaticSprite("file:sprites/red_left.png", "redL");
    }
}
