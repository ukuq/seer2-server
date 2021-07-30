package seer2.constant;

import java.util.HashMap;

public enum FightMode {
    UNKNOWN_MODE(0),

    SINGLE_PVE(1),

    FIGHT_TRAINING_PET(2),

    FIGHT_BOSS(3),

    FIGHT_DOOR_NORMAL(4),

    FIGHT_DOOR_50V50(5),

    FIGHT_INDREAM(6),

    FIGHT_ACTIIVITY(7),

    FIGHT_TRAIN_INIT_MON(8),

    FIGHT_MAKE_TROUBLE(9),

    FIGHT_NPC(10),

    FIGHT_CHOOK_FIGHT(11),

    FIGHT_ACTIVITY_BOSS_USE_ONE_MON(12),

    FIGHT_NPC_MAX(13),

    FIGHT_ACT_VS_WuSha(22),

    FIGHT_PVP(100),

    FIGHT_RING(101),

    FIGHT_PVP_DOOR_NORMAL(102),

    FIGHT_PVP_DOOR_50V50(103),

    FIGHT_PVP_ACTIVIITY(104),

    FIGHT_PVP_INVITE_1V1(105),

    FIGHT_PVP_INVITE_NVN(106),

    FIGHT_PVP_INVITE_2V2(107),

    FIGHT_PVP_WOHOO(109);
    private int id;
    private static HashMap<Integer,FightMode> map;
    FightMode(int id){
        this.id=id;
    }
    public int getId(){
        return id;
    }
    public static HashMap<Integer, FightMode> getMap() {
        if(map!=null)return map;
        map = new HashMap<>();
        FightMode[] values = FightMode.values();
        for (FightMode value : values) {
            map.put(value.getId(), value);
        }
        return map;
    }

    public static String findDescriptionById(int id) {
        FightMode command = getMap().get(id);
        return (command != null)?command.toString():FightMode.UNKNOWN_MODE.toString();
    }
}

