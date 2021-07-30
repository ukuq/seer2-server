package seer2.server.utils;

import java.util.Random;

public class ArrayUtils {
    private static Random r = new Random();
    public static int getRandomOne(int[] arr){
        return arr[r.nextInt(arr.length)];
    }
    public static int[] parseFromString(String s) {
        String[] ss = s.split(" ");
        int[] arr = new int[ss.length];
        for (int i = 0; i < ss.length; i++) {
            arr[i] = Integer.parseInt(ss[i]);
        }
        return arr;
    }

    public static boolean contains(int[] arr,Object object){
        for (Object o:arr){
            if(o.equals(object))return true;
        }
        return false;
    }
}
