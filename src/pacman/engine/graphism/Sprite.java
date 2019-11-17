package pacman.engine.graphism;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public abstract class Sprite implements DrawableObject{
    protected double width;
    protected double height;
    protected Point2D point;
    protected double ratioFromParent;
    protected String name;
    protected String key;

    Sprite(String name){
        point = new Point2D(0, 0);
        this.name = name;
    }

    public void loadSprite(){}

    protected void reloadImageSize(){}

    public double getRatioFromParent() {
        return ratioFromParent;
    }

    public void setRatioFromParent(double ratioFromParent) {
        this.ratioFromParent = ratioFromParent;
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
        reloadImageSize();
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
    public void draw(GraphicsContext gc){}

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
