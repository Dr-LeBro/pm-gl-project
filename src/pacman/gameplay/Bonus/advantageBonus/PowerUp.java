package pacman.gameplay.Bonus.advantageBonus;

public class PowerUp {
    private long timeStarted = 0; //time in milliS when resize started
    private long state = 0;
    private long duration = 5000; // in millisecond, duration on resize
    private long cooldown = 10000; // in millisecond, duration on cooldown of resize
    private String name = "";

    PowerUp(long duration, long cooldown, String name){
        this.duration = duration;
        this.cooldown = cooldown;
        this.name = name;
    }




}
