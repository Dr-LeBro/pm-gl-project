package pacman.engine.graphism;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public abstract class Sprite implements DrawableObject{
    protected double width; //logical width, have to apply ratio
    protected double height; //logical height, have to apply ratio
    protected Point2D point;
    protected double ratioX;
    protected double ratioY;
    protected boolean updateRatio;
    protected String name;
    protected String key;

    Sprite(String name){
        point = new Point2D(0, 0);
        this.name = name;
    }

    public void loadSprite(){}

    @Override
    public void load(){
        loadSprite();
    }

    @Override
    public void setRatios(double ratioX, double ratioY) {
        if(ratioX == this.ratioX && ratioY == this.ratioY){
            updateRatio = false;
        }else{
            updateRatio = true;
        }
        this.ratioX = ratioX;
        this.ratioY = ratioY;
    }

    @Override
    public Point2D getPoint() {
        return point;
    }

    @Override
    public void setPoint(double x, double y) {
        this.point = new Point2D(x, y);
    }

    @Override
    public void setSize(double width, double height){
        setWidth(width);
        setHeight(height);
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public boolean isReadyToDraw(){
        return false;
    }

    @Override
    public void draw(GraphicsContext gc){ }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}
