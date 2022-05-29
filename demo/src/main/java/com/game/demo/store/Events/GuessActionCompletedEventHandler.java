package com.game.demo.store.Events;

public class GuessActionCompletedEventHandler extends EventHandler<GuessActionCompletedEvent> {

    public GuessActionCompletedEventHandler(Callback callback){
        super(GuessActionCompletedEvent.class, callback);
    }

    
}
