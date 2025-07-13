package me.felek.game.worldgen;

import me.felek.lib.utils.MathUtils;

import java.util.Random;

public class StructureRarity {
    private int r1;
    private int r2;
    private int r3;
    private int r4;

    public StructureRarity(int r1, int r2, int r3, int r4) {
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.r4 = r4;
    }

    public boolean isShouldGenerate(){
        int num1 = MathUtils.random(r1, r2);
        int num2 = MathUtils.random(r3, r4);

        return num1 == num2;
    }
}
