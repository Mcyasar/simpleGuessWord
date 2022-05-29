package com.game.demo.store.Events;

public class GuessActionFailedEventHandler extends EventHandler<GuessActionFailedEvent> {

    public GuessActionFailedEventHandler(Callback callback){
        super(GuessActionFailedEvent.class, callback);
    }
    
}
