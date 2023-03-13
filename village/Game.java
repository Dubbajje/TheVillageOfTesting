package org.village;
import java.util.Scanner;



public class Game {
    public Boolean GameOver;
    Village village;
    public Game(){
        GameOver = false;
    }

    public String Setup(){
        System.out.println("Name your village: ");
        Scanner sc = new Scanner(System.in);
        String player = sc.nextLine();
        return player;
    }
    public void PrintMenu(){
        System.out.println(village.getName());
        System.out.print("Days gone: " + village.getDaysGone());
        System.out.print("\tBuildings: " + village.getNumberOfBuildings());
        System.out.print("\tPopulation: " + village.getPopulation());
        System.out.println("\tDead: " + village.getDeadPeople());
        System.out.print("Food: " + village.getFood());
        System.out.print("\tMetal: " + village.getMetal());
        System.out.println("\tWood: " + village.getWood());

        System.out.println("What´s your choice?");
        System.out.println("1. Add Worker");
        System.out.println("2. Build");
        System.out.println("3. Next day");

    }
    public int PlayersChoice(){
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        return choice;
    }

    public void ButtonPressed(int choice){
        switch(choice){
            case 1: village.addWorker();
                break;
            case 2: village.ChooseProject();
                break;
            case 3: village.Day();
                break;
            default:
                break;
        }
    }

    public void Run() {
        String player = Setup();
        village = new Village(player);
        while (!GameOver) {
            PrintMenu();
            int choice = PlayersChoice();
            ButtonPressed(choice);
            DidYouWin();
            DidYouLose();
        }
    }
    public void DidYouWin(){
        if(village.CastleExists()){
            System.out.println("The Castle is ready! You Won! It took " + village.getDaysGone() + " days!");
            GameOver = true;}
    }
    public void DidYouLose(){
        if(village.getDeadPeople() > 0 && village.getPopulation() < 1){
        System.out.println("Game Over!");
        GameOver = true;}
    }
}
