package pacman.engine.core.Map;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pacman.Main;
import pacman.engine.core.Block.Block;
import pacman.engine.core.Entity.EntityType;
import pacman.engine.core.Entity.MovableEntity;
import pacman.engine.core.Entity.StaticEntity;
import pacman.engine.graphism.StaticSprite;
import pacman.gameplay.Bonus.advantageBonus.PacGomme;
import pacman.gameplay.ghost.*;
import pacman.gameplay.pacman.Pacman;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

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

    @Test
    void setAndGetId() {
        Map mapT = new Map(0, 0);
        mapT.setId("ID_Test");
        Assertions.assertEquals("ID_Test", mapT.getId());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 10, 100})
    void getMaxX(int x) {
        Map mapT = new Map(x, 0);
        Assertions.assertEquals(x, mapT.getMaxX());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 10, 100})
    void getMaxY(int y) {
        Map mapT = new Map(0, y);
        Assertions.assertEquals(y, mapT.getMaxY());
    }

    @Test
    void isWonEmpty() {
        Map mapT = new Map(0, 0);
        Assertions.assertTrue(mapT.isWon());
    }

    @Test
    void setStaticEntity() {
        Map mapT = new Map(2,2);
        mapT.setStaticEntity(0, 0, new PacGomme(0, 0));
    }

    @Test
    void isWonNotEmptyNotVisible() {
        Map mapT = new Map(2, 2);
        mapT.setStaticEntity(0, 0, new PacGomme(0, 0));
        Assertions.assertTrue(mapT.isWon());
    }

    @Test
    void isWonNotEmptyVisible() {
        Map mapT = new Map(2, 2);
        PacGomme pg = new PacGomme(0, 0);
        pg.setVisible(true);
        mapT.setStaticEntity(0, 0, pg);
        Assertions.assertFalse(mapT.isWon());
    }

    @Test
    void addMovableToList() {
        Map mapT = new Map(0,0);
        mapT.addMovableToList(new MovableEntity(EntityType.EMPTY, new StaticSprite("file:sprites/test.png", "Test"), 0, 1));
    }

    @Test
    void getPacMan() {
        Map mapT = new Map(0,0);
        Pacman pacT = new Pacman(3, 0, 0);
        mapT.addMovableToList(pacT);
        Assertions.assertEquals(pacT, mapT.getPacMan());
    }

    @Test
    void getGhosts() {
        Map mapT = new Map(2,2);
        Ghost P = new Pinky(0, 0);
        Ghost I = new Inky(0, 1);
        Ghost C = new Clyde(1, 0);
        Ghost B = new Blinky(1, 1);
        mapT.addMovableToList(P);
        mapT.addMovableToList(I);
        mapT.addMovableToList(C);
        mapT.addMovableToList(B);
        ArrayList<Ghost> gArray = mapT.getGhosts();
        Assertions.assertEquals(P, gArray.get(0), "Pinky ne correspond pas");
        Assertions.assertEquals(I, gArray.get(1), "Inky ne correspond pas");
        Assertions.assertEquals(C, gArray.get(2), "Clyde ne correspond pas");
        Assertions.assertEquals(B, gArray.get(3), "Blinky ne correspond pas");
    }

    @Test
    void setStaticMap() {
        Map mapT = new Map(2,2);
        mapT.setStaticMap(0, 0, new Block(0, 0));
    }

    @Test
    void getStaticEntityMap() {
        Map mapT = new Map(5,5);

        PacGomme pg = new PacGomme(3, 4);
        mapT.setStaticEntity(3, 4, pg);

        Pacman pacT = new Pacman(3, 4, 4);
        mapT.addMovableToList(pacT);

        Ghost P = new Pinky(0, 0);
        Ghost I = new Inky(0, 1);
        Ghost C = new Clyde(1, 0);
        Ghost B = new Blinky(1, 1);
        mapT.addMovableToList(P);
        mapT.addMovableToList(I);
        mapT.addMovableToList(C);
        mapT.addMovableToList(B);

        Block b = new Block(3, 4);
        mapT.setStaticMap(3, 4, b);

        StaticEntity[][] se = mapT.getStaticEntityMap();

        for(int i = 0 ; i < 5 ; i++){
            for(int j = 0 ; j < 5 ; j++){
                if(i == 3 && j == 4)
                    Assertions.assertEquals(pg, se[i][j]);
                else
                    Assertions.assertNull(se[i][j]);
            }
        }
    }

    @Test
    void getMovableList() {
        Map mapT = new Map(5,5);

        PacGomme pg = new PacGomme(3, 4);
        mapT.setStaticEntity(3, 4, pg);

        Pacman pacT = new Pacman(3, 4, 4);
        mapT.addMovableToList(pacT);

        Ghost P = new Pinky(0, 0);
        Ghost I = new Inky(0, 1);
        Ghost C = new Clyde(1, 0);
        Ghost B = new Blinky(1, 1);
        mapT.addMovableToList(P);
        mapT.addMovableToList(I);
        mapT.addMovableToList(C);
        mapT.addMovableToList(B);

        Block b = new Block(3, 4);
        mapT.setStaticMap(3, 4, b);

        ArrayList<MovableEntity> ml = mapT.getMovableList();

        Assertions.assertEquals(5, ml.size());
        Assertions.assertEquals(pacT, ml.get(0), "PacMan ne correspond pas");
        Assertions.assertEquals(P, ml.get(1), "Pinky ne correspond pas");
        Assertions.assertEquals(I, ml.get(2), "Inky ne correspond pas");
        Assertions.assertEquals(C, ml.get(3), "Clyde ne correspond pas");
        Assertions.assertEquals(B, ml.get(4), "Blinky ne correspond pas");
    }

    @Test
    void getStaticMap() {
        Map mapT = new Map(5,5);

        PacGomme pg = new PacGomme(3, 4);
        mapT.setStaticEntity(3, 4, pg);

        Pacman pacT = new Pacman(3, 4, 4);
        mapT.addMovableToList(pacT);

        Ghost P = new Pinky(0, 0);
        Ghost I = new Inky(0, 1);
        Ghost C = new Clyde(1, 0);
        Ghost B = new Blinky(1, 1);
        mapT.addMovableToList(P);
        mapT.addMovableToList(I);
        mapT.addMovableToList(C);
        mapT.addMovableToList(B);

        Block b = new Block(4, 3);
        mapT.setStaticMap(4, 3, b);

        Block[][] sm = mapT.getStaticMap();

        for(int i = 0 ; i < 5 ; i++){
            for(int j = 0 ; j < 5 ; j++){
                if(i == 4 && j == 3)
                    Assertions.assertEquals(b, sm[i][j]);
                else
                    Assertions.assertNull(sm[i][j]);
            }
        }
    }
}