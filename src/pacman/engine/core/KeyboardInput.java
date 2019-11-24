package pacman.engine.core;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyboardInput {

    private KeyCode lastKeyPressed;
    private Scene scene;

    public KeyboardInput(Scene scene){
        this.scene = scene;
        scene.getRoot().requestFocus();
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
        KeyCode returnCode = lastKeyPressed;
        lastKeyPressed = null;
        return returnCode;
    }

    public void removeListener(){
        scene.setOnKeyPressed(null);
    }
}
