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

    protected boolean visible;

    protected Sprite sprite;

    public Entity(EntityType kind) {
        type = kind;
        visible = false;

    }

    public Entity(EntityType kind, double x, double y) {
        type = kind;
        visible = false;
        this.x = x;
        this.y = y;
    }

    public EntityType getType() {
        return type;
    }

    public void loadSprite(Sprite sprite){
        this.sprite = sprite;
    }

    //send false if is nothing to show
    public boolean spawn(){
        if(sprite == null){
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

    public boolean isVisible() {
        return visible;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
