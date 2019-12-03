package pacman.gameplay.ghost;

import pacman.engine.graphism.Sprite;
import pacman.engine.graphism.StaticSprite;
import pacman.gameplay.ghost.mode.Mode;

/* This is the red ghost */
public class Blinky extends IAGhost {
    private Sprite[] sprites;

    /* Blinky starts outside the ghost house */
    public Blinky(int x, int y) {
        super(0, 0, x, y);
        sprites = new Sprite[4];
        sprites[0] = new StaticSprite("file:sprites/red_up.png", "redU");
        sprites[1] = new StaticSprite("file:sprites/red_right.png", "redR");
        sprites[2] = new StaticSprite("file:sprites/red_down.png", "redD");
        sprites[3] = new StaticSprite("file:sprites/red_left.png", "redL");
        setMovingSprites(sprites);
    }

    @Override
    public void chase()
    {
        Mode mode = getMode();
        switch (mode) {
            case CHASE:
                break;
            case SCATTER:
                break;
            case FRIGHTENED:
                break;
        }
    }

    @Override
    public void aggressiveChase()
    {

    }

    @Override
    public void scatterChase()
    {

    }

    @Override
    public void frightenedChase()
    {

    }
}
