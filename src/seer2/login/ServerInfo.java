package seer2.login;

import seer2.hu.ByteArray;

public class ServerInfo {

    private int serverId;
    private String serverIp;
    private int serverPort;
    private int userCount;
    private int friendCount;
    private int isNewSvr;
    private int isRecommendSvr;


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


    @Override
    public String toString() {
        return "\nServerInfo{" +
                "serverId=" + serverId +
                ", serverIp='" + serverIp + '\'' +
                ", serverPort=" + serverPort +
                ", userCount=" + userCount +
                ", friendCount=" + friendCount +
                ", isNewSvr=" + isNewSvr +
                ", isRecommendSvr=" + isRecommendSvr +
                '}';
    }

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public int getFriendCount() {
        return friendCount;
    }

    public void setFriendCount(int friendCount) {
        this.friendCount = friendCount;
    }

    public int getIsNewSvr() {
        return isNewSvr;
    }

    public void setIsNewSvr(int isNewSvr) {
        this.isNewSvr = isNewSvr;
    }

    public int getIsRecommendSvr() {
        return isRecommendSvr;
    }

    public void setIsRecommendSvr(int isRecommendSvr) {
        this.isRecommendSvr = isRecommendSvr;
    }
}
