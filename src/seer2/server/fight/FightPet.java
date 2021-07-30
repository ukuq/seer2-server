package seer2.server.fight;

import seer2.server.data.PetData;
import seer2.server.config.Monster;
import seer2.server.constants.PetAttribute;
import seer2.server.utils.Util;

import java.util.*;

public class FightPet {
    public PetData petData;
    public Monster monster;

    public byte position;

    public int[] basic;
    public final int[] basicLevel;

    private static final int MAX_BASIC_LEVEL = 12;

    public FightPet(PetData petData) {
        this.petData = petData;
        monster = Monster.get(petData.monsterId);
        basic = petData.calculateBasic();
        basicLevel = new int[]{6, 6, 6, 6, 6};
    }

    public int getPid() {
        return petData.catchTime;
    }


    public int getMaxHp() {
        return basic[PetAttribute.HP];
    }

    public int getHp() {
        return petData.hp;
    }

    public void setHp(int hp) {
        petData.hp = Util.between(hp, 0, getMaxHp());
    }

    public int getSpeed() {
        return basic[PetAttribute.SPEED];
    }

    public int getAtk() {
        return basic[PetAttribute.ATK];
    }

    public int getSpAtk() {
        return basic[PetAttribute.SPATK];
    }


    public int getDef() {
        return basic[PetAttribute.DEF];
    }

    public int getSpDef() {
        return basic[PetAttribute.SPDEF];
    }


    public void setBasicLevel(int type, int value) {
        this.basicLevel[type] = Util.between(value, 0, MAX_BASIC_LEVEL);
    }

    public List<Integer> getSkillIds() {
        return this.petData.skillIds;
    }


    public short getLevel() {
        return petData.level;
    }

    public int getType() {
        return monster.type;
    }
}
