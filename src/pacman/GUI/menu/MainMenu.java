package pacman.GUI.menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pacman.engine.graphism.GUIElements.ButtonManager;

public class MainMenu {
    private GridPane mainRoot;
    private GridPane rootOfMenu;
    private GridPane buttons;
    private SecondaryMenu secondaryMenu;

    public MainMenu(GridPane root){
        mainRoot = root;
        rootOfMenu = new GridPane();
        ButtonManager launchGameSingle = new ButtonManager("SinglePlayer", (EventHandler<ActionEvent>) actionEvent -> showSingle());
        launchGameSingle.setStyle1();
        ButtonManager gotoOpt = new ButtonManager("Options", (EventHandler<ActionEvent>) actionEvent -> showOpt());
        gotoOpt.setStyle1();
        ButtonManager launchGameMulti = new ButtonManager("MultiPlayer", (EventHandler<ActionEvent>) actionEvent -> showMulti());
        launchGameMulti.setStyle1();

        buttons = new GridPane();
        buttons.add(launchGameSingle.getComponent(), 0, 0);
        buttons.add(gotoOpt.getComponent(), 0, 1);
        buttons.add(launchGameMulti.getComponent(), 0, 2);
        for (Node button:buttons.getChildren()) {
            GridPane.setHgrow(button, Priority.ALWAYS);
        }


        rootOfMenu.add(buttons,0,0);
        GridPane.setHgrow(rootOfMenu, Priority.ALWAYS);
        GridPane.setVgrow(rootOfMenu, Priority.ALWAYS);
        GridPane.setHgrow(buttons, Priority.ALWAYS);
        GridPane.setVgrow(buttons, Priority.ALWAYS);
        rootOfMenu.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        mainRoot.getChildren().add(rootOfMenu);


    }


    public void showSingle(){
        clearSecondaryMenu();
        secondaryMenu = new SecondaryMenuSingle(rootOfMenu);
        rootOfMenu.add(secondaryMenu.getRootOfMenu(), 1, 0);
    }


    public void showOpt(){
        clearSecondaryMenu();
        secondaryMenu = new SecondaryMenuOpt(rootOfMenu);
        rootOfMenu.add(secondaryMenu.getRootOfMenu(), 1, 0);
    }

    public void showMulti(){
        clearSecondaryMenu();
        secondaryMenu = new SecondaryMenuTest(rootOfMenu);
        rootOfMenu.add(secondaryMenu.getRootOfMenu(), 1, 0);
    }

    public void clearSecondaryMenu(){
        try{
            rootOfMenu.getChildren().remove(secondaryMenu.getRootOfMenu());
            secondaryMenu = null;
        }catch (Exception e){ }
    }



}
