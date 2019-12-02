package pacman.gameplay.ghost;

import pacman.engine.graphism.Sprite;
import pacman.engine.graphism.StaticSprite;

/* This is the orange ghost */
public class Clyde extends IAGhost {
    private Sprite[] sprites;

    /* Clyde starts outside the ghost house */
    public Clyde(int x, int y) {
        super(3, 12.0, x, y);
        sprites = new Sprite[4];
        sprites[0] = new StaticSprite("file:sprites/orange_up.png", "redU");
        sprites[1] = new StaticSprite("file:sprites/orange_right.png", "redR");
        sprites[2] = new StaticSprite("file:sprites/orange_down.png", "redD");
        sprites[3] = new StaticSprite("file:sprites/orange_left.png", "redL");
        setMovingSprites(sprites);
    }

    @Override
    public void chase()
    {

    }
}