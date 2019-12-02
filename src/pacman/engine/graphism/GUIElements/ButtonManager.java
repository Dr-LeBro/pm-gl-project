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

    public void setStyle1(){
        button.setStyle(
        "    -fx-padding: 5 22 5 22;" +
        "    -fx-border-color: #e2e2e2;" +
        "    -fx-border-width: 2;" +
        "    -fx-background-radius: 0;" +
        "    -fx-background-color: #1d1d1d;" +
        "    -fx-font-family: \"Segoe UI\", Helvetica, Arial, sans-serif;" +
        "    -fx-font-size: 11pt;" +
        "    -fx-text-fill: #d8d8d8;" +
        "    -fx-background-insets: 0 0 0 0, 0, 1, 2;"
                );
    }
}
