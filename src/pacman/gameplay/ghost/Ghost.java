package pacman.gameplay.ghost;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import pacman.engine.core.Block.Block;
import pacman.engine.core.Entity.Entity;
import pacman.engine.core.Entity.EntityType;
import pacman.engine.core.Entity.MovableEntity;
import pacman.engine.core.GameState;
import pacman.engine.core.Map.Map;
import pacman.engine.graphism.Sprite;
import pacman.engine.graphism.StaticSprite;
import pacman.engine.physic.movement.Direction;
import pacman.gameplay.ghost.mode.Mode;
import pacman.gameplay.scoreManager.Score;

import java.util.LinkedList;
import java.util.Random;

import static pacman.engine.physic.movement.Direction.*;

public class Ghost extends MovableEntity {
    private int behaviour;
    private double respawnTime;
    private Point2D pos;
    private Mode mode;

    public Ghost(int behaviour, double respawnTime, int x, int y) {
        super(EntityType.GHOST, new StaticSprite("file:sprites/ghost2_up.png", "ghost"), x*Map.ArrayUnit, y*Map.ArrayUnit, 3*Map.ArrayUnit,0.82);
        pos = new Point2D(x, y);
        Sprite[] sprites = new Sprite[4];
        sprites[0] = new StaticSprite("file:sprites/ghost1_up.png", "ghostU");
        sprites[1] = new StaticSprite("file:sprites/ghost1_right.png", "ghostR");
        sprites[2] = new StaticSprite("file:sprites/ghost1_down.png", "ghostD");
        sprites[3] = new StaticSprite("file:sprites/ghost1_left.png", "ghostL");
        if(setMovingSprites(sprites)){
            System.out.println("Ghost Sprites loaded");
        }
        mode = Mode.CHASE;
        this.behaviour = behaviour;
        this.respawnTime = respawnTime;
    }

    public int getBehaviour() {
        return behaviour;
    }

    public double getRespawnTime() {
        return respawnTime;
    }

    public Point2D getPos() {
        return pos;
    }

    public Mode getMode() {
        return mode;
    }

    @Override
    public void kill() {
        Score.getInstance().add(100);
        super.kill();
        respawn(spawnX, spawnY);
    }


    private boolean validPosition (Position pos){
        Block[][] blocks = GameState.getInstance().getCurrMap().getStaticMap();
        if (blocks[pos.getX()-1][pos.getY()-1] == null &&
                blocks[pos.getX()-1][pos.getY()] == null &&
                blocks[pos.getX()-1][pos.getY()+1] == null &&
                blocks[pos.getX()][pos.getY()-1] == null &&
                blocks[pos.getX()][pos.getY()+1] == null &&
                blocks[pos.getX()+1][pos.getY()-1] == null &&
                blocks[pos.getX()+1][pos.getY()] == null &&
                blocks[pos.getX()+1][pos.getY()+1] == null )
            return true;
        else
            return false;
    }

    private boolean validPosition (int x, int y){
        if (x < 1 || x > GameState.getInstance().getCurrMap().getMaxX() - 2 || y < 1 || y > GameState.getInstance().getCurrMap().getMaxY() - 2) {
            return false;
        }
        Block[][] blocks = GameState.getInstance().getCurrMap().getStaticMap();
        Position pos = new Position(x, y);
        if (blocks[pos.getX()-1][pos.getY()-1] == null &&
                blocks[pos.getX()-1][pos.getY()] == null &&
                blocks[pos.getX()-1][pos.getY()+1] == null &&
                blocks[pos.getX()][pos.getY()-1] == null &&
                blocks[pos.getX()][pos.getY()+1] == null &&
                blocks[pos.getX()+1][pos.getY()-1] == null &&
                blocks[pos.getX()+1][pos.getY()] == null &&
                blocks[pos.getX()+1][pos.getY()+1] == null )
            return true;
        else
            return false;
    }

