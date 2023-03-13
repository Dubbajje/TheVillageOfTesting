package org.village;

import java.util.ArrayList;

import java.util.Scanner;

public class Village {
    private String name = "";
    private int food = 0;
    private int wood = 0;

    public void setWood(int wood) {
        this.wood = wood;
    }

    public void setMetal(int metal) {
        this.metal = metal;
    }

    private int metal = 0;
    private int population = 0;
    private int deadPeople = 0;
    private int daysGone = 0;

    GameTweaks gameTweaks = new GameTweaks();
    DatabaseConnection db = new DatabaseConnection();

    public ArrayList<Worker> getWorkers() {
        return workers;
    }

    private ArrayList<Worker> workers = new ArrayList<>();
    private ArrayList<Building> buildings = new ArrayList<>();

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    private ArrayList<Building> projectsToComplete = new ArrayList<>();

    public ArrayList<Building> getProjectsToComplete() {
        return projectsToComplete;
    }


    public Village(String name) {
        this.name = name;
        for (int i = 0; i < 3; i++) {
            buildings.add(new House(true));
        }
        food = 10;
    }

    public Village() {
        this.name = "";
        this.food = 10;
        this.wood = 0;
        this.metal = 0;
        this.population = 0;
        this.deadPeople = 0;
        this.daysGone = 0;
        this.workers = getWorkers();
        this.buildings = getBuildings();
        this.projectsToComplete = getProjectsToComplete();

        for (int i = 0; i < 3; i++) {
            buildings.add(new House(true));
        }
        food = 10;
    }

    public int getWood() {
        return wood;
    }

    public int getMetal() {
        return metal;
    }

    public int getFood() {
        return food;
    }

    public int getPopulation() {
        return population;
    }

    public String getName() {
        return name;
    }

    public int getDaysGone() {
        return daysGone;
    }

    public void setFood(int eats) {
        food += eats;
    }

    public int getDeadPeople() {
        return deadPeople;
    }

    public void AddWood() {
        for (Building building : getBuildings()) {
            if (building instanceof Woodmill) {
                wood += 2;
            }
        }
        wood += 1;
    }

    public void AddMetal() {
        for (Building building : getBuildings()) {
            if (building instanceof Quarry) {
                metal += 2;
            }
        }
        metal += 1;
    }

    public void AddFood() {
        for (Building building : getBuildings()) {
            if (building instanceof Farm) {
                food += 10;
            }
        }
        food += 5;
    }

    public int getNumberOfBuildings() {
        return getBuildings().size();
    }

    public void Build() {
        AddBuildingToVillage();
        if (projectsToComplete.size() == 0)
            return;
        else if (!projectsToComplete.get(0).isComplete) {
            projectsToComplete.get(0).aDayworked();
        }
    }

    public void PrintProjectsToBuild() {
        System.out.println("1.House");
        System.out.println("2.Farm");
        System.out.println("3.Woodmill");
        System.out.println("4.Quarry");
        System.out.println("5.Castle");
        System.out.println("6.Exit menu");
    }

    public Boolean CanBuildAProject(int project) {
        switch (project) {
            case 1:
                if (getWood() >= 5)
                    return true;
                else {
                    System.out.println("Not enough resources");
                    return false;
                }
            case 2:
                if (getWood() >= 5 && getMetal() >= 1)
                    return true;
                else {
                    System.out.println("Not enough resources");
                    return false;
                }
            case 3:
                if (getWood() >= 5 && getMetal() >= 2)
                    return true;
                else {
                    System.out.println("Not enough resources");
                    return false;
                }
            case 4:
                if (getWood() >= 3 && getMetal() >= 5)
                    return true;
                else {
                    System.out.println("Not enough resources");
                    return false;
                }

            case 5:
                if (getWood() >= 50 && getMetal() >= 50)
                    return true;
                else {
                    System.out.println("Not enough resources");
                    return false;
                }

        }
        return false;
    }

