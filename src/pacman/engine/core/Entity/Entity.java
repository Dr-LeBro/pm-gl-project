package pacman.engine.core.Entity;

import pacman.engine.physic.hitBox.HitBox;

import static pacman.engine.core.Entity.EntityType.empty;

public abstract class Entity {

    protected EntityType type;
    protected HitBox hitBox;

    protected double x;
    protected double y;

    protected boolean isVisible;

    public Entity(EntityType kind) {
        type = kind;
        isVisible = false;

    }

    public Entity(EntityType kind, double x, double y) {
        type = kind;
        isVisible = false;
        this.x = x;
        this.y = y;
    }

    public EntityType getType() {
        return type;
    }

    public void spawn(){
        isVisible = true;
    }

    public void kill(){
        isVisible = false;
    }

    public void delete(){
        type = empty;
    }

    public void move(){
        System.out.println("This entity can't move");
    }

    public double getY()
    {
        return y;
    }

    public double getX()
    {
        return x;
    }

    public boolean isVisible() {
        return isVisible;
    }
}