    private  Position[] validAdjacentPosition(Position pos){
        final int LARGEUR = GameState.getInstance().getCurrMap().getMaxX();
        final int LONGUEUR = GameState.getInstance().getCurrMap().getMaxY();
        Position[] adjacent = new Position[4];

        boolean inContact = false;
        for (int i = 0; i < adjacent.length; i++) {
            adjacent[i] = null;
        }
        Block[][] blocks = GameState.getInstance().getCurrMap().getStaticMap();
        Position tempPos;
        tempPos = new Position(pos.getX(), pos.getY()-1);
        if (pos.getY()-1 > 0 && validPosition(tempPos))
            adjacent[0] = tempPos;
        tempPos = new Position(pos.getX()+1, pos.getY());
        if (pos.getX() + 1  < LARGEUR && validPosition(tempPos))
            adjacent[1] = tempPos;
        tempPos = new Position(pos.getX(), pos.getY()+1);
        if (pos.getY()+1 < LONGUEUR && validPosition(tempPos))
            adjacent[2] = tempPos;
        tempPos = new Position(pos.getX()-1, pos.getY());
        if (pos.getX()-1 > 0 && validPosition(tempPos))
            adjacent[3] = tempPos;
        return adjacent;

        /*Entity[][] walls = GameState.getInstance().getCurrMap().getSurroundingStaticMap((int)(pos.getX()/ Map.ArrayUnit),(int)(pos.getY() - 1/ Map.ArrayUnit));
        for (int i = 0; i < walls.length && !inContact; i++) {
            for (int j = 0; j < walls[i].length && !inContact; j++) {
                if (walls[i][j] != null && this.hitBox.isInContact(this.sizeX, this.sizeY, pos.getX(), pos.getY() - 1, walls[i][j])) {
                    inContact = true;
                }
            }
        }
        if (!inContact && pos.getY() - 1 > 0)
            adjacent[0] = new Position(pos.getX(), pos.getY() - 1); // LEFT TILE
        inContact = false;
        walls = GameState.getInstance().getCurrMap().getSurroundingStaticMap((int)(pos.getX() - 1/ Map.ArrayUnit),(int)(pos.getY() / Map.ArrayUnit));
        for (int i = 0; i < walls.length && !inContact; i++) {
            for (int j = 0; j < walls[i].length && !inContact; j++) {
                if (walls[i][j] != null && this.hitBox.isInContact(this.sizeX, this.sizeY, pos.getX() - 1, pos.getY(), walls[i][j])) {
                    inContact = true;
                }
            }
        }
        if (!inContact && pos.getX() - 1 > 0)
            adjacent[1] = new Position(pos.getX() - 1, pos.getY()); // UP TILE
        inContact = false;
        walls = GameState.getInstance().getCurrMap().getSurroundingStaticMap((int)(pos.getX() / Map.ArrayUnit),(int)(pos.getY() + 1 / Map.ArrayUnit));
        for (int i = 0; i < walls.length && !inContact; i++) {
            for (int j = 0; j < walls[i].length && !inContact; j++) {
                if (walls[i][j] != null && this.hitBox.isInContact(this.sizeX, this.sizeY, pos.getX() , pos.getY() + 1, walls[i][j])) {
                    inContact = true;
                }
            }
        }
        if (!inContact && pos.getY() + 1 < LONGUEUR)
            adjacent[2] = new Position(pos.getX() , pos.getY() + 1); // RIGHT TILE
        inContact = false;
        walls = GameState.getInstance().getCurrMap().getSurroundingStaticMap((int)(pos.getX() + 1 / Map.ArrayUnit),(int)(pos.getY() / Map.ArrayUnit));
        for (int i = 0; i < walls.length && !inContact; i++) {
            for (int j = 0; j < walls[i].length && !inContact; j++) {
                if (walls[i][j] != null && this.hitBox.isInContact(this.sizeX, this.sizeY, pos.getX() + 1, pos.getY(), walls[i][j])) {
                    inContact = true;
                }
            }
        }
        if (!inContact && pos.getX() + 1 < LARGEUR)
            adjacent[1] = new Position(pos.getX() + 1, pos.getY()); // DOWN TILE

        return adjacent;*/
    }

    public Position posNextIntersection(Position targetPos, Direction targetDir) {
        boolean isValid = false;
        Position tempPos = new Position(targetPos);
        Position ghostPos = new Position(((int)Math.floor(this.x) + Map.ArrayUnit/2)/ Map.ArrayUnit, ((int)Math.floor(this.y) + Map.ArrayUnit/2) / Map.ArrayUnit);
        int tempX, tempY;
        while (!isValid){
            switch (targetDir){
                case UP:
                    tempPos.setY(tempPos.getY()-1);
                    tempX = tempPos.getX();
                    tempY = tempPos.getY();
                    if ( !validPosition(tempX, tempY - 1)
                         || validPosition(tempX - 1, tempY)
                         || validPosition(tempX + 1, tempY)
                        ) {
                        isValid = true;
                    }
                    break;
                case RIGHT:
                    tempPos.setX(tempPos.getX()+1);
                    tempX = tempPos.getX();
                    tempY = tempPos.getY();
                    if ( !validPosition(tempX + 1, tempY)
                            || validPosition(tempX, tempY - 1)
                            || validPosition(tempX, tempY + 1)
                    ) {
                        isValid = true;
                    }
                    break;
                case DOWN:
                    tempPos.setY(tempPos.getY()+1);
                    tempX = tempPos.getX();
                    tempY = tempPos.getY();
                    if ( !validPosition(tempX, tempY + 1)
                            || validPosition(tempX - 1, tempY)
                            || validPosition(tempX + 1, tempY)
                    ) {
                        isValid = true;
                    }
                    break;
                case LEFT:
                    tempPos.setX(tempPos.getX()-1);
                    tempX = tempPos.getX();
                    tempY = tempPos.getY();
                    if ( !validPosition(tempX - 1, tempY)
                            || validPosition(tempX, tempY - 1)
                            || validPosition(tempX, tempY + 1)
                    ) {
                        isValid = true;
                    }
                    break;
                default:
                    return tempPos;
            }
            if (ghostPos.getX() == tempX && ghostPos.getY() == tempY){
                tempPos.setXY(targetPos.getX(), targetPos.getY());
                isValid = true;
            }
        }

        return tempPos;
    }

