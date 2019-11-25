package pacman.engine.core.Map;

import pacman.engine.core.Block.Block;
import pacman.engine.core.Entity.EntityType;
import pacman.engine.core.Entity.MovableEntity;
import pacman.engine.core.Entity.StaticEntity;
import pacman.engine.core.Utilis.Matrix;

import java.util.ArrayList;

public class Map {

    public static final int ArrayUnit = 10;

    private Block[][] staticMap; //walls
    private StaticEntity[][] staticEntityMap; //pac-gommes, fruits, etc
    private ArrayList<MovableEntity> movableList; //pac-man, ghosts
    private int maxX = 0;
    private int maxY = 0;
    public String id = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map(int x, int y)
    {
        staticMap = new Block[x][y];
        staticEntityMap = new StaticEntity[x][y];
        movableList = new ArrayList<MovableEntity>();
        maxX = x;
        maxY = y;
    }

    // TODO A RETIRER
    public void afficheContenuMap(){
        for (int i = 0; i < maxX; i++){
            for (int j = 0; j < maxY; j++){
                if (staticMap[i][j] != null) System.out.println("Bloc en " + i + " " + j);
                if (staticEntityMap[i][j] != null) System.out.println("Static entity en " + i + " " + j);
            }
        }
    }
    // A RETIRER

    public void addMovableToList(MovableEntity e)
    {
        movableList.add(e);
    }

    public void setStaticEntity(int x,int y, StaticEntity e)
    {
        if(x < 0 || x > maxX || y < 0 || y < maxY)
            return;
        staticEntityMap[x][y] = e;
    }

    public void setStaticMap(int x,int y, Block e)
    {
        if(x < 0 || x > maxX || y < 0 || y > maxY)
            return;
        staticMap[x][y] = e;
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

    public Block[][] getSurroundingStaticMap(int x, int y)
    {
        if(x < 0 || x > maxX || y < 0 || y < maxY)
            return null;
        return (Block[][])Matrix.extractSubMatrix(this.staticMap, Math.max(0, x-1), Math.min(this.staticMap.length-1, x+1), Math.max(0, y-1), Math.min(this.staticMap[0].length-1, y+1));
    }

    public StaticEntity[][] getSurroundingStaticEntityMap(int x, int y)
    {
        if(x < 0 || x > maxX || y < 0 || y < maxY)
            return null;
        return (StaticEntity[][])Matrix.extractSubMatrix(this.staticEntityMap, Math.max(0, x-1), Math.min(this.staticEntityMap.length-1, x+1), Math.max(0, y-1), Math.min(this.staticEntityMap[0].length-1, y+1));
    }

    public EntityType[][] getStaticMapVisual()
    {
        if(staticMap.length == 0)
            return null;

        EntityType[][] returnMap = new EntityType[staticMap.length][staticMap[0].length];

        for(int x = 0; x < staticMap.length; x++)
            for(int y = 0; y < staticMap[0].length; y++)
                returnMap[x][y] = staticMap[x][y].getType();

        return returnMap;
    }

    public EntityType[][] getStaticEntityMapVisual()
    {
        if(staticMap.length == 0)
            return null;

        EntityType[][] returnMap = new EntityType[staticMap.length][staticMap[0].length];

        for(int x = 0; x < staticEntityMap.length; x++)
            for(int y = 0; y < staticEntityMap[0].length; y++)
                    returnMap[x][y] = staticEntityMap[x][y].getType();

        return returnMap;
    }

    public EntityType[][] getCompleteMapStaticVisual()
    {
        if(staticMap.length == 0)
            return null;

        EntityType[][] returnMap = new EntityType[staticMap.length][staticMap[0].length];

        for(int x = 0; x < staticMap.length; x++)
            for(int y = 0; y < staticMap[0].length; y++)
                returnMap[x][y] = staticMap[x][y].getType();

        for(int x = 0; x < staticEntityMap.length; x++)
            for(int y = 0; y < staticEntityMap[0].length; y++)
                if(returnMap[x][y] == EntityType.EMPTY)
                    returnMap[x][y] = staticEntityMap[x][y].getType();

        return returnMap;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }
}
