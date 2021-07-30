//package seer2.server;
//
//import com.hukaidi.util.NumUtil;
//import org.apache.commons.lang3.tuple.Pair;
//import seer2.hu.ByteArray;
//import seer2.hu.MEncryptTool;
//import seer2.hu.Message;
//import seer2.server.data.PetData;
//import seer2.server.fight.FightPet;
//import seer2.server.fight.Fighter;
//import seer2.server.fight.RoundRecord;
//import seer2.server.fight.buff.BuffMarkInfo;
//import seer2.server.fight.Arena;
//import seer2.server.utils.ArrayUtils;
//
//import java.util.*;
//
//public class S2Arena extends Arena implements S2Handler {
//
//    private int[] checkReasons = {RoundRecord.REASON_PET_DYING, RoundRecord.REASON_SKILL_NOT_EXIST, RoundRecord.REASON_ANGER_NOT_ENOUGH};
//    private int[] missReasons = {RoundRecord.REASON_SKILL_NOT_HIT, RoundRecord.REASON_SKILL_MISS};
//
//    private S2User s2User;
//
//    private int notifyIndex;
//
//    public S2Arena(Fighter f1, Fighter f2, byte weather) {
//        super(f1, f2, weather);
//    }
//
//    public static S2Arena fightWild(S2User user, int index) {
//        var f1 = new Fighter(user.id, user.fightPetInfos);
//        var f2 = new Fighter(0, Collections.singletonList(new PetData(1, index)));
//        S2Arena instance = new S2Arena(f1, f2, (byte) 0);
//        for (FightPet f : instance.f2.pets) {
//            f.petData.skillIds = f.petData.candidateSkillIds;
//        }
//        instance.fightMode = 7;
//        return instance;
//    }
//
//    @Override
//    public void roundStart() {
//        super.roundStart();
//        s2User.write(15, ByteArray.writeToBytes(64, 1, f1.uid, f1.fightPet.getPid(), f1.fightPet.getAnger(), 1, f2.uid, f2.fightPet.getPid(), f2.fightPet.getAnger()));
//    }
//
//    @Override
//    public RoundRecord atkCheck(Fighter f1, Fighter f2) {
//        RoundRecord r = super.atkCheck(f1, f2);
//        if (ArrayUtils.contains(checkReasons, r.reason)) return r;
//        s2User.write(16, ByteArray.writeToBytes(24, f1.uid, f1.fightPet.getPid(), f1.fightPet.getAnger(), f2.uid, f2.fightPet.getPid(), f2.fightPet.getAnger()));
//        return r;
//    }
//
//    @Override
//    public RoundRecord atkExec(Fighter f1, Fighter f2) {
//        RoundRecord r = super.atkExec(f1, f2);
//        if (ArrayUtils.contains(checkReasons, r.reason)) return r;
//        ByteArray ba = ByteArray.getInstance(1024);
//        ba.writeVar(2);
//        write3(1, ba, f1);
//        write3(0, ba, f2);
//        ba.writeVar(notifyIndex++, r.superHit > 1 ? 1 : 0, 100, r.atkTimes, r.damage);
//        s2User.write(3, ba.getUsedBytes());
//        System.out.println(r);
//        return r;
//    }
//
//    private void write3(int isAtker, ByteArray ba, Fighter faster) {
//        ba.writeByte((byte) isAtker);
//        ba.writeVar(faster.uid, faster.fightPet.getPid());
//        ba.writeUnsignedInt(isAtker == 1 ? faster.fightPet.skill.id : 0);
//        ba.writeVar(faster.fightPet.position, faster.fightPet.petData.hp, faster.fightPet.getMaxHp(), (short) faster.fightPet.getAnger(), (byte) 0, (byte) faster.fightPet.basicLevel[0], (byte) faster.fightPet.basicLevel[1], (byte) faster.fightPet.basicLevel[2], (byte) faster.fightPet.basicLevel[3], (byte) faster.fightPet.basicLevel[4]);
//
//        Collection<BuffMarkInfo> c = faster.fightPet.buffMarks.values();
//        ba.writeVar(c.size());
//        for (BuffMarkInfo b : c) {
//            ba.writeVar(b.id, b.round - round, b.value, 0, b.count);
//        }
//
//    }
//
////cmdId:[9-通知每回合状态影响 （无请求包，返回包为通知包）], length:39, uid:263942591, sequenceIndex:0,
////statusCode:0
////proxy-s:311:[00, 00, 00, 00, 01, 00, 00, 00, 00, 01, 00, 00, 00, e8, 03, 00, 00, 6a, ff, ff, ff]
//
//    public static void main(String[] args) {
//        byte[] bytes = Message.trans("00, e8, 03, 00, 00, 6a, ff");
//        System.out.println(NumUtil.bytesToHex(MEncryptTool.MEncrypt(bytes)));
//        System.out.println(NumUtil.bytesToHex(MEncryptTool.encrypt1(bytes)));
//
//        System.out.println(NumUtil.bytesToHex(MEncryptTool.MDecrypt(MEncryptTool.MEncrypt(bytes))));
//        System.out.println(NumUtil.bytesToHex(MEncryptTool.decrypt1(MEncryptTool.encrypt1(bytes))));
//        System.out.println();
//
//        ByteArray b = new ByteArray(Message.trans("00, 00, 00, 00, 01, 00, 00, 00, 00, 01, 00, 00, 00, e8, 03, 00, 00, 6a, ff, ff, ff"));
//        System.out.println(b.readUnsignedInt());
//        System.out.println(b.readUnsignedInt());
//        System.out.println(b.readUnsignedByte());
//        System.out.println(b.readUnsignedInt());
//        System.out.println(b.readUnsignedInt());
//        System.out.println(b.readUnsignedInt());
//        System.out.println(b.bytesAvailable());
//        ByteArray ba = ByteArray.getInstance(128);
//        ba.writeVar(10000, 1000000, (byte) 0, 1);
//        int i = -10000;
//        ba.writeVar(1000, i);
//        System.out.println();
//        b = new ByteArray(ba.getUsedBytes());
//        System.out.println(b.readUnsignedInt());
//        System.out.println(b.readUnsignedInt());
//        System.out.println(b.readUnsignedByte());
//        System.out.println(b.readUnsignedInt());
//        System.out.println(b.readUnsignedInt());
//        System.out.println(b.readUnsignedInt());
//        System.out.println(b.bytesAvailable());
//
//        System.out.println();
//        for (byte by : ByteArray.writeToBytes(4, -150)) {
//            System.out.println(Integer.toHexString(by & 0xff));
//        }
//    }
//
//    @Override
//    public void roundEnd() {
//        super.roundEnd();
//
//        writeBuffResult(f1);
//        writeBuffResult(f2);
//
//        if (f1.fightPet.isDying() || f2.fightPet.isDying()) {
//            if (f1.hasAlivePet() && f2.hasAlivePet()) {
//                if (f2.fightPet.isDying()) {
//                    replacePet(f2, -1);
//                }
//            } else {
//                endFight(s2User, f1.fightPet.isDying() ? (byte) 2 : 1, f1.pets);
//                return;
//            }
//        }
//        s2User.write(7, ByteArray.writeToBytes(2, (short) round));
//    }
//
//    private void writeBuffResult(Fighter f1) {
//        if (f1.buffResult.size() > 0) {
//            ByteArray ba = ByteArray.getInstance(128);
//            ba.writeVar(f1.uid, f1.fightPet.getPid(), (byte) 0, f1.buffResult.size());
//            for (Pair<Integer, Integer> pair : f1.buffResult) {
//                ba.writeVar(pair.getLeft(), pair.getRight());
//            }
//            s2User.write(9, ba.bytes);
//        }
//    }
//
//    @Override
//    public void parse(Message m, S2User s2User) {
//        if (m.cmdId == 1) {
//            //randomInit();
//            initRound();
//            ByteArray ba = new ByteArray(new byte[1024]);
//            ba.writeVar(fightMode);
//            write1(ba, f1);
//            write1(ba, f2);
//            ba.writeVar(weather, (byte) 0);
//            this.s2User = s2User;
//            s2User.write(1, ba.getUsedBytes());
//            s2User.handlerMap.put(1501, this);
//            s2User.handlerMap.put(1502, this);
//            s2User.handlerMap.put(1509, this);//逃跑
//            s2User.handlerMap.put(1032, this);
//        } else if (m.cmdId == 1501) {
//            s2User.write(m, null);
//            s2User.write(2, null);
//        } else if (m.cmdId == 1502 || m.cmdId == 1032) {
//            roundBefore();
//            var skillId = m.data.readUnsignedInt();
//            if (m.cmdId == 1502) {
//                //写回技能选择结果
//                s2User.write(m, ByteArray.writeToBytes(8, f1.fightPet.getPid(), skillId));
//            } else if (m.cmdId == 1032) {
//                replacePet(f1, skillId);
//                skillId = 0;
//                s2User.write(m, new byte[1]);
//                s2User.write(8, ByteArray.writeToBytes(16, m.uid, f1.fightPet.getPid(), f1.fightPet.getAnger()));
//            }
//
//            System.out.println("skillId:" + skillId);
//            f1.fightPet.setSkillById(skillId);
//            var l = f2.fightPet.petData.skillIds;
//            f2.fightPet.setSkillById(l.get((int) (Math.random() * l.size())));
//
//            //更新精灵信息
//            roundStart();
//
//            //更新双方怒气
//            atkCheck(faster, slower);
//            //计算伤害结果
//            atkExec(faster, slower);
//
//            atkCheck(slower, faster);
//            atkExec(slower, faster);
//
//            roundEnd();
//        } else if (m.cmdId == 1509) {
//            s2User.write(m, ByteArray.writeToBytes(4, m.uid));
//            s2User.write(12, ByteArray.writeToBytes(4, m.uid));
//            endFight(s2User, (byte) 2, null);
//        }
//    }
//
//    private void endFight(S2User s2User, byte winner, List<FightPet> pets) {
//        if (pets == null) pets = new ArrayList<>();
//        s2User.write(5, ByteArray.writeToBytes(8, 0, 200));
//        ByteArray ba = ByteArray.getInstance(1024);
//        ba.writeVar((byte) 0, winner);
//        ba.writeVar(pets.size());
//        for (FightPet p : pets) {
//            p.petData.writeBaseInfo(ba);
//            ba.writeVar(p.petData.skillIds, 0, (short) 0, 0, 0, 0);
//        }
//        ba.writeVar(0, 0, 0);
//        s2User.write(1507, ba.getUsedBytes());
//    }
//
//    private void write1(ByteArray ba, Fighter f1) {
//        ba.writeVar(f1.side, f1.uid, 1, f1.uid);
//        ba.writeUTFBytes("用户", 16);
//        ba.writeUnsignedInt(f1.pets.size());
//        for (FightPet p : f1.pets) {
//            ba.writeVar(p.getPid(), p.getAnger(), p.petData.monsterId, p.position, p.petData.level, p.petData.hp, p.getMaxHp());
//            ba.writeVar(p.petData.skillIds);
//            ba.writeVar(0);
//        }
//        ba.writeVar(0);
//    }
//
//}