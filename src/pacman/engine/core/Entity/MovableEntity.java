package pacman.engine.core.Entity;

import pacman.engine.graphism.Sprite;
import pacman.engine.physic.movement.Movement;

public class MovableEntity extends Entity {

    protected Movement move;

    protected Sprite movingSprites[];

    public MovableEntity(EntityType kind)
    {
        super(kind);
        //TODO implement with enumTypeDirection.values().length
        movingSprites = new Sprite[4];
    }

    public MovableEntity(EntityType kind, double x, double y)
    {
        super(kind, x, y);
        //TODO implement with enumTypeMoves.values().length
        movingSprites = new Sprite[4];
    }

    public boolean loadMovingSprites(Sprite sprites[]){
        if(sprites.length == 4){
            movingSprites = sprites;
            return true;
        }else{
            return false;
        }
    }

    //TODO implement with direction
    /*
    public Sprite getCurrentMovingSprite(){
    }
    */


    /* waiting for spec
    public void move(Direction dir){
        Movement.
    }

     */
}
