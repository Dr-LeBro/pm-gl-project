package pacman.gameplay.ghost;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import pacman.engine.core.Block.Block;
import pacman.engine.core.Entity.MovableEntity;
import pacman.engine.core.GameState;
import pacman.engine.core.Map.Map;
import pacman.engine.graphism.Sprite;
import pacman.engine.graphism.StaticSprite;
import pacman.gameplay.ghost.mode.Mode;
import pacman.gameplay.pacman.Pacman;

import java.util.ArrayList;
import java.util.LinkedList;

/* This is the orange ghost */
public class Clyde extends IAGhost {
    private Sprite[] sprites;
    private Mode mode;
    private double previousX = -1, previousY = -1;

    /* Clyde starts outside the ghost house */
    public Clyde(int x, int y) {
        super(3, 12.0, x, y);
        sprites = new Sprite[4];
        sprites[0] = new StaticSprite("file:sprites/orange_up.png", "redU");
        sprites[1] = new StaticSprite("file:sprites/orange_right.png", "redR");
        sprites[2] = new StaticSprite("file:sprites/orange_down.png", "redD");
        sprites[3] = new StaticSprite("file:sprites/orange_left.png", "redL");
        setMovingSprites(sprites);
    }

    @Override
    public KeyCode chase()
    {
        KeyCode kc = null;  //TODO : jté kc
        MovableEntity pacman = GameState.getInstance().getCurrMap().getPacMan();
        kc =  ghostIA(((int)Math.floor(pacman.getX()) + Map.ArrayUnit/2)/ Map.ArrayUnit, ((int)Math.floor(pacman.getY()) + Map.ArrayUnit/2) / Map.ArrayUnit);
        mode = Mode.SCATTER;
        switch (mode) {
            case CHASE:
                aggressiveChase();
                break;
            case SCATTER:
                kc = scatterChase();
                break;
            case FRIGHTENED:
                break;
        }

        return kc;
    }

    @Override
    public void aggressiveChase()
    {
        Pacman pm = (Pacman) GameState.getInstance().getCurrMap().getPacMan();
        Point2D posPacman = new Point2D(pm.getX(), pm.getY());
        double posX = super.getX(), posY = super.getY();
        //System.out.println("posX = " + posY + " ; previousX = " + previousY);

        if(posPacman.distance(posX-1, posY) < posPacman.distance(posX+1, posY))
            move(KeyCode.LEFT);
        else
            move(KeyCode.RIGHT);

        if(posPacman.distance(posX, posY-1) > posPacman.distance(posX, posY+1))
            move(KeyCode.DOWN);
        else
            move(KeyCode.UP);

        previousX = posX;
        previousY = posY;

    }

    @Override
    public KeyCode scatterChase()
    {
        Graph.convertToGraph(GameState.getInstance().getCurrMap());
        return null;
    }

    @Override
    public void frightenedChase()
    {

    }

    class DepthSearch {
        private ArrayList<Point2D> visited;
        private LinkedList<Point2D> current;
        Point2D target;

        public DepthSearch(int x, int y) {
            visited = new ArrayList<>();
            current = new LinkedList<>();
            target = new Point2D(x, y);
        }


    }

    static class Graph {


        public static Graph convertToGraph(Map map)
        {
            //System.out.println("============================");
            Block[][] blockMap = map.getStaticMap();
            //for(Block b : blockMap[2])
                //System.out.println(b == null);
            return null;
        }
    }
}