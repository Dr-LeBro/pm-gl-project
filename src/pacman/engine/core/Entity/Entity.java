package pacman.engine.core.Entity;

import static pacman.engine.core.Entity.EntityType.*;

public abstract class Entity {

    protected EntityType type;

    public Entity(){
        type = empty;
    }

    public EntityType getType(){
        return type;
    }

    public void spawn(){}
    public void kill(){}


}
