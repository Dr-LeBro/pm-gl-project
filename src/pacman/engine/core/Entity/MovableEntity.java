package pacman.engine.core.Entity;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import pacman.engine.graphism.Sprite;
import pacman.engine.physic.movement.Direction;
import pacman.engine.physic.movement.Movement;
import pacman.gameplay.Game;


public class MovableEntity extends Entity {

    private Movement moveManager;
    private Direction dir;
    private Sprite movingSprites[];

    public MovableEntity(EntityType kind, Sprite baseSprite, double size)
    {
        super(kind, baseSprite, size);
        moveManager = new Movement(0.32);
        dir = Direction.STANDING;
        //TODO add animated sprite manager
    }

    public MovableEntity(EntityType kind, Sprite baseSprite, double x, double y, double size)
    {
        super(kind, baseSprite, x, y, size);
        moveManager = new Movement(0.32);
        dir = Direction.STANDING;
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
            if(dir == Direction.STANDING){
                return baseSprite;
            }else if(dir == Direction.UP){
                return movingSprites[0];
            } else if(dir == Direction.DOWN){
                return movingSprites[1];
            } else if(dir == Direction.RIGHT){
                return movingSprites[2];
            } else if(dir == Direction.LEFT){
                return movingSprites[3];
            }
            return baseSprite;

        }else{
            return null;
        }
    }

    public boolean setCurrentDir(KeyCode keyPressed){
        Direction lastDir = dir;
        if(keyPressed == KeyCode.UP){
            dir = Direction.UP;
        }else if(keyPressed == KeyCode.DOWN){
            dir = Direction.DOWN;
        }else if(keyPressed == KeyCode.LEFT){
            dir = Direction.LEFT;
        }else if(keyPressed == KeyCode.RIGHT){
            dir = Direction.RIGHT;
        }
        return dir != lastDir;
    }

    public void move(){
        //TODO add check of hitbox
        Point2D point = moveManager.move(x, y, dir);
        x = point.getX();
        y = point.getY();
        //System.out.println(x*Game.ratioX + " " + y*Game.ratioY);
        getSprite().setPoint(x*Game.ratioX, y*Game.ratioY);
    }


}
