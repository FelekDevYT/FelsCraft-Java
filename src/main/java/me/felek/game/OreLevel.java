package me.felek.game;

import java.util.Random;

public class OreLevel {
    private int minY;
    private int maxY;

    private int[] rndLevel;

    public OreLevel(int minY, int maxY, int[] rndLevel) {
        this.minY = minY;
        this.maxY = maxY;//F=F F=S(1,5)(1,3)
        if(rndLevel.length == 4){
            this.rndLevel = rndLevel;
        }
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxY() {
        return maxY;
    }

    public boolean isValid(int y){
        if(y > minY && y < maxY){
            return true;
        }

        return false;
    }

    public boolean isShouldGenerate(int y){
        if(isValid(y)){
            int num1 = new Random().nextInt(rndLevel[0], rndLevel[1]);
            int num2 = new Random().nextInt(rndLevel[2], rndLevel[3]);
            if(num1 == num2){
                return true;
            }
        }

        return false;
    }
}
