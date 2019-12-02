package pacman.gameplay.scoreManager;

import pacman.GUI.inGameGUI.MainGameGUI;
import pacman.gameplay.GameEvent;

public class Score {
    private static Score INSTANCE = new Score();
    private int score = 0;

    private Score(){}

    public static Score getInstance(){
        return INSTANCE;
    }

    public void add(int points){
        score = score + points;
        System.out.println(score);

        //call GUI
        MainGameGUI.eventHandler.fireEvent(new GameEvent(this, MainGameGUI.eventHandler, GameEvent.GAME_SCORE_UPDATED));
    }

    public int getScore(){
        return score;
    }

    public void resetScore()
    {
        score = 0;
    }
}
