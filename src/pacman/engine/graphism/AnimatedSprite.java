package pacman.engine.graphism;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class AnimatedSprite extends Sprite{
    private ArrayList<ImageManager> images;
    private int fpsRefreshRate;
    private int currentImage;

    public AnimatedSprite(ArrayList<String> imagesPaths, String name,int fpsRefreshRate){
        super(name);
        this.fpsRefreshRate = fpsRefreshRate;
        for(int i = 0; i<imagesPaths.size(); i++){
            images.add(new ImageManager(imagesPaths.get(0)));
        }
    }

    @Override
    public void loadSprite(){
        for(int i=0; i<images.size(); i++){
            images.get(i).loadImage();
        }
    }

    @Override
    public void draw(GraphicsContext gc){
        currentImage ++;
        if(currentImage >= fpsRefreshRate) currentImage = 0;
        gc.drawImage(images.get(currentImage%images.size()).getImage(), point.getX()-width/2, point.getY()-height/2);
    }

    @Override
    public boolean isReadyToDraw(){
        for(int i=0; i<images.size(); i++){
            if(images.get(i).getImage() == null){
                return false;
            }
        }
        return true;
    }
}
