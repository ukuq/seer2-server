package seer2.server.fight;

import seer2.hu.ByteArray;
import seer2.hu.Message;
import seer2.server.S2Handler;
import seer2.server.S2User;
import seer2.server.config.Monster;
import seer2.server.config.PetSkill;
import seer2.server.data.PetData;
import seer2.server.fight.buff.BuffMarkInfo;
import seer2.server.utils.Util;

import java.util.*;
import java.util.stream.Collectors;

public class S2Arena extends Arena2 implements S2Handler {
    public static final byte SIDE_LEFT = 1;
    public static final byte SIDE_RIGHT = 2;

    int notifyIndex = 0;

    public S2Arena(Fighter f1, Fighter f2) {
        super(f1, f2);
        f1.side = SIDE_LEFT;
        f2.side = SIDE_RIGHT;
    }

    private S2User s2User;

    public static S2Arena fightWild(S2User user, int index) {
        if (!Monster.valid(index)) index = 1;
        var f1 = new Fighter(user.id, user.fightPetInfos);
        var f2 = new Fighter(0, Collections.singletonList(new PetData(0, index)));
        S2Arena instance = new S2Arena(f1, f2);
        for (FightPet f : f2.pets) {
            f.petData.skillIds = f.petData.candidateSkillIds;
        }
        instance.fightMode = 7;
        return instance;
    }

    @Override
    public void fightBefore() {
        s2User.write(15, ByteArray.writeToBytes(64, 1, left.uid, left.fightPet.getPid(), left.getAnger(), 1, right.uid, right.fightPet.getPid(), right.getAnger()));
    }

    @Override
    protected void fightAfter() {
        writeBuffResult(left);
        writeBuffResult(right);

        if (left.isDying() || right.isDying()) {
            if (left.hasAlivePet() || right.hasAlivePet()) {
                endFight(s2User, left.isDying() ? (byte) 2 : 1, left.pets);
                return;
            }
        }
        s2User.write(7, ByteArray.writeToBytes(2, (short) round));
    }

    @Override
    protected void attackBefore() {
        s2User.write(16, ByteArray.writeToBytes(24, left.uid, left.fightPet.getPid(), left.getAnger(), right.uid, right.fightPet.getPid(), right.getAnger()));
    }

    @Override
    protected void attackAfter() {
        ByteArray ba = ByteArray.getInstance(1024);
        ba.writeVar(2);
        write3(1, ba, currentAtkEr);
        write3(0, ba, getEnemy(currentAtkEr));
        ba.writeVar(notifyIndex++, currentAtkEr.buffEffect.superHit > 1 ? 1 : 0, 100, currentAtkEr.buffEffect.atkTimes, currentAtkEr.buffEffect.damage);
        s2User.write(3, ba.getUsedBytes());
    }

    @Override
    public void parse(Message m, S2User s2User) {
        if (m.cmdId == 1) {
            //randomInit();
            ByteArray ba = new ByteArray(new byte[1024]);
            ba.writeVar(fightMode);
            write1(ba, left);
            write1(ba, right);
            ba.writeVar(weather, (byte) 0);
            this.s2User = s2User;
            s2User.write(1, ba.getUsedBytes());
            s2User.handlerMap.put(1501, this);
            s2User.handlerMap.put(1502, this);
            s2User.handlerMap.put(1509, this);//逃跑
            s2User.handlerMap.put(1032, this);
        } else if (m.cmdId == 1501) {
            s2User.write(m, null);
            s2User.write(2, null);
        } else if (m.cmdId == 1502 || m.cmdId == 1032) {
            var skillId = m.data.readUnsignedInt();
            if (m.cmdId == 1502) {
                //写回技能选择结果
                s2User.write(m, ByteArray.writeToBytes(8, left.fightPet.getPid(), skillId));
            } else if (m.cmdId == 1032) {
                replace(left, skillId);
                skillId = 0;
                s2User.write(m, new byte[1]);
                s2User.write(8, ByteArray.writeToBytes(16, m.uid, left.fightPet.getPid(), left.getAnger()));
            }

            if (right.isDying()) {
                replace(right, -1);
            }

            System.out.println("skillId:" + skillId);
            var l = right.fightPet.getSkillIds().stream().filter(i -> PetSkill.get(i).anger <= right.getAnger()).collect(Collectors.toList());
            var skill2 = l.size() == 0 ? 0 : Util.random(l);
            fight(skillId, skill2);
        } else if (m.cmdId == 1509) {
            s2User.write(m, ByteArray.writeToBytes(4, m.uid));
            s2User.write(12, ByteArray.writeToBytes(4, m.uid));
            endFight(s2User, (byte) 2, null);
        }
    }

