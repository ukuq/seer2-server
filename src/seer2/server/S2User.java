package seer2.server;

import seer2.MainFrame;
import seer2.hu.ByteArray;
import seer2.hu.Message;
import seer2.server.config.Monster;
import seer2.server.config.PetSkill;
import seer2.server.data.PetData;
import seer2.server.data.TeamData;
import seer2.server.fight.S2Arena;
import seer2.user.UserInfo;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class S2User implements S2Handler {
    public int id;
    private Socket socket;
    public final HashMap<Integer, S2Handler> handlerMap;

    public S2User(Socket socket, int id) {
        this.socket = socket;
        this.id = id;
        handlerMap = new HashMap<>();
        handlerMap.put(1001, this);
    }

    public void error(Message m, int code) {
        write(m.cmdId, m.uid, m.sequenceIndex, code, null);
    }

    public void write(Message m, byte[] data) {
        write(m.cmdId, m.uid, m.sequenceIndex, 0, data);
    }

    public void write(int cmdId, byte[] data) {
        write(cmdId, id, 0, 0, data);
    }

    public void write(int cmdId, int uid, int sequenceIndex, int statusCode, byte[] data) {
        try {
            socket.getOutputStream().write(MainFrame.ProxyHandler.parseMsg(Message.genMsg(cmdId, uid, sequenceIndex, statusCode, data)));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    S2Arena arena;


    @Override
    public void parse(Message m, S2User s2User) {
        var reqData = m.data;
        switch (m.getCommand()) {
            case ONLINE_LOGIN_1001 -> {
                write(m, writeLoginInfo(ByteArray.getInstance(1024)).getUsedBytes());
                handlerMap.put(1030, this);
                handlerMap.put(1500, this);
                handlerMap.put(1016, this);
                handlerMap.put(1017, this);
                handlerMap.put(1018, this);
                handlerMap.put(1020, this);
                handlerMap.put(1215, this);
                handlerMap.put(1002, this);
                handlerMap.put(1003, this);
                handlerMap.put(1028, this);
                handlerMap.put(1038, this);
                handlerMap.put(1063, this);//无返回包
                handlerMap.put(1010, this);
                handlerMap.put(1142, this);
                handlerMap.put(1065, this);
            }
            //换技能
            case PET_REPLACE_SKILL_1030 -> {
                int catchTime = m.data.readUnsignedInt();
                int c = m.data.readUnsignedInt();
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = 0; i < c; i++) {
                    list.add(m.data.readUnsignedInt());
                }
                PetData pet = findPetInBag(catchTime);
                if (!pet.candidateSkillIds.containsAll(list)) {
                    error(m, 500);
                    return;
                }
                int s4 = 0, s1 = 0;
                for (Integer sid : list) {
                    if (PetSkill.get(sid).category >= 80) s1++;
                    else s4++;
                }
                if (s4 > 4 || s1 > 1) {
                    error(m, 501);
                    return;
                }
                pet.skillIds = list;
                write(m, ByteArray.writeToBytes(128, catchTime, list));
            }
            case FIGHT_START_WILD_1500 -> {
                //判定是否可对战
                write(m, null);
                write(4, null);
                arena = S2Arena.fightWild(this, m.data.readUnsignedInt());
                handlerMap.put(1, arena);
            }
            //恢复体力
            case TREAT_ALL_PET_1215 -> {
                int sum = 0;
                for (PetData p : fightPetInfos) {
                    sum += p.getMaxHp() - p.hp;
                    p.hp = p.getMaxHp();
                }
                write(m, ByteArray.writeToBytes(4, sum));
            }
            case PET_GET_STORAGE_LIST_1016 -> {
                int start = m.data.readUnsignedInt();
                int end = Math.min(m.data.readUnsignedInt(), Monster.keys.size());
                ByteArray ba = ByteArray.getInstance(1024 * 5 * 4);
                ba.writeUnsignedInt(end - start);
                for (Integer i : Monster.keys.subList(start, end)) {
                    ba.writeVar(i, i, (short) 100, 0, 0);
                }
                write(m, ba.getUsedBytes());
            }
            case PET_SIMPLE_INFO_1017 -> {
                ByteArray ba = ByteArray.getInstance(1024);
                int id = m.data.readUnsignedInt();
                ba.writeVar(id, (byte) 0, (short) 100, 600, 600, (short) 60, (short) 60, (short) 60, (short) 60, (short) 60, 0, (short) 0, id);
                ba.writeVar(0, (short) 50, (short) 50, 0, 0, 0, 0);
                write(m, ba.getUsedBytes());
            }
            case PET_SET_STARTING_1018 -> {
                //设置首发精灵
                int ctime = m.data.readUnsignedInt();
                PetData pet = findPetInBag(ctime);
                fightPetInfos.remove(pet);
                fightPetInfos.add(0, pet);
                write(m, ByteArray.writeToBytes(8, 0, ctime));
            }
            case PET_SET_STORAGE_STATUS_1020 -> {
                //放入背包或仓库
                int ctime = m.data.readUnsignedInt();
                ByteArray ba = ByteArray.getInstance(1024);
                ba.writeVar(0, ctime);
                PetData pet;
                if (m.data.readUnsignedByte() == 1) {//放入背包
                    ba.writeVar((byte) 1, 0);
                    pet = new PetData(ctime, ctime);
                    pet.state = 1;
                    fightPetInfos.add(pet);
                    pet.writePetInfo(ba);
                } else {
                    pet = findPetInBag(ctime);
                    fightPetInfos.remove(pet);
                    ba.writeVar((byte) 0);
                }
                write(m, ba.getUsedBytes());
            }
            case USER_ENTER_MAP_1002 -> {
                //进入地图
                mapId = m.data.readUnsignedInt();
                m.data.readUnsignedInt();
                x = m.data.readUnsignedInt();
                y = m.data.readUnsignedInt();
                m.data.readUnsignedInt();

                ByteArray ba = ByteArray.getInstance(1024);
                writeEnterMap(ba);
                ba.writeByte((byte) 0);
                writeNonoInfo(ba);
                ba.writeVar(0, birthdayInfo, isYearVip);
                write(m, ba.getUsedBytes());

                ba = ByteArray.getInstance(64);//随机刷怪
                ba.writeUnsignedInt(3);
                for (int i = 0; i < 3; i++) {
                    int r = Monster.getRandomId();
                    ba.writeVar(r, r, (short) 2, 0, (byte) 0);
                }
                write(1103, ba.getUsedBytes());
            }
            case USER_LEAVE_MAP_1003 -> {
                //离开地图
                write(m, ByteArray.writeToBytes(5, m.uid));
            }
            case USER_GET_SIMPLE_INFO_1028 -> {
                //获取简单用户信息
                write(m, writeUserSimpleInfo(ByteArray.getInstance(1024)).getUsedBytes());
            }
            case USER_GET_DETAIL_INFO_1010 -> {
                m.data.readInt();
                ByteArray ba = ByteArray.getInstance(1024);
                writeUserSimpleInfo(ba).writeVar(createTime, medalId, petCount, petLevel, sptCount, medalCount);
                ba.writeUTF(signature);
                teamData.writeBase(ba);
                ba.writeVar(0, 0);//morphInfo
                write(m, ba.getUsedBytes());
            }
            case HOME_GET_ENTRY_INFO_1038 -> {
                write(m, ByteArray.getInstance(1024).writeByte((byte) 0).writeInt(0).writeByte((byte) 0).writeInt(0).writeInt(0).writeInt(0).getUsedBytes());
            }
            case DAILY_LIMIT_1065 -> {
                var t = reqData.readUnsignedInt();
                write(m, ByteArray.writeToBytes(8, t, dayLimitMap.getOrDefault(t, 0)));
            }
            case ACTIVE_COUNT_1142 -> {
                var t = reqData.readUnsignedInt();
                var ba = ByteArray.getInstance(t * 4 + 4).writeInt(t);
                for (int i = 0; i < t; i++) {
                    ba.writeInt(activityMap.getOrDefault(reqData.readInt(), 0));
                }
                write(m, ba.getUsedBytes());
            }
        }
    }

    private PetData findPetInBag(int cTime) {
        for (PetData p : fightPetInfos) {
            if (p.catchTime == cTime) {
                return p;
            }
        }
        throw new RuntimeException();
    }


    private String nick;
    private int color, x, y, medalId, createTime, coins, mapId, sceneId, trainerScore, maxAvailableTime, availableTime, doubleExpTime,
            isShowLoginAwardPanel, stateFlag, callerUserId, honorNum, payFlag, vipPoint, leftDay, energyBall, currentEnergy, totalEnergy,
            nonoFlag, nonoColor, onLineDays, giftIndex, summerRegistrationDays, birthdayInfo, isYearVip;
    private List<Integer> equipIds, nonoEquipIds, completeQuestIds, lastCompleteQuestIds, inProgressQuestIds, equipeBallIds, expiredEquipIds;
    private byte sex, highestPetLevel, petLevel, vipFlag, vipLevel, onceVipFlag, luckyLeftDay, luckyMsgType, luckyFlag, nonoHas;

    public List<PetData> fightPetInfos, storagePetInfos, petInfos;

    private int plantLevel, petCount, sptCount, medalCount;
    private List<Integer> activityData;
    //    private List<NonoTimeData> nonoTimes;
    private String signature, nonoName, ip;

    private TeamData teamData;

    private final Map<Integer, Integer> dayLimitMap = new HashMap<>();
    private final Map<Integer, Integer> activityMap = new HashMap<>();
    private final Map<Integer, Integer> taskMap = new HashMap<>();

    {
        createTime = (int) (System.currentTimeMillis() / 1000);
        var a = new ArrayList<Integer>();
        activityData = equipIds = nonoEquipIds = completeQuestIds = lastCompleteQuestIds = inProgressQuestIds = equipeBallIds = expiredEquipIds = new ArrayList<>();
        completeQuestIds = new ArrayList<>();
        completeQuestIds.add(1);
        completeQuestIds.add(83);
        completeQuestIds.add(99);
//        nonoTimes = new ArrayList<>();
        nonoName = "1";
        teamData = new TeamData();
        storagePetInfos = petInfos = new ArrayList<>();
        fightPetInfos = new ArrayList<>();
        fightPetInfos.add(new PetData(1));
        fightPetInfos.get(0).state = 3;
        nick = "字符";
        signature = "签个名big";
        mapId = 90;
        highestPetLevel = 100;
        maxAvailableTime = availableTime = 3600 * 5;
        vipFlag = 1;
        vipLevel = 5;
        isYearVip = 1;
        ip = "127.0.0.1";
        dayLimitMap.put(5018, 1);//每日签到
        activityMap.put(206080,1);//密保
        activityMap.put(206230,2);//添加到桌面
        taskMap.put(10247,2);//星魂任务
    }


    public ByteArray writeLoginInfo(ByteArray b) {
        b.writeUnsignedInt(id);
        b.writeUTFBytes(nick, UserInfo.NICK_DATA_LEN);
        b.writeVar(color, x, y, medalId, createTime, equipIds, nonoEquipIds, sex, coins, mapId, sceneId, trainerScore, highestPetLevel);
        b.writeUnsignedInt((int) (System.currentTimeMillis() / 1000));//loginTime
        b.writeVar(completeQuestIds, lastCompleteQuestIds, inProgressQuestIds);

        b.writeUnsignedInt(fightPetInfos.size());
        for (PetData p : fightPetInfos) {
            p.writePetInfo(b);
        }
        b.writeUnsignedInt(storagePetInfos.size());
        for (PetData p : storagePetInfos) {
            p.writePetInfo(b);
        }

        b.writeUnsignedInt(petInfos.size());
        for (PetData p : petInfos) {
            p.writeSimplePetInfo(b);
        }

        b.writeVar(maxAvailableTime, availableTime, doubleExpTime, isShowLoginAwardPanel, stateFlag, callerUserId);
        teamData.writeBase(b);
        teamData.writeMore(b);
        b.writeVar(honorNum, payFlag, vipFlag, vipLevel, vipPoint, leftDay, equipeBallIds, energyBall, currentEnergy, totalEnergy, onceVipFlag, luckyLeftDay, luckyMsgType, luckyFlag);
        writeNonoInfo(b);

        b.writeUnsignedInt(0);
//        b.writeUnsignedInt(nonoTimes.size());
//        for (NonoTimeData n : nonoTimes) {
//            n.writeNonoTimeInfo(b);
//        }

        b.writeVar(expiredEquipIds, onLineDays, giftIndex, summerRegistrationDays, birthdayInfo);
        b.writeUTFBytes(ip, UserInfo.IP_ARRAY_LEN);
        b.writeVar(isYearVip);
        return b;
    }

    public ByteArray writeUserSimpleInfo(ByteArray b) {
        b.writeVar(id, sex);
        b.writeUTFBytes(nick, UserInfo.NICK_DATA_LEN);
        b.writeVar(color, trainerScore, equipIds, vipFlag, vipLevel, plantLevel);
        return b;
    }

    public void writeUserDetailInfo(ByteArray b) {
        writeUserSimpleInfo(b);
        b.writeVar(createTime, medalId, petCount, petLevel, sptCount, medalCount);
        b.writeUTF(signature);
        teamData.writeBase(b);
    }

    public void writeNonoInfo(ByteArray b) {
        b.writeVar(nonoHas);
        b.writeUTFBytes(nonoName, 16);
        b.writeVar(nonoFlag, nonoColor);
    }

    public void writeEnterMap(ByteArray b) {
        b.writeUnsignedInt(id);
        b.writeUTFBytes(nick, UserInfo.NICK_DATA_LEN);
        b.writeVar(color, x, y, medalId, createTime, equipIds, nonoEquipIds);
        b.writeVar(0, 0);//followingPetInfo so on
        b.writeVar(vipFlag, vipLevel, activityData, trainerScore);
    }
}
