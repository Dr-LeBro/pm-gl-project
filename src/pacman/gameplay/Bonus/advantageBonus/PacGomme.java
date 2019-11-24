package pacman.gameplay.Bonus.advantageBonus;

import pacman.engine.core.Entity.EntityType;
import pacman.engine.core.Entity.StaticEntity;
import pacman.engine.graphism.StaticSprite;

public class PacGomme extends StaticEntity {
        public PacGomme() {
            super(EntityType.PACK_GOMME, new StaticSprite("file:sprites/pacgomme.png", "wall"),10);
        }
}
