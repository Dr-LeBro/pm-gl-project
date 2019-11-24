package pacman.GUI.menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import pacman.engine.graphism.GUIElements.ButtonManager;

public class MainMenu {
    private GridPane mainRoot;
    private GridPane rootOfMenu;
    private SecondaryMenu secondaryMenu;

    public MainMenu(GridPane root){
        mainRoot = root;
        rootOfMenu = new GridPane();
        ButtonManager launchGameSingle = new ButtonManager("SinglePlayer", (EventHandler<ActionEvent>) actionEvent -> showSingle());
        ButtonManager gotoOpt = new ButtonManager("Options", (EventHandler<ActionEvent>) actionEvent -> showOpt());
        ButtonManager launchGameMulti = new ButtonManager("MultiPlayer", (EventHandler<ActionEvent>) actionEvent -> showMulti());


        rootOfMenu.add(launchGameSingle.getComponent(), 0, 0);
        rootOfMenu.add(gotoOpt.getComponent(), 0, 1);
        rootOfMenu.add(launchGameMulti.getComponent(), 0, 2);



        mainRoot.getChildren().add(rootOfMenu);




    }


    public void showSingle(){
        clearSecondaryMenu();
        secondaryMenu = new SecondaryMenuSingle(rootOfMenu);
        rootOfMenu.add(secondaryMenu.getRootOfMenu(), 1, 0);
    }


    public void showOpt(){
        clearSecondaryMenu();
    }

    public void showMulti(){
        clearSecondaryMenu();
    }

    public void clearSecondaryMenu(){
        try{
            rootOfMenu.getChildren().remove(secondaryMenu.getRootOfMenu());
            secondaryMenu = null;
        }catch (Exception e){ }
    }



}
