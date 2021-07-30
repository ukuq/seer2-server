package seer2.pet;

import seer2.hu.ByteArray;
import seer2.hu.Vector;

public class PetInfo {
    public int catchTime;
    public int sex;
    public int level;
    public int hp;
    public int maxHp;
    public int atk;
    public int specialAtk;
    public int defence;
    public int specialDefence;
    public int speed;
    public int expToLevelUp;
    public int character;
    public int resourceId;
    public PetDefinition petDefinition;
    public int freeTime;
    public int treasureTime;
    public int physicalHeight;
    public int physicalWeight;
    public int potential = -1;
    public int emblemId = -1;
    public int decorationId = -1;
    public int battleLevel;
    private int flag = -1;
    public boolean isInBag;
    public boolean isInStorageBag;
    public boolean isStarting;
    public boolean isFollowing;
    public boolean isInFreeStorage;
    public boolean isTraining;
    public boolean isAggraisal;
    public boolean isDevilTraining;
    public boolean isSubFighter;
    public boolean isInitialPet;
    public boolean isSetBirth;
    public boolean isBirthIng;
    public boolean isTwoPet;
    public boolean isTestCheck;
    public PetLearningInfo learningInfo;
    public PetSkillInfo skillInfo = new PetSkillInfo();
    public int trainingCount;
    public int twoExp;
    public int threeExp;
    public int twoStudy;
    public PetFightStarInfo petFightStarInfo;
    public int potentialAtk;
    public int potentialDef;
    public int potentialSpAtk;
    public int potentialSpDef;
    public int potentialSpeed;
    public int potentialHp;
    public int totalPotential;
    public boolean hasStar;
    public int evolveLevel;
    public boolean isPetRiding;
    public int petRideChipId = -1;
    public int chipPutOnTime = 0;
    public Vector<PetItemInfo> itemList;

    public void readPetInfo(ByteArray param2){
        readBaseInfo(param2);
        setFlag(param2.readUnsignedInt());
        readLearning(param2);
        readSkillInfo(param2);
        readCandidateSkillInfo(param2);
        readPotential(param2);
        readStarInfo(param2);
        physicalHeight = param2.readUnsignedShort();
        physicalWeight = param2.readUnsignedShort();
        emblemId = param2.readUnsignedInt();
        trainingCount = param2.readUnsignedShort();
        decorationId = param2.readUnsignedInt();
        petRideChipId = param2.readUnsignedInt();
        chipPutOnTime = param2.readUnsignedInt();
        evolveLevel = param2.readUnsignedInt();
    }

    public void readBaseInfo(ByteArray param2) {
        catchTime = param2.readUnsignedInt();
        sex = param2.readUnsignedByte();
        level = param2.readUnsignedShort();
        hp = param2.readUnsignedInt();
        readSixAttr(param2);
        expToLevelUp = param2.readUnsignedInt();
        character = param2.readUnsignedShort();
        resourceId = param2.readUnsignedInt();
    }
    public void readSkillInfo(ByteArray param2){
        skillInfo.skillInfoVec=SkillInfo.parseSkills(param2);
    }
    public void readCandidateSkillInfo(ByteArray param2){
        skillInfo.candidateSkillInfoVec=SkillInfo.parseSkills(param2);;
    }

    public void readStarInfo(ByteArray param2){
        if(petFightStarInfo==null)petFightStarInfo = new PetFightStarInfo();
        petFightStarInfo.clear();
        int c1= param2.readUnsignedInt();
        int t1,t2;
        for (int i = 0; i < c1; i++) {
            t1 = param2.readUnsignedInt();
            t2 = param2.readUnsignedInt();
            petFightStarInfo.add(t2,t1);
        }
    }

    public void readSixAttr(ByteArray param1){
        maxHp = param1.readUnsignedInt();
        atk = param1.readUnsignedShort();
        specialAtk = param1.readUnsignedShort();
        defence = param1.readUnsignedShort();
        specialDefence = param1.readUnsignedShort();
        speed = param1.readUnsignedShort();
    }

    public void readPotential(ByteArray param2){
        potential = param2.readUnsignedInt();
        battleLevel = param2.readUnsignedInt();
        potentialAtk = param2.readUnsignedInt();
        potentialDef = param2.readUnsignedInt();
        potentialSpAtk = param2.readUnsignedInt();
        potentialSpDef = param2.readUnsignedInt();
        potentialSpeed = param2.readUnsignedInt();
        potentialHp = param2.readUnsignedInt();
    }

    public void readLearning(ByteArray param2){
        learningInfo= new PetLearningInfo();
        learningInfo.pointUnused = param2.readUnsignedShort();
        learningInfo.pointHp = param2.readUnsignedShort();
        learningInfo.pointAtk = param2.readUnsignedShort();
        learningInfo.pointSpecialAtk = param2.readUnsignedShort();
        learningInfo.pointDefence = param2.readUnsignedShort();
        learningInfo.pointSpecialDefence = param2.readUnsignedShort();
        learningInfo.pointSpeed = param2.readUnsignedShort();
    }

    public int getFlag(){
        return this.flag;
    }
    public void setFlag(int param1){
        this.flag = param1;
        this.isInBag = (param1 & 1) == 1;
        this.isStarting = (param1 & 2) == 2;
        this.isFollowing = (param1 & 4) == 4;
        this.isInFreeStorage = (param1 & 8) == 8;
        this.isTraining = (param1 & 16) == 16;
        this.isAggraisal = (param1 & 32) == 32;
        this.isDevilTraining = (param1 & 64) == 64;
        this.isSubFighter = (param1 & 128) == 128;
        this.isSetBirth = (param1 & 256) == 256;
        this.isBirthIng = (param1 & 1024) == 1024;
        this.isTwoPet = (param1 & 2048) == 2048;
        this.isPetRiding = (param1 & 8192) == 8192;
        this.isInStorageBag = (param1 & 16384) == 16384;
    }
}