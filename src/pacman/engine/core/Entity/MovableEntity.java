package pacman.engine.core.Entity;

public class MovableEntity implements Entity {

    private EntityType type;
    private double x;
    private double y;

    public MovableEntity(EntityType kind, double x, double y)
    {
        this.type = kind;
        this.x = x;
        this.y = y;
    }

    public EntityType getType() {
        return type;
    }

    public double getY()
    {
        return y;
    }

    public double getX()
    {
        return x;
    }
}
