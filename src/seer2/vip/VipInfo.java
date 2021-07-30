package seer2.vip;

import seer2.hu.ByteArray;
import seer2.hu.Vector;

import java.util.ArrayList;

public class VipInfo {
    public static String ENERGY_CHANGE = "energyChange";
    public static String VIP_UPDATE = "vipUpdate";
    public static String LUCKY_UPDATE = "luckyUpdate";
    public int vipFlag;
    public int payFlag;
    public int level;
    public int point;
    public int totalPoint;
    public int leftDay;
    private int currentEnergy;
    public int totalEnergy;
    public int energyBall;
    public int equipeBallCnt;
    public Vector<Integer> equipeBallVec;
    public int onceVipFlag;
    public int luckyLeftDay;
    public int luckyMsgType;
    public int luckyFlag;
    public void readBase(ByteArray param1){
        this.payFlag = param1.readUnsignedInt();
        this.vipFlag = param1.readUnsignedByte();
        this.level = param1.readUnsignedByte();
        this.point = param1.readUnsignedInt();
        this.leftDay = param1.readUnsignedInt();
        this.equipeBallCnt = param1.readUnsignedInt();
        this.equipeBallVec = new Vector<>();
        for(int i = 0; i < this.equipeBallCnt; i++) {
            this.equipeBallVec.push(param1.readUnsignedInt());
        }
        this.energyBall = param1.readUnsignedInt();
        //this.currentEnergy =
        param1.readUnsignedInt();
        this.totalEnergy = param1.readUnsignedInt();
        this.onceVipFlag = param1.readUnsignedByte();
        this.luckyLeftDay = param1.readUnsignedByte();
        this.luckyMsgType = param1.readUnsignedByte();
        this.luckyFlag = param1.readUnsignedByte();
    }

}
