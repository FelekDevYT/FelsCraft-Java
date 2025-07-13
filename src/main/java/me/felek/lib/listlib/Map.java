package me.felek.lib.listlib;

import java.util.ArrayList;
import java.util.List;

public class Map<T, I> {
    private List<T> names = new ArrayList<>();
    private List<I> values = new ArrayList<>();

    public void set(T name, I value){
        if(names.contains(name)){
            for(int i = 0; i < names.size(); i++){
                if(names.get(i).equals(name)){
                    values.set(i, value);
                }
            }
        }else{
            names.add(name);
            values.add(value);
        }
    }

    public void add(T name, I value){
        names.add(name);
        values.add(value);
    }

    public boolean containsKey(I key){
        return names.contains(key);
    }

    public I get(T name){
        for(int i = 0; i < names.size(); i++){
            if(names.get(i).equals(name)){
                return values.get(i);
            }
        }

        return null;
    }
}
