package pacman.GUI.inGameGUI;

import javafx.geometry.Insets;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PowerUpGUI {
    private ProgressIndicator durationBar;
    private ProgressIndicator cooldownBar;
    private double cooldownMax = -1;
    private double durationMax = -1;
    private String name = "";
    private GridPane display;

    public PowerUpGUI(String name){
        this.name = name;
        durationBar = new ProgressIndicator(0);
        cooldownBar = new ProgressIndicator(0);
        GridPane.setFillWidth(durationBar, true);
        GridPane.setFillWidth(cooldownBar, true);

        durationBar.setMinWidth(20);
        cooldownBar.setMinWidth(20);
        durationBar.setMinHeight(20);
        cooldownBar.setMinHeight(20);

        durationBar.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,250,0.6), CornerRadii.EMPTY, Insets.EMPTY)));
        cooldownBar.setBackground(new Background(new BackgroundFill(Color.rgb(250,0,0,0.6), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void setCooldown(double cooldown) {
        if(cooldownMax == -1 || cooldown == -1){
            cooldownMax = cooldown;
        }
        cooldownBar.setProgress(((cooldownMax-cooldown)/cooldownMax));
    }

    public void setDuration(double duration) {
        if(durationMax == -1 || duration == -1){
            durationMax = duration;
        }
        durationBar.setProgress(((durationMax-duration)/durationMax));
    }


    public GridPane getPowerUpDisplay(){
        if(display == null) {
            display = new GridPane();

            Text temp;
            temp = new Text(name);
            temp.setFont(Font.font(30));
            display.add(temp, 1, 0);

            temp = new Text("Duration");
            display.add(temp, 0, 1);
            display.add(durationBar, 0, 2);

            temp = new Text("Cooldown");
            display.add(temp, 2, 1);
            display.add(cooldownBar, 2, 2);
        }
        return display;
    }
}
