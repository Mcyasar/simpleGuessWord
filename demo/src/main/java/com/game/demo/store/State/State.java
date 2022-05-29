package com.game.demo.store.State;

import java.util.HashMap;

import com.game.demo.store.StateManager;
import com.game.demo.store.Actions.Action;

public class State {

    private StateManager stateManager;
    private String name;
    private HashMap<String, Action> actionList = new HashMap<>();

    public State(String name, StateManager stateManager){
        this.stateManager = stateManager;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void subscribeAction(Action action){
        action.setStateManager(stateManager);
        actionList.put(action.getName(), action);
    }

    public Action getActionByName(String actionName){
        return actionList.get(actionName);
    }

    public HashMap<String, Action> getActionList() {
        return actionList;
    }

    public void setActionList(HashMap<String, Action> actionList) {
        this.actionList = actionList;
    }    
    
     
}
