package pacman.gameplay.scoreManager;

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
    }
}
