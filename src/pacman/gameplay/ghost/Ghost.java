package pacman.gameplay.ghost;

import pacman.engine.core.Entity.EntityType;
import pacman.engine.core.Entity.MovableEntity;
import pacman.engine.graphism.Sprite;
import pacman.engine.graphism.StaticSprite;

public class Ghost extends MovableEntity {
    private boolean invincible;
    private int behaviour;
    private int respawnTime;

    public Ghost(boolean invincible, int behaviour, int respawnTime) {
        super(EntityType.GHOST, new StaticSprite("file:sprites/ghost02_up.png", "ghost"), 40, 40, 100, 0.32);
        Sprite sprites[] = new Sprite[4];
        sprites[0] = new StaticSprite("file:sprites/qhost01_up.png", "qhostU");
        sprites[1] = new StaticSprite("file:sprites/qhost01_right.png", "qhostR");
        sprites[2] = new StaticSprite("file:sprites/qhost01_down.png", "qhostD");
        sprites[3] = new StaticSprite("file:sprites/qhost01_left.png", "qhostL");
        setMovingSprites(sprites);
        this.invincible = invincible;
        this.behaviour = behaviour;
        this.respawnTime = respawnTime;
    }

    public boolean isInvincible() {
        return invincible;
    }

    public int getBehaviour() {
        return behaviour;
    }

    public int getRespawnTime() {
        return respawnTime;
    }
}
