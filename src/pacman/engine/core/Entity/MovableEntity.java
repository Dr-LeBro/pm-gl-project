package pacman.engine.core.Entity;

public class MovableEntity implements Entity {

    private EntityType type;

    public MovableEntity(EntityType kind)
    {
        this.type = kind;
    }

    public EntityType getType() {
        return type;
    }
}
