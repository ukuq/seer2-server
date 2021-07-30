package seer2.manager;

import seer2.hu.ByteArray;

public class TimeManager {
    public static final String TIMEUPDATE = "timeUpdate";
    public static int loginTime;
    public static int serverTime;
    public static int availableTime;
    public static int doubleExpTime;
    public static int maxAvailableTime;
    public static boolean isAllowOpenBatteryPanel = true;
    public static int precisionServerTime;
    public static int onlineTime;

    public static void setupLogin(ByteArray param1) {
        loginTime = param1.readUnsignedInt();
        serverTime = loginTime;
        precisionServerTime = loginTime;
        //updatePrecision();
        onlineTime = 0;
        //setupOnlineTime();
    }

    public static void setupTime(ByteArray param1) {
        maxAvailableTime = param1.readUnsignedInt();
        availableTime = param1.readUnsignedInt();
        doubleExpTime = param1.readUnsignedInt();
    }
    public static void setupOnlineTime(){
        //DayLimitManager.getDoCount(898,i->onlineTime = serverTime - loginTime + i);
    }
    public static int getx(){
        return 1;
    }
}
