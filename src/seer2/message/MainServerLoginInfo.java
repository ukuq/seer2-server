package seer2.message;

import seer2.hu.ByteArray;
import seer2.hu.Message;

import java.util.Arrays;

public class MainServerLoginInfo {

    public static final int cmid=103;
    public static final byte[] bytes={42, 0, 0, 0, 103, 0, -65, 113, -69, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 49, 3, -118, -85, 109, -27, -61, -78, 28, 65, 16, -13, 96, 8, -1, -128, 1, 0, 0, 0};

    public long resultFlag;
    public String account;
    public String password;
    public byte[] session;
    public boolean hasRole;
    public byte[] verifyImgId;
    public long verifyImgDataSize;
    public byte[] verifyImgData;
    public String lastLoginIP;
    public long lastLoginTime;
    public String lastLoginCity;
    public String curLoginCity;

    public static void main(String[] args) {
        Message message =new Message(bytes);
        System.out.println(message.parseInfo());
        MainServerLoginInfo m=new MainServerLoginInfo(message.data);
        System.out.println(m);
        System.out.println();
    }

    public  MainServerLoginInfo(ByteArray param1)
    {
        this.resultFlag = param1.readUnsignedInt();
        if (this.resultFlag < 0)
        {
            return;
        }
        if (this.resultFlag == 0)
        {
            this.session = new byte[16];
            param1.readBytes(this.session, 0, 16);
            this.hasRole = param1.readUnsignedInt() > 0;
        }
        else if (this.resultFlag < 6)
        {
            this.verifyImgId = new byte[16];
            param1.readBytes(this.verifyImgId, 0, 16);
            this.verifyImgDataSize = param1.readUnsignedInt();
            this.verifyImgData = new byte[16];
            param1.readBytes(this.verifyImgData, 0, (int)this.verifyImgDataSize);
        }
        else
        {
            this.session = new byte[16];
            param1.readBytes(this.session, 0, 16);
            this.hasRole = param1.readUnsignedInt() > 0;
            this.lastLoginIP = uintToIp((int)param1.readUnsignedInt());
            this.lastLoginTime = param1.readUnsignedInt();
            this.lastLoginCity = param1.readUTFBytes(64);
            this.curLoginCity = param1.readUTFBytes(64);
        }
    }// end function

    private static String uintToIp(int uint){
        StringBuilder sb=new StringBuilder("");
        for (int i = 0; i < 4; i++) {
            sb.append((uint & 0x2)* 16 +(uint & 0x1));
            sb.append(".");
            uint>>=2;
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
    @Override
    public String toString() {
        return "resultFlag:"+resultFlag+", account:"+account+", password:"+password+", session:"+ Arrays.toString(session)+", hasRole:"+hasRole+", verifyImgId:"+verifyImgId+", verifyImgDataSize:"+verifyImgDataSize+", verifyImgData:"+verifyImgData+", lastLoginIP:"+lastLoginIP+", lastLoginTime:"+lastLoginTime+", lastLoginCity:"+lastLoginCity+", curLoginCity:"+curLoginCity;}
}