    public KeyCode randomNonOppositeDirection(Direction dir){
        int randomNum = new Random().nextInt(4);
        //System.out.println(randomNum);
        int count = 0;
        while (count < 4){
            if ( (dir == Direction.UP && randomNum != 2) || (dir == Direction.RIGHT && randomNum != 3) || (dir == Direction.DOWN && randomNum != 0) || (dir == Direction.LEFT && randomNum != 1) ){
                switch (randomNum) {
                    case 0:
                        return KeyCode.UP;
                    case 1:
                        return KeyCode.RIGHT;
                    case 2:
                        return KeyCode.DOWN;
                    case 3:
                        return KeyCode.LEFT;
                }
            }
            randomNum = (randomNum + 1) % 4;
            count++;
        }
        return null;
    }

    public KeyCode randomValidDirection(Position ghostPos){
        int randomNum = new Random().nextInt(4);
        //System.out.println(randomNum);
        int count = 0;
        Position[] adjPos = validAdjacentPosition(ghostPos);
        /*for (int i = 0; i < 4; i++){
            if (adjPos[i]!=null){
                System.out.println("i : " + i);
            }
        }*/
        while (count < 4){
            if (adjPos[randomNum] != null){
                switch (randomNum) {
                    case 0:
                        return KeyCode.UP;
                    case 1:
                        return KeyCode.RIGHT;
                    case 2:
                        return KeyCode.DOWN;
                    case 3:
                        return KeyCode.LEFT;
                }
            }
            randomNum = (randomNum + 1) % 4;
            count++;
        }
        return null;
    }

    public boolean isIntersection(Position pos){
        boolean isFreeUp = false, isFreeRight = false, isFreeDown = false, isFreeLeft = false;
        Position[] tabPos = validAdjacentPosition(pos);
        if (tabPos[0] != null)
            isFreeUp = true;
        if (tabPos[1] != null)
            isFreeRight = true;
        if (tabPos[2] != null)
            isFreeDown = true;
        if (tabPos[3] != null)
            isFreeLeft = true;
        return (isFreeUp && isFreeRight) || (isFreeRight && isFreeDown) || (isFreeDown && isFreeLeft) || (isFreeLeft && isFreeUp);
    }

