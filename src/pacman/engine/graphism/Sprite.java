package pacman.engine.graphism;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public abstract class Sprite implements DrawableObject{
    protected double width;
    protected double height;
    protected Point2D point;
    protected double ratioFromParent;

    Sprite(){
        point = new Point2D(0, 0);
    }

    public void loadSprite(){}

    public void draw(GraphicsContext gc){}

    protected void reloadImageSize(){}

    public void setSize(double width, double height){
        setWidth(width);
        setHeight(height);
        reloadImageSize();
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }


    public double getRatioFromParent() {
        return ratioFromParent;
    }

    public void setRatioFromParent(double ratioFromParent) {
        this.ratioFromParent = ratioFromParent;
    }

    public boolean isReadyToDraw(){
        return false;
    }

    public Point2D getPoint() {
        return point;
    }

    public void setPoint(double x, double y) {
        this.point = new Point2D(x, y);
    }
}
