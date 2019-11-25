package pacman.engine.core.Entity;

import pacman.engine.graphism.ResizableCanvas;
import pacman.engine.graphism.Sprite;
import pacman.engine.physic.hitBox.HitBox;

import static pacman.engine.core.Entity.EntityType.EMPTY;

public abstract class Entity {
    // TODO MAKE SO THAT THE HITBOX CAN BE TURNED OFF
    protected EntityType type;

    protected HitBox hitBox;

    protected double x;
    protected double y;
    protected double sizeX;
    protected double sizeY;

    protected boolean visible;

    protected Sprite currentSprite;


    public Entity(EntityType kind, Sprite sprite, double size) {
        type = kind;
        visible = false;
        sizeX = sizeY = size;
        this.currentSprite = sprite;
        this.currentSprite.setPoint(x,y);
        this.currentSprite.setSize(size, size);
        hitBox = new HitBox();
        this.x = 0;
        this.y = 0;
    }

    public Entity(EntityType kind, Sprite sprite, double x, double y, double size) {
        type = kind;
        visible = false;
        this.x = x;
        this.y = y;
        sizeX = sizeY = size;
        this.currentSprite = sprite;
        this.currentSprite.setPoint(x,y);
        this.currentSprite.setSize(size, size);
        hitBox = new HitBox();
    }

    public EntityType getType() {
        return type;
    }

    public void setSprite(Sprite sprite, ResizableCanvas canvas){
        if(currentSprite != null){
            canvas.removeDrawingElement(currentSprite);
        }
        this.currentSprite = sprite;
        this.currentSprite.setPoint(x, y);
    }

    public void drawCurrentSprite(ResizableCanvas canvas){
        if(isVisible()) {
            canvas.addDrawingElement(currentSprite);
        }
    }

    //send false if is nothing to show
    public boolean spawn(ResizableCanvas canvas){
        if(currentSprite == null){
            visible = false;
        }else{
            visible = true;
            drawCurrentSprite(canvas);
        }
        return visible;
    }


    public void kill(ResizableCanvas canvas){
        visible = false;
        canvas.removeDrawingElement(currentSprite);
    }

    public void delete(ResizableCanvas canvas){
        type = EMPTY;
        canvas.removeDrawingElement(currentSprite);
    }

    public void move(){
        System.out.println("This entity can't move");
    }

    public double getY()
    {
        return y;
    }

    public double getX()
    {
        return x;
    }

    public double getSizeX() { return sizeX; }

    public double getSizeY() { return sizeY; }

    public boolean isVisible() {
        return visible;
    }

    public Sprite getSprite(){
        if(isVisible()){
            return currentSprite;
        } else {
            return null;
        }
    }
}
