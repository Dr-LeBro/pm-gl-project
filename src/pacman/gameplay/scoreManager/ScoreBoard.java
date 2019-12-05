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
            ranking[i] = i + " - " + prefs.get("pseudo"+i,"empty") + " : " + prefs.getInt("score"+i,0);
        }
    }

    public void refresh()
    {
        prefs = Preferences.userNodeForPackage(ScoreBoard.class);
        ranking = new String[SCORE_BOARD_SIZE];

        for(int i = 0; i < SCORE_BOARD_SIZE; i++)
        {
            ranking[i] = i + " - " + prefs.get("pseudo"+i,"empty") + " : " + prefs.getInt("score"+i,0);
        }
    }

    public static ScoreBoard getInstance(){
        return INSTANCE;
    }

    public void saveScore(String pseudo, int score)
    {
        String tempPseudo;
        int tempScore;
        String tempPseudo2;
        int tempScore2;
        for(int i = 0; i < SCORE_BOARD_SIZE ; i++)
        {
            if(prefs.getInt("score"+i,0) < score)
            {
                tempPseudo = prefs.get("pseudo"+i,"empty");
                tempScore = prefs.getInt("score"+i,0);
                prefs.put("pseudo"+i, pseudo);
                prefs.putInt("score"+i, score);

                for(int j = i; j < SCORE_BOARD_SIZE-1; j++)
                {
                    tempPseudo2 = prefs.get("pseudo"+(j+1),"empty");
                    tempScore2 = prefs.getInt("score"+(j+1),0);
                    prefs.put("pseudo"+(j+1),tempPseudo);
                    prefs.putInt("score"+(j+1),tempScore);
                    tempPseudo = tempPseudo2;
                    tempScore = tempScore2;
                }
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
