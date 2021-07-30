//package seer2.server.fight;
//import seer2.server.config.PetSkill;
//
//import java.util.*;
//
//public class BaseArena extends Arena{
//
//    public static void main(String[] args) {
//        BaseArena b = new BaseArena();
//        b.test();
//    }
//
//
//    public void test(){
//        randomInit();
//
//
//        initRound();
//        Scanner input = new Scanner(System.in);
//        while(true){
//            roundBefore();
//            System.out.format("%5s %3d/%3d %10s %2d %10s %3d/%3d %5s\n",f1.fightPet.monster.name,f1.fightPet.petData.hp,f1.fightPet.getMaxHp(),"",round,"",f2.fightPet.petData.hp,f2.fightPet.getMaxHp(),f2.fightPet.monster.name);
//            System.out.format("%5s %3d/%3d %24s %3d/%3d %5s\n","",f1.fightPet.getAnger(),100,"",f2.fightPet.getAnger(),100,"");
//            System.out.println("L:"+Arrays.toString(f1.fightPet.basicLevel)+"        "+"R:"+Arrays.toString(f2.fightPet.basicLevel));
//           f1.fightPet.buffMarks.values().forEach(buffMarkInfo -> {System.out.print(buffMarkInfo.id+" "+ buffMarkInfo.round+" "+ buffMarkInfo.count);});
//           System.out.println();
//           f2.fightPet.buffMarks.values().forEach(buffMarkInfo -> {System.out.print(buffMarkInfo.id+" "+ buffMarkInfo.round+" "+ buffMarkInfo.count);});
//           System.out.println();
//
//            System.out.println("0:战斗 1:换场 2:药剂 3:捕捉 4:逃跑");
//            switch (input.nextInt()){
//                case 0:
//                    System.out.println("tips:请选择一个技能");
//                    for (Integer i : f1.fightPet.petData.skillIds){
//                        System.out.print(" "+ PetSkill.get(i));
//                    }
//
//                    List<Integer> l;
//                    f1.fightPet.setSkillById(input.nextInt());
//                    l = f2.fightPet.petData.skillIds;
//                    f2.fightPet.setSkillById(l.get((int)(Math.random()*l.size())));
//
//
//                    roundStart();
//                    atkCheck(faster, slower);
//                    System.out.print("f1:"+f1.fightPet.getAnger()+", "+f1.fightPet.petData.hp+"  ");
//                    System.out.println("f2:"+f2.fightPet.getAnger()+", "+f2.fightPet.petData.hp);
//                    var r = atkExec(faster, slower);
//                    System.out.print("f1:"+f1.fightPet.getAnger()+", "+f1.fightPet.petData.hp+"  ");
//                    System.out.println("f2:"+f2.fightPet.getAnger()+", "+f2.fightPet.petData.hp);
//                    System.out.println(r);
//
//                    atkCheck(slower, faster);
//                    System.out.print("f1:"+f1.fightPet.getAnger()+", "+f1.fightPet.petData.hp+"  ");
//                    System.out.println("f2:"+f2.fightPet.getAnger()+", "+f2.fightPet.petData.hp);
//                    r = atkExec(slower, faster);
//                    System.out.print("f1:"+f1.fightPet.getAnger()+", "+f1.fightPet.petData.hp+"  ");
//                    System.out.println("f2:"+f2.fightPet.getAnger()+", "+f2.fightPet.petData.hp);
//                    System.out.println(r);
//
//
//
//                    System.out.println();
//
//                    roundEnd();
//                    System.out.print("f1:"+f1.fightPet.getAnger()+", "+f1.fightPet.petData.hp+"  ");
//                    System.out.println("f2:"+f2.fightPet.getAnger()+", "+f2.fightPet.petData.hp);
//
//                    System.out.println("end----");
//
//                    if(f1.fightPet.isDying()){
//                        System.out.println("精灵死亡, 请更换精灵:");
//                        printPet();
//                        replacePet(f1,input.nextInt());
//                    }
//                    if(f1.fightPet==null){
//                        System.out.println("f2 胜利");
//                        return;
//                    }
//                    if(f2.fightPet.isDying())replacePet(f2,1);
//                    if(f2.fightPet == null){
//                        System.out.println("f1 胜利");
//                        return;
//                    }
//                    break;
//                case 1:
//                    printPet();
//                    replacePet(f1,input.nextInt());
//                case 2:
//                case 3:
//                case 4:
//                    break;
//            }
//        }
//    }
//
//    private void printPet(){
//        System.out.println("tips:请选择一只精灵");
//        f1.pets.forEach((p->{System.out.println(p.getPid()+": LV:"+ p.petData.level+" "+p.monster.name+" "+p.petData.hp+"/"+p.getMaxHp());}));
//    }
//
//}
