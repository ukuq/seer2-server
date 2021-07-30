package seer2.fight;

import seer2.hu.ByteArray;
import seer2.hu.Message;
import seer2.hu.Vector;

public class FightUserInfo {
    public int userId;
    public String nick;
    public Vector<FighterInfo> fighterInfoVec;
    public Vector<FighterInfo> changeFighterInfoVec;
    public FightUserInfo(ByteArray param1)
    {
        this.userId = param1.readUnsignedInt();
        this.nick = param1.readUTFBytes(16);
        Message.logger.info("userId:"+userId+", nick:"+nick);
        int c1 = param1.readUnsignedInt();
        this.fighterInfoVec = new Vector<>();
        Message.logger.info("fighterInfoVec:");
        for (int i = 0; i < c1; i++) {
            FighterInfo fi = new FighterInfo(this.userId,param1);
            fi.isChangePet=false;
            fi.isChange=0;
            this.fighterInfoVec.push(fi);
        }
        Message.logger.info("changeFighterInfoVec:");
        c1 = param1.readUnsignedInt();
        this.changeFighterInfoVec = new Vector<>();
        for (int i = 0; i < c1; i++) {
            FighterInfo fi = new FighterInfo(this.userId,param1);
            fi.isChangePet=true;
            fi.isChange=0;
            this.fighterInfoVec.push(fi);
        }
    }
}
