package com.game.demo.store.Events;

public abstract class EventHandler<T extends Event> {

    protected Callback callback;
    private String eventName;

    public EventHandler(Class<T> clazz, Callback callback){
        this.callback = callback;
        eventName = clazz.getName();    
    }    

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @FunctionalInterface  
    public interface Callback {
        void execute();
    }

    public void execute() throws Exception{
        callback.execute();
    }
    
}
