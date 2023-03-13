package org.village;

import java.util.Random;

public class GameTweaks {

    public int RandomWorker(){
        Random rand = new Random();
        return rand.nextInt(1,5);
    }
}
