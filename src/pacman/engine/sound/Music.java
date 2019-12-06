package pacman.engine.sound;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;

public class Music {
    private static final Music instance = new Music();      //single instance of Music
    private ArrayList<MediaPlayer> sounds;                  //list of musics

    private Music() {
        sounds = new ArrayList<>();
    }

    private Music(String path) {
        sounds = new ArrayList<>();
        addSound(path);
    }

    private Music(ArrayList<MediaPlayer> sounds) {
        this.sounds = new ArrayList<>(sounds);
    }

    public static final Music getInstance() {
        return instance;
    }

    public ArrayList<MediaPlayer> getSounds() {
        return sounds;
    }

    public void addSound(String path) {
        String sound = new File(path).toURI().toString();
        sounds.add(new MediaPlayer(new Media(sound)));
    }

    //Plays the music determined by the index
    public void playSound(int index) {
        sounds.get(index).setAutoPlay(true);
        //sounds.get(index).seek(Duration.ZERO);

        //We check sounds is big enough
        if(index < sounds.size())
            sounds.get(index).play();
        else
            System.out.println("This sound does not exist.");
        sounds.get(index).seek(Duration.ZERO);
    }

    //Plays the music determined by the path
    public void playSound(String path) {
        MediaPlayer mp = new MediaPlayer(new Media(path));
        //We check if sounds contains a sound named <path>
        if(sounds.contains(mp))
            playSound(sounds.indexOf(mp));
        else
            System.out.println("This sound does not exist.");
    }

    //Stops the music determined by the index
    public void stopSound(int index) {
        //We check sounds is big enough
        if(index <= sounds.size())
            sounds.get(index).stop();
        else
            System.out.println("This sound does not exist.");
    }

    //Stops the music determined by the path
    public void stopSound(String path) {
        MediaPlayer mp = new MediaPlayer(new Media(path));
        //We check if sounds contains a sound named <path>
        if(sounds.contains(mp))
            stopSound(sounds.indexOf(mp));
        else
            System.out.println("This sound does not exist.");
    }

    //Stops all the musics
    public void stopAllSounds() {
        for(int i = 0 ; i < sounds.size() ; i++)
            stopSound(i);
    }

    //Removes the music determined by the index
    public MediaPlayer removeSound(int index) {
        //We check sounds is big enough
        if(index <= sounds.size())
            return sounds.remove(index);
        else {
            System.out.println("This sound does not exist.");
            return null;
        }
    }

    //Removes the music determined by the path
    public MediaPlayer removeSound(String path) {
        MediaPlayer mp = new MediaPlayer(new Media(path));

        //We check if sounds contains a sound named <path>
        if(sounds.contains(mp))
            return removeSound(sounds.indexOf(mp));
        else {
            System.out.println("This sound does not exist.");
            return null;
        }
    }

    //Removes all the musics
    public void removeAllSounds() {
        for(int i = 0 ; i < sounds.size() ; i++)
            removeSound(i);
    }

    //Adjusts the volume of all the musics
    public void setVolume(double v) {
        for(int i = 0 ; i < sounds.size() ; i++)
            setVolume(i, v);
    }

    //Adjusts the volume of the music determined by the index
    public void setVolume(int index, double v) {
        if(index < sounds.size())
            sounds.get(index).setVolume(v);
        else
            System.out.println("There is no sound for this index.");
    }

    //Repeats a music
    public void playUnlimitedSound(int index) {
        sounds.get(index).setAutoPlay(true);
        sounds.get(index).setCycleCount(MediaPlayer.INDEFINITE);
        playSound(index);
    }
}
