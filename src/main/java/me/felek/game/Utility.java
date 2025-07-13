package me.felek.game;

import java.util.ArrayList;

public class Utility {
    public static int reverseIndex(int index){
        ArrayList<Integer> idxs = new ArrayList<>();

        for(int i = 0; i < Game.SCREEN_WIDTH /Game.BLOCK_SIZE; i++){
            idxs.add(Game.SCREEN_HEIGHT /Game.BLOCK_SIZE - i);
        }

        return idxs.get(index);
    }
}
