package pacman.gameplay.Bonus.effects;

import pacman.engine.core.Entity.Entity;
import pacman.engine.core.GameState;
import pacman.gameplay.Bonus.scoreBonus.ScoreBonus;

import java.util.Random;

public class SuperPacGommeEffect extends Thread {
    @Override
    public void run() {
        Entity temp = GameState.getInstance().getCurrMap().getPacMan();
        if(GameState.getInstance().getCurrMap().getPacMan() != null) {
            temp.setInvulnerable(true);
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if(GameState.getInstance().getCurrMap().getPacMan() != null) {
                    temp.setInvulnerable(false);
                    if (new Random().nextInt(20) > 10)
                        ScoreBonus.getInstance().spawnBonus();
                }
            }
        }
    }
}
