package pacman.GUI.menu;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


public class SecondaryMenu {
    protected GridPane mainRoot;
    protected GridPane rootOfMenu;

    public SecondaryMenu(GridPane root){
        mainRoot = root;
        rootOfMenu = new GridPane();
        GridPane.setHgrow(rootOfMenu, Priority.ALWAYS);
        GridPane.setVgrow(rootOfMenu, Priority.ALWAYS);

        rootOfMenu.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public GridPane getRootOfMenu() {
        return rootOfMenu;
    }
}
