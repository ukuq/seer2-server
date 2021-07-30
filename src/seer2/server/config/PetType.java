package seer2.server.config;

public class PetType {
    //属性克制
    public static int advantage(int t1, int t2) {
        return 100;
    }

    //本系优势
    public static int benefit(int t1, int t2) {
        return t1 == t2 ? 150 : 100;
    }
}
