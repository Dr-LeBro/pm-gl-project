package pacman.engine.physic.hitBox;

import pacman.engine.core.Entity.Entity;
import pacman.engine.core.GameState;
import pacman.engine.core.Map.Map;

public class HitBox {
    public static boolean isInContact(double sizeX, double sizeY, double x, double y, Entity e) {
        if(e != null)
            return (Math.abs(x - e.getX()) < (sizeX + e.getSizeX())/2.0 && Math.abs(y - e.getY()) < (sizeY + e.getSizeY())/2.0) && e.isVisible();
        return false;
    }

    public boolean isInContact(Entity e, Entity bloc) {
        return (Math.abs(e.getX() - bloc.getX()) < (e.getSizeX() + bloc.getSizeX())/2.0 && Math.abs(e.getY() - bloc.getY()) < (e.getSizeY() + bloc.getSizeY())/2.0) && bloc.isVisible();
    }

    public static boolean canBePlaced(double x,double y, Entity e) {
        Entity[][] walls = GameState.getInstance().getCurrMap().getSurroundingStaticMap((int)x/Map.ArrayUnit, (int)y/Map.ArrayUnit);
        if(walls != null) {
            for (int i = 0; i < walls.length; i++) {
                for (int j = 0; j < walls[i].length; j++) {
                    if (walls[i][j] != null && isInContact(e.getSizeX(), e.getSizeY(), x, y, walls[i][j])) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public static boolean canBePlaced(double x,double y, Entity e, double sizeX,double sizeY) {
        Entity[][] walls = GameState.getInstance().getCurrMap().getSurroundingStaticMap((int)x/Map.ArrayUnit, (int)y/Map.ArrayUnit);
        if(walls != null) {
            for (int i = 0; i < walls.length; i++) {
                for (int j = 0; j < walls[i].length; j++) {
                    if (walls[i][j] != null && isInContact(sizeX, sizeY, x, y, walls[i][j])) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
}
