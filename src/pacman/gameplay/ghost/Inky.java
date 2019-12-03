package pacman.gameplay.ghost;

import javafx.scene.input.KeyCode;
import pacman.engine.core.Entity.MovableEntity;
import pacman.engine.core.GameState;
import pacman.engine.core.Map.Map;
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
    public KeyCode chase()
    {
        MovableEntity pacman = GameState.getInstance().getCurrMap().getPacMan();
        return ghostIA(((int)Math.floor(pacman.getX()) + Map.ArrayUnit/2)/ Map.ArrayUnit, ((int)Math.floor(pacman.getY()) + Map.ArrayUnit/2) / Map.ArrayUnit);
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
