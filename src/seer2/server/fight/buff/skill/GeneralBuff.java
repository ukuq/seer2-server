//package seer2.server.fight.buff.skill;
//
//import seer2.server.fight.Fighter;
//import seer2.server.fight.buff.BuffHookType;
//import seer2.server.fight.buff.MultiBuff;
//import seer2.server.fight.Arena;
//
//public class GeneralBuff extends MultiBuff {
//
//    private int[] arr;
//
//    public GeneralBuff(int[] arr) {
//        super(0);
//        this.arr = arr;
//    }
//
//    @Override
//    public void addBuff(Arena arena, Fighter fighter) {
//        int hookType =  BuffHookType.ATK_EXEC_SKILL_HIT;
//        if(arr[0]==17||arr[0]==18){
//            hookType = BuffHookType.SPEED_CAL;
//        }
//        fighter.fightPet.addBuff(hookType, this);
//    }
//
//    @Override
//    public void execBuff(Arena arena, Fighter fighter) {
//        for (int i = 0; i < arr.length; i+=3) {
//            Fighter f = ((arr[i]&0x3) == 1)?fighter:((arr[i]&0x3) == 2)?arena.getEnemy(fighter):null;
//            if(f==null)return;
//            //1 自己能力变化 2 对方
//            if(arr[i]<3)f.changeBasicLevel(arr[i+1],arr[i+2]);
//                //5 血量变化 6 对方
//            else if(arr[i]<7){//111
//                float r = ((float) arr[i+1])/arr[i+2];
//                if(r<1)r = r*f.fightPet.getMaxHp();
//                System.out.println("r:"+r);
//                f.changeHp((int)r);
//                //9 怒气变化 10
//            }else if(arr[i]<11){//1011
//                f.changeAnger(arr[i+1]);
//                //13 14 异常不良状态
//            }else if(arr[i]<15){//1111
//                f.setBuffMark(arr[i+1],0).round=arena.round+arr[i+2];
//            }
//            //17 18 先后制
//            else if(arr[i]<19){
//                float r = ((float) arr[i+1])/arr[i+2];
//                if(r<1)r = r*f.loc_speed;
//                fighter.loc_speed += r;
//            }
//        }
//    }
//}
