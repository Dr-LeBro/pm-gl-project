package pacman.GUI.menu;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import pacman.Main;
import pacman.engine.graphism.GUIElements.ButtonManager;
import pacman.engine.graphism.GUIElements.SliderManager;

/**
 * Extends of secondaryMenu
 * Show options
 */
public class SecondaryMenuOpt extends SecondaryMenu{
    private SliderManager resolution; //slider to choose resolution
    private Label resolutionDisplay = new Label(); //show resolution
    private static Point2D lowResWidth = new Point2D(848, 480); //low res spec
    private static Point2D mediumResWidth = new Point2D(1280, 720); //medium res spec
    private static Point2D highResWidth = new Point2D(1920, 1080); //high res spec

    /**
     * menu option constructor
     * @param root root of main menu
     */
    public SecondaryMenuOpt(GridPane root) {
        super(root);


        ChangeListener<Number> selectResolutionListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                showOpt1();
            }
        };

        /* resolution option set up*/
        Text resizeText = new Text("resize");
        resolution = new SliderManager(selectResolutionListener, 1, 3,1);
        ButtonManager applyResize = new ButtonManager("Apply", event -> changeOpt1());
        resolutionDisplay.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        showOpt1();

        /* preset slider option */
        if(Main.primaryStage.getWidth() == lowResWidth.getX()){
            resolution.setValue(1);
        }else if(Main.primaryStage.getWidth() == mediumResWidth.getX()){
            resolution.setValue(2);
        }else if(Main.primaryStage.getWidth() == highResWidth.getX()){
            resolution.setValue(3);
        }

        /* add option set up to menu*/
        rootOfMenu.add(resizeText, 0, 0);
        rootOfMenu.add(resolution.getComponent(), 0, 1);
        rootOfMenu.add(applyResize.getComponent(), 0, 2);
        rootOfMenu.add(resolutionDisplay, 1, 1);

    }

    /**
     * Change opt1 (resolution)
     */
    private void changeOpt1(){
        switch (resolution.getValue()){
            case 1:
                Main.primaryStage.setWidth(lowResWidth.getX());
                Main.primaryStage.setHeight(lowResWidth.getY());
                break;
            case 2:
                Main.primaryStage.setWidth(mediumResWidth.getX());
                Main.primaryStage.setHeight(mediumResWidth.getY());
                break;
            case 3:
                Main.primaryStage.setWidth(highResWidth.getX());
                Main.primaryStage.setHeight(highResWidth.getY());
                break;
        }
    }

    /**
     * showOpt1 (resolution)
     */
    private void showOpt1(){
        switch (resolution.getValue()){
            case 1:
                resolutionDisplay.setText(lowResWidth.getX() + "x" + lowResWidth.getY());
                break;
            case 2:
                resolutionDisplay.setText(mediumResWidth.getX() + "x" + mediumResWidth.getY());
                break;
            case 3:
                resolutionDisplay.setText(highResWidth.getX() + "x" + highResWidth.getY());
                break;
        }
    }
}


