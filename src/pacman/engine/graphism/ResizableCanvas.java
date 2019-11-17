package pacman.engine.graphism;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class ResizableCanvas extends Canvas{

    protected double midX;
    protected double midY;
    protected double width;
    protected double height;
    protected double minDistance;
    protected ArrayList<DrawableObject> drawableObjects;

    public ResizableCanvas(){
        widthProperty().addListener(evtW ->{
            resizeCanvas(super.getWidth(), super.getHeight());
            callDrawingElements();
        });
        heightProperty().addListener(evtH ->{
            resizeCanvas(super.getWidth(), super.getHeight());
            callDrawingElements();
        });
        drawableObjects = new ArrayList<>();
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
        drawableObjects.add(newObject);
    }



    private void callDrawingElements() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, width, height);
        for(int i =0; i<drawableObjects.size(); i++){
            if(drawableObjects.get(i).isReadyToDraw()){
                drawableObjects.get(i).draw(gc);
            }
        }
    }

    public void clear(){

    }

    @Override
    public boolean isResizable() {
        return true;
    }
}
