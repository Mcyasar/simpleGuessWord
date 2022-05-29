package com.game.demo.store.Events;

import java.util.HashMap;

public class EventHandlerManager {

    private HashMap<String, EventHandler<?>> eventHandlerList = new HashMap<>();

    public <T extends Event> void registerEventsAndHandlers(EventHandler<T> handler){
        eventHandlerList.put(handler.getEventName(), handler);
    }

    public HashMap<String, EventHandler<?>> getEventHandlerList() {
        return eventHandlerList;
    }
    
}
