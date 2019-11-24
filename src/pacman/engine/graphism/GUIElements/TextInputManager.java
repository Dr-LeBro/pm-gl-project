package pacman.engine.graphism.GUIElements;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.TextField;

public class TextInputManager {

    TextField textInput;

    public TextInputManager(EventHandler<ActionEvent> event){
        textInput = new TextField();
    }

    public void changeText(String content){
        textInput.setText(content);
    }

    public void changeEvent(EventHandler<ActionEvent> event){
        textInput.setOnAction(event);
    }

    public TextField getComponent() {
        return textInput;
    }


}
