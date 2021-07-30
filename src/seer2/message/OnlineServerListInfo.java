package seer2.message;

import hu.util.ListMapUtil;
import seer2.MainFrame;
import seer2.hu.ByteArray;
import seer2.hu.Message;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * @ 105
 */
public class OnlineServerListInfo {

    public static final int cmid=105;
    public static final byte[] bytes={39, 2, 0, 0, 105, 0, -65, 113, -69, 15, 0, 0, 0, 0, 0, 0, 0, 0, 100, 0, 0, 0, 10, 0, 0, 0, 84, 0, 49, 49, 56, 46, 56, 57, 46, 49, 52, 57, 46, 49, 56, 57, 0, -35, 4, 5, 0, 0, 0, 0, 0, 0, 1, 0, 52, 55, 46, 57, 52, 46, 49, 53, 46, 54,48,0,0,0, 0, -35, -79, 4, 0, 0, 0, 0, 1, 0, 99, 0, 49, 49, 56, 46, 56, 57, 46, 49, 52, 57, 46, 49, 56, 57, 0, -35, 19, 5, 0, 0, 0, 0, 0, 0, 94, 0, 49, 49, 56, 46, 56, 57, 46, 49, 52, 57, 46, 49, 56, 57, 0, -35, 14, 5, 0, 0, 0, 0, 0, 0, 93, 0, 49, 49, 56, 46, 56, 57, 46, 49, 52, 57, 46, 49, 56, 57, 0, -35, 13, 5, 0, 0, 0, 0, 0, 0, 91, 0, 49, 49, 56, 46, 56, 57, 46, 49, 52, 57, 46, 49, 56, 57, 0, -35, 11, 5, 0, 0, 0, 0, 0, 0, 90, 0, 49, 49, 56, 46, 56, 57, 46, 49, 52, 57, 46, 49, 56, 57, 0, -35, 10, 5, 0, 0, 0, 0, 0, 0, 88, 0, 49, 49, 56, 46, 56, 57, 46, 49, 52, 57, 46, 49, 56, 57, 0, -35, 8, 5, 0, 0, 0, 0, 0, 0, 85, 0, 49, 49, 56, 46, 56, 57, 46, 49, 52, 57, 46, 49, 56, 57, 0, -35, 5, 5, 0, 0, 0, 0, 0, 0, 82, 0, 49, 49, 56, 46, 56, 57, 46, 49, 52, 57, 46, 49, 56, 57, 0, -35, 2, 5, 0, 0, 0, 0, 0, 0, 0, 61, 0, 0, 0, -26, 93, 66, 9, 18, 35, 71, 19, -33, -49, 95, 12, -51, 36, 48, 23, -90, -60, 82, 22, -104, -46, 17, 3, 53, 126, -71, 5, 11, 73, 23, 8, 55, 10, 64, 5, -111, -63, -50, 15, -72, -64, 36, 4, -97, 110, 63, 7, 5, 14, 34, 26, -42, 42, 14, 6, -92, 9, -119, 24, 58, -118, -47, 22, 50, 64, 96, 2, 47, 28, 73, 17, -17, -15, -121, 15, 24, -76, 49, 27, -60, -116, 65, 27, 126, -16, -51, 5, -10, -69, 12, 0, 109, 67, 97, 29, 30, -81, 113, 29, -71, -86, -119, 29, -81, 37, -97, 29, 116, -123, 117, 29, 122, 104, -105, 31, -112, 21, -105, 31, 44, -7, -128, 31, 13, 121, 127, 31, 79, -51, -122, 31, 108, 29, 110, 31, -38, 123, -81, 31, -86, -70, -102, 31, 59, -22, 109, 31, -99, -119, -43, 22, -41, 24, -72, 31, 47, -121, 22, 21, 20, -126, 39, 33, 65, -87, -11, 30, -37, -111, -75, 36, 55, 119, 87, 22, -14, -123, 126, 31, 97, -17, -116, 31, 125, 110, 70, 18, -60, -93, -11, 11, 33, -117, 111, 22, -19, 65, 36, 20, -106, 22, 66, 26, 126, 22, 81, 24, -44, -47, -115, 2, -34, 67, -59, 53, 52, 126, -60, 48, -19, 84, -85, 53, 93, 113, -12, 47, -97, 12, 97, 30, -117, 25, -74, 53, 50, 120, 118, 21, -24, 90, -66, 0, 3, 0, 0, 0, 96, -67, -22, 8, -127, 86, 39, 27, 3, 81, 67, 39};

    public ArrayList<ServerInfo> serverInfos;
    public long isNewPlayer;
    public byte[] friendData;
    public long serverTotalCount;

    public static void main(String[]args){
        System.out.println(Arrays.toString("47.94.15.60".getBytes()));
        System.out.println(Arrays.toString("111.230.233.136".getBytes()));
        Message message =new Message(MainFrame._105);
        System.out.println(message.parseInfo());
        OnlineServerListInfo o=new OnlineServerListInfo(message.data);
        //ListMapUtil.listPrint(o.serverInfos);
        System.out.println(o);
    }

    public OnlineServerListInfo(ByteArray byteArray){
        serverInfos=new ArrayList<>();
        serverTotalCount=byteArray.readUnsignedInt();
        long c=byteArray.readUnsignedInt();
        for (int i = 0; i < c; i++) {
            serverInfos.add(new ServerInfo(byteArray));
        }
        isNewPlayer=byteArray.readUnsignedByte();
        friendData=null;
    }

    @Override
    public String toString() {
        return "serverTotalCount:"+serverTotalCount+", isNewPlayer:"+isNewPlayer+", serverInfos:\n"+ ListMapUtil.iPrint(serverInfos);
    }
}
class ServerInfo{
    public long serverId;
    public String serverIp;
    public long serverPort;
    public long userCount;
    public long friendCount;
    public long isNewSvr;
    public long isRecommendSvr;

    @Override
    public String toString() {
        return "id:"+serverId+ ", ip:"+serverIp +", port:"+serverPort +", users:"+userCount +", friends:"+friendCount+", isN:"+isNewSvr +", isR:"+isRecommendSvr;
    }

    public ServerInfo(ByteArray param1){
        if(param1!=null){
            this.serverId = param1.readUnsignedShort();
            this.serverIp = param1.readUTFBytes(16);
            this.serverPort = param1.readUnsignedShort();
            this.userCount = param1.readUnsignedInt();
            this.friendCount = param1.readUnsignedByte();
            this.isNewSvr = param1.readUnsignedByte();
        }
    }
}