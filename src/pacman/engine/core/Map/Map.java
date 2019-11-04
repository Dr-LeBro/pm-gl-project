package pacman.engine.core.Map;

import pacman.engine.core.Block.Block;
import pacman.engine.core.Entity.MovableEntity;
import pacman.engine.core.Entity.StaticEntity;

import java.util.ArrayList;

public class Map {

    private Block[][] staticMap; //walls
    private StaticEntity[][] staticEntityMap; //pac-gommes, fruits, etc
    private ArrayList<MovableEntity> movableList; //pac-man, ghosts

    Map(int x, int y)
    {
        staticMap = new Block[x][y];
        staticEntityMap = new StaticEntity[x][y];
        movableList = new ArrayList<MovableEntity>();
    }

}
