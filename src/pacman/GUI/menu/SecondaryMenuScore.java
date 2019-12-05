package pacman.GUI.menu;

import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import pacman.gameplay.scoreManager.ScoreBoard;

/**
 * Extends of secondaryMenu
 * Show options
 */
public class SecondaryMenuScore extends SecondaryMenu{

    /**
     * menu option constructor
     * @param root root of main menu
     */
    public SecondaryMenuScore(GridPane root) {
        super(root);

        ScoreBoard.getInstance().refresh();
        TextArea rankingBox = new TextArea(String.join("\n",ScoreBoard.getInstance().getRanking()));
        rankingBox.setDisable(true);

        /* add option set up to menu*/
        rootOfMenu.add(rankingBox, 0, 0);
    }


}


