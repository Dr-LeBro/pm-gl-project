package pacman.gameplay.ghost;

import pacman.engine.graphism.Sprite;
import pacman.engine.graphism.StaticSprite;

/* This is the pink ghost */
public class Pinky extends IAGhost {
    private Sprite[] sprites;

    public Pinky(int x, int y) {
        super(1, 1.0, x, y);
        sprites = new Sprite[4];
        sprites[0] = new StaticSprite("file:sprites/pink_up.png", "pinkU");
        sprites[1] = new StaticSprite("file:sprites/pink_right.png", "pinkR");
        sprites[2] = new StaticSprite("file:sprites/pink_down.png", "pinkD");
        sprites[3] = new StaticSprite("file:sprites/pink_left.png", "pinkL");
        setMovingSprites(sprites);
    }

    @Override
    public void chase()
    {

    }
}
