package pacman.engine.physic.movement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import javafx.geometry.Point2D;

class MovementTest {

    @ParameterizedTest
    @ValueSource(ints = {-100, -1, 0, 1, 100})
    void setSpeed(int i) {
        Movement mvt = new Movement(i);
        Assertions.assertEquals(mvt.getSpeed(), i);
    }

    @ParameterizedTest
    @ValueSource(ints = {-100, -1, 0, 1, 100})
    void moveUp(int i){
        Movement mvt = new Movement(i);
        Point2D newPos = mvt.move(0, 0, Direction.UP);
        Assertions.assertEquals(newPos.getX(), 0);
        Assertions.assertEquals(newPos.getY(), -i);
    }

    @ParameterizedTest
    @ValueSource(ints = {-100, -1, 0, 1, 100})
    void moveDown(int i){
        Movement mvt = new Movement(i);
        Point2D newPos = mvt.move(0, 0, Direction.DOWN);
        Assertions.assertEquals(newPos.getX(), 0);
        Assertions.assertEquals(newPos.getY(), i);
    }

    @ParameterizedTest
    @ValueSource(ints = {-100, -1, 0, 1, 100})
    void moveLeft(int i){
        Movement mvt = new Movement(i);
        Point2D newPos = mvt.move(0, 0, Direction.LEFT);
        Assertions.assertEquals(newPos.getX(), -i);
        Assertions.assertEquals(newPos.getY(), 0);
    }

    @ParameterizedTest
    @ValueSource(ints = {-100, -1, 0, 1, 100})
    void moveRight(int i){
        Movement mvt = new Movement(i);
        Point2D newPos = mvt.move(0, 0, Direction.RIGHT);
        Assertions.assertEquals(newPos.getX(), i);
        Assertions.assertEquals(newPos.getY(), 0);
    }

    @ParameterizedTest
    @ValueSource(ints = {-100, -1, 0, 1, 100})
    void moveStanding(int i){
        Movement mvt = new Movement(i);
        Point2D newPos = mvt.move(0, 0, Direction.STANDING);
        Assertions.assertEquals(newPos.getX(), 0);
        Assertions.assertEquals(newPos.getY(), 0);
    }

}