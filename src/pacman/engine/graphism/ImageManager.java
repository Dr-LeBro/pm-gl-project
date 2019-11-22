package pacman.engine.graphism;

import javafx.scene.image.Image;

import java.io.File;

public class ImageManager {
    private Image image;
    private String path;
    private double width;
    private double height;

    public ImageManager(String imagePath){
        path = imagePath;
    }

    public ImageManager(String imagePath, int width, int height){
        setImagePath(imagePath);
        setWidth(width);
        setHeight(height);
    }

    public void setImagePath(String imagePath){
        File f = new File(path);
        if(f.isFile()){
            path = imagePath;
        }else{
            path = null;
        }
    }

    public boolean loadImage(){
        if(path == null) return false;
        if(width > 0 & height > 0){
            image = new Image(path, width, height, false, false);
        }else{
            image = new Image(path);
        }
        return true;
    }

    public boolean loadImageWithSize(double width, double height){
        setWidth(width);
        setHeight(height);
        return loadImage();
    }

    public void setHeight(double height){
        if(height>0){
            this.height = height;
        }
    }

    public void setWidth(double width){
        if(width > 0){
            this.width = width;
        }
    }

    public Image getImage() {
        return image;
    }
}
