package seer2.vip;

import seer2.hu.ByteArray;

public class NonoInfo {
    public boolean isHava;
    public String nonoName;
    public int color = 0;
    public int equipId = 0;
    public int flag = 0;
    public boolean isFollowing = false;
    public void readData(ByteArray param1){
        isHava = param1.readUnsignedByte() == 1;//updateNonoInfo
        nonoName = param1.readUTFBytes(16);
        flag = param1.readUnsignedInt();
        color = param1.readUnsignedInt();
    }
}
