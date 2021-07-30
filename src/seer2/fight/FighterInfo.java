package seer2.fight;

import seer2.hu.ByteArray;
import seer2.hu.Message;
import seer2.pet.PetDefinition;
import seer2.pet.SkillInfo;

import java.util.ArrayList;
import java.util.List;

public class FighterInfo {
    public int isChangeStatus = 0;
    public boolean isChangePet;
    public int isChange;
    public int userId;
    public int catchTime;
    public int resourceId;
    public int position;
    public int level;
    public int hp;
    public int maxHp;
    public int fightAnger = 100;
    public int maxAnger = 100;
    public List<Integer> skillInfoVec;
    public int evolveLevel;
    public boolean isFightAngle;
    public PetDefinition petDefinition;
    public List<FighterBuffInfo> fightBuffInfoVec;
    public FighterInfo(int param1, ByteArray param2) {
        this.fightBuffInfoVec = new ArrayList<>();
        this.userId = param1;
        this.catchTime = param2.readUnsignedInt();
        this.fightAnger = param2.readUnsignedInt();
        this.resourceId = param2.readUnsignedInt();
        this.position = param2.readUnsignedByte();
        this.level = param2.readUnsignedShort();
        this.hp = param2.readUnsignedInt();
        this.maxHp = param2.readUnsignedInt();
        this.skillInfoVec = new ArrayList<>();
        int c1 = param2.readUnsignedInt();
        for (int i = 0; i < c1; i++) {
            this.skillInfoVec.add(param2.readUnsignedInt());
        }
        this.evolveLevel = param2.readUnsignedInt();
        Message.logger.info("catchTime:"+catchTime+", resourceId:"+resourceId+", position:"+position);
        Message.logger.info("Lv:"+level+" anger:"+fightAnger+" "+hp+"/"+maxHp+"      "+skillInfoVec);
    }
}
