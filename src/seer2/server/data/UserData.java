package seer2.server.data;

import seer2.hu.ByteArray;
import seer2.hu.Message;
import seer2.message.LoginInfo;
import seer2.server.config.Monster;
import seer2.server.config.PetSkill;
import seer2.user.UserInfo;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserData {
    private int uid, mapId, sceneId, x, y, createTime;
    public String nick = "1";
    public List<PetData> fightPetInfos, storagePetInfos;
    public List<Integer> completeQuestIds, inProgressQuestIds;

    public UserData() {
        fightPetInfos = new ArrayList<>();
        storagePetInfos = new ArrayList<>();
        completeQuestIds = new ArrayList<>();
        inProgressQuestIds = new ArrayList<>();
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public int getSceneId() {
        return sceneId;
    }

    public void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getBagPetIds() {
        StringBuilder sb = new StringBuilder();
        for (PetData p : fightPetInfos) {
            sb.append(p.catchTime);
            sb.append(",");
        }
        sb.append(":");
        for (PetData p : storagePetInfos) {
            sb.append(p.catchTime);
            sb.append(",");
        }
        return sb.toString();
    }

    public void setBagPetIds(String bagPetIds) {
        Pattern p = Pattern.compile("\\d+");
        int mid = bagPetIds.indexOf(":");
        String s = bagPetIds.substring(0, mid);
        Matcher m = p.matcher(s);
        while (m.find()) {
            fightPetInfos.add(new PetData(Integer.parseInt(m.group())));
        }
        s = bagPetIds.substring(mid + 1);
        m = p.matcher(s);
        while (m.find()) {
            storagePetInfos.add(new PetData(Integer.parseInt(m.group())));
        }
        System.out.println(getBagPetIds());
    }

    public String getQuestIds() {
        StringBuilder sb = new StringBuilder();
        for (int i : completeQuestIds) {
            sb.append(i);
        }
        sb.append(":");
        for (int i : inProgressQuestIds) {
            sb.append(i);
        }
        return sb.toString();
    }

    public void setQuestIds(String questIds) {
        Pattern p = Pattern.compile("\\d+");
        int mid = questIds.indexOf(":");
        String s = questIds.substring(0, mid);
        Matcher m = p.matcher(s);
        while (m.find()) {
            completeQuestIds.add(Integer.parseInt(m.group()));
        }
        s = questIds.substring(mid + 1);
        m = p.matcher(s);
        while (m.find()) {
            inProgressQuestIds.add(Integer.parseInt(m.group()));
        }
    }

    {
//        createTime = (int)(System.currentTimeMillis() /1000);
//        var a = new ArrayList<Integer>();
//        //a.add(1);
//        activityData = equipIds = nonoEquipIds = completeQuestIds = lastCompleteQuestIds = inProgressQuestIds = equipeBallIds = expiredEquipIds = new ArrayList<>();
//        completeQuestIds= new ArrayList<>();
//        completeQuestIds.add(1);
//        completeQuestIds.add(83);
//        completeQuestIds.add(99);
//        nonoTimes = new ArrayList<>();
//        teamData = new TeamData();
//        storagePetInfos = petInfos = new ArrayList<>();
//        fightPetInfos.add(new PetData(500));
//        fightPetInfos.get(0).state = 3;
//        nick  = "字符";
//        signature="签个名big";
//        dailyLimitMap = new HashMap<>();
//        mapId=90;
//        highestPetLevel=100;
//        availableTime=3600*5;
//        vipFlag=1;
//        vipLevel=5;
//        isYearVip=1;
    }

    class NonoTimeData {
        byte type;
        int catchTime;
        int endTime;

        public void writeNonoTimeInfo(ByteArray b) {
            b.writeVar(type, catchTime, endTime);
        }
    }
}
