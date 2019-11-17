package pacman.engine.graphism;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class AnimatedSprite extends Sprite{
    private ArrayList<ImageManager> images;
    private int fpsRefreshRate;

    public AnimatedSprite(ArrayList<String> imagesPaths, int fpsRefreshRate){
        super();
        this.fpsRefreshRate = fpsRefreshRate;
        for(int i = 0; i<imagesPaths.size(); i++){
            images.add(new ImageManager(imagesPaths.get(0)));
        }
    }

    public void loadSprite(){
        for(int i=0; i<images.size(); i++){
            images.get(i).loadImage();
        }
    }

    protected void reloadImageSize(){
        for(int i=0; i<images.size(); i++){
            images.get(i).loadImageWithSize(width, height);
        }
    }

    public void draw(GraphicsContext gc, double posX, double posY, int i){
        gc.drawImage(images.get(i).getImage(), posX, posY);
    }

    public boolean isReadyToDraw(){
        for(int i=0; i<images.size(); i++){
            if(images.get(i).getImage() == null){
                return false;
            }
        }
        return true;
    }

    @Override
    public void draw(GraphicsContext gc) {
        //TODO make anim
        gc.drawImage(images.get(0).getImage(), point.getX(), point.getY());
    }
}
