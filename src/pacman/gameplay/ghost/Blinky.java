package pacman.gameplay.ghost;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import pacman.engine.core.GameState;
import pacman.engine.core.Entity.MovableEntity;
import pacman.engine.core.Map.Map;
import pacman.engine.graphism.Sprite;
import pacman.engine.graphism.StaticSprite;
import pacman.gameplay.ghost.mode.Mode;
import pacman.gameplay.pacman.Pacman;

/* This is the red ghost */
public class Blinky extends IAGhost {
    private Sprite[] sprites;
    private Mode mode;

    /* Blinky starts outside the ghost house */
    public Blinky(int x, int y) {
        super(0, 0, x, y);
        sprites = new Sprite[4];
        sprites[0] = new StaticSprite("file:sprites/red_up.png", "redU");
        sprites[1] = new StaticSprite("file:sprites/red_right.png", "redR");
        sprites[2] = new StaticSprite("file:sprites/red_down.png", "redD");
        sprites[3] = new StaticSprite("file:sprites/red_left.png", "redL");
        setMovingSprites(sprites);
        mode = Mode.CHASE;
    }

    @Override
    public KeyCode chase()
    {
        MovableEntity pacman = GameState.getInstance().getCurrMap().getPacMan();
        return ghostIA(((int)Math.floor(pacman.getX()) + Map.ArrayUnit/2)/ Map.ArrayUnit, ((int)Math.floor(pacman.getY()) + Map.ArrayUnit/2) / Map.ArrayUnit);
        //Position endPos = new Position(((int)Math.floor(pacman.getX()) + Map.ArrayUnit/2)/ Map.ArrayUnit, ((int)Math.floor(pacman.getY()) + Map.ArrayUnit/2) / Map.ArrayUnit);
        /*mode = getMode();
        switch (mode) {
            case CHASE:
                aggressiveChase();
                break;
            case SCATTER:
                break;
            case FRIGHTENED:
                break;
        }*/
    }

    @Override
    public void aggressiveChase()
    {
        Pacman pm = (Pacman) GameState.getInstance().getCurrMap().getPacMan();
        Point2D pos = getPos();
        double posX = pos.getX(), posY = pos.getY();
        if(pos.distance(posX-1.0, posY) < pos.distance(posX+1.0, posY))
            move(KeyCode.LEFT);
        else
            move(KeyCode.RIGHT);

        if(pos.distance(posX, posY-1.0) < pos.distance(posX, posY+1.0))
            move(KeyCode.DOWN);
        else
            move(KeyCode.UP);
    }

    @Override
    public KeyCode scatterChase()
    {

        return null;
    }

    @Override
    public void frightenedChase()
    {

    }
}
