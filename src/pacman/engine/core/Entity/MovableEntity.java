package pacman.engine.core.Entity;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import pacman.engine.core.Block.Block;
import pacman.engine.graphism.ResizableCanvas;
import pacman.engine.graphism.Sprite;
import pacman.engine.physic.movement.Direction;
import pacman.engine.physic.movement.Movement;
import pacman.gameplay.Game;


public class MovableEntity extends Entity {

    private Movement moveManager;
    private Direction dir;
    private Sprite movingSprites[];

    public MovableEntity(EntityType kind, Sprite baseSprite, double size, double speed)
    {
        super(kind, baseSprite, size);
        moveManager = new Movement(speed);
        dir = Direction.STANDING;
        //TODO add animated sprite manager
    }

    public MovableEntity(EntityType kind, Sprite baseSprite, double x, double y, double size, double speed)
    {
        super(kind, baseSprite, x, y, size);
        moveManager = new Movement(speed);
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

    @Override
    public void drawCurrentSprite(ResizableCanvas canvas){
        canvas.removeDrawingElement(currentSprite);
        if(isVisible()) {
            if (dir == Direction.STANDING) {
                currentSprite = movingSprites[0];
            } else if (dir == Direction.UP) {
                currentSprite = movingSprites[0];
            } else if (dir == Direction.DOWN) {
                currentSprite = movingSprites[1];
            } else if (dir == Direction.RIGHT) {
                currentSprite = movingSprites[2];
            } else if (dir == Direction.LEFT) {
                currentSprite = movingSprites[3];
            }
            canvas.addDrawingElement(currentSprite);
        }
    }

    private boolean setCurrentDir(KeyCode keyPressed){
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

    public void move(KeyCode keyPressed, ResizableCanvas canvas) {
        if(setCurrentDir(keyPressed)){
            drawCurrentSprite(canvas);
        }
        boolean inContact = false;
        double tempX, tempY;
        Point2D point = moveManager.move(x, y, dir);
        tempX = point.getX();
        tempY = point.getY();
        if (Game.labyrynth == null)
            System.out.println("NULL");
        try {
            //TODO REPLACE WITH getSurroundingStaticMap later
            //System.out.println(this.map.getStaticMap());

            Block[][] walls = Game.labyrynth.getStaticMap();
            for (int i = 0; i < Game.labyrynth.getMaxX(); i++) {
                for (int j = 0; j < Game.labyrynth.getMaxY(); j++) {
                    if (walls[i][j] != null && this.hitBox.isInContact(sizeX, sizeY, tempX, tempY, walls[i][j])) {
                        inContact = true;
                        break;
                    }
                }
            }
        } catch (NullPointerException e) {
             e.printStackTrace();
        }

        if (!inContact) {
            this.x = tempX;
            this.y = tempY;
        }
            getSprite().setPoint(x, y);
    }

}
