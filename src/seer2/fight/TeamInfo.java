package seer2.fight;

import seer2.hu.ByteArray;
import seer2.hu.Message;

import java.util.ArrayList;
import java.util.List;

public class TeamInfo {
    public int leaderId;

    public int serverSide;

    public List<FightUserInfo> fightUserInfoVec;
    public TeamInfo(ByteArray param1,int fightMode) {
        this.serverSide = param1.readUnsignedByte();
        this.leaderId = param1.readUnsignedInt();
        Message.logger.info("serverSide:"+serverSide+ ", leaderId:"+leaderId);
        int c1 = param1.readUnsignedInt();
        this.fightUserInfoVec = new ArrayList<>();
        for (int i = 0; i < c1; i++) {
            this.fightUserInfoVec.add(new FightUserInfo(param1));
        }
    }
}
