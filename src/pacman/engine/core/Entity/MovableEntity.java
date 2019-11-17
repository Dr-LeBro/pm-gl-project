package pacman.engine.core.Entity;

import pacman.engine.graphism.Sprite;
import pacman.engine.physic.movement.Movement;

public class MovableEntity extends Entity {

    protected Movement move;

    protected Sprite movingSprites[];

    public MovableEntity(EntityType kind, Sprite baseSprite, double size)
    {
        super(kind, baseSprite, size);
        //TODO implement with enumTypeDirection.values().length
        //TODO add animated sprite manager
    }

    public MovableEntity(EntityType kind, Sprite baseSprite, double x, double y, double size)
    {
        super(kind, baseSprite, x, y, size);
        //TODO implement with enumTypeMoves.values().length

    }

    public boolean setMovingSprites(Sprite sprites[]){
        if(sprites.length == 4){
            movingSprites = sprites;
            for(int i = 0; i<movingSprites.length; i++){
                movingSprites[i].setSize(sizeX, sizeY);
                movingSprites[i].setPoint(x,y);
            }
            return true;
        }else{
            return false;
        }
    }

    public Sprite getSprite(){
        if(isVisible()){
            //TODO make sprite with directions
            return movingSprites[0];
        }else{
            return null;
        }
    }

    //TODO implement with direction
    /*
    public Sprite getCurrentMovingSprite(){
    }
    */
    /* waiting for spec
    //TODO add sprite implementation
    public void move(Direction dir){
        Movement.
    }

     */
}
