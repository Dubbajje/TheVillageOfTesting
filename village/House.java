package org.village;

public class House extends Building {

    public House(){
        super();
        daysToComplete = 3;
        isComplete = false;
        daysWorkedOn = 0;
        woodCost = 5;

    }
    public House(boolean isComplete){
        super();
        daysToComplete = 3;
        this.isComplete = isComplete;
        woodCost = 5;
        daysWorkedOn = 0;

    }
}

