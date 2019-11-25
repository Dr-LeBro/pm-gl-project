package pacman.engine.core.Map;

import pacman.engine.core.Block.Block;
import pacman.engine.core.Entity.Entity;
import pacman.engine.core.Entity.EntityType;
import pacman.engine.core.Entity.MovableEntity;
import pacman.engine.core.Entity.StaticEntity;
import pacman.engine.core.Utilis.Matrix;
import pacman.engine.graphism.Sprite;
import pacman.engine.graphism.StaticSprite;

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

    public MovableEntity getPacMan()
    {
        for (int i = 0; i < movableList.size(); i++) {
            if(movableList.get(i).getType() == EntityType.PACMAN)
                return movableList.get(i);
        }
        return null;
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
        return (StaticEntity[][])Matrix.extractSubMatrix(this.staticEntityMap, Math.max(0, x-2), Math.min(this.staticEntityMap.length-1, x+2), Math.max(0, y-2), Math.min(this.staticEntityMap[0].length-1, y+2));
    }

    public ArrayList<Sprite> getStaticMapVisual()
    {
        if(staticMap.length == 0)
            return null;

        ArrayList<Sprite> returnMap = new ArrayList<Sprite>();

        for(int x = 0; x < staticMap.length; x++)
            for(int y = 0; y < staticMap[0].length; y++) {
                if(staticMap[x][y] != null) {
                    staticMap[x][y].setVisible(true);
                    returnMap.add(staticMap[x][y].getSprite());
                }
                else
                {
                    Sprite temp = null;
                    if(staticMap[Math.max(0, x-1)][y] != null)
                    {
                        temp = new StaticSprite("file:sprites/empty_border_left.png", "borderU");
                    }
                    if(staticMap[Math.min(staticMap.length-1, x+1)][y] != null)
                    {
                        temp = new StaticSprite("file:sprites/empty_border_right.png", "borderD");

                    }
                    if(staticMap[x][Math.max(0,y-1)] != null)
                    {
                        temp = new StaticSprite("file:sprites/empty_border_top.png", "borderL");
                    }
                    if(staticMap[x][Math.min(staticMap[0].length-1,y+1)] != null)
                    {
                        temp = new StaticSprite("file:sprites/empty_border_down.png", "borderR");
                    }
                    if(temp != null)
                    {
                        temp.setPoint(x*Map.ArrayUnit,y*Map.ArrayUnit);
                        temp.setSize(Map.ArrayUnit,Map.ArrayUnit);
                        returnMap.add(temp);
                    }
                }
            }

        return returnMap;
    }

    public Entity[][] getStaticEntityMapVisual()
    {
        if(staticMap.length == 0)
            return null;

        Entity[][] returnMap = new Entity[staticMap.length][staticMap[0].length];

        for(int x = 0; x < staticEntityMap.length; x++)
            for(int y = 0; y < staticEntityMap[0].length; y++)
                    returnMap[x][y] = staticEntityMap[x][y];

        return returnMap;
    }

    public Entity[][] getCompleteMapStaticVisual()
    {
        if(staticMap.length == 0)
            return null;

        Entity[][] returnMap = new Entity[staticMap.length][staticMap[0].length];

        for(int x = 0; x < staticMap.length; x++)
            for(int y = 0; y < staticMap[0].length; y++)
                returnMap[x][y] = staticMap[x][y];

        for(int x = 0; x < staticEntityMap.length; x++)
            for(int y = 0; y < staticEntityMap[0].length; y++)
                if(returnMap[x][y] == null)
                    returnMap[x][y] = staticEntityMap[x][y];

        return returnMap;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }
}
