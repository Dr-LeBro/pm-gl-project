package pacman.engine.physic.movement;

import javafx.geometry.Point2D;

public class Movement {
    private double speed;

    public Movement (double speed){
        this.speed = speed;
    }

    public Point2D move(double x, double y, Direction direction){
        switch (direction) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            default:
        }
        return new Point2D(x , y);
    }
}