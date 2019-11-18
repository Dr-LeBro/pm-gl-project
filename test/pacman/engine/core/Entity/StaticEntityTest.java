package pacman.engine.core.Entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

class StaticEntityTest {

    @ParameterizedTest
    @EnumSource(EntityType.class)
    void getTypeSimple(EntityType type) {
//        Entity entityTest = new StaticEntity(type);
//        Assertions.assertEquals(type, entityTest.getType());
    }

    @ParameterizedTest
    @EnumSource(EntityType.class)
    void getTypeCoord(EntityType type) {
//        Entity entityTest = new StaticEntity(type, 0, 0);
//        Assertions.assertEquals(type, entityTest.getType());
    }

    @ParameterizedTest
    @ValueSource(ints = {-100, -1, 0, 1, 100})
    void getX(int x) {
//        Entity entityTest = new StaticEntity(EntityType.EMPTY, x, 0);
//        Assertions.assertEquals(x, entityTest.getX());
    }

    @ParameterizedTest
    @ValueSource(ints = {-100, -1, 0, 1, 100})
    void getY(int y) {
//        Entity entityTest = new StaticEntity(EntityType.EMPTY, 0, y);
//        Assertions.assertEquals(y, entityTest.getY());
    }

}