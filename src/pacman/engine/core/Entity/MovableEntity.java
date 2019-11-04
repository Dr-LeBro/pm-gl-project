package pacman.engine.core.Entity;

import pacman.engine.physic.movement.Movement;

public class MovableEntity extends Entity {

    protected Movement move;

    public MovableEntity(EntityType kind)
    {
        super(kind);
    }

    public MovableEntity(EntityType kind, double x, double y)
    {
        super(kind, x, y);
    }


}
