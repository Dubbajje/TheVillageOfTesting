package org.village;

import java.util.List;

public abstract class Building {



    protected int woodCost;
    protected int metalCost;
    protected boolean isComplete;
    protected int daysToComplete;
    protected int daysWorkedOn;

    public int getDaysWorkedOn(){
        return daysWorkedOn;
    }
    public void aDayworked(){
        daysWorkedOn++;
    }
    public int getWoodCost() {
        return woodCost;
    }
    public int getMetalCost() {
        return metalCost;
    }

    public boolean CheckIfBuildingIsComplete() {
        if (getDaysWorkedOn() == daysToComplete) {
            isComplete = true;
            return true;
        }
        return false;
    }
}

