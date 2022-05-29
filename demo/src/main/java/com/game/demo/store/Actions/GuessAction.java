package com.game.demo.store.Actions;

import com.game.demo.store.Events.Event;

public class GuessAction extends Action {
    
    public GuessAction(Event successEvent, Event failedEvent, Callback callback){
        super(successEvent, failedEvent, callback);
        this.name = "GuessAction";        
    }
}
