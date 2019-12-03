package pacman.gameplay.ghost;

public abstract class IAGhost extends Ghost {

    public IAGhost(int behaviour, double respawnTime, int x, int y) {
        super(behaviour, respawnTime, x, y);
    }

    public abstract void chase();
    public abstract void aggressiveChase();
    public abstract void scatterChase();
    public abstract void frightenedChase();
}
