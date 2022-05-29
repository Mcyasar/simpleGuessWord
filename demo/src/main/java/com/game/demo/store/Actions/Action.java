package com.game.demo.store.Actions;

import com.game.demo.store.StateManager;
import com.game.demo.store.Events.Event;

public abstract class Action {

    protected String name;
    protected String description;
    protected StateManager stateManager;
    protected Callback callback;
    //when action executions are finished, success or failed events are dispactehd
    protected Event successEvent;
    protected Event failedEvent;

    @FunctionalInterface  
    public interface Callback {
        ActionResult execute();
    }

    public Action(Event successEvent, Event failedEvent, Callback callback){
        this.successEvent = successEvent;
        this.failedEvent = failedEvent;
        this.callback = callback;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StateManager getStateManager() {
        return stateManager;
    }

    public void setStateManager(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public void execute() throws Exception{
        stateManager.setCurrentAction(this);
        ActionResult result = callback.execute();
        if(result.isActionSuccessOrFalse()){
            stateManager.dispatchEvent(successEvent);
            return;
        }

        stateManager.dispatchEvent(failedEvent);
    }
}
