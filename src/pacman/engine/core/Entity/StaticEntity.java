package pacman.engine.core.Entity;

public class StaticEntity implements Entity {

    private EntityType type;

    public StaticEntity(EntityType kind)
    {
        this.type = kind;
    }

    public EntityType getType() {
        return type;
    }
}
