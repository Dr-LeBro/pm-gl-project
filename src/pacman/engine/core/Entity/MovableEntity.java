package pacman.engine.core.Entity;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import pacman.engine.core.GameState;
import pacman.engine.core.Map.Map;
import pacman.engine.graphism.Sprite;
import pacman.engine.physic.movement.Direction;
import pacman.engine.physic.movement.Movement;


public class MovableEntity extends Entity {

    private Movement moveManager;
    private Direction actualDir; // Direction the entity is currently heading toward
    private Direction wishedDirection; // Direction the entity wishes to go
    private Sprite movingSprites[];

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
    public void drawCurrentSprite(){
        GameState.getInstance().getCanvas().removeDrawingElement(currentSprite);
        if(isVisible()) {
            if (this.actualDir == Direction.STANDING) {
                currentSprite = movingSprites[0];
            } else if (this.actualDir == Direction.UP) {
                currentSprite = movingSprites[0];
            } else if (this.actualDir == Direction.DOWN) {
                currentSprite = movingSprites[1];
            } else if (this.actualDir == Direction.RIGHT) {
                currentSprite = movingSprites[2];
            } else if (this.actualDir == Direction.LEFT) {
                currentSprite = movingSprites[3];
            }
            GameState.getInstance().getCanvas().addDrawingElement(currentSprite);
        }
    }


    private void setWishedDirection(KeyCode keyPressed){
        if(keyPressed == KeyCode.UP){
            if (Direction.UP != this.actualDir) this.wishedDirection = Direction.UP;
        }else if(keyPressed == KeyCode.DOWN){
            if (Direction.DOWN != this.actualDir) this.wishedDirection = Direction.DOWN;
        }else if(keyPressed == KeyCode.LEFT){
            if (Direction.LEFT != this.actualDir) this.wishedDirection = Direction.LEFT;
        }else if(keyPressed == KeyCode.RIGHT){
            if (Direction.RIGHT != this.actualDir) this.wishedDirection = Direction.RIGHT;
        }
    }


    public void move(KeyCode keyPressed) {
        setWishedDirection(keyPressed);
        boolean inContactWished = false, inContactDir = false;
        double tempXWished, tempYWished, tempXDir, tempYDir;
        Point2D pointWished = moveManager.move(x, y, this.wishedDirection);
        Point2D pointDir = moveManager.move(x, y, this.actualDir);
        tempXWished = pointWished.getX();
        tempYWished = pointWished.getY();
        tempXDir = pointDir.getX();
        tempYDir = pointDir.getY();
        try {
            Entity[][] walls = GameState.getInstance().getCurrMap().getSurroundingStaticMap((int)(x/ Map.ArrayUnit),(int)(y / Map.ArrayUnit));
            for (int i = 0; i < walls.length && !inContactWished && this.wishedDirection != Direction.STANDING; i++){
                for (int j = 0; j < walls[i].length  && !inContactWished; j++){
                    if (walls[i][j] != null && this.hitBox.isInContact(sizeX, sizeY, tempXWished, tempYWished, walls[i][j])) {
                        inContactWished = true; // Can't go in wished direction
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
            Entity[][] staticEntities = GameState.getInstance().getCurrMap().getSurroundingStaticEntityMap((int)(x/ Map.ArrayUnit),(int)(y / Map.ArrayUnit));
            for (int i = 0; i < staticEntities.length ; i++){
                for (int j = 0; j < staticEntities[i].length ; j++){
                    if (staticEntities[i][j] != null && this.hitBox.isInContact(sizeX, sizeY, x, y, staticEntities[i][j])) {
                        staticEntities[i][j].kill();
                    }
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        if (!inContactWished && this.wishedDirection != Direction.STANDING) {
            this.x = tempXWished;
            this.y = tempYWished;
            this.actualDir = this.wishedDirection;
            this.wishedDirection = Direction.STANDING;
            drawCurrentSprite();
        } else if (!inContactDir) {
            this.x = tempXDir;
            this.y = tempYDir;
        }
        getSprite().setPoint(x, y);
    }
}
