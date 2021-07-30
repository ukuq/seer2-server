package seer2.login;


import seer2.hu.ByteArray;

import java.util.ArrayList;

public class OnlineServerListInfo {

    public int isNewPlayer;
    public int serverTotalCount;
    public ArrayList<ServerInfo> serverInfos;

    public OnlineServerListInfo(ByteArray param1) {
        serverInfos = new ArrayList<>();
        serverTotalCount = param1.readUnsignedInt();
        long c = param1.readUnsignedInt();
        for (int i = 0; i < c; i++) {
            serverInfos.add(new ServerInfo(param1));
        }
        isNewPlayer = param1.readUnsignedByte();
        //buddy
        c = param1.readUnsignedInt();
        System.out.println(c);
        for (int i = 0; i < c; i++) {
            System.out.print(param1.readUnsignedInt() + ", ");
        }
        //black
        c = param1.readUnsignedInt();
        System.out.println(c);
        for (int i = 0; i < c; i++) {
            System.out.print(param1.readUnsignedInt() + ", ");
        }
        System.out.println("check: " + param1.bytesAvailable());
    }

    @Override
    public String toString() {
        return "OnlineServerListInfo{" +
                "isNewPlayer=" + isNewPlayer +
                ", serverTotalCount=" + serverTotalCount +
                ", serverInfos=" + serverInfos +
                '}';
    }
}
