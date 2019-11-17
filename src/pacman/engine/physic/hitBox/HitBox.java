package pacman.engine.physic.hitBox;

import java.lang.Math;
import pacman.engine.core.Entity.Entity;

public class HitBox {
    public boolean isInContact(Entity e1, Entity e2){
        return Math.abs(e1.getX() - e2.getX()) <= e1.getSizeX() + e2.getSizeX() && Math.abs(e1.getY() - e2.getY()) <= e1.getSizeX() + e2.getSizeX();
    }
}
