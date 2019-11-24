package pacman.engine.physic.hitBox;

import java.lang.Math;
import pacman.engine.core.Entity.Entity;

public class HitBox {
    public boolean isInContact(double sizeX, double sizeY, double x, double y, Entity e){
        return Math.abs(x - e.getX()) <= sizeX + e.getSizeX() && Math.abs(y - e.getY()) <= sizeY + e.getSizeY();
    }
}
