package pacman.engine.graphism;

import javafx.scene.canvas.GraphicsContext;

public class StaticSprite extends Sprite{
    private ImageManager image;

    public StaticSprite(String imagesPaths, String name){
        super(name);
        image = new ImageManager(imagesPaths);
    }

    @Override
    public void loadSprite(){
        image.loadImage();
    }

    @Override
    protected void reloadImageSize(){
        image.loadImageWithSize(width, height);
    }

    @Override
    public boolean isReadyToDraw(){
        if(image.getImage() == null){
            return false;
        }
        return true;
    }

    @Override
    public void draw(GraphicsContext gc){
        gc.drawImage(image.getImage(), point.getX(), point.getY());
    }
}
