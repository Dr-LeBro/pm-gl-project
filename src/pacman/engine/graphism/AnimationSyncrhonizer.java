package pacman.engine.graphism;

public class AnimationSyncrhonizer {
    private static double fpsRefreshRate = 60;
    private static double timeRatio = 1/fpsRefreshRate;
    private double animDuration;
    private static double currentFrame = 1;

    public AnimationSyncrhonizer(double animeTime){
        animDuration = animeTime;
    }

    public static void incrementFrame(){
        currentFrame ++;
    }

    public int getImageIdToDraw(int size){
        double ratio = size/animDuration;
        double time = currentFrame*timeRatio;
        double id = time*ratio;
        if(time >= animDuration){
            currentFrame = 0;
        }
        return (int) id%size;
    }
}
