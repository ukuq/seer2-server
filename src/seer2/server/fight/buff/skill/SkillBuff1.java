//package seer2.server.fight.buff.skill;
//
//import seer2.server.fight.Fighter;
//import seer2.server.fight.buff.*;
//import seer2.server.fight.Arena;
//
//
//public class SkillBuff1 extends MultiBuff {
//
//    private static final int[] MARKS = {3000};
//    private static final int[] BUFF_IDS = {10004, 10005, 10008,10011,10016};
//    private static final int[] HOOK_TYPES = {BuffHookType.ATK_EXEC_SKILL_HIT, BuffHookType.ATK_EXEC_SUPER_HIT, BuffHookType.ATK_EXEC_ATK_TIMES,BuffHookType.ATK_EXEC_SKILL_POWER,BuffHookType.ATK_EXEC_SKILL_HIT};
//
//    public SkillBuff1(int buffId) {
//        super(buffId);
//    }
//
//    @Override
//    public void addBuff(Arena arena, Fighter fighter) {
//        addBuff0(fighter,BUFF_IDS,HOOK_TYPES);
//    }
//
//    @Override
//    public void execBuff(Arena arena, Fighter fighter) {
//
//        Fighter enemy = arena.getEnemy(fighter);
//        BuffMarkInfo mark = enemy.fightPet.getBuffMark(MARKS[0]);
//
//        if (buffId == BUFF_IDS[0]) {
//            if (mark.count < 2) enemy.setBuffMark(MARKS[0], mark.count + 1);
//        } else if (buffId == BUFF_IDS[1]) {
//            //每一个增加致命率45%
//            if (mark.count <= 0) return;
//            enemy.fightPet.removeBuffMark(MARKS[0]);
//            fighter.loc_superHitRate += mark.count * 0.45f;
//        } else if (buffId == BUFF_IDS[2]) {
//            if (mark.count <= 0) return;
//            enemy.fightPet.removeBuffMark(MARKS[0]);
//            fighter.loc_atkTimes += mark.count;
//        } else if(buffId == BUFF_IDS[3]){
//            if (mark.count <= 0) return;
//            enemy.fightPet.removeBuffMark(MARKS[0]);
//            fighter.loc_skillPower += mark.count*50;
//        }else if(buffId == BUFF_IDS[4]){
//            if (mark.count < 2) enemy.setBuffMark(MARKS[0], 2);
//        }
//    }
//}
