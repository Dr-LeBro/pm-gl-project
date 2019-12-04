package pacman.gameplay.ghost;

import javafx.scene.input.KeyCode;

public abstract class IAGhost extends Ghost {

    public IAGhost(int behaviour, double respawnTime, int x, int y) {
        super(behaviour, respawnTime, x, y);
    }

    public abstract KeyCode chase();
    public abstract void aggressiveChase();
    public abstract KeyCode scatterChase();
    public abstract void frightenedChase();
}
