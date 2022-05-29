package com.game.demo.store;

import java.util.HashMap;

import com.game.demo.store.Actions.Action;
import com.game.demo.store.State.State;
import com.game.demo.store.Events.Event;
import com.game.demo.store.Events.EventHandler;
import com.game.demo.store.Events.EventHandlerManager;

public class StateManager {

    private State currentState;
    private Action currentAction;
    private HashMap<String, State> stateList = new HashMap<>();
    private EventHandlerManager eventHandlerManager = new EventHandlerManager();

    public void subscribeState(State state){
        stateList.put(state.getName(), state);
    }

    public State getCurrentState() {
        return currentState;
    }

    public State getState(String stateName) {
        return stateList.get(stateName);
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public Action getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(Action currentAction) {
        this.currentAction = currentAction;
    }

    public EventHandlerManager getEventHandlerManager() {
        return eventHandlerManager;
    }

    public void setEventHandlerManager(EventHandlerManager eventHandlerManager) {
        this.eventHandlerManager = eventHandlerManager;
    }

    public void dispatchEvent(Event event) throws Exception{
        EventHandler<?> handler = eventHandlerManager.getEventHandlerList().get(event.getClass().getName());
        handler.execute();
    }

    
    
}
