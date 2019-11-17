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
    private double minDistance;
    private Color fontColor;
    private HashMap<String, DrawableObject> drawableObjects;

    public ResizableCanvas(){
        fontColor = new Color(0,0,0,1); //RGBA
        widthProperty().addListener(evtW ->{
            resizeCanvas(super.getWidth(), super.getHeight());
            callDrawingElements();
        });
        heightProperty().addListener(evtH ->{
            resizeCanvas(super.getWidth(), super.getHeight());
            callDrawingElements();
        });
        drawableObjects = new HashMap<>();
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
        resizeDrawingElements();
    }

    //TODO: resizeDrawingElements
    private void resizeDrawingElements(){

    }

    public void addDrawingElementAndDraw(DrawableObject newObject){
        GraphicsContext gc = getGraphicsContext2D();
        addDrawingElement(newObject);
        if(newObject.isReadyToDraw()){
            newObject.draw(gc);
        }
    }

    public void addDrawingElement(DrawableObject newObject){

        String basicKey = newObject.getName();
        String key = basicKey;
        while(drawableObjects.containsKey(key)){
            key = basicKey + Math.random()*10000;
        }
        newObject.setKey(key);
        drawableObjects.put(newObject.getKey(), newObject);
    }

    public void removeDrawingElement(DrawableObject newObject){
        drawableObjects.remove(newObject.getKey());
    }

    private void callDrawingElements() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, width, height);
        for (String key : drawableObjects.keySet()) {
            if(drawableObjects.get(key).isReadyToDraw()){
                drawableObjects.get(key).draw(gc);
            }
        }
    }

    public void resetCache(){
        drawableObjects.clear();
    }

    public void clear(){
        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(fontColor);
        gc.fillRect(0,0,width,height);
        gc.setFill(Color.WHITE);
    }

    @Override
    public boolean isResizable() {
        return true;
    }
}
