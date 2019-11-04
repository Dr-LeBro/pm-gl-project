package pacman.engine.core.Map;

import pacman.engine.core.Block.Block;
import pacman.engine.core.Entity.EntityType;
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

    public ArrayList<MovableEntity> getMovableList()
    {
        return movableList;
    }

    public StaticEntity[][] getStaticEntityMap()
    {
        return staticEntityMap;
    }

    public Block[][] getStaticMap()
    {
        return this.staticMap;
    }

    public EntityType[][] getMapStaticVisual()
    {
        if(staticMap.length == 0)
            return null;

        EntityType[][] returnMap = new EntityType[staticMap.length][staticMap[0].length];
        
        for(int x = 0; x < staticMap.length; x++)
            for(int y = 0; y < staticMap[0].length; y++)
                returnMap[x][y] = staticMap[x][y].getType();

        for(int x = 0; x < staticEntityMap.length; x++)
            for(int y = 0; y < staticEntityMap[0].length; y++)
                if(returnMap[x][y] == EntityType.empty)
                    returnMap[x][y] = staticEntityMap[x][y].getType();

        return returnMap;
    }



}
