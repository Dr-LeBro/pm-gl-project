package pacman.GUI.menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pacman.engine.graphism.GUIElements.ButtonManager;

/**
 * Main menu of game, show firsts categories of menu
 */
public class MainMenu {
    private GridPane rootOfMenu;
    private SecondaryMenu secondaryMenu;

    /**
     * Construc the main menu
     * @param mainRoot parent root
     */
    public MainMenu(GridPane mainRoot){
        rootOfMenu = new GridPane(); //root of menu
        /*button launch game single*/
        ButtonManager launchGameSingle = new ButtonManager("SinglePlayer", (EventHandler<ActionEvent>) actionEvent -> showSingle());
        launchGameSingle.setStyle1();

        /*button options*/
        ButtonManager gotoOpt = new ButtonManager("Options", (EventHandler<ActionEvent>) actionEvent -> showOpt());
        gotoOpt.setStyle1();

        /*button launch game multi*/
        ButtonManager launchGameMulti = new ButtonManager("MultiPlayer", (EventHandler<ActionEvent>) actionEvent -> showMulti());
        launchGameMulti.setStyle1();

        GridPane buttons = new GridPane(); //Grid of buttons

        /* add all buttons to grid*/
        buttons.add(launchGameSingle.getComponent(), 0, 0);
        buttons.add(gotoOpt.getComponent(), 0, 1);
        buttons.add(launchGameMulti.getComponent(), 0, 2);

        //set buttons more longer
        for (Node button: buttons.getChildren()) {
            GridPane.setHgrow(button, Priority.ALWAYS);
        }


        rootOfMenu.add(buttons,0,0);//add buttons grid to root of menu

        /*make menu and grid expandable*/
        GridPane.setHgrow(rootOfMenu, Priority.ALWAYS);
        GridPane.setVgrow(rootOfMenu, Priority.ALWAYS);
        GridPane.setHgrow(buttons, Priority.ALWAYS);
        GridPane.setVgrow(buttons, Priority.ALWAYS);

        rootOfMenu.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        mainRoot.getChildren().add(rootOfMenu);


    }

    /**
     * show secondary menu single
     */
    private void showSingle(){
        clearSecondaryMenu();
        secondaryMenu = new SecondaryMenuSingle(rootOfMenu);
        rootOfMenu.add(secondaryMenu.getRootOfMenu(), 1, 0);
    }

    /**
     * show secondary menu option
     */
    private void showOpt(){
        clearSecondaryMenu();
        secondaryMenu = new SecondaryMenuOpt(rootOfMenu);
        rootOfMenu.add(secondaryMenu.getRootOfMenu(), 1, 0);
    }

    /**
     * show secondary menu multi
     */
    private void showMulti(){
        clearSecondaryMenu();
        secondaryMenu = new SecondaryMenuTest(rootOfMenu);
        rootOfMenu.add(secondaryMenu.getRootOfMenu(), 1, 0);
    }

    /**
     * clear secondary menu
     */
    private void clearSecondaryMenu(){
        try{
            rootOfMenu.getChildren().remove(secondaryMenu.getRootOfMenu());
            secondaryMenu = null;
        }catch (Exception e){ }
    }



}
