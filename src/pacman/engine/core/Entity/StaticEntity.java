package pacman.engine.core.Entity;

public class StaticEntity extends Entity {


    public StaticEntity(EntityType kind)
    {
        super(kind);
    }

    public StaticEntity(EntityType kind, double x, double y)
    {
        super(kind, x, y);
    }
}
