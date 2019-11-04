package pacman.engine.core.Entity;

public class StaticEntity extends Entity {


    public StaticEntity(EntityType kind)
    {
        this.type = kind;
    }

    public EntityType getType() {
        return type;
    }
}
