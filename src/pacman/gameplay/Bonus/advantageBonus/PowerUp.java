package pacman.gameplay.Bonus.advantageBonus;

import pacman.GUI.inGameGUI.MainGameGUI;
import pacman.gameplay.GameEvent;

public class PowerUp {
    private long timeStarted = 0; //time in milliS when resize started
    private int state = 0; //0 can be used, 1 currently used and in cooldown, 2 just in cooldown
    private int ended = 1;
    private long duration = 5000; // in millisecond, duration on resize
    private long cooldown = 10000; // in millisecond, duration on cooldown of resize
    private String name = "";

    public PowerUp(long duration, long cooldown, String name){
        this.duration = duration;
        this.cooldown = cooldown;
        this.name = name;
    }

    public boolean use(){
        if(state == 0){
            timeStarted = System.currentTimeMillis();
            state = 1;
            ended = -1;
            String[] eventArgs = new String[1];
            eventArgs[0] = name;
            MainGameGUI.eventHandler.fireEvent(new GameEvent(this, MainGameGUI.eventHandler, GameEvent.GAME_POWERUP_USED, eventArgs));
            return true;
        }else{
            return false;
        }
    }

    public int checkState(){
        long currentTime = System.currentTimeMillis();
        String[] eventArgs = new String[2];
        eventArgs[0] = name;
        if(state == 1 && currentTime-timeStarted>=duration){
            state = 2;
            eventArgs[1] = String.valueOf(timeCdLeft());
            MainGameGUI.eventHandler.fireEvent(new GameEvent(this, MainGameGUI.eventHandler, GameEvent.GAME_POWERUP_IN_COOLDOWN, eventArgs));
            ended = 0;
        }else if((state == 1 || state == 2) && currentTime-timeStarted>=cooldown){
            state = 0;
            eventArgs[1] = "0";
            MainGameGUI.eventHandler.fireEvent(new GameEvent(this, MainGameGUI.eventHandler, GameEvent.GAME_POWERUP_READY, eventArgs));
        }else if(state == 1){
            eventArgs[1] = String.valueOf(timeUseLeft());
            MainGameGUI.eventHandler.fireEvent(new GameEvent(this, MainGameGUI.eventHandler, GameEvent.GAME_POWERUP_USED, eventArgs));
        }else if(state == 2){
            eventArgs[1] = String.valueOf(timeCdLeft());
            MainGameGUI.eventHandler.fireEvent(new GameEvent(this, MainGameGUI.eventHandler, GameEvent.GAME_POWERUP_IN_COOLDOWN, eventArgs));
        }
        return state;
    }

    public boolean canBeUsed(){
        return state == 0;
    }

    public boolean isEndedTargeted(){
        if((state == 2 || state == 0) && ended == 0){
            ended = 1;
            return false;
        }
        return true;
    }

    public long timeUseLeft(){
        return duration - (System.currentTimeMillis() - timeStarted);
    }

    public long timeCdLeft(){
        return cooldown - (System.currentTimeMillis() - timeStarted);
    }



}
