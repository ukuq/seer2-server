package seer2.server.utils;

import java.util.List;
import java.util.Random;

public class Util {
    public static int between(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }
    private static final Random rand = new Random();
    public static <T> T random(List<T> list) {
        return list.get(rand.nextInt(list.size()));
    }
}
