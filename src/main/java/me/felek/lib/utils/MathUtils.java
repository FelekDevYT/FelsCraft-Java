package me.felek.lib.utils;

import java.util.Random;

public class MathUtils {
    public static int random(int min, int max){
        return new Random().nextInt(max - min + 1) + min;
    }
}
