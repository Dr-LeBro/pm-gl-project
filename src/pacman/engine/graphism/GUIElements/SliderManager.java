package pacman.engine.graphism.GUIElements;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.Slider;

public class SliderManager {
    Slider slider;
    int min;
    int max;
    int step;


    public SliderManager(ChangeListener<Number> listener, int min, int max, int step) {
        this.min = min;
        this.max = max;
        this.step = step;
        slider = new Slider(min, max, step);
        slider.setShowTickMarks(false);
        slider.valueProperty().addListener(listener);
    }

    public void changeEvent(ChangeListener<Number> listener){
        slider.valueProperty().addListener(listener);
    }

    public Slider getComponent() {
        return slider;
    }

    public int getValue(){
        return (int) Math.round(slider.getValue());

    }
}
