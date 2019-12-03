package pacman.engine.graphism;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.HashMap;

public class ResizableCanvas extends Canvas{

    private double width;
    private double height;
    private int labSizeX;
    private int labSizeY;
    private double ratioX;
    private double ratioY;
    private Color fontColor;
    //TODO: think about making a second hashMap only for static objects (bg of game)
    private HashMap<String, DrawableObject> drawableObjects;

    public ResizableCanvas(int labSizeX, int labSizeY, double width, double height){
        super(width, height);
        this.labSizeY = labSizeY;
        this.labSizeX = labSizeX;
        fontColor = Color.rgb(1,20,50,1.0); //RGBA

        widthProperty().addListener(evtW ->{
            resize(super.getWidth(), super.getHeight());
            draw();
        });
        heightProperty().addListener(evtH ->{
            resize(super.getWidth(), super.getHeight());
            draw();
        });
        drawableObjects = new HashMap<>();
        resize(width, height);
    }

    @Override
    public void resize(double x, double y){
        clear();
        super.setHeight(y);
        super.setWidth(x);
        super.resize(x, y);
        resizeCanvas(x,y);
    }

    private void resizeCanvas(double width, double height){
        if(width == this.width && height == this.height) return;
        this.width = width;
        this.height = height;
        ratioX = width/labSizeX;
        ratioY = height/labSizeY;
        resizeDrawingElements();
    }

    private void resizeDrawingElements(){
        //System.out.println("Resize elements: " + this);
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
        //System.out.println(drawableObjects.get(key).getName() + " : " + drawableObjects.get(key).getPoint());
    }

    public void removeDrawingElement(DrawableObject removedObject){
        if(removedObject == null) return;
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
