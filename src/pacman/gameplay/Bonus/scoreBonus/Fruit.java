package pacman.gameplay.Bonus.scoreBonus;

import pacman.engine.core.Entity.EntityType;
import pacman.engine.core.Entity.StaticEntity;
import pacman.engine.core.Map.Map;
import pacman.engine.graphism.StaticSprite;
import pacman.gameplay.scoreManager.Score;

public class Fruit extends StaticEntity {
    public Fruit(double x, double y, double size, String spritePath) {
            super(EntityType.FRUIT, new StaticSprite(spritePath, "bonus"), (double) x * Map.ArrayUnit, (double) y * Map.ArrayUnit, Map.ArrayUnit*3);
            System.out.println("Fruit!!!!!!");
    }

    @Override
    public void kill()
    {
        super.kill();
        Score.getInstance().add(200);
    }
}
