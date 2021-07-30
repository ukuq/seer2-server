package seer2.user;

import seer2.hu.ByteArray;

public class TeamMainInfo {
    public int id;
    public String name;
    public int logoFront;
    public int logoBack;
    public int logoColor;
    public int userPost;
    public int dissolveDay;
    public int teamTotalExp;
    public int teamTodayExp;
    public int teamExpLevel;
    public int teamLevelNeedExp;
    public int userTotalContribute;
    public int userTodayContribute;
    public int userContributeLevel;
    public int userLevelNeedContribute;
    public int teamMaxExpPerday;
    public int userMaxContributePerday = 500;
    public int rank;
    public int actorActive;
    public int actorSetExp;
    public int dayActorActive;
    public int isCre = 0;
    public String msg = "";
    public static void readBaseInfo(TeamMainInfo param1, ByteArray param2){

            param1.id = param2.readUnsignedInt();
            param1.name = param2.readUTFBytes(25);
            param1.logoFront = param2.readUnsignedInt();
            param1.logoBack = param2.readUnsignedInt();
            param1.logoColor = param2.readUnsignedInt();

    }

    public static void readMoreInfo(TeamMainInfo param1, ByteArray param2) {
        param1.dissolveDay = param2.readUnsignedInt();
        param1.userPost = param2.readUnsignedByte();
        param1.userTotalContribute = param2.readUnsignedInt();
        param2.readUnsignedInt();
    }
}
