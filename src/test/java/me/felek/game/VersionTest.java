package me.felek.game;

import me.felek.game.utils.VersionUtil;

import java.util.ArrayList;
import java.util.List;

public class VersionTest {
    public static void main(String[] args) {
        List<String> strs = new ArrayList<String>();
        strs.add("1.0");
        strs.add("1.1");
        strs.add("0.1");
        strs.add("0.2");
        strs.add("0.0");
        strs.add("10.6");

        if (VersionUtil.compareVersion(strs.get(0)) != 2) {
            throw new RuntimeException();
        }
        if (VersionUtil.compareVersion(strs.get(1)) != 2) {
            throw new RuntimeException();
        }
        if (VersionUtil.compareVersion(strs.get(2)) != 1) {
            throw new RuntimeException();
        }
        if (VersionUtil.compareVersion(strs.get(3)) != 2) {
            throw new RuntimeException();
        }
        if (VersionUtil.compareVersion(strs.get(4)) != 0) {
            throw new RuntimeException();
        }
        if (VersionUtil.compareVersion(strs.get(5)) != 2) {
            throw new RuntimeException();
        }

        System.out.println("All tests passed.");
    }
}
