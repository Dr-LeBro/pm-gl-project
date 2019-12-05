package pacman.gameplay.Bonus.advantageBonus;

import pacman.engine.core.Entity.EntityType;
import pacman.engine.core.Entity.StaticEntity;
import pacman.engine.core.Map.Map;
import pacman.engine.graphism.StaticSprite;
import pacman.gameplay.Bonus.scoreBonus.ScoreBonus;
import pacman.gameplay.scoreManager.Score;

import java.util.Random;

public class PacGomme extends StaticEntity {
    public PacGomme(int posX, int posY) {
        super(EntityType.PAC_GOMME, new StaticSprite("file:sprites/pacgomme.png", "pacgomme"), (double) posX * Map.ArrayUnit, (double) posY * Map.ArrayUnit, Map.ArrayUnit*0.99);
    }
    public void kill()
    {
        super.kill();
        Score.getInstance().add(10);
        if(new Random().nextInt(100)==1)
            ScoreBonus.getInstance().spawnBonus();
    }
}
