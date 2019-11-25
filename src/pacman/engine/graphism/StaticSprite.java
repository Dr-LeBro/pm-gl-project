package pacman.engine.graphism;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class StaticSprite extends Sprite{
    private ImageManager image;

    public StaticSprite(String imagesPaths, String name){
        super(name);
        image = new ImageManager(imagesPaths);
    }

    @Override
    public void loadSprite(){
        image.loadImageWithSize(width*ratioX, height*ratioY);
        updateRatio = false;
    }

    @Override
    public boolean isReadyToDraw(){
        if(image.getImage() == null || updateRatio){
            return false;
        }
        return true;
    }

    @Override
    public void draw(GraphicsContext gc){
        double xCenter = point.getX()*ratioX - width*ratioX/2;
        double yCenter = point.getY()*ratioY - height*ratioY/2;
        gc.drawImage(image.getImage(), xCenter, yCenter);

        /*
        gc.setFill(Color.RED);
        gc.fillRect(point.getX()*ratioX-2, point.getY()*ratioY-2, 4, 4);

        gc.setStroke(Color.RED);
        gc.strokeRect(point.getX()*ratioX - width*ratioX/2, point.getY()*ratioY - height*ratioY/2, width*ratioX, height*ratioY);
        */
    }
}
