package pacman.gameplay.ghost;

import pacman.engine.graphism.Sprite;
import pacman.engine.graphism.StaticSprite;

/* This is the blue ghost */
public class Inky extends IAGhost {
    private Sprite[] sprites;

    public Inky(int x, int y) {
        super(2, 7.0, x, y);
        sprites = new Sprite[4];
        sprites[0] = new StaticSprite("file:sprites/blue_up.png", "pinkU");
        sprites[1] = new StaticSprite("file:sprites/blue_right.png", "pinkR");
        sprites[2] = new StaticSprite("file:sprites/blue_down.png", "pinkD");
        sprites[3] = new StaticSprite("file:sprites/blue_left.png", "pinkL");
        setMovingSprites(sprites);
    }

    @Override
    public void chase()
    {

    }
}
