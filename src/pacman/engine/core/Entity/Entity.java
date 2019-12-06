package pacman.engine.core.Entity;

import pacman.engine.core.GameState;
import pacman.engine.graphism.Sprite;
import pacman.engine.physic.hitBox.HitBox;

import static pacman.engine.core.Entity.EntityType.EMPTY;

public abstract class Entity {

    protected EntityType type;

    protected HitBox hitBox;

    protected double x;
    protected double y;
    protected double sizeX;
    protected double sizeY;

    protected boolean visible;

    protected boolean invulnerable;

    protected Sprite currentSprite;

    public Entity(EntityType kind, Sprite sprite, double size) {
        type = kind;
        visible = false;
        invulnerable = false;
        sizeX = sizeY = size;
        this.currentSprite = sprite;
        this.currentSprite.setPoint(x,y);
        this.currentSprite.setSize(size, size);
        hitBox = new HitBox();
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

    public void setSprite(Sprite sprite){
        if(currentSprite != null){
            GameState.getInstance().getCanvas().removeDrawingElement(currentSprite);
        }
        this.currentSprite = sprite;
        this.currentSprite.setPoint(x, y);
    }

    public void drawCurrentSprite(){
        if(isVisible()) {
            GameState.getInstance().getCanvas().addDrawingElement(currentSprite);
        }
    }

    public void resize(double x, double y){
        sizeX = x;
        sizeY = y;
        currentSprite.setSize(sizeX, sizeY);
        GameState.getInstance().getCanvas().removeDrawingElement(currentSprite);
        spawn();
    }

    //send false if is nothing to show
    public boolean spawn(){
        if(currentSprite == null){
            visible = false;
        }else{
            visible = true;
            drawCurrentSprite();
        }
        return visible;
    }

    public void respawn(double x, double y){
        this.x = x;
        this.y = y;
        spawn();
    }

    public void setInvulnerable(boolean val)
    {
        invulnerable = val;
    }

    public boolean getInvulnerable()
    {
        return invulnerable;
    }

    public void kill(){
        visible = false;
        GameState.getInstance().getCanvas().removeDrawingElement(currentSprite);
    }

    public void delete(){
        type = EMPTY;
        visible = false;
        GameState.getInstance().getCanvas().removeDrawingElement(currentSprite);
        System.out.println("");
    }

    public void move(){
        System.out.println("This entity can't move");
    }

    public double getY()
    {
        return y;
    }

    public double getX() {
        return x;
    }

    public double getSizeX() { return sizeX; }

    public double getSizeY() { return sizeY; }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean vis) {
        visible = vis;
    }

    public Sprite getSprite(){
        if(isVisible()){
            return currentSprite;
        }else{
            return null;
        }
    }
}
