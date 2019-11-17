package pacman.engine.core.Block;

import pacman.engine.core.Entity.EntityType;
import pacman.engine.core.Entity.StaticEntity;
import pacman.engine.graphism.Sprite;

public class Block extends StaticEntity {

    public Block(Sprite sprite, double size) {
        super(EntityType.BLOCK, sprite, size);
    }

}
