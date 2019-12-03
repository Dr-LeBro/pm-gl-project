package pacman.gameplay.scoreManager;

import java.util.prefs.Preferences;

public class ScoreBoard {
    public static int SCORE_BOARD_SIZE = 10;

    private static ScoreBoard INSTANCE = new ScoreBoard();
    private Preferences prefs;

    private String ranking[];

    public ScoreBoard()
    {
        prefs = Preferences.userNodeForPackage(ScoreBoard.class);
        ranking = new String[SCORE_BOARD_SIZE];

        for(int i = 0; i < SCORE_BOARD_SIZE; i++)
        {
            ranking[i] = prefs.get("pseudo"+i,"empty") + " : " + prefs.getInt("score"+i,0);
        }
    }

    public static ScoreBoard getInstance(){
        return INSTANCE;
    }

    public void saveScore(String pseudo, int score)
    {
        for(int i = 0; i < SCORE_BOARD_SIZE ; i++)
        {
            if(prefs.getInt("score"+i,0) < score)
            {
                for(int j = i; j < SCORE_BOARD_SIZE-1; j++)
                {
                    prefs.put("pseudo"+(j+1),prefs.get("pseudo"+j,"empty"));
                    prefs.putInt("score"+(j+1),prefs.getInt("score"+j,0));
                }
                prefs.put("pseudo"+i, pseudo);
                prefs.putInt("score"+i, score);
                return;
            }
        }
    }

    public void print()
    {
        for(int i = 0; i < SCORE_BOARD_SIZE; i++)
        {
            System.out.println(ranking[i]);
        }
    }

    public String[] getRanking() {
        return ranking;
    }
}
