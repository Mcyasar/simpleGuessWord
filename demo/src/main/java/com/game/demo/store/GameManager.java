package com.game.demo.store;


import java.util.HashMap;

import com.game.demo.store.Actions.Action;
import com.game.demo.store.Actions.ActionResult;
import com.game.demo.store.Actions.GuessAction;
import com.game.demo.store.Events.EventHandlerManager;
import com.game.demo.store.Events.GuessActionCompletedEvent;
import com.game.demo.store.Events.GuessActionCompletedEventHandler;
import com.game.demo.store.Events.GuessActionFailedEvent;
import com.game.demo.store.Events.GuessActionFailedEventHandler;
import com.game.demo.store.State.State;


public class GameManager {

    //game attributes and functions are described ing GameContext.
    private GameContext gameContext;
    //state flow is executed by state manager.
    private StateManager stateManager;
    //event registrations are done here and in the event executions game attributes or state values are changed.
    EventHandlerManager handlerManager;

    public GameManager(){
        initialize();
    }

    private void initialize(){
        
        gameContext = new GameContext(2, "sharpener");

        stateManager = new StateManager();
		setStateManager(stateManager);
        handlerManager = stateManager.getEventHandlerManager();

        //States are created here and current state is set.
		State userGuessState = new State("UserGuessState", stateManager);
		stateManager.subscribeState(userGuessState);
        stateManager.setCurrentState(userGuessState);
        State gameOverState = new State("GameOverState", stateManager);
        stateManager.subscribeState(gameOverState);
        State finishState = new State("FinishState", stateManager);
        stateManager.subscribeState(finishState);

        //actions are also created here independent from states and registered to states.
        //Also here success and fail events are given to the action. After action execution, success or failed events are dispacthed.
		GuessAction guessAction = new GuessAction(new GuessActionCompletedEvent(), new GuessActionFailedEvent(), () -> {
            try {
                //game executions are given to action by callback function aspect; by doing so, game execution descriptions are done independent from actions.
                gameContext.checkGuessWord();
            } catch (Exception e) {
                return new ActionResult(false);
            }
            //action success           
            return new ActionResult();
        });
        userGuessState.subscribeAction(guessAction);
        
        //event handlers and callback functions are registered here
        handlerManager.registerEventsAndHandlers(new GuessActionCompletedEventHandler(() -> {
            if(gameContext.getCorrectlyGuessedWordLength() == gameContext.getWord().length()){
                stateManager.setCurrentState(stateManager.getState("FinishState"));
            }else if(gameContext.getGuessCount() <= 0){
                stateManager.setCurrentState(stateManager.getState("GameOverState"));
              }
        }));

        handlerManager.registerEventsAndHandlers(new GuessActionFailedEventHandler(() -> {
            System.out.println("An error occured, please try again.");
      }));
    }

    public void start() throws Exception{
        System.out.println("Welcome to Word Guess Game :)");
        System.out.println("You will geuss " + gameContext.getWord().length() + " letter word and you have " + gameContext.getGuessCount() + " number of attempts, good luck :)");
        while(true){        
            executeCurrentState();
            State curState = stateManager.getCurrentState();
            if(curState.getName().equals("GameOverState")){
                System.out.println("Sorry. You did not succeed. May the force be with you!");
                break;
            }

            if(curState.getName().equals("FinishState")){
                System.out.println("Congratulations, you know the word \"" + gameContext.getWord() + "\"!");
                break;
            }
        }
    }

    private void executeCurrentState() throws Exception{

        State curState = stateManager.getCurrentState();

        HashMap<String, Action> actionList = curState.getActionList();
        if(actionList.size() == 1){
            actionList.values().stream().findFirst().get().execute();
            return;
        }

        //there could be more than one action registered to a state, so, the actions will be ordered and the system will wait the user to select the action is a specific state.
        //TODO: more than one action handling in a state will be developed
        int actionOption = 1;
        StringBuilder actionSelectionList = new StringBuilder();
        for(String key : actionList.keySet()){
            actionSelectionList.append((actionOption++)+" ) " + key);
        }

        System.out.println("Please select an action by pressing action selection number: ");
    }
    

    public StateManager getStateManager() {
        return stateManager;
    }

    public void setStateManager(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public void initCurrentState() {
        
    }

    public GameContext getGameContext() {
        return gameContext;
    }

    public void setGameContext(GameContext gameContext) {
        this.gameContext = gameContext;
    }

}
