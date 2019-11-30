package pacman.gameplay.scoreManager;

public class Score {
    static int score = 0;
    public static void add(int points)
    {
        score = score + points;
        System.out.println(score);
    }
}
