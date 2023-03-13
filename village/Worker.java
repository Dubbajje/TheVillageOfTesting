package org.village;

public class Worker{

    public Worker(String name, String occupation, Workassignment workassignment){
        this.name = name;
        this.workassignment = workassignment;
        this.occupation = occupation;
    }

    String name = "";
    String occupation;
    Boolean isHungry = false;
    int daysHungry = 0;
    Boolean isAlive = true;
    Workassignment workassignment;

    public void DoWork(){
        if(!isHungry){
        workassignment.workassignment();}
    }
}
