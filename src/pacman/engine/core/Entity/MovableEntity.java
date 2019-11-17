package pacman.engine.core.Entity;

import pacman.engine.graphism.AnimatedSprite;
import pacman.engine.graphism.Sprite;
import pacman.engine.graphism.StaticSprite;
import pacman.engine.physic.movement.Movement;

import java.util.ArrayList;

public class MovableEntity extends Entity {

    protected Movement move;

    protected Sprite movingSprites[];

    public MovableEntity(EntityType kind)
    {
        super(kind);
        //TODO implement with enumTypeDirection.values().length
        movingSprites = new AnimatedSprite[4];
        //TODO add animated sprite manager
        movingSprites[0] = new StaticSprite("", "");
        movingSprites[1] = new StaticSprite("", "");
        movingSprites[2] = new StaticSprite("", "");
        movingSprites[3] = new StaticSprite("", "");
    }

    public MovableEntity(EntityType kind, double x, double y)
    {
        super(kind, x, y);
        //TODO implement with enumTypeMoves.values().length
        movingSprites = new Sprite[4];

        sprite.setPoint(x,y);
        for(int i = 0; i<movingSprites.length; i++){
            movingSprites[i].setPoint(x,y);
        }
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
    //TODO add sprite implementation
    public void move(Direction dir){
        Movement.
    }

     */
}
