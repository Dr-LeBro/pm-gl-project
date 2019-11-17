package pacman.engine.core.Block;

import pacman.engine.core.Entity.EntityType;
import pacman.engine.core.Entity.StaticEntity;
import pacman.engine.graphism.StaticSprite;

public class Block extends StaticEntity {

    public Block() {
        super(EntityType.BLOCK, new StaticSprite("file:sprites/wall.png", "wall"),10);
    }

}
