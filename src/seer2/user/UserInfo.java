package seer2.user;

import seer2.hu.ByteArray;
import seer2.hu.Vector;
import seer2.item.EquipItem;
import seer2.pet.PetInfo;
import seer2.vip.MorphInfo;
import seer2.vip.NonoInfo;
import seer2.vip.VipInfo;

public class UserInfo{
    public static final int NICK_DATA_LEN = 16;
    public static final int IP_ARRAY_LEN = 20;
    public String loginAccount;
    public int id;
    public String nick;
    public int x;
    public int y;
    public int medalId;
    public int createTime;
    public int serverID;
    public int trainerScore;
    public int color;
    public Vector<EquipItem> equipVec;
    public int sex;
    public int coins;
    public int highestPetLevel;
    public int petCount;
    public int petLevel;
    public int sptCount;
    public int medalCount;
    public String signature;
    public PetInfo followingPetInfo;
    public PetInfo ridingPetInfo;
    public TeamMainInfo teamInfo;
    public VipInfo vipInfo;
    public NonoInfo nonoInfo;
    public Vector<Integer> activityData;
    public Vector<Integer> clientCacheData;
    public int stateFlag;
    public int callerUserId;
    public int troop = 0;
    public int activityStatus = 0;
    public int honorNum = 0;
    public int isPlanHook = 0;
    public int isOnhook = 0;
    public int plantLevel = 0;
    public int moneyCount = 0;
    public MorphInfo morphInfo;
    public BirthdayInfo birthdayInfo;
    public String ip;
    public int isYearVip;
    public UserInfo(ByteArray byteArray){
        this();
        readBaseInfo(byteArray);
    }
    public UserInfo(){
        this.teamInfo = new TeamMainInfo();
        this.vipInfo = new VipInfo();
        this.nonoInfo = new NonoInfo();
        this.activityData = new Vector<>();
        this.clientCacheData = new Vector<>();
        this.morphInfo = new MorphInfo();
        this.birthdayInfo = new BirthdayInfo();
    }
    public void readBaseInfo(ByteArray param2){
        id = param2.readUnsignedInt();
        nick = param2.readUTFBytes(UserInfo.NICK_DATA_LEN);
        color = param2.readUnsignedInt();
        x = param2.readUnsignedInt();
        y = param2.readUnsignedInt();
        medalId = param2.readUnsignedInt();
        createTime = param2.readUnsignedInt();
        int  c1 = param2.readUnsignedInt();
        equipVec = new Vector<>();
        for (int i = 0; i < c1; i++) {
            equipVec.push(new EquipItem(param2.readUnsignedInt()));
        }
        c1 = param2.readUnsignedInt();
        int tmp = 0;
        for (int i = 0; i < c1; i++) {
            tmp = param2.readUnsignedInt();
        }
        nonoInfo.equipId=tmp;
    }

    public void readSimpleInfo(ByteArray param1)
    {
        readUserInfoFromSex(param1);
        vipInfo.vipFlag = param1.readUnsignedByte();
        vipInfo.level =param1.readUnsignedByte();
        plantLevel = param1.readUnsignedInt();
    }

    public void readUserInfoFromSex(ByteArray param1){
        sex = param1.readUnsignedByte();
        nick = param1.readUTFBytes(UserInfo.NICK_DATA_LEN);
        color = param1.readUnsignedInt();
        trainerScore = param1.readUnsignedInt();
        equipVec = new Vector<>();
        int c = param1.readUnsignedInt();
        for (int i = 0; i < c; i++) {
            equipVec.push(new EquipItem(param1.readUnsignedInt()));
        }
    }

    public void parseEnterMap(ByteArray param2){
        PetInfo petInfo;
        readBaseInfo(param2);
        int c = param2.readUnsignedInt();
        for (int i = 0; i < c; i++) {
            petInfo = new PetInfo();
            petInfo.catchTime = param2.readUnsignedInt();
            petInfo.resourceId = param2.readUnsignedInt();
            petInfo.sex = param2.readUnsignedByte();
            petInfo.level = param2.readUnsignedByte();
            petInfo.character = param2.readUnsignedInt();
            petInfo.potential = param2.readUnsignedInt();
            petInfo.setFlag(param2.readUnsignedInt());
            petInfo.petRideChipId = param2.readUnsignedInt();
            petInfo.chipPutOnTime = param2.readUnsignedInt();
            petInfo.evolveLevel = param2.readUnsignedInt();
            if(i == 0)
            {
                followingPetInfo = petInfo;
            }
            else
            {
                ridingPetInfo = petInfo;
            }
        }
        troop = param2.readUnsignedInt();
        vipInfo.vipFlag = param2.readUnsignedByte();
        vipInfo.level =param2.readUnsignedByte();
        c =  param2.readUnsignedInt();
        activityData = new Vector<>();
        for (int i = 0; i < c; i++) {
            activityData.push(param2.readUnsignedInt());
        }
        trainerScore = param2.readUnsignedInt();
    }
}

