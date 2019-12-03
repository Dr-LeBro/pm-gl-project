package pacman.engine.physic.hitBox;

import pacman.engine.core.Entity.Entity;

public class HitBox {
    public boolean isInContact(double sizeX, double sizeY, double x, double y, Entity e) {
        if(e != null)
            return (Math.abs(x - e.getX()) < (sizeX + e.getSizeX())/2.0 && Math.abs(y - e.getY()) < (sizeY + e.getSizeY())/2.0) && e.isVisible();
        return false;
    }

    public boolean isInContact(Entity e, Entity bloc) {
        return (Math.abs(e.getX() - bloc.getX()) < (e.getSizeX() + bloc.getSizeX())/2.0 && Math.abs(e.getY() - bloc.getY()) < (e.getSizeY() + bloc.getSizeY())/2.0) && bloc.isVisible();
    }
}
