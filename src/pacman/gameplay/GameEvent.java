package pacman.gameplay;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public class GameEvent extends Event {

    public static final EventType<GameEvent> GAME_END =
            new EventType<>(Event.ANY, "GAME_END");

    public static final EventType<GameEvent> GAME_SCORE_UPDATED =
            new EventType<>(Event.ANY, "GAME_SCORE_UPDATED");

    public static final EventType<GameEvent> GAME_LIFE_UPDATED =
            new EventType<>(Event.ANY, "GAME_LIFE_UPDATED");

    public GameEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }

    public GameEvent(Object o, EventTarget eventTarget, EventType<? extends Event> eventType) {
        super(o, eventTarget, eventType);
    }
}
