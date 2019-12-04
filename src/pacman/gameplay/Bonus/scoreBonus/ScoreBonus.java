package pacman.gameplay.Bonus.scoreBonus;

import pacman.engine.core.GameState;
import pacman.engine.core.Map.Map;
import pacman.engine.physic.hitBox.HitBox;

import java.util.Random;

public class ScoreBonus {
    private static ScoreBonus INSTANCE = new ScoreBonus();

    private ScoreBonus(){}

    public static ScoreBonus getInstance(){
        return INSTANCE;
    }

    public void spawnBonus() {
        Fruit toAdd;
        boolean inContact = true;
        while(true) {
            int x = new Random().nextInt(40);
            int y = new Random().nextInt(40);

            if (new Random().nextInt(20) > 10) {
                toAdd = new Fruit(x , y , 3, "file:sprites/cherry.png");
            } else {
                toAdd = new Fruit(x , y , 3, "file:sprites/banana.png");
            }

            if(HitBox.canBePlaced(x * Map.ArrayUnit, y * Map.ArrayUnit, toAdd))
            {
                if(GameState.getInstance().getCurrMap().getStaticEntityMap()[x][y] != null)
                    GameState.getInstance().getCurrMap().getStaticEntityMap()[x][y].kill();
                GameState.getInstance().getCurrMap().setStaticEntity(x,y,toAdd);
                toAdd.spawn();
                System.out.println(toAdd.getX()/Map.ArrayUnit + " " + toAdd.getY()/Map.ArrayUnit);
                return;
            }
        }
    }
}
