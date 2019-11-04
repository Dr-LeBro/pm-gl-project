package pacman.engine.core.Entity;

import pacman.engine.physic.hitBox.HitBox;

import static pacman.engine.core.Entity.EntityType.empty;

public abstract class Entity {

    protected EntityType type;
    protected HitBox hitBox;

    protected double x;
    protected double y;

    public Entity(EntityType kind) {
        type = empty;
    }

    public Entity(EntityType kind, double x, double y) {
        type = empty;
    }

    public EntityType getType() {
        return type;
    }

    public void spawn(){}
    public void kill(){}

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

}
