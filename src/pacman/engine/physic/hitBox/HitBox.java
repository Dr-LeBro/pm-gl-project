package pacman.engine.physic.hitBox;

import pacman.engine.core.Entity.Entity;

public class HitBox {
    public boolean isInContact(double sizeX, double sizeY, double x, double y, Entity e) {
        if(e != null)
            return (Math.abs(x - e.getX()) < (sizeX + e.getSizeX())/2.0 && Math.abs(y - e.getY()) < (sizeY + e.getSizeY())/2.0) && e.isVisible();
        return false;
    }
}
