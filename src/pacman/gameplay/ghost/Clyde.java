package pacman.gameplay.ghost;

import pacman.engine.graphism.Sprite;
import pacman.engine.graphism.StaticSprite;

/* This is the red ghost */
public class Clyde extends IAGhost {
    private Sprite[] sprites;

    /* Clyde starts outside the ghost house */
    public Clyde(int x, int y) {
        super(0, 0, x, y);
        sprites = new Sprite[4];
        sprites[0] = new StaticSprite("file:sprites/red_up.png", "redU");
        sprites[1] = new StaticSprite("file:sprites/red_right.png", "redR");
        sprites[2] = new StaticSprite("file:sprites/red_down.png", "redD");
        sprites[3] = new StaticSprite("file:sprites/red_left.png", "redL");
    }
}