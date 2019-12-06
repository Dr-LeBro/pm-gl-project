package pacman.gameplay.ghost;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import pacman.engine.core.Block.Block;
import pacman.engine.core.Entity.Entity;
import pacman.engine.core.Entity.EntityType;
import pacman.engine.core.Entity.MovableEntity;
import pacman.engine.core.Entity.StaticEntity;
import pacman.engine.core.GameState;
import pacman.engine.core.Map.Map;
import pacman.engine.graphism.Sprite;
import pacman.engine.graphism.StaticSprite;
import pacman.gameplay.ghost.mode.Mode;
import pacman.gameplay.pacman.Pacman;

import java.util.ArrayList;
import java.util.LinkedList;

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
    public KeyCode chase()
    {
        KeyCode kc = null;  //TODO : jté kc
        MovableEntity pacman = GameState.getInstance().getCurrMap().getPacMan();
        return ghostIA(0, 0);
        /* kc =  ghostIA(((int)Math.floor(pacman.getX()) + Map.ArrayUnit/2)/ Map.ArrayUnit, ((int)Math.floor(pacman.getY()) + Map.ArrayUnit/2) / Map.ArrayUnit);
        mode = Mode.SCATTER;
        switch (mode) {
            case CHASE:
                aggressiveChase();
                break;
            case SCATTER:
                //kc = scatterChase();
                break;
            case FRIGHTENED:
                break;
        }

        return kc;*/
    }

    @Override
    public void aggressiveChase() {

    }

    @Override
    public KeyCode scatterChase() {
        return null;
    }

    @Override
    public void frightenedChase()
    {

    }
}