package seer2.pet;

import seer2.hu.ByteArray;

import java.util.HashMap;

public class PetInfoManager {
    public static final int MAXBAGPETCOUNT = 6;
    public static HashMap<Integer,PetInfo> petInfoMap = new HashMap();
    public static HashMap<Integer,PetInfo> petStorageInfoMap = new HashMap();
    //public static EventDispatcher eventDispatcher = new EventDispatcher();
    //public static Function getPetInfoFunc;
    //public static Function getPetInfoErrorFunc;
    public static final int PUTTOSTORAGEFLAG = 0;
    public static final int PUTINBAGFLAG = 1;
    public static final int UNFOLLOWFLAG = 0;
    public static final int FOLLOWFLAG = 1;
    public static int requestAddToBagPetId;
    //public static Function getPetStateCallBack;
    public static final int ATK = 0;
    public static final int Def = 1;
    public static final int Spatk = 2;
    public static final int Spdef = 3;
    public static final int Spd = 4;
    public static final int Hp = 5;
    //public static final Array QUALITYLIMIT = [[0,119],[120,239],[240,359],[360,479],[480,599],[600,719],[720,720]];
    public static void setupPetInfoData(ByteArray param1){
        handleFightPetInfo(param1);
        handleStoragePetInfo(param1);
        initPetHandle(param1);
        //listenerDelete();
        //PetPowerUpdate.setup();
    }
    private static void handleFightPetInfo(ByteArray param1){
        hsAdder(param1,petInfoMap);
    }
    private static void handleStoragePetInfo(ByteArray param1){
        hsAdder(param1,petStorageInfoMap);
    }
    private static void hsAdder(ByteArray param1, HashMap<Integer,PetInfo> hm){
        PetInfo p1 = null;
        int c1 = param1.readUnsignedInt();
        for (int i = 0; i < c1; i++) {
            p1=new PetInfo();
            p1.readPetInfo(param1);
            hm.put(p1.catchTime,p1);
        }
    }
    private static void initPetHandle(ByteArray param1){
        int c1 = param1.readUnsignedInt();
        PetInfo p1 =null;
        for (int i = 0; i < c1; i++) {
            p1 = getPetInfoFromMap(param1.readUnsignedInt());
            p1.resourceId = param1.readUnsignedInt();
            p1.level = param1.readUnsignedShort();
            p1.setFlag(param1.readUnsignedInt());
            p1.isInitialPet = true;
            petInfoMap.put(p1.catchTime,p1);
        }
    }
    public static PetInfo getPetInfoFromMap(int catchTime){
        PetInfo  p1 = null;
        if(petInfoMap.containsKey(catchTime)) {
            p1 = petInfoMap.get(catchTime);
        }else {
            p1 = new PetInfo();
            p1.catchTime = catchTime;
        }
        return p1;
    }
}