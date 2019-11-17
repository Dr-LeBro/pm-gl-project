package pacman.engine.core.Entity;

import pacman.engine.graphism.Sprite;
import pacman.engine.graphism.StaticSprite;

public class StaticEntity extends Entity {

    protected Sprite sprite;

    public StaticEntity(EntityType kind)
    {
        super(kind);
        sprite = new StaticSprite("");
    }

    public StaticEntity(EntityType kind, double x, double y)
    {
        super(kind, x, y);
        sprite.setPoint(x,y);
    }
}
