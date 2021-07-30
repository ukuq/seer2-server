//package seer2.server.fight.buff;
//
//import seer2.server.fight.Fighter;
//import seer2.server.fight.Arena;
//
//public class MultiBuff implements IBuff{
//
//    protected int buffId;
//
//    public MultiBuff(int buffId) {
//        this.buffId = buffId;
//    }
//
//    protected final void addBuff0(Fighter fighter,int[] buffIds,int[] hookTypes){
//        for (int i =0;i< buffIds.length;i++) {
//            if (buffIds[i] == buffId) {
//                fighter.fightPet.addBuff(hookTypes[i], this);
//            }
//        }
//    }
//
//    public void addBuff(Arena arena, Fighter fighter){
//
//    }
//
//    @Override
//    public void execBuff(Arena arena, Fighter fighter) {
//
//    }
//
//    @Override
//    public int getPriority() {
//        return 0;
//    }
//}
