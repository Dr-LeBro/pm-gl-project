package pacman.engine.core;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyboardInput {

    private KeyCode lastKeyPressed;

    public KeyboardInput(Scene scene){
        scene.setOnKeyPressed(keyEvent -> handleEvent(keyEvent));
    }

    private void handleEvent(KeyEvent keyEvent){
        if(keyEvent.getCode() == KeyCode.RIGHT){
            lastKeyPressed = KeyCode.RIGHT;
        }else if(keyEvent.getCode() == KeyCode.LEFT){
            lastKeyPressed = KeyCode.LEFT;
        }else if(keyEvent.getCode() == KeyCode.UP){
            lastKeyPressed = KeyCode.UP;
        }else if(keyEvent.getCode() == KeyCode.DOWN) {
            lastKeyPressed = KeyCode.DOWN;
        }else{ }
        System.out.println(keyEvent.getCode());
    }


    public KeyCode getLastKeyPressed() {
        return lastKeyPressed;
    }
}
