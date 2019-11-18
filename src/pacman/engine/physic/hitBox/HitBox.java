package pacman.engine.physic.hitBox;

import java.lang.Math;
import pacman.engine.core.Entity.Entity;

public class HitBox {
    public boolean isInContact(double x, double y, Entity e){
        return Math.abs(x - e.getX()) <= x + e.getSizeX() && Math.abs(y - e.getY()) <= y + e.getSizeX();
    }
}
