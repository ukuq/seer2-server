package seer2.constant;

import java.util.HashMap;

public class FightWeather {
    private static HashMap<Integer,String> map;
    static {
        map = new HashMap<>();
        map.put(1,"晴天");
        map.put(2,"雨天");
        map.put(3,"炎热");
        map.put(4,"暴风雪");
        map.put(5,"沙尘暴");
        map.put(6,"地震");
        map.put(7,"雷暴天");
        map.put(8,"飓风");
        map.put(9,"夜幕");
        map.put(10,"异空间");
        map.put(11,"古战场");
        map.put(12,"虫群");
        map.put(13,"星移");
        map.put(14,"三级星移");
        map.put(15,"二级星移");
        map.put(17,"强震");
        map.put(18,"黄沙暴");
        map.put(20,"MAX星移");
        map.put(23,"烈日");
    }
    public static HashMap<Integer, String> getMap() {
        return map;
    }

    public static String findDescriptionById(int id) {
        return map.get(id)==null?"未知":map.get(id);
    }
}
