package pacman.gameplay.ghost;

import pacman.engine.core.Entity.EntityType;
import pacman.engine.core.Entity.MovableEntity;
import pacman.engine.core.Map.Map;
import pacman.engine.graphism.ResizableCanvas;
import pacman.engine.graphism.Sprite;
import pacman.engine.graphism.StaticSprite;

public class Ghost extends MovableEntity {
    private boolean invincible;
    private int behaviour;
    private double respawnTime;
    private static double initialX = 40, initialY = 40;

    public Ghost(int behaviour, double respawnTime) {
        super(EntityType.GHOST, new StaticSprite("file:sprites/ghost02_up.png", "ghost"), initialX, initialY, 2*Map.ArrayUnit, 0.32);
        Sprite[] sprites = new Sprite[4];
        sprites[0] = new StaticSprite("file:sprites/qhost01_up.png", "qhostU");
        sprites[1] = new StaticSprite("file:sprites/qhost01_right.png", "qhostR");
        sprites[2] = new StaticSprite("file:sprites/qhost01_down.png", "qhostD");
        sprites[3] = new StaticSprite("file:sprites/qhost01_left.png", "qhostL");
        setMovingSprites(sprites);
        invincible = true;
        this.behaviour = behaviour;
        this.respawnTime = respawnTime;
    }

    public boolean isInvincible() {
        return invincible;
    }

    public int getBehaviour() {
        return behaviour;
    }

    public double getRespawnTime() {
        return respawnTime;
    }

    public void getDamaged(ResizableCanvas canvas) {
        kill(canvas);
        respawn(canvas, initialX, initialY);
    }
}
