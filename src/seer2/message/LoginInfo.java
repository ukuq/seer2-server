package seer2.message;

import seer2.hu.ByteArray;
import seer2.hu.Vector;
import seer2.manager.SeerVar;
import seer2.pet.PetInfoManager;
import seer2.user.TeamMainInfo;
import seer2.manager.TimeManager;
import seer2.user.UserInfo;

public class LoginInfo{
    public static final LoginInfo loginInfo = new LoginInfo();
    public int serverID;
    public String onlineIP;
    public int onlinePort;
    public byte[] session;
    public ByteArray userData;
    public int account;
    public String loginAccount;
    public int mapId;
    public int sceneId;
    public UserInfo userInfo;
    public Vector<Integer> completeQuestIdVec;
    public Vector<Integer> lastCompleteQuestIdVec;
    public Vector<Integer> inProgressQuestIdVec;
    public int isShowLoginAwardPanel;
    public Vector<Integer> expiredEquipList;
    public int giftindex;
    public int onLineDays;
    public boolean dayOneLogin;
    public int summerRegistrationDays;
    private LoginInfo(){

    }
//    public static function setFromBaseInfo(param1:Object) : void
//    {
//        _session = param1.session;
//        _serverID = param1.serverId;
//        _onlineIP = param1.onlineServerIp;
//        _onlinePort = param1.onlineServerPort;
//        _userData = param1.userData;
//        _account = param1.account;
//        _loginAccount = param1.loginAccount;
//    }

    public void setFromOnline(ByteArray param1){
        userInfo = new UserInfo(param1);
        userInfo.serverID = serverID;
        userInfo.sex = param1.readUnsignedByte();
        userInfo.coins = param1.readUnsignedInt();
        mapId = param1.readUnsignedInt();
        sceneId = param1.readUnsignedInt();
        userInfo.trainerScore = param1.readUnsignedInt();
        userInfo.highestPetLevel = param1.readUnsignedByte();
        TimeManager.setupLogin(param1);
        parseQuestData(param1);
        PetInfoManager.setupPetInfoData(param1);
        TimeManager.setupTime(param1);
        isShowLoginAwardPanel = param1.readUnsignedInt();
        userInfo.stateFlag = param1.readUnsignedInt() & 1;
        userInfo.callerUserId = param1.readUnsignedInt();
        TeamMainInfo.readBaseInfo(userInfo.teamInfo,param1);
        TeamMainInfo.readMoreInfo(userInfo.teamInfo,param1);
        userInfo.honorNum = param1.readUnsignedInt();
        userInfo.vipInfo.readBase(param1);
        userInfo.nonoInfo.readData(param1);
        SeerVar.nonoButlerController.parseData(param1);
        parseExpiredEquip(param1);
        onLineDays = param1.readUnsignedInt();
        giftindex = param1.readUnsignedInt();
        summerRegistrationDays = param1.readUnsignedInt();
        int c2  = param1.readUnsignedInt();
        if(c2!=0){
            userInfo.birthdayInfo.isOpen = true;
            userInfo.birthdayInfo.year = c2/10000;
            userInfo.birthdayInfo.month = c2%10000/100;
            userInfo.birthdayInfo.day = c2%100;
        }
        userInfo.ip = param1.readUTFBytes(UserInfo.IP_ARRAY_LEN);
        userInfo.isYearVip = param1.readUnsignedInt();
        System.out.println("LoginInfo check state:" + param1.bytesAvailable());
    }
    private void parseExpiredEquip(ByteArray param1){
        int c1 = param1.readUnsignedInt();
        expiredEquipList = new Vector<>();
        for (int i = 0; i <c1; i++) {
            expiredEquipList.push(param1.readUnsignedInt());
        }
    }
    private void parseQuestData(ByteArray param1){
        completeQuestIdVec=new Vector<>();
        lastCompleteQuestIdVec=new Vector<>();
        inProgressQuestIdVec=new Vector<>();
        parseVector(completeQuestIdVec,param1);
        parseVector(lastCompleteQuestIdVec,param1);
        parseVector(inProgressQuestIdVec,param1);
    }
    private void parseVector(Vector<Integer> vector,ByteArray param1){
        int c1 = param1.readUnsignedInt();
        for (int i = 0; i < c1; i++) {
            vector.push(param1.readUnsignedInt());
        }
    }
}


