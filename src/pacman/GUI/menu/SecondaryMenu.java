package pacman.GUI.menu;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * abstract class to create a secondary menu
 */
public abstract class SecondaryMenu {
    protected GridPane mainRoot; //Parent root
    protected GridPane rootOfMenu; //root of secondary menu

    /**
     * Construct of secondary menu
     * @param root root of main menu
     */
    public SecondaryMenu(GridPane root){
        mainRoot = root;
        rootOfMenu = new GridPane();

        /* set secondary menu expandable */
        GridPane.setHgrow(rootOfMenu, Priority.ALWAYS);
        GridPane.setVgrow(rootOfMenu, Priority.ALWAYS);

        rootOfMenu.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY))); //set background grey
    }

    public GridPane getRootOfMenu() {
        return rootOfMenu;
    }
}
