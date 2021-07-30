package hu.util;


import java.io.*;


public class FileT {
    public static final String fileName = "C:\\Users\\15591\\Desktop\\seer2\\resources\\TaomeeCoreDLL.swf";


    public static void main(String[] args) {
        swfDecompress(fileName);
        //swfCompress(fileName);
    }


    public static void swfDecompress(String fileName) {
        FileInputStream fin;
        FileOutputStream fout;
        try {
            fin = new FileInputStream(new File(fileName));
            fout = new FileOutputStream(new File(fileName + "_dec.swf"));
            fin.read(new byte[7], 0, 7);
            fout.write(ZLibUtils.decompress(fin));
            System.out.println("decompress succeed");
            fout.close();
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("succeed");
    }

    public static void swfCompress(String fileName) {
        FileInputStream fin;
        FileOutputStream fout;
        try {
            fin = new FileInputStream(new File(fileName));
            fout = new FileOutputStream(new File("comp_" + fileName));
            fout.write(new byte[7], 0, 7);
            fout.write(ZLibUtils.compress(fin.readAllBytes()));
            System.out.println("compress succeed");
            fout.close();
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("succeed");
    }
}


