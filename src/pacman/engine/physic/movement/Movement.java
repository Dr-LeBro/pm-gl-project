package pacman.engine.physic.movement;

import javafx.geometry.Point2D;

public class Movement {
    private double speed;

    public Movement (double speed){
        this.speed = speed;
    }

    public Point2D move(double x, double y, Direction direction){
        switch (direction) {
            case up:
                y -= speed;
                break;
            case down:
                y += speed;
                break;
            case left:
                x -= speed;
                break;
            case right:
                y += speed;
                break;
            default:
        }
        return new Point2D(x , y);
    }
}