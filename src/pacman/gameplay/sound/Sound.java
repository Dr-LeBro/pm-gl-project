package pacman.gameplay.sound;

import javafx.util.Duration;
import pacman.engine.sound.Music;

public class Sound {
    private static String beginning = "./sounds/pacman_beginning.wav";
    private static String chomp = "sounds/pacman_chomp.wav";
    private static String death = "sounds/pacman_death.wav";
    private static String eatfruit = "sounds/pacman_eatfruit.wav";
    private static String eatghost = "sounds/pacman_eatghost.wav";
    private static boolean bool = true;

    //Instanciates the list of musics with these
    public static void instantiate() {
        Music.getInstance().addSound(beginning);
        Music.getInstance().addSound(chomp);
        Music.getInstance().addSound(death);
        Music.getInstance().addSound(eatfruit);
        Music.getInstance().addSound(eatghost);
        Music.getInstance().setVolume(0.1);
    }

    public static void chomp() {
        if(bool) {
            Music.getInstance().getSounds().get(1).setStartTime(Duration.ZERO);
            Music.getInstance().getSounds().get(1).setStopTime(Music.getInstance().getSounds().get(1).getMedia().getDuration().divide(4.0));
        }

        else {
            Music.getInstance().getSounds().get(1).setStartTime((Music.getInstance().getSounds().get(1).getMedia().getDuration().divide(4.0)));
            Music.getInstance().getSounds().get(1).setStopTime(Music.getInstance().getSounds().get(1).getMedia().getDuration().divide(2.0));
        }
        bool = !bool;
        Music.getInstance().playSound(1);
    }
}
