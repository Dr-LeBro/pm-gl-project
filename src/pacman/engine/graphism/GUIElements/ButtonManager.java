package pacman.engine.graphism.GUIElements;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


public class ButtonManager {
    Button button;

    public ButtonManager(String content, EventHandler<ActionEvent> event){
        button = new Button();
        button.setText(content);
        button.setOnAction(event);
    }

    public void changeText(String content){
        button.setText(content);
    }

    public void changeEvent(EventHandler<ActionEvent> event){
        button.setOnAction(event);
    }

    public Button getComponent() {
        return button;
    }
}
