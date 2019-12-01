package pacman.gameplay.Bonus.advantageBonus;

import pacman.engine.core.Entity.EntityType;
import pacman.engine.core.Entity.StaticEntity;
import pacman.engine.core.Map.Map;
import pacman.engine.graphism.ResizableCanvas;
import pacman.engine.graphism.StaticSprite;
import pacman.gameplay.scoreManager.Score;

public class PacGomme extends StaticEntity {
    public PacGomme(int posX, int posY) {
        super(EntityType.PAC_GOMME, new StaticSprite("file:sprites/pacgomme.png", "pacgomme"), (double) posX * Map.ArrayUnit, (double) posY * Map.ArrayUnit, Map.ArrayUnit*0.99);
    }
    public void kill(ResizableCanvas canvas)
    {
        super.kill(canvas);
        Score.getInstance().add(10);
    }
}