    private void write1(ByteArray ba, Fighter f1) {
        ba.writeVar(f1.side, f1.uid, 1, f1.uid);
        ba.writeUTFBytes("用户", 16);
        ba.writeUnsignedInt(f1.pets.size());
        for (FightPet p : f1.pets) {
            ba.writeVar(p.getPid(), f1.getAnger(), p.monster.id, p.position, p.getLevel(), p.getHp(), p.getMaxHp());
            ba.writeVar(p.getSkillIds());
            ba.writeVar(0);
        }
        ba.writeVar(0);
    }

    private void write3(int isAtker, ByteArray ba, Fighter faster) {
        ba.writeByte((byte) isAtker);
        ba.writeVar(faster.uid, faster.fightPet.getPid());
        ba.writeUnsignedInt(isAtker == 1 ? faster.getSkill().id : 0);
        ba.writeVar(faster.fightPet.position, faster.fightPet.getHp(), faster.fightPet.getMaxHp(), (short) faster.getAnger(), (byte) 0, (byte) faster.fightPet.basicLevel[0], (byte) faster.fightPet.basicLevel[1], (byte) faster.fightPet.basicLevel[2], (byte) faster.fightPet.basicLevel[3], (byte) faster.fightPet.basicLevel[4]);

        Collection<BuffMarkInfo> c = List.of();
        ba.writeVar(c.size());
        for (BuffMarkInfo b : c) {
            ba.writeVar(b.id, b.round - round, b.value, 0, b.count);
        }
    }

    private void writeBuffResult(Fighter f1) {
        return;
//        if (f1.buffResult.size() > 0) {
//            ByteArray ba = ByteArray.getInstance(128);
//            ba.writeVar(f1.uid, f1.fightPet.getPid(), (byte) 0, f1.buffResult.size());
//            for (Pair<Integer, Integer> pair : f1.buffResult) {
//                ba.writeVar(pair.getLeft(), pair.getRight());
//            }
//            s2User.write(9, ba.bytes);
//        }
    }

    private void endFight(S2User s2User, byte winner, List<FightPet> pets) {
        if (pets == null) pets = new ArrayList<>();
        s2User.write(5, ByteArray.writeToBytes(8, 0, 200));
        ByteArray ba = ByteArray.getInstance(1024);
        ba.writeVar((byte) 0, winner);
        ba.writeVar(pets.size());
        for (FightPet p : pets) {
            p.petData.writeBaseInfo(ba);
            ba.writeVar(p.petData.skillIds, 0, (short) 0, 0, 0, 0);
        }
        ba.writeVar(0, 0, 0);
        s2User.write(1507, ba.getUsedBytes());
    }

    public static void main(String[] args) {
//        var a = fightWild(1, 4);
//        while (true) {
//            a.roundStart();
//        }
    }

    Scanner in = new Scanner(System.in);

    public void roundStart() {
        System.out.format("---%s:%d---                                   ---%s:%d---\n", left.fightPet.monster.name, left.fightPet.getLevel(), right.fightPet.monster.name, right.fightPet.getLevel());
        System.out.format("%3d/%3d                                                   %3d/%3d\n", left.fightPet.getHp(), left.fightPet.getMaxHp(), right.fightPet.getHp(), right.fightPet.getMaxHp());
        System.out.format("%3d/100                                                   %3d/100\n", left.getAnger(), right.getAnger());
        System.out.print(left.fightPet.getPid() + ":[");
        for (FightPet pet : left.pets) {
            System.out.print(" " + pet.getPid());
        }
        System.out.print(" ]");
        System.out.print("                                             ");
        System.out.print(left.fightPet.getPid() + ":[");
        for (FightPet pet : left.pets) {
            System.out.print(" " + pet.getPid());
        }
        System.out.print(" ]");
        System.out.println();
        System.out.format("---------------------------skills:%d--------------------------------------\n", left.fightPet.getPid());
        for (int i : left.fightPet.getSkillIds()) {
            var s = PetSkill.get(i);
            System.out.format("%d %8s %3d %3d\n", s.id, s.name, s.power, s.anger);
        }

        System.out.print("选择一个:");
        int s = in.nextInt();
        fight(s, right.fightPet.getSkillIds().get((int) (Math.random() * right.fightPet.getSkillIds().size())));
        System.out.println("-------------------------------------------------------------------------\n\n");
    }
}
