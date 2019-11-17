package pacman.engine.core.Entity;

import pacman.engine.graphism.Sprite;
import pacman.engine.physic.hitBox.HitBox;

import static pacman.engine.core.Entity.EntityType.EMPTY;

public abstract class Entity {

    protected EntityType type;
    //TODO implement hitbox calls

    protected HitBox hitBox;

    protected double x;
    protected double y;
    protected double sizeX;
    protected double sizeY;

    protected boolean visible;

    protected Sprite baseSprite;

    public Entity(EntityType kind, Sprite sprite, double size) {
        type = kind;
        visible = false;
        sizeX = sizeY = size;
        this.baseSprite = sprite;
        this.baseSprite.setPoint(x,y);
        this.baseSprite.setSize(size, size);
    }

    public Entity(EntityType kind, Sprite sprite, double x, double y, double size) {
        type = kind;
        visible = false;
        this.x = x;
        this.y = y;
        sizeX = sizeY = size;
        this.baseSprite = sprite;
        this.baseSprite.setPoint(x,y);
        this.baseSprite.setSize(size, size);
    }

    public EntityType getType() {
        return type;
    }

    public void setSprite(Sprite sprite){
        this.baseSprite = sprite;
        this.baseSprite.setPoint(x, y);
    }

    //send false if is nothing to show
    public boolean spawn(){
        if(baseSprite == null){
            visible = false;
        }else{
            visible = true;
        }
        return visible;
    }

    public void kill(){
        visible = false;
    }

    public void delete(){
        type = EMPTY;
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
            return baseSprite;
        }else{
            return null;
        }
    }
}