    public KeyCode ghostIA(int targetX, int targetY){
        //System.out.println("startPos :" + startPos + "    endPos :" + endPos + "   Deplacement :"+ deplacement);
        //System.out.println("StartPos = " + startPos.toString() + "  EndPos = " + endPos.toString());
        final int LARGEUR = GameState.getInstance().getCurrMap().getMaxX();
        final int LONGUEUR = GameState.getInstance().getCurrMap().getMaxY();
        Position startPos = new Position(((int)Math.floor(this.x) + Map.ArrayUnit/2)/ Map.ArrayUnit, ((int)Math.floor(this.y) + Map.ArrayUnit/2) / Map.ArrayUnit);
        //System.out.println(startPos.toString() + "  x : " + this.x + "  y : " + this.y + "  actualDir : " + getActualDir() + "  wishedDir : " + this.getWishedDirection());
        Position endPos = new Position(targetX, targetY);
        //Position endPos = new Position(((int)Math.floor(pacman.getX()) + Map.ArrayUnit/2)/ Map.ArrayUnit, ((int)Math.floor(pacman.getY()) + Map.ArrayUnit/2) / Map.ArrayUnit);
        if (startPos.equals(endPos))
            return randomValidDirection(startPos);
        boolean[][] alreadyVisited = new boolean[LARGEUR][LONGUEUR];// In java, the basic value of each cell for an array of boolean is false
        Position[][] predecessor = new Position[LARGEUR][LONGUEUR];
        QueueMaze queue = new QueueMaze();
        queue.addQueue(startPos);
        alreadyVisited[startPos.getX()][startPos.getY()] = true;
        outerloop:
        while(!queue.isEmpty()){
            Position head = queue.removeHead();
            Position[] tabPos = validAdjacentPosition(head);
            for (int i=0; i<tabPos.length; i++){
                //System.out.println(i + "  " + tabPos[i]);
                if (tabPos[i]!=null && !alreadyVisited[tabPos[i].getX()][tabPos[i].getY()]){
                    //System.out.println(tabPos[i].x + "   " + tabPos[i].y);
                    predecessor[tabPos[i].getX()][tabPos[i].getY()] = head;//On inscrit dans la nouvelle case les coordonnÃ©es de la case mÃ¨re pour pouvoir "remonter"
                    queue.addQueue(tabPos[i]);
                    alreadyVisited[tabPos[i].getX()][tabPos[i].getY()] = true;
                    if (tabPos[i].equals(endPos))break outerloop;//On sort si on atteint le goal
                }
            }
        }


        //Position[] searchPath = new Position[50];
        //int lastX = -2, lastY = -2; // We just need the direction we have to take, not the whole path
        //System.out.println("endPos.x = " +  endPos.getX() + "    endPos.y = " +  endPos.getY());
        if(endPos.getX() >= 0 && endPos.getY() >= 0 && endPos.getX() < LARGEUR && endPos.getY() < LONGUEUR && alreadyVisited[endPos.getX()][endPos.getY()]){ // if goal is reached
            int xPred = endPos.getX(), yPred = endPos.getY(), i = 0, xTemp;
            //searchPath[i++]=endPos;
            //System.out.println("StartPos = " + startPos.toString() + "  EndPos = " + predecessor[xPred][yPred].toString());
            while (!predecessor[xPred][yPred].equals(startPos)){
                //searchPath[i] = predecessor[xPred][yPred];
                xTemp = xPred; // because xPred is modified after the first instruction
                xPred = predecessor[xPred][yPred].getX();
                yPred = predecessor[xTemp][yPred].getY();//Ici
                i++;
                //System.out.println(xPred + "   " + yPred);
            }
            //for (int n = 0; n < searchPath.length; n++){
             //   if (searchPath[n] != null){
                    //System.out.println("n = " + n + "SearchPath : " + searchPath[n].toString());
             //   }
            //}
            //System.out.println(i);

            if (xPred - 1 == startPos.getX() && yPred == startPos.getY()){
                //System.out.println("RIGHT");
                return KeyCode.RIGHT;
            }
            else if (xPred + 1 == startPos.getX() && yPred == startPos.getY()){
                //System.out.println("LEFT");
                return KeyCode.LEFT;
            }
            else if (xPred == startPos.getX() && yPred - 1 == startPos.getY()){
                //System.out.println("DOWN");
                return KeyCode.DOWN;
            }
            else if (xPred == startPos.getX() && yPred + 1 == startPos.getY()){
                //System.out.println("UP");
                return KeyCode.UP;
            }
            else {
                System.out.println("lastX : " + xPred + "  lastY : " + yPred + "  startPos.getX() : " + startPos.getX() + "startPos.getY()" + startPos.getY());
                return null;
            }
        }
        else {
            //System.out.println("No path " + this.sizeX);
            if (this.getActualDir() == STANDING){
                //System.out.println("X : " + this.x + " Y : " + this.y);
                return randomValidDirection(startPos);
            } else if (this.getWishedDirection() == STANDING) {
                return this.randomNonOppositeDirection(this.getActualDir());
            }
            return null;
        }


    }

