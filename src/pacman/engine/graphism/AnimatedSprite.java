package pacman.engine.graphism;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import pacman.engine.core.Map.Map;

import java.util.ArrayList;

public class AnimatedSprite extends Sprite{
    private ArrayList<ImageManager> images;
    private AnimationSyncrhonizer animSync;

    @Override
    public void loadSprite(){
        for(int i=0; i<images.size(); i++){
            images.get(i).loadImageWithSize(width*ratioX, height*ratioY);
        }
        updateRatio = false;
    }

    public AnimatedSprite(ArrayList<String> imagesPaths, String name, AnimationSyncrhonizer animSync){
        super(name);
        images = new ArrayList<>();
        this.animSync = animSync;
        for(int i = 0; i<imagesPaths.size(); i++){
            images.add(new ImageManager(imagesPaths.get(i)));
        }
    }

    @Override
    public void draw(GraphicsContext gc){
        double xPadding = (double) Map.ArrayUnit/2*ratioX;
        double yPadding = (double) Map.ArrayUnit/2*ratioY;

        double xCenter = point.getX()*ratioX - width*ratioX/2 + xPadding;
        double yCenter = point.getY()*ratioY - height*ratioY/2 + yPadding;
        gc.drawImage(images.get(animSync.getImageIdTodraw(images.size())).getImage(), xCenter, yCenter);

        gc.setFill(Color.RED);
        gc.fillRect(point.getX()*ratioX-2+ xPadding, point.getY()*ratioY-2 + yPadding, 4, 4);

        gc.setStroke(Color.RED);
        gc.strokeRect(xCenter , yCenter, width*ratioX, height*ratioY);
    }

    @Override
    public boolean isReadyToDraw(){
        if(updateRatio) return false;
        for(int i=0; i<images.size(); i++){
            if(images.get(i).getImage() == null){
                return false;
            }
        }
        return true;
    }
}