    public void ChooseProject(){
        Scanner sc = new Scanner(System.in);
        PrintProjectsToBuild();
        int project = sc.nextInt();
        addBuildingProjectToList(project);
    }
    public void addBuildingProjectToList(int project){
        int metalLeft;
        int woodLeft;

            switch (project) {

                case 1:
                    if(CanBuildAProject(1)) {
                        Building house = new House();
                        woodLeft = getWood() -  house.getWoodCost();
                        setWood(woodLeft);
                        projectsToComplete.add(house);}
                    break;
                case 2:
                    if (CanBuildAProject(2)) {
                        Building woodmill = new Woodmill();
                        woodLeft = getWood() - woodmill.getWoodCost();
                        metalLeft = getMetal() - woodmill.getMetalCost();
                        setWood(woodLeft);
                        setMetal(metalLeft);
                        projectsToComplete.add(woodmill);}
                    break;
                case 3:
                    if (CanBuildAProject(3)){
                        Building farm = new Farm();
                        woodLeft = getWood() - farm.getWoodCost();
                        metalLeft = getMetal() - farm.getMetalCost();
                        setWood(woodLeft);
                        setMetal(metalLeft);
                        projectsToComplete.add(farm);}
                    break;
                case 4:
                    if(CanBuildAProject(4)){
                        Building quarry = new Quarry();
                        woodLeft = getWood() - quarry.getWoodCost();
                        metalLeft = getMetal() - quarry.getMetalCost();
                        setWood(woodLeft);
                        setMetal(metalLeft);
                        projectsToComplete.add(quarry);}
                    break;
                case 5: if(CanBuildAProject(5)){
                    Building castle = new Castle();
                    woodLeft = getWood() - castle.getWoodCost();
                    metalLeft = getMetal() - castle.getMetalCost();
                    setWood(woodLeft);
                    setMetal(metalLeft);
                    projectsToComplete.add(castle);}
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Please, choose another option");
                    break;
            }
    }

    public void addWorker() {
        if(!CheckIfPlayerCanAddMoreWorkers()){
            return;
        }
        System.out.println("Name your worker: ");
        Scanner sc = new Scanner(System.in);
        name = sc.next();
        System.out.println();
        int occupationSwitch = PrintWorkMenu();
        chooseOccupation(name, occupationSwitch);

    }
    public void addRandomWorker(String name, GameTweaks gameTweaks){

        chooseOccupation(name, gameTweaks.RandomWorker());
    }

    public void chooseOccupation(String name, int occupation){
        switch (occupation) {
            case 1 -> workers.add(new Worker(name, "Farmer", () -> AddFood()));
            case 2 -> workers.add(new Worker(name, "Miner", () -> AddMetal()));
            case 3 -> workers.add(new Worker(name, "Lumberjack", () -> AddWood()));
            case 4 -> workers.add(new Worker(name, "Builder", () -> Build()));
        }
        population++;

    }
    public int PrintWorkMenu(){
        System.out.println("1.Collect food");
        System.out.println("2.Collect metal");
        System.out.println("3.Collect wood");
        System.out.println("4.Build");
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public void Day(){
        BuryDead();
        FeedWorkers();
        AddBuildingToVillage();
        for (Worker work:workers) {
            work.DoWork();
        }
        daysGone++;
    }
    public void FeedWorkers(){
        for (Worker worker: workers) {
            if(food < 1 || worker.isHungry){
                worker.isHungry = true;
                worker.daysHungry++;}

            else {
                setFood(-1);
                worker.isHungry = false;
                worker.daysHungry = 0;
            }
        }
    }
    public void Funeral(){
        for (Worker worker:workers) {
            if (worker.daysHungry >= 40){
                worker.isAlive = false;
                deadPeople++;
                population--;
            }
        }
    }
    public void BuryDead()
    {
        Funeral();
        for (int i = 0; i < getWorkers().size(); i++){
            if(!getWorkers().get(i).isAlive){
                System.out.println(getWorkers().get(i).name + " died. R.I.P.");
                workers.remove(i);
            }
        }
    }
    public void AddBuildingToVillage(){
        for(int i = 0; i < getProjectsToComplete().size(); i++){
            Building building = getProjectsToComplete().get(i);
            building.CheckIfBuildingIsComplete();
            if(building.isComplete){
                getBuildings().add(building);
                getProjectsToComplete().remove(i);
            }
        }
    }
    public int getAmountsOfHouses(){
        int count = 0;
        for (Building building: buildings
        ) {
            if(building instanceof House)
                count++;}
        return count;
    }

    public boolean CheckIfPlayerCanAddMoreWorkers(){
        if((getAmountsOfHouses() * 2) >= getPopulation()){
            return true;
        }
        else {
            System.out.println("Build more houses before adding more people.");
            return false;
        }
    }
    public boolean CastleExists(){
        for (Building building: getBuildings()
             ) {
            if(building instanceof Castle)
                return true;
        }
        return false;
    }
    public void LoadProgress(DatabaseConnection db){

        db.Load();
        this.name = db.GetName();
        this.metal = db.GetMetal();
        this.food = db.GetFood();
        this.wood = db.GetWood();
        this.daysGone = db.GetDaysGone();
        this.buildings = db.GetBuildings();
        this.workers = db.GetWorkers();
        this.projectsToComplete = db.GetProjectsToComplete();
        this.deadPeople = db.GetDeadPeople();


    }
    public void SaveProgress(){
        db.Save(this);

    }
}