    @Override
    public void move(KeyCode keyPressed) {
        setWishedDirection(keyPressed);
        boolean inContactWished = false, inContactWishedTurn = false, inContactDir = false;
        double tempXWished, tempYWished, tempXDir, tempYDir, greatestX, greatestY;
        double tempXWishedTurn = -1;
        double tempYWishedTurn = -1;
        Point2D pointWished = moveManager.move(x, y, this.wishedDirection);
        Point2D pointDir = moveManager.move(x, y, this.actualDir);
        tempXWished = pointWished.getX();
        tempYWished = pointWished.getY();
        tempXDir = pointDir.getX();
        tempYDir = pointDir.getY();

        if (this.x > tempXDir) greatestX = this.x;
        else greatestX = tempXDir;
        if (this.y > tempYDir) greatestY = this.y;
        else greatestY = tempYDir;
        if (Math.floor(tempXDir) != Math.floor(this.x) && Math.floor(tempXDir) != tempXDir){
            double differenceX = Math.abs(Math.floor(greatestX) - tempXDir);
            if (this.wishedDirection == UP){
                tempXWishedTurn = Math.floor(greatestX);
                tempYWishedTurn = this.y - differenceX;
            } else if (this.wishedDirection == DOWN){
                tempXWishedTurn = Math.floor(greatestX);
                tempYWishedTurn = this.y + differenceX;
            }
        }
        if (Math.floor(tempYDir) != Math.floor(this.y) && Math.floor(tempYDir) != tempYDir){
            double differenceY = Math.abs(Math.floor(greatestY) - tempYDir);
            if (this.wishedDirection == RIGHT){
                tempXWishedTurn = this.x + differenceY;
                tempYWishedTurn = Math.floor(greatestY);
            } else if (this.wishedDirection == LEFT){
                tempXWishedTurn = this.x - differenceY;
                tempYWishedTurn = Math.floor(greatestY);
            }
        }


        Entity[][] walls = GameState.getInstance().getCurrMap().getSurroundingStaticMap((int)(x/ Map.ArrayUnit),(int)(y / Map.ArrayUnit));
        if(walls != null) {
            for (int i = 0; i < walls.length && !inContactWished && this.wishedDirection != Direction.STANDING; i++) {
                for (int j = 0; j < walls[i].length && !inContactWished; j++) {
                    if (walls[i][j] != null && this.hitBox.isInContact(sizeX, sizeY, tempXWished, tempYWished, walls[i][j])) {
                        inContactWished = true; // Can't go in wished direction
                    }
                }
            }
            for (int i = 0; i < walls.length && !inContactWishedTurn && this.wishedDirection != Direction.STANDING && tempXWishedTurn != -1; i++) {
                for (int j = 0; j < walls[i].length && !inContactWishedTurn; j++) {
                    if (walls[i][j] != null && this.hitBox.isInContact(sizeX, sizeY, tempXWishedTurn, tempYWishedTurn, walls[i][j])) {
                        inContactWishedTurn = true; // Can't go in wished direction
                    }
                }
            }
            for (int i = 0; i < walls.length && !inContactDir; i++) {
                for (int j = 0; j < walls[i].length && !inContactDir; j++) {
                    if (walls[i][j] != null && this.hitBox.isInContact(sizeX, sizeY, tempXDir, tempYDir, walls[i][j])) {
                        inContactDir = true;
                    }
                }
            }
        }

        if (!inContactWished && this.wishedDirection != Direction.STANDING) {
            this.x = tempXWished;
            this.y = tempYWished;
            this.actualDir = this.wishedDirection;
            this.wishedDirection = Direction.STANDING;
            drawCurrentSprite();
        } else if (!inContactWishedTurn && tempXWishedTurn != -1 && tempYWishedTurn != -1) {
            this.x = tempXWishedTurn;
            this.y = tempYWishedTurn;
            this.actualDir = this.wishedDirection;
            this.wishedDirection = Direction.STANDING;
            drawCurrentSprite();
        } else if (!inContactDir) {
            this.x = tempXDir;
            this.y = tempYDir;
        } else {
            this.actualDir = STANDING;
        }
        if(isVisible()){
            getSprite().setPoint(x, y);
        }
        if (this.hitBox.isInContact(sizeX, sizeY, x, y, GameState.getInstance().getCurrMap().getPacMan())) {
            if(GameState.getInstance().getCurrMap().getPacMan().getInvulnerable())
                this.kill();
            else
                GameState.getInstance().getCurrMap().getPacMan().kill();
        }
    }

    protected class QueueMaze {
        LinkedList<Position> fifo = new LinkedList<Position>();
        protected QueueMaze() {
        }
        protected void addQueue(Position p){
            fifo.add(p);
        }
        protected Position removeHead(){
            return fifo.poll();
        }
        protected boolean isEmpty(){
            return fifo.peekLast() == null;
        }


    }
    protected class Position {
        private int x;
        private int y;


        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }


        public Position(Position p) {
            this.x = p.x;
            this.y = p.y;
        }

        public Position(){
            x = 0;
            y = 0;
        }


        public int getX() {
            return x;
        }


        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public void setXY(int x, int y){
            this.x = x;
            this.y = y;
        }

        public boolean equals(Position p){
            return this.getX() == p.getX() && this.getY() == p.getY();
        }

        public int distance(Position p){
            return (int) Math.sqrt((this.x - p.x)*(this.x - p.x)+(this.y - p.y)*(this.y - p.y));
        }

        public String toString(){
            return "X = " + this.x + "  Y = " + this.y;
        }
    }
}
