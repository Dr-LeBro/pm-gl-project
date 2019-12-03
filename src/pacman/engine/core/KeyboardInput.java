package pacman.engine.core;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyboardInput {

    private KeyCode lastKeyPressed;
    private Node root;

    public KeyboardInput(Node root){
        this.root = root;
        root.requestFocus();
        root.setOnKeyPressed(keyEvent -> handleEvent(keyEvent));
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
        }else if(keyEvent.getCode() == KeyCode.A){
            lastKeyPressed = KeyCode.A;
        }
        //System.out.println(keyEvent.getCode());
    }


    public KeyCode getLastKeyPressed() {
        KeyCode returnCode = lastKeyPressed;
        lastKeyPressed = null;
        return returnCode;
    }

    public void removeListener(){
        root.setOnKeyPressed(null);
    }
}
