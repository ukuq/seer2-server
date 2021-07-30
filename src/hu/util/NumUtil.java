package hu.util;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumUtil {
    public static int[] bytesToUnsignedInts(byte[] a){
        int[] b = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i] & 0xff;
        }
        return b;
    }
    public static String bytesToHex(byte[] a){
        return intsToHex(bytesToUnsignedInts(a));
    }
    public static String intsToHex(int[] a) {
        if (a == null)
            return "null";
        int iMax = a.length - 1;
        if (iMax == -1)
            return "[]";
        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            if(a[i]<0x10)b.append('0');
            b.append(Integer.toHexString(a[i]));
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }
    public static int[] hexToInts(String hex){
        String[] ss = hex.split(", ");
        int[] ints = new int[ss.length];
        for (int i = 0; i < ss.length; i++) {
            ints[i]=Integer.parseInt(ss[i],16);
        }
        return ints;
    }

    public static byte[] byteMerger(byte[] bt1, byte[] bt2){
        byte[] bt3 = new byte[bt1.length+bt2.length];
        System.arraycopy(bt1, 0, bt3, 0, bt1.length);
        System.arraycopy(bt2, 0, bt3, bt1.length, bt2.length);
        return bt3;
    }
}
