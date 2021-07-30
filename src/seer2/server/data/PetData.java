package seer2.server.data;

import seer2.hu.ByteArray;
import seer2.server.config.Monster;
import seer2.server.config.PetCharacter;
import seer2.server.constants.PetAttribute;

import java.util.*;

public class PetData{
    public int uid,catchTime,hp,monsterId,characterId,state;

    public final int[] potential;
    public final short[] points;
    public byte sex;
    public short level;

    public List<Integer> skillIds,candidateSkillIds;

    //升级 未知 未知 纹章 二纹 骑乘 骑乘 神魔
    private int expToLevelUp, trainingCount, battleLevel,emblemId,decorationId,petRideChipId,chipPutOnTime,evolveLevel;
    private short height,weight;
    private Map<Integer,Integer> starSoulMap;
    private final int[] basic;

    {
        level=100;
        evolveLevel=1008;
    }
    public PetData(){
        potential = new int[7];
        points = new short[7];
        basic = new int[6];
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getCatchTime() {
        return catchTime;
    }

    public void setCatchTime(int catchTime) {
        this.catchTime = catchTime;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMonsterId() {
        return monsterId;
    }

    public void setMonsterId(int monsterId) {
        this.monsterId = monsterId;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int[] getPotential() {
        return potential;
    }

    public short[] getPoints() {
        return points;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short level) {
        this.level = level;
    }

    public List<Integer> getSkillIds() {
        return skillIds;
    }

    public void setSkillIds(List<Integer> skillIds) {
        this.skillIds = skillIds;
    }

    public List<Integer> getCandidateSkillIds() {
        return candidateSkillIds;
    }

    public void setCandidateSkillIds(List<Integer> candidateSkillIds) {
        this.candidateSkillIds = candidateSkillIds;
    }

    public PetData(int monsterId) {
        this((int)(System.currentTimeMillis()/1000)+(int)(Math.random()*100000),monsterId);
    }

    public PetData(int catchTime,int monsterId){
        this.catchTime = catchTime;
        this.monsterId = monsterId;
        Random r = new Random();
        potential = new int[]{r.nextInt(121),r.nextInt(121),r.nextInt(121),r.nextInt(121),r.nextInt(121),r.nextInt(121),r.nextInt(32)};
        points = new short[]{0,0,0,0,0,0,0};
        starSoulMap = new HashMap<>();
        characterId = PetCharacter.getRandomId();
        basic = new int[6];
        calculateBasic();
        hp=basic[PetAttribute.HP];
        candidateSkillIds = new ArrayList<>();
        int i = monsterId;
        while (i > 0) {
            Monster m = Monster.get(i);
            candidateSkillIds.addAll(m.skillIds);
            i = m.evolvesFrom;
        }
        Collections.sort(candidateSkillIds);
        skillIds=new ArrayList<>(candidateSkillIds.subList(0,Math.min(4,candidateSkillIds.size())));
    }

    public void writePetInfo(ByteArray b) {
        writeBaseInfo(b);
        ArrayList<Integer> arrayList = new ArrayList<>(candidateSkillIds);
        arrayList.removeAll(skillIds);
        b.writeVar(state,points[6],points[5],points[0],points[2],points[1],points[3],points[4],skillIds,arrayList);
        b.writeVar(potential[6],battleLevel,potential[0],potential[1],potential[2],potential[3],potential[4],potential[5]);

        b.writeVar(starSoulMap);

        b.writeVar(height,weight,emblemId ,(short)trainingCount,decorationId,petRideChipId,chipPutOnTime,evolveLevel);
    }
    public void writeSimplePetInfo(ByteArray b){
        b.writeVar(catchTime,monsterId,level,state);
    }
    public void writeBaseInfo(ByteArray b){
        b.writeVar(catchTime,sex,level,hp,basic[5],(short)basic[0],(short)basic[2],(short)basic[1],(short)basic[3],(short)basic[4],expToLevelUp,(short)(characterId-1),monsterId);
    }

    public int[] calculateBasic(){
        //referring from src/com/taomee/seer2/app/pet/data/PetInfoManager.as
        int[] mb = Monster.get(monsterId).basic;
        float[] cb = PetCharacter.get(characterId).effect;
        for (int i = 0; i < 6; i++) {
            basic[i]=(int)(((mb[i]*2+potential[i])*(level /100.0) +level+10+points[i]/4.0)*cb[i]);
        }
        return basic.clone();
    }

    public int getMaxHp(){
        return basic[PetAttribute.HP];
    }

}