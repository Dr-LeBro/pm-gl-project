package pacman.engine.core.Block;

import pacman.engine.core.Entity.EntityType;
import pacman.engine.core.Entity.StaticEntity;
import pacman.engine.graphism.StaticSprite;

public class Block extends StaticEntity {
    public Block() {
        super(EntityType.BLOCK, new StaticSprite("file:sprites/wall.png", "wall"),10);
    }

    public Block(int posX, int posY) {
        // TODO FAIRE CONVERSION PROPRE
        super(EntityType.BLOCK, new StaticSprite("file:sprites/wall.png", "wall"), (double) posX*3, (double) posY*3, 4);
    }

}
