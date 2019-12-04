package pacman.gameplay.Bonus;

import pacman.engine.core.Entity.EntityType;
import pacman.engine.core.Entity.StaticEntity;
import pacman.engine.core.GameState;
import pacman.engine.core.Map.Map;
import pacman.engine.graphism.StaticSprite;
import pacman.gameplay.pacman.Pacman;

public class SuperPacGomme extends StaticEntity {
    public SuperPacGomme(int posX, int posY) {
        super(EntityType.SUPER_PAC_GOMME, new StaticSprite("file:sprites/superpacgomme.png", "superpacgomme"), (double) posX * Map.ArrayUnit, (double) posY * Map.ArrayUnit, Map.ArrayUnit*0.99);
    }
    public void kill()
    {
        ((Pacman)GameState.getInstance().getCurrMap().getPacMan()).superPowerUp(true);
        super.kill();
    }
}