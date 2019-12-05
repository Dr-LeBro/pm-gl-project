package pacman.engine.core.Entity;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import pacman.Main;
import pacman.engine.graphism.StaticSprite;

class StaticEntityTest {

    @BeforeAll
    static void setupJFX(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                new JFXPanel();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        new Main().start(new Stage());
                    }
                });
            }
        });
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @EnumSource(EntityType.class)
    void getTypeSimple(EntityType type) {
        Entity entityTest = new StaticEntity(type, new StaticSprite("file:sprites/test.png", "Test"), 0);
        Assertions.assertEquals(type, entityTest.getType());
    }

    @ParameterizedTest
    @EnumSource(EntityType.class)
    void getTypeCoord(EntityType type) {
        Entity entityTest = new StaticEntity(type, new StaticSprite("file:sprites/test.png", "Test"), 0, 0, 0);
        Assertions.assertEquals(type, entityTest.getType());
    }

    @ParameterizedTest
    @ValueSource(ints = {-100, -1, 0, 1, 100})
    void getX(int x) {
        Entity entityTest = new StaticEntity(EntityType.EMPTY, new StaticSprite("file:sprites/test.png", "Test"), x, 0, 0);
        Assertions.assertEquals(x, entityTest.getX());
    }

    @ParameterizedTest
    @ValueSource(ints = {-100, -1, 0, 1, 100})
    void getY(int y) {
        Entity entityTest = new StaticEntity(EntityType.EMPTY, new StaticSprite("file:sprites/test.png", "Test"), 0, y, 0);
        Assertions.assertEquals(y, entityTest.getY());
    }

}