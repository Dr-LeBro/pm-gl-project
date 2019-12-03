package pacman.gameplay.Bonus.effects;

import pacman.engine.core.GameState;

public class SuperPacGommeEffect extends Thread {
    @Override
    public void run() {
        GameState.getInstance().getCurrMap().getPacMan().setInvulnerable(true);
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            GameState.getInstance().getCurrMap().getPacMan().setInvulnerable(false);
        }
    }
}
