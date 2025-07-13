package me.felek.game.utils;

import me.felek.game.World;

public class VersionUtil {

    // Возвращает:
    //   1 - versions are equal
    //   2 - new version
    //   0 - old version
    //  -1 - error
    public static int compareVersion(String version) {
        try {
            String[] mm = version.split("\\.");
            String[] gmm = World.WORLD_VERSION.split("\\.");

            if (mm.length < 2 || gmm.length < 2) {
                return -1;
            }

            int major = Integer.parseInt(mm[0]);
            int gameMajor = Integer.parseInt(gmm[0]);

            if (major != gameMajor) {
                return (major > gameMajor) ? 2 : 0;
            }

            int minor = Integer.parseInt(mm[1]);
            int gameMinor = Integer.parseInt(gmm[1]);

            if (minor != gameMinor) {
                return (minor > gameMinor) ? 2 : 0;
            }

            return 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return -1;
        }
    }
}