package pacman.GUI.menu;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point2D;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import pacman.Main;
import pacman.engine.graphism.GUIElements.ButtonManager;
import pacman.engine.graphism.GUIElements.SliderManager;

public class SecondaryMenuOpt extends SecondaryMenu{
    private SliderManager resolution;
    private static Point2D lowResWidth = new Point2D(848, 480);
    private static Point2D mediumResWidth = new Point2D(1280, 720);
    private static Point2D highResWidth = new Point2D(1920, 1080);

    public SecondaryMenuOpt(GridPane root) {
        super(root);
        ChangeListener<Number> selectResolutionListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            }
        };

        resolution = new SliderManager(selectResolutionListener, 1, 3,1);
        ButtonManager applyResize = new ButtonManager("Apply", event -> changeOpt1());

        if(Main.primaryStage.getWidth() == lowResWidth.getX()){
            resolution.setValue(1);
        }else if(Main.primaryStage.getWidth() == mediumResWidth.getX()){
            resolution.setValue(2);
        }else if(Main.primaryStage.getWidth() == highResWidth.getX()){
            resolution.setValue(3);
        }

        Text resizeText = new Text("resize");

        rootOfMenu.add(resizeText, 0, 0);
        rootOfMenu.add(resolution.getComponent(), 0, 1);
        rootOfMenu.add(applyResize.getComponent(), 0, 2);

    }

    public void changeOpt1(){
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
}


