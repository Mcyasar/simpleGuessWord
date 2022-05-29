package com.game.demo.store.Actions;

public class ActionResult {

    private boolean actionSuccessOrFalse;

    public ActionResult(){ 
        actionSuccessOrFalse = true;
    }

    public ActionResult(boolean actionSuccessOrFalse){
        this.actionSuccessOrFalse = actionSuccessOrFalse;
    }

    public boolean isActionSuccessOrFalse() {
        return actionSuccessOrFalse;
    }

    public void setActionSuccessOrFalse(boolean actionSuccessOrFalse) {
        this.actionSuccessOrFalse = actionSuccessOrFalse;
    }

}
