package pacman.gameplay.ghost;

import javafx.scene.input.KeyCode;
import pacman.engine.core.Entity.MovableEntity;
import pacman.engine.core.GameState;
import pacman.engine.core.Map.Map;
import pacman.engine.graphism.Sprite;
import pacman.engine.graphism.StaticSprite;

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
    public KeyCode chase()
    {
        MovableEntity pacman = GameState.getInstance().getCurrMap().getPacMan();
        return ghostIA(((int)Math.floor(pacman.getX()) + Map.ArrayUnit/2)/ Map.ArrayUnit, ((int)Math.floor(pacman.getY()) + Map.ArrayUnit/2) / Map.ArrayUnit);
        //Position endPos = new Position(((int)Math.floor(pacman.getX()) + Map.ArrayUnit/2)/ Map.ArrayUnit, ((int)Math.floor(pacman.getY()) + Map.ArrayUnit/2) / Map.ArrayUnit);
    }
}
