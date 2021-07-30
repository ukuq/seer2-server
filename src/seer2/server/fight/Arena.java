//package seer2.server.fight;
//
//import seer2.server.config.PetSkill;
//import seer2.server.constants.PetAttribute;
//import seer2.server.data.PetData;
//import seer2.server.fight.buff.BuffHookType;
//
//import java.util.*;
//
//public class Arena {
//    public Fighter f1,f2;
//    public Fighter faster, slower, atker;
//    public int round;
//    public byte weather;
//    public byte fightMode;
//    public List<RoundRecord> recordList;
//    public int hookTypeNow;
//
//    public static final byte SIDE_LEFT=1;
//    public static final byte SIDE_RIGHT=2;
//
//    RoundRecord record;
//    PetSkill skill;
//
//    public void randomInit(){
//        fightMode = 7;
//
//        f1 = new Fighter(1,Collections.singletonList(new PetData(1,4)));
//        FightPet f = f1.pets.get(0);
//        f.basic[PetAttribute.HP]=100000;
//        f.petData.hp=100000;
//        f.basic[PetAttribute.ATK] =999;
//        f.petData.skillIds = new ArrayList<>();
//        f.petData.skillIds.add(10016);
//        f.petData.skillIds.add(10015);
//        f.petData.skillIds.add(10057);
//        f.petData.skillIds.add(10011);
//        f.basic[PetAttribute.SPEED]=100000;
//
//        f2 = new Fighter(2,Collections.singletonList(new PetData(1,1083)));
//        f = f2.pets.get(0);
//        f.basic[PetAttribute.HP]=2000;
//        f.petData.hp=2000;
//    }
//
//    public Arena(){}
//
//    public Arena(Fighter f1,Fighter f2,byte weather){
//        this.weather=weather;
//        this.round=1;
//        this.recordList= new ArrayList<>();
//        this.f1=f1;
//        this.f2=f2;
//        f1.loadArena(this,SIDE_LEFT);
//        f2.loadArena(this,SIDE_RIGHT);
//    }
//
//    public void preRound(){
//
//    }
//
//    public void initRound(){
//
//    }
//
//    protected void roundBefore(){
//        f1.roundBefore();
//        f2.roundBefore();
//    }
//
//    protected void roundStart(){
//        f1.checkSkill();
//        f2.checkSkill();
//        faster = f1;
//        slower = f2;
//        if(f1.getSpeed() < f2.getSpeed()){
//            faster = f2;
//            slower = f1;
//        }
//    }
//
//    protected RoundRecord atkCheck(Fighter f1, Fighter f2){
//        atker = f1;
//        record = new RoundRecord();
//        recordList.add(record);
//        record.atker = f1.fightPet.getPid();
//        record.defener = f2.fightPet.getPid();
//        record.atkerId = f1.uid;
//        record.defenerId = f2.uid;
//        record.round = round;
//
//        //精灵死亡否
//        if(f1.fightPet.isDying() || f2.fightPet.isDying()){
//            record.reason = RoundRecord.REASON_PET_DYING;
//            return record;
//        }
//
//        f1.execBuffs(BuffHookType.ATK_MOVE_BEFORE);
//
//        skill = f1.fightPet.skill;
//        record.skillId = skill.id;
//        if(skill.id==0){
//            record.reason=RoundRecord.REASON_SKILL_NOT_EXIST;
//            return record;
//        }
//
//        //怒气够否
//        if(!f1.isAngerEnough(skill.anger)){
//            record.reason=RoundRecord.REASON_ANGER_NOT_ENOUGH;
//            return record;
//        }
//
//        record.angerConsume = f1.angerConsume(skill.anger);
//
//        //命中否
//        if(!f1.isAtkHit(skill.accuracy)){
//            record.reason=RoundRecord.REASON_SKILL_NOT_HIT;
//            return record;
//        }
//
//        if(skill.category!=4 && f2.isAtkMiss()){
//            record.reason=RoundRecord.REASON_SKILL_MISS;
//            return record;
//        }
//
//        if(skill.category!=4){
//            f1.angerReward(15);
//        }
//        return record;
//    }
//
//    protected RoundRecord atkExec(Fighter f1, Fighter f2){
//
//        if(record.reason !=0)return record;
//
//        f1.execBuffs(BuffHookType.ATK_EXEC_SKILL_HIT);
//
//        if(skill.category ==4)return record;
//        float atk = f1.getAtk(skill.category);
//        float skillPower = f1.getSkillPower(skill.power);
//        float def = f2.getDef(skill.category);
//        float levelBenefit = f1.getLevelBenefit();
//        float skillBenefit = f1.getSkillBenefit();
//        float skillAdvantage = f1.getSkillAdvantage(f2.fightPet.monster.type);
//        record.random = f1.getRandomNum();
//        record.superHit = f1.getSuperHitNum();
//        record.atkTimes = f1.getAtkTimesNum();
//        //计算伤害 伤害检查固伤
//        //伤害公式 (((level * 0.4f + 2)/50)  * power * 攻防系数 + 2)  * skillBenefit*skillAdvantage*rand
//        int result = (int)f1.getDamage((atk*skillPower/def+2)*levelBenefit*skillBenefit*skillAdvantage*record.random);
//
//        result = result*record.superHit*record.atkTimes;
//
//        record.damage = result;
//        //执行伤害
//        //onDamageExec();
//        int real = f2.hpDamage(result);
//        //afterDamageExec();
//
//        f2.angerGain(real*100/f2.fightPet.getMaxHp());
//
//        //onAtkEnd();
//        //攻击结束
//        return record;
//    }
//
//    protected void roundEnd(){
//        f1.roundEnd();
//        f2.roundEnd();
//        round++;
//    }
//
//    public void replacePet(Fighter f1, int petCtime){
//        FightPet pet = f1.fightPet;
//        if(f1.getOneAlivePet(petCtime)!=null){
//            f1.fightPet.setAnger((int)(pet.getAnger()*0.8f));
//        }
//    }
//
//    public void escape(Fighter f1, Fighter f2, int petCtime){
//        f1.getOneAlivePet(petCtime);
//    }
//
//    public Fighter getEnemy(Fighter f){
//        return f==f1?f2:f1;
//    }
//
//    public BuffHook getBuffHook(){
//        return BuffHook.NONE;
//    }
//
//}
