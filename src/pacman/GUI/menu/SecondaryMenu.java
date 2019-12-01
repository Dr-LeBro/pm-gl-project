package pacman.GUI.menu;

import javafx.scene.layout.GridPane;



public class SecondaryMenu {
    protected GridPane mainRoot;
    protected GridPane rootOfMenu;

    public SecondaryMenu(GridPane root){
        mainRoot = root;
        rootOfMenu = new GridPane();
    }

    public GridPane getRootOfMenu() {
        return rootOfMenu;
    }
}
