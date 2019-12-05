package pacman.engine.core.Entity;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import pacman.engine.core.GameState;
import pacman.engine.core.Map.Map;
import pacman.engine.graphism.Sprite;
import pacman.engine.physic.movement.Direction;
import pacman.engine.physic.movement.Movement;

import static pacman.engine.physic.movement.Direction.*;


public class MovableEntity extends Entity {

    protected Movement moveManager;
    protected Direction actualDir; // Direction the entity is currently heading toward
    protected Direction wishedDirection; // Direction the entity wishes to go
    private Sprite movingSprites[];
    protected double spawnX, spawnY;


    public MovableEntity(EntityType kind, Sprite baseSprite, double size, double speed)
    {
        super(kind, baseSprite, size);
        moveManager = new Movement(speed);
        this.actualDir = Direction.STANDING;
        this.wishedDirection= Direction.STANDING;
    }

    public MovableEntity(EntityType kind, Sprite baseSprite, double x, double y, double size, double speed)
    {
        super(kind, baseSprite, x, y, size);
        spawnX = x;
        spawnY = y;
        moveManager = new Movement(speed);
        this.actualDir = Direction.STANDING;
        this.wishedDirection= Direction.STANDING;
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
    public void resize(double x, double y){
        sizeX = x;
        sizeY = y;
        for(int i = 0; i<movingSprites.length; i++){
            GameState.getInstance().getCanvas().removeDrawingElement(movingSprites[i]);
            movingSprites[i].setSize(sizeX, sizeY);
        }
        drawCurrentSprite();
    }

    @Override
    public void drawCurrentSprite(){
        GameState.getInstance().getCanvas().removeDrawingElement(currentSprite);
        if(isVisible()) {
            if (this.actualDir == Direction.STANDING) {
                currentSprite = movingSprites[0];
            } else if (this.actualDir == UP) {
                currentSprite = movingSprites[0];
            } else if (this.actualDir == DOWN) {
                currentSprite = movingSprites[2];
            } else if (this.actualDir == Direction.RIGHT) {
                currentSprite = movingSprites[1];
            } else if (this.actualDir == Direction.LEFT) {
                currentSprite = movingSprites[3];
            }
            GameState.getInstance().getCanvas().addDrawingElement(currentSprite);
        }
    }


    protected void setWishedDirection(KeyCode keyPressed){
        if(keyPressed == KeyCode.UP){
            if (UP != this.actualDir) this.wishedDirection = UP;
        }else if(keyPressed == KeyCode.DOWN){
            if (DOWN != this.actualDir) this.wishedDirection = DOWN;
        }else if(keyPressed == KeyCode.LEFT){
            if (Direction.LEFT != this.actualDir) this.wishedDirection = Direction.LEFT;
        }else if(keyPressed == KeyCode.RIGHT){
            if (Direction.RIGHT != this.actualDir) this.wishedDirection = Direction.RIGHT;
        }
    }

    @Override
    public void kill()
    {
        super.kill();
    }


    public void move(KeyCode keyPressed) {
        setWishedDirection(keyPressed);
        boolean inContactWished = false, inContactWishedTurn = false, inContactDir = false;
        double tempXWished, tempYWished, tempXDir, tempYDir, greatestX, greatestY;
        double tempXWishedTurn = -1;
        double tempYWishedTurn = -1;
        Point2D pointWished = moveManager.move(x, y, this.wishedDirection);
        Point2D pointDir = moveManager.move(x, y, this.actualDir);
        tempXWished = pointWished.getX();
        tempYWished = pointWished.getY();
        tempXDir = pointDir.getX();
        tempYDir = pointDir.getY();

        // TODO if speed > 1 then consider multiple tests at once
        if (this.x > tempXDir) greatestX = this.x;
        else greatestX = tempXDir;
        if (this.y > tempYDir) greatestY = this.y;
        else greatestY = tempYDir;
        if (Math.floor(tempXDir) != Math.floor(this.x) && Math.floor(tempXDir) != tempXDir){
            double differenceX = Math.abs(Math.floor(greatestX) - tempXDir);
            if (this.wishedDirection == UP){
                tempXWishedTurn = Math.floor(greatestX);
                tempYWishedTurn = this.y - differenceX;
            } else if (this.wishedDirection == DOWN){
                tempXWishedTurn = Math.floor(greatestX);
                tempYWishedTurn = this.y + differenceX;
            }
        }
        if (Math.floor(tempYDir) != Math.floor(this.y) && Math.floor(tempYDir) != tempYDir){
            double differenceY = Math.abs(Math.floor(greatestY) - tempYDir);
            if (this.wishedDirection == RIGHT){
                tempXWishedTurn = this.x + differenceY;
                tempYWishedTurn = Math.floor(greatestY);
            } else if (this.wishedDirection == LEFT){
                tempXWishedTurn = this.x - differenceY;
                tempYWishedTurn = Math.floor(greatestY);
            }
        }


        Entity[][] walls = GameState.getInstance().getCurrMap().getSurroundingStaticMap((int)(x/ Map.ArrayUnit),(int)(y / Map.ArrayUnit));
        if(walls != null) {
            for (int i = 0; i < walls.length && !inContactWished && this.wishedDirection != Direction.STANDING; i++) {
                for (int j = 0; j < walls[i].length && !inContactWished; j++) {
                    if (walls[i][j] != null && this.hitBox.isInContact(sizeX, sizeY, tempXWished, tempYWished, walls[i][j])) {
                        inContactWished = true; // Can't go in wished direction
                    }
                }
            }
            for (int i = 0; i < walls.length && !inContactWishedTurn && this.wishedDirection != Direction.STANDING && tempXWishedTurn != -1; i++) {
                for (int j = 0; j < walls[i].length && !inContactWishedTurn; j++) {
                    if (walls[i][j] != null && this.hitBox.isInContact(sizeX, sizeY, tempXWishedTurn, tempYWishedTurn, walls[i][j])) {
                        inContactWishedTurn = true; // Can't go in wished direction
                    }
                }
            }
            for (int i = 0; i < walls.length && !inContactDir; i++) {
                for (int j = 0; j < walls[i].length && !inContactDir; j++) {
                    if (walls[i][j] != null && this.hitBox.isInContact(sizeX, sizeY, tempXDir, tempYDir, walls[i][j])) {
                        inContactDir = true;
                    }
                }
            }
        }

        if (!inContactWished && this.wishedDirection != Direction.STANDING) {
            this.x = tempXWished;
            this.y = tempYWished;
            this.actualDir = this.wishedDirection;
            this.wishedDirection = Direction.STANDING;
            drawCurrentSprite();
        } else if (!inContactWishedTurn && tempXWishedTurn != -1 && tempYWishedTurn != -1) {
            this.x = tempXWishedTurn;
            this.y = tempYWishedTurn;
            this.actualDir = this.wishedDirection;
            this.wishedDirection = Direction.STANDING;
            drawCurrentSprite();
        } else if (!inContactDir) {
            this.x = tempXDir;
            this.y = tempYDir;
        }
        if(isVisible()){
            getSprite().setPoint(x, y);
        }
    }

    public Direction getWishedDirection() {
        return wishedDirection;
    }

    public Direction getActualDir() {
        return actualDir;
    }
}
