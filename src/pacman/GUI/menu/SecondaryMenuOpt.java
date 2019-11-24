package pacman.GUI.menu;

import javafx.scene.layout.GridPane;
import pacman.engine.graphism.GUIElements.ButtonManager;

public class SecondaryMenuOpt extends SecondaryMenu{
    public SecondaryMenuOpt(GridPane root) {
        super(root);
        ButtonManager launchGameSingle = new ButtonManager("resize", actionEvent -> changeOpt1());
        rootOfMenu.add(launchGameSingle.getComponent(), 0, 0);
    }

    public void changeOpt1(){
        System.out.println("Not effective");
    }
}


