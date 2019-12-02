package pacman.GUI.menu;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;


public class SecondaryMenuTest extends SecondaryMenu{


    public SecondaryMenuTest(GridPane root){
        super(root);

        ImageView iv = new ImageView("file:sprites/pacman01_up.png");
        iv.setFitHeight(200);
        iv.setFitWidth(200);
        Text inftx = new Text("Infrastructure");
        StackPane pane = new StackPane();

        pane.getChildren().add(iv);
        pane.getChildren().add(inftx);

        pane.setAlignment(Pos.CENTER);

        rootOfMenu.add(pane,0,0);
    }

}
