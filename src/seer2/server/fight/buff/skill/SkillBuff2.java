//package seer2.server.fight.buff.skill;
//
//import seer2.server.fight.Fighter;
//import seer2.server.fight.buff.BuffHookType;
//import seer2.server.fight.buff.MultiBuff;
//import seer2.server.fight.Arena;
//
//public class SkillBuff2 extends MultiBuff {
//
//
//    public SkillBuff2(int buffId) {
//        super(buffId);
//    }
//    private static final int[] BUFF_IDS = {1,1000};
//    private static final int[] HOOK_TYPES = {BuffHookType.ATK_MOVE_BEFORE,BuffHookType.ROUND_END};
//
//    @Override
//    public void addBuff(Arena arena, Fighter fighter) {
//        addBuff0(fighter,BUFF_IDS,HOOK_TYPES);
//    }
//
//    @Override
//    public void execBuff(Arena arena, Fighter fighter) {
//        if(buffId == 1){//异常状态,跳过本回合
//            if(arena.hookTypeNow == BuffHookType.ATK_MOVE_BEFORE){
//                fighter.fightPet.setSkillById(0);
//            }
//        }else if(buffId == 1000){//不良状态,扣血
//            if(arena.hookTypeNow == BuffHookType.ROUND_END){
//                fighter.addBuffResult(1000,0-(int)(fighter.fightPet.getMaxHp()/8.0));
//            }
//        }
//
//    }
//}
