package pacman.gameplay.Bonus.scoreBonus;

import pacman.engine.core.Entity.EntityType;
import pacman.engine.core.Entity.StaticEntity;
import pacman.engine.core.Map.Map;
import pacman.engine.graphism.StaticSprite;
import pacman.engine.sound.Music;
import pacman.gameplay.scoreManager.Score;

public class Fruit extends StaticEntity {
    public Fruit(double x, double y, double size, String spritePath) {
            super(EntityType.FRUIT, new StaticSprite(spritePath, "bonus"+spritePath), (double) x * Map.ArrayUnit, (double) y * Map.ArrayUnit, Map.ArrayUnit*3);
    }

    @Override
    public void kill()
    {
        Music.getInstance().playSound(3);
        System.out.println("");
        super.kill();
        Score.getInstance().add(200);
    }
}
