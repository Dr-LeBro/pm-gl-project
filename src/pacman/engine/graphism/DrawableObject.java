package pacman.engine.graphism;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public interface DrawableObject {
    void draw(GraphicsContext gc);

    void setSize(double width, double height);

    double getWidth();

    void setWidth(double width);

    double getHeight();

    void setHeight(double height);

    boolean isReadyToDraw();

    Point2D getPoint();

    void setPoint(double x, double y);

    String getName();

    void setKey(String key);

    String getKey();
}
