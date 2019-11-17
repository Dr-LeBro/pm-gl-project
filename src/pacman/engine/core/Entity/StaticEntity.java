package pacman.engine.core.Entity;

import pacman.engine.graphism.Sprite;

public class StaticEntity extends Entity {

    public StaticEntity(EntityType kind, Sprite sprite, double size)
    {
        super(kind, sprite, size);
    }

    public StaticEntity(EntityType kind, Sprite sprite, double x, double y, double size)
    {
        super(kind, sprite, x, y, size);
    }



}
