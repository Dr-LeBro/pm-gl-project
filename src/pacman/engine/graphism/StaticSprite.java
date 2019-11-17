package pacman.engine.graphism;

import javafx.scene.canvas.GraphicsContext;

public class StaticSprite extends Sprite{
    private ImageManager image;

    public StaticSprite(String imagesPaths){
        super();
        image = new ImageManager(imagesPaths);
    }

    public void setSize(){

    }

    public void loadSprite(){
        image.loadImage();
    }

    protected void reloadImageSize(){
        image.loadImageWithSize(width, height);
    }

    public boolean isReadyToDraw(){
        if(image.getImage() == null){
            return false;
        }
        return true;
    }

    public void draw(GraphicsContext gc){
        gc.drawImage(image.getImage(), point.getX(), point.getY());
    }
}
