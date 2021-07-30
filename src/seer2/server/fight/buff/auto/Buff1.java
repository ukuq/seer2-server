//package seer2.server.fight.buff.auto;
//
//import seer2.server.fight.Arena;
//import seer2.server.fight.BuffHook;
//import seer2.server.fight.Fighter;
//import seer2.server.fight.buff.BuffMarkInfo;
//import seer2.server.fight.buff.IBuff;
//
//public class Buff1 implements IBuff {
//    @Override
//    public int getPriority() {
//        return 0;
//    }
//
//    @Override
//    public void execBuff(Arena arena, Fighter fighter) {
//
//    }
//
//    @Override
//    public void exec(Arena arena, Fighter self, BuffHook hook, int bid) {
//        //尘埃
//        var p = self.fightPet;
//        if (p.getMaxHp() > p.getHp() * 2) {
//            p.buffEffect.skillPower *= 2;
//        }
//
//        //双凫乘雁
//        var m = p.buffMarks.get(1);
//        if (m == null) {
//            p.buffMarks.put(1, new BuffMarkInfo());
//        } else {
//            m.count += 2;
//        }
//
//        //碧海沧流
//        p.buffEffect.fast += 3;
//        var op = arena.getEnemy(self).fightPet;
//        //op.addBuff();
//    }
//}
