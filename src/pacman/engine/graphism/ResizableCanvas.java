package pacman.engine.graphism;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.HashMap;

public class ResizableCanvas extends Canvas{

    private double midX;
    private double midY;
    private double width;
    private double height;
    private int labSizeX;
    private int labSizeY;
    private double ratioX;
    private double ratioY;
    private double minDistance;
    private Color fontColor;
    //TODO: think about make a second hashMap only for static objects (bg of game)
    private HashMap<String, DrawableObject> drawableObjects;

    public ResizableCanvas(int labSizeX, int labSizeY, int width, int height){
        super(width, height);
        this.labSizeY = labSizeY;
        this.labSizeX = labSizeX;
        fontColor = Color.rgb(1,20,50,1.0); //RGBA

        widthProperty().addListener(evtW ->{
            resizeCanvas(super.getWidth(), super.getHeight());
            clear();
            callDrawingElements();
        });
        heightProperty().addListener(evtH ->{
            resizeCanvas(super.getWidth(), super.getHeight());
            clear();
            callDrawingElements();
        });
        drawableObjects = new HashMap<>();
        resizeCanvas(width, height);
    }

    private void resizeCanvas(double width, double height){
        this.midX = width/2;
        this.midY = height/2;
        this.width = width;
        this.height = height;
        if(width<=height){
            minDistance = width;
        }else{
            minDistance = height;
        }
        ratioX = width/labSizeX;
        ratioY = height/labSizeY;
        resizeDrawingElements();
    }

    //TODO: resizeDrawingElements
    private void resizeDrawingElements(){
        for (String key : drawableObjects.keySet()) {
            drawableObjects.get(key).setRatios(ratioX, ratioY);
        }
    }

    public void addDrawingElement(DrawableObject newObject){
        if(newObject == null || newObject.getKey() != null) return;
        String basicKey = newObject.getName();
        String key = basicKey;
        while(drawableObjects.containsKey(key)){
            key = basicKey + Math.random()*10000;
        }
        newObject.setKey(key);
        newObject.setRatios(ratioX, ratioY);
        drawableObjects.put(newObject.getKey(), newObject);
    }

    public void removeDrawingElement(DrawableObject removedObject){
        drawableObjects.remove(removedObject.getKey());
        removedObject.setKey(null);
    }

    public void draw(){
        clear();
        callDrawingElements();
    }

    private void callDrawingElements() {
        GraphicsContext gc = getGraphicsContext2D();
        for (String key : drawableObjects.keySet()) {
            if(!drawableObjects.get(key).isReadyToDraw()){
                drawableObjects.get(key).load();
            }
            drawableObjects.get(key).draw(gc);
        }
    }

    public void resetCache(){
        for (String key : drawableObjects.keySet()) {
            drawableObjects.get(key).setKey(null);
        }
        drawableObjects.clear();
    }

    public void clear(){
        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(fontColor);
        gc.clearRect(0, 0, width, height);
        gc.fillRect(0,0,width,height);
        gc.setFill(Color.WHITE);
    }

    @Override
    public boolean isResizable() {
        return true;
    }
}
