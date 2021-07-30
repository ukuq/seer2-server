package seer2.server.fight;

import seer2.server.config.PetType;

import java.util.*;

public class Arena2 {
    public Fighter left, right, currentAtkEr;
    public int round;
    public byte weather;
    public byte fightMode;
    public List<RoundRecord> recordList;
    public RoundRecord record;

    public Arena2(Fighter left, Fighter right) {
        this.round = 1;
        this.recordList = new ArrayList<>();
        this.left = left;
        this.right = right;
        left.setAnger(20);
        right.setAnger(20);
    }

    protected void fightBefore() {

    }

    protected void fightAfter() {

    }

    protected void attackBefore() {

    }

    protected void attackAfter() {

    }

    public void fight(int skillId1, int skillId2) {
        System.out.println("技能选择：" + skillId1 + ", " + skillId2);
        record = new RoundRecord();
        record.round = round;

        fightBefore();

        left.setSkillById(skillId1);//设置技能
        right.setSkillById(skillId2);

        initEffect(left, right);
        initEffect(right, left);


        var be1 = left.buffEffect;
        var be2 = right.buffEffect;
        record.effects = new BuffEffect[]{be1, be2};
        var first = be1.fast > be2.fast ? left : (be1.fast < be2.fast ? right : (be1.speed >= be2.speed ? left : right));
        var second = first == left ? right : left;
        record.faster = first.uid;
        System.out.println("first: " + first.uid);
        attack(first, second);
        attack(second, first);

        left.addAnger(be1.angerRound);
        right.addAnger(be2.angerRound);

        fightAfter();
        round++;
    }

    private static void initEffect(Fighter atkEr, Fighter defEr) {
        var skill = atkEr.getSkill();
        var be = atkEr.buffEffect = new BuffEffect();
        be.skillAnger = skill.anger;
        be.skillAccuracy = skill.accuracy;
        be.skillMiss = 0;
        be.skillPower = skill.power;

        be.skillLevelBenefit = atkEr.fightPet.getLevel();
        be.skillBenefit = PetType.benefit(skill.type, atkEr.fightPet.getType());
        be.skillAdvantage = PetType.advantage(atkEr.fightPet.getType(), defEr.fightPet.getType());


        be.damageFixed = 0;
        be.damageMin = 0;
        be.damageMax = Integer.MAX_VALUE;
        be.superHit = Math.random() < 0.2 ? 2 : 1;
        be.atkTimes = 1;

        be.angerReward = skill.category == 4 ? 0 : 15;
        be.angerConsume = be.skillAnger;
        be.angerRound = 15;

        be.angerConvert = 0;

        be.speed = atkEr.fightPet.getSpeed();
        be.atk = atkEr.getAtk(skill.category);
        be.def = atkEr.getDef(defEr.getSkill().category);
        be.fast = 0;
    }

    private void attack(Fighter atkEr, Fighter defEr) {
        if (atkEr.isDying() || defEr.isDying()) {
            return;
        }
        currentAtkEr = atkEr;
        attack0(atkEr, defEr);
        attackBefore();
        attack1(atkEr, defEr);
        attackAfter();
        System.out.println("攻击结果：" + atkEr.buffEffect.reason + " " + atkEr.buffEffect.damage + " " + atkEr.buffEffect.realDamage);
    }

    private static void attack0(Fighter atkEr, Fighter defEr) {
        //精灵死亡否
        if (atkEr.isDying() || defEr.isDying()) {
            atkEr.buffEffect.reason = RoundRecord.REASON_PET_DYING;
            return;
        }

        if (atkEr.getSkill().id == 0) {
            atkEr.buffEffect.reason = RoundRecord.REASON_SKILL_NOT_EXIST;
            return;
        }

        //怒气够否
        if (atkEr.buffEffect.skillAnger > atkEr.getAnger()) {
            atkEr.buffEffect.reason = RoundRecord.REASON_ANGER_NOT_ENOUGH;
            return;
        }

        atkEr.addAnger(-atkEr.buffEffect.angerConsume);

        //命中否
        if (atkEr.buffEffect.skillAccuracy < Math.random() * 100) {
            atkEr.buffEffect.reason = RoundRecord.REASON_SKILL_NOT_HIT;
            atkEr.buffEffect.atkTimes = 0;
            return;
        }

        if (atkEr.getSkill().category != 4) {
            if (defEr.buffEffect.skillMiss > Math.random() * 100) {
                atkEr.buffEffect.reason = RoundRecord.REASON_SKILL_MISS;
                atkEr.buffEffect.atkTimes = 0;
            }
        }
    }

    private static void attack1(Fighter atkEr, Fighter defEr) {
        if (atkEr.getSkill().category == 4 || atkEr.buffEffect.reason != 0) return;
        int atk = atkEr.buffEffect.atk;
        int skillPower = atkEr.buffEffect.skillPower;
        int def = defEr.buffEffect.def;
        float levelBenefit = (atkEr.buffEffect.skillLevelBenefit * 0.4f + 2) / 50;
        float skillBenefit = atkEr.buffEffect.skillBenefit / 100f;
        float skillAdvantage = atkEr.buffEffect.skillAdvantage / 100f;
        float random = (float) (Math.random() * (255 - 217) + 217) / 255;
        int superHit = atkEr.buffEffect.superHit;
        int atkTimes = atkEr.buffEffect.atkTimes;
        //计算伤害 伤害检查固伤
        //伤害公式 (((level * 0.4f + 2)/50)  * power * 攻防系数 + 2)  * skillBenefit*skillAdvantage*rand
        int result = (int) ((1.0 * atk * skillPower / def + 2) * levelBenefit * skillBenefit * skillAdvantage * random * superHit * atkTimes);

        result = atkEr.buffEffect.damage = Math.min(atkEr.buffEffect.damageMax, Math.max(result, atkEr.buffEffect.damageMin));

        //执行伤害
        int real = atkEr.buffEffect.realDamage = -defEr.addHp(-result);

        defEr.buffEffect.angerConvert = real * 100 / defEr.fightPet.getMaxHp();

        atkEr.addAnger(atkEr.buffEffect.angerReward);
        defEr.addAnger(defEr.buffEffect.angerConvert);
    }


    public static void replace(Fighter fighter, int pid) {
        fighter.setAnger((int) (fighter.getAnger() * 0.8f));
        fighter.fightPet.position = 0;
        fighter.fightPet = fighter.pets.stream().filter(p -> p.getHp() > 0 && p.getPid() == pid).findAny().orElse(findAnyAlivePet(fighter));
        if (fighter.fightPet != null) fighter.fightPet.position = 1;
    }

    public static FightPet findAnyAlivePet(Fighter fighter) {
        return fighter.pets.stream().filter(p -> p.getHp() > 0).findAny().orElse(null);
    }


    public void escape(Fighter f1, Fighter f2, int petCtime) {

    }

    public Fighter getEnemy(Fighter f) {
        return f == left ? right : left;
    }

    public BuffHook getBuffHook() {
        return BuffHook.NONE;
    }

}
