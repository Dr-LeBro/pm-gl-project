package pacman.gameplay.ghost;

import pacman.engine.core.Entity.EntityType;
import pacman.engine.core.Entity.MovableEntity;

public class Ghost extends MovableEntity {
    private boolean invincible;
    private int behaviour;
    private int respawnTime;

    public Ghost(boolean invincible, int behaviour, int respawnTime) {
        super(EntityType.ghost);
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
