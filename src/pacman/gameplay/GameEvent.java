package pacman.gameplay;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public class GameEvent extends Event {

    private String args[];

    public static final EventType<GameEvent> GAME_END =
            new EventType<>(Event.ANY, "GAME_END");

    public static final EventType<GameEvent> GAME_SCORE_UPDATED =
            new EventType<>(Event.ANY, "GAME_SCORE_UPDATED");

    public static final EventType<GameEvent> GAME_LIFE_UPDATED =
            new EventType<>(Event.ANY, "GAME_LIFE_UPDATED");

    public static final EventType<GameEvent> GAME_POWERUP_USED =
            new EventType<>(Event.ANY, "GAME_POWERUP_USED");

    public static final EventType<GameEvent> GAME_POWERUP_IN_COOLDOWN =
            new EventType<>(Event.ANY, "GAME_POWERUP_IN_COOLDOWN");

    public static final EventType<GameEvent> GAME_POWERUP_READY =
            new EventType<>(Event.ANY, "GAME_POWERUP_READY");

    public GameEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }

    public GameEvent(Object o, EventTarget eventTarget, EventType<? extends Event> eventType) {
        super(o, eventTarget, eventType);
    }

    public GameEvent(Object o, EventTarget eventTarget, EventType<? extends Event> eventType, String[] args) {
        super(o, eventTarget, eventType);
        this.args = args;
    }
}
