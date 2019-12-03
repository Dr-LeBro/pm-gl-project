package pacman.gameplay.ghost;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import pacman.engine.core.Entity.EntityType;
import pacman.engine.core.Entity.MovableEntity;
import pacman.engine.core.GameState;
import pacman.engine.core.Map.Map;
import pacman.engine.graphism.Sprite;
import pacman.engine.graphism.StaticSprite;
import pacman.gameplay.ghost.mode.Mode;
import pacman.gameplay.scoreManager.Score;

public class Ghost extends MovableEntity {
    private int behaviour;
    private double respawnTime;
    private Point2D pos;
    private Mode mode;

    public Ghost(int behaviour, double respawnTime, int x, int y) {
        super(EntityType.GHOST, new StaticSprite("file:sprites/ghost2_up.png", "ghost"), x*Map.ArrayUnit, y*Map.ArrayUnit, 3*Map.ArrayUnit,0.32);
        pos = new Point2D(x, y);
        Sprite[] sprites = new Sprite[4];
        sprites[0] = new StaticSprite("file:sprites/ghost1_up.png", "ghostU");
        sprites[1] = new StaticSprite("file:sprites/ghost1_right.png", "ghostR");
        sprites[2] = new StaticSprite("file:sprites/ghost1_down.png", "ghostD");
        sprites[3] = new StaticSprite("file:sprites/ghost1_left.png", "ghostL");
        if(setMovingSprites(sprites)){
            System.out.println("Ghost Sprites loaded");
        }
        mode = Mode.SCATTER;
        this.behaviour = behaviour;
        this.respawnTime = respawnTime;
    }

    public int getBehaviour() {
        return behaviour;
    }

    public double getRespawnTime() {
        return respawnTime;
    }

    @Override
    public void kill() {
        Score.getInstance().add(100);
        super.kill();
        respawn(spawnX, spawnY);
    }

    public void move(KeyCode keyPressed){
        super.move(keyPressed);
        if (this.hitBox.isInContact(sizeX, sizeY, x, y, GameState.getInstance().getCurrMap().getPacMan())) {
            if(GameState.getInstance().getCurrMap().getPacMan().getInvulnerable())
                this.kill();
            else
                GameState.getInstance().getCurrMap().getPacMan().kill();
        }
    }
}
