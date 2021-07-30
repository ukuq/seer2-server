package seer2.parser;

import seer2.constant.FightMode;
import seer2.constant.FightWeather;
import seer2.fight.TeamInfo;
import seer2.hu.ByteArray;
import seer2.hu.Message;
import seer2.hu.Vector;
import seer2.pet.PetInfo;
import seer2.pet.TrainingPetInfo;
import seer2.user.UserInfo;

import java.text.SimpleDateFormat;
import java.util.*;

public class MessageParser{
    private static Map<Integer,BaseParser> map;
    private static TeamInfo teamInfo1,teamInfo2;
    public interface BaseParser {
        void parse(ByteArray b);
        byte[] getExampleBytes();
    }

    static {
        map = new HashMap<>();
        map.put(1,new M1Parser());
        map.put(3,new M3Parser());
        map.put(1521,new M1521Parser());
        map.put(1051,new M1051Parser());

    }

    public static BaseParser get(int id){
        return map.get(id);
    }


    static class M1Parser implements BaseParser{

        @Override
        public void parse(ByteArray b) {
            Message.logger.info("fightMode:" + FightMode.findDescriptionById(b.readUnsignedByte()));
            Message.logger.info("team-1 -------------------");
            teamInfo1 = new TeamInfo(b, -1);
            Message.logger.info("team-2 -------------------");
            teamInfo2 = new TeamInfo(b, -1);
            Message.logger.info("fightWeather:" + FightWeather.findDescriptionById(b.readUnsignedByte()) + ", canCatch:" + (b.readUnsignedByte() == 0));
            System.out.println();
        }

        private static byte[] exampleBytes = Message.trans("7, 1, d5, 6e, a9, 35, 1, 0, 0, 0, d5, 6e, a9, 35, e9, a1, bd, e7, 9a, ae, e7, 9a, 84, e6, 98, 8e, e6, 98, 9f, 0, 6, 0, 0, 0, 54, cb, 95, 5e, 14, 0, 0, 0, 0, a, 0, 0, 1, 64, 0, 9f, 2, 0, 0, 9f, 2, 0, 0, 5, 0, 0, 0, e, 48, 0, 0, d, 48, 0, 0, 10, 48, 0, 0, 11, 48, 0, 0, f, 48, 0, 0, 0, 0, 0, 0, a6, 8f, 6a, 5b, 0, 0, 0, 0, 3, 0, 0, 0, 0, 5c, 0, d7, 1, 0, 0, d7, 1, 0, 0, 5, 0, 0, 0, 1b, 27, 0, 0, 1f, 27, 0, 0, 13, 27, 0, 0, 17, 27, 0, 0, 1d, 27, 0, 0, 0, 0, 0, 0, 8f, c8, 6b, 5b, 0, 0, 0, 0, c, 0, 0, 0, 0, 55, 0, 8b, 1, 0, 0, 8b, 1, 0, 0, 5, 0, 0, 0, 43, 27, 0, 0, 50, 27, 0, 0, 51, 27, 0, 0, 49, 27, 0, 0, 4e, 27, 0, 0, 0, 0, 0, 0, d6, e7, b0, 5e, 0, 0, 0, 0, c9, 0, 0, 0, 0, 60, 0, 18, 2, 0, 0, 18, 2, 0, 0, 5, 0, 0, 0, 32, 27, 0, 0, cc, 2a, 0, 0, 71, 2b, 0, 0, 72, 2b, 0, 0, 7a, 2b, 0, 0, 0, 0, 0, 0, f, e8, b0, 5e, 0, 0, 0, 0, 32, 0, 0, 0, 0, 57, 0, f1, 1, 0, 0, f1, 1, 0, 0, 5, 0, 0, 0, 64, 27, 0, 0, 32, 28, 0, 0, 33, 28, 0, 0, 34, 28, 0, 0, 3d, 28, 0, 0, 0, 0, 0, 0, c6, 29, b4, 5e, 0, 0, 0, 0, 7b, 0, 0, 0, 0, 52, 0, 93, 1, 0, 0, 93, 1, 0, 0, 5, 0, 0, 0, 32, 27, 0, 0, 65, 27, 0, 0, 9e, 29, 0, 0, 9f, 29, 0, 0, a8, 29, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, d9, 97, 51, 68, 40, 7f, 0, 0, 8b, 77, e3, 39, 40, 7f, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 14, 0, 0, 0, 3b, 4, 0, 0, 1, 64, 0, 58, 2, 0, 0, 58, 2, 0, 0, 1, 0, 0, 0, 44, 33, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1");
        @Override
        public byte[] getExampleBytes() {
            return exampleBytes;
        }
    }

    static class M1051Parser implements BaseParser{

        @Override
        public void parse(ByteArray b) {
            System.out.println("1051-"+b.readUnsignedInt());
            int c = b.readUnsignedInt();
            if(c>0)System.out.println("add:");
            for (int i = 0; i < c; i++) {
                System.out.println(b.readUnsignedInt()+","+b.readShort()+","+b.readUnsignedInt());
            }
            c = b.readUnsignedInt();
            if(c>0)System.out.println("add pet:");
            for (int i = 0; i < c; i++) {
                System.out.println(b.readUnsignedInt()+","+1+","+b.readUnsignedInt());
            }
            c = b.readUnsignedInt();
            if(c>0)System.out.println("remove:");
            for (int i = 0; i < c; i++) {
                System.out.println(b.readUnsignedInt()+","+b.readShort()+","+b.readUnsignedInt());
            }
        }


        private final static byte[]  exampleBytes = Message.trans("01, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 01, 00, 00, 00, 84, 1f, 06, 00, 32, 00, 00, 00, 00, 00");
        @Override
        public byte[] getExampleBytes() {
            return exampleBytes;
        }
    }

    static class M1521Parser implements BaseParser{
        int maxId,midId,minId;
        List<RankServerInfo> rankInfoList;
        private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        public void parse(ByteArray param1){
            this.maxId=param1.readUnsignedInt();
            this.midId = param1.readUnsignedInt();
            this.minId = param1.readUnsignedInt();
            rankInfoList=new ArrayList<>();
            int c = param1.readUnsignedInt();
            for (int i = 0; i < c; i++) {
                RankServerInfo rankServerInfo =  new RankServerInfo(param1);
                if(new Date(rankServerInfo.scoreTime * 1000L).getYear()+ 1900 >2017)rankInfoList.add(rankServerInfo);
            }
            rankInfoList.sort(RankServerInfo::compareTo);
            System.out.println(this);
        }

        @Override
        public String toString() {
            return "maxId:"+maxId+", midId:"+midId+", minId:"+minId+", rankInfoList:\n"+rankInfoList;
        }


        private byte[] exampleBytes = Message.trans("a, 0, 0, 0, 1, 0, 0, 0, c9, 0, 0, 0, b, 0, 0, 0, 0, fb, e6, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 77, bf, 37, 5a, fb, 4, 0, 0, 10, 0, 0, 0, 34, 33, 39, 39, e7, 8e, 8b, e8, 80, 85, 0, 0, 0, 0, 0, 0, 78, 43, 64, a, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 61, df, 48, 5a, be, 4, 0, 0, 10, 0, 0, 0, e5, 85, 89, e6, 98, 8e, 20, 35, 35, 35, 35, 0, 0, 0, 0, 0, fc, e8, 39, f, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 93, f7, 40, 5a, 83, 4, 0, 0, 10, 0, 0, 0, e5, 86, b7, e9, 9b, a8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, dd, 89, f3, 19, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 9e, f9, 40, 5a, 6b, 4, 0, 0, 10, 0, 0, 0, e9, be, 99, e4, bf, a1, e4, b8, b6, 0, 0, 0, 0, 0, 0, 0, 2f, 47, 8f, 24, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 5c, a4, 22, 5a, 5c, 4, 0, 0, 10, 0, 0, 0, e5, a4, a9, e4, bd, bf, 20, 20, e2, 98, 81, 0, 0, 0, 0, 0, 9, a2, 64, 2, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, da, 56, 3e, 5a, 50, 4, 0, 0, 10, 0, 0, 0, 34, 33, 39, 39, e5, b0, 8f, e7, bf, bc, 0, 0, 0, 0, 0, 0, 6f, 5b, 14, 18, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, cd, ee, 44, 5a, 28, 4, 0, 0, 10, 0, 0, 0, e8, 84, b1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, c4, 7a, 6a, 10, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 16, 8b, 2a, 5a, 13, 4, 0, 0, 10, 0, 0, 0, e7, 93, 9c, e5, 85, ae, e5, 85, ae, 0, 0, 0, 0, 0, 0, 0, b8, 19, 14, d, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, fe, d9, 48, 5a, 9, 4, 0, 0, 10, 0, 0, 0, e6, 98, 9f, e6, 9a, 9d, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, b8, c0, ff, 11, 0, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 71, 3a, 46, 5a, 9, 4, 0, 0, 10, 0, 0, 0, 73, 68, 69, 6e, e7, 92, 80, e7, 92, a8, e4, b8, b6, 0, 0, 0, 23, af, 76, f, 0, 0, 0, 0, a, 0, 0, 0, 0, 0, 0, 0, 58, dd, 48, 5a, 6, 4, 0, 0, 10, 0, 0, 0, e4, b8, 9d, e8, b7, af, e8, 8a, b1, e9, 9b, a8, 0, 0, 0, 0");
        @Override
        public byte[] getExampleBytes() {
            return exampleBytes;
        }

        class RankServerInfo implements Comparable<RankServerInfo>{
            int userId,currRank,score,scoreTime;
            String nick;
            public RankServerInfo(ByteArray param1){
                this.userId = param1.readUnsignedInt();
                param1.readUnsignedInt();
                this.currRank = param1.readUnsignedInt();
                param1.readUnsignedInt();
                this.scoreTime = param1.readUnsignedInt();
                this.score = param1.readUnsignedInt();
                int flag = param1.readUnsignedInt();
                if(flag != 0)
                {
                    this.nick = param1.readUTFBytes(flag);
//                System.out.println(param1.bytesAvailable());
//                System.out.println(nick);
                }
            }
            @Override
            public int compareTo(RankServerInfo o) {
                return this.currRank - o.currRank;
            }

            @Override
            public String toString() {
                return String.format("\nuserId:%-9d  currRank:%-3d  scoreTime:%s  score:%-4d  nick:%-20s",userId,currRank,sdf.format(new Date(scoreTime * 1000L)),score,nick);
            }
        }
    }

    static class M1005Parser implements BaseParser{

        @Override
        public void parse(ByteArray b) {
            int c = b.readUnsignedInt();
            for (int i = 0; i < c; i++) {
                int referenceId = b.readUnsignedInt();
                int quantity = b.readUnsignedShort();
                int expiryTime = b.readUnsignedInt();
                System.out.println("r:"+referenceId+", q:"+quantity+", t:"+expiryTime);
            }
        }

        private byte[] exampleBytes = Message.trans("4, 0, 0, 0, 33, 3c, 9, 0, 1, 0, 0, 0, 0, 0, be, 41, 9, 0, 1, 0, 0, 0, 0, 0, e1, 41, 9, 0, 1, 0, 0, 0, 0, 0, d9, 42, 9, 0, f4, 1, 0, 0, 0, 0");
        {
            exampleBytes = Message.trans("2d, 0, 0, 0, 43, d, 3, 0, 29, 1, 0, 0, 0, 0, 47, d, 3, 0, 1, 0, 0, 0, 0, 0, 4e, d, 3, 0, 29, 1, 0, 0, 0, 0, 50, d, 3, 0, 4, 0, 0, 0, 0, 0, 55, d, 3, 0, 2, 0, 0, 0, 0, 0, 56, d, 3, 0, 6, 0, 0, 0, 0, 0, 57, d, 3, 0, 2, 0, 0, 0, 0, 0, 7e, d, 3, 0, 6, 0, 0, 0, 0, 0, 80, d, 3, 0, 2, 0, 0, 0, 0, 0, 9, e, 3, 0, 4, 0, 0, 0, 0, 0, a, e, 3, 0, e, 0, 0, 0, 0, 0, 16, e, 3, 0, 1, 0, 0, 0, 0, 0, 18, e, 3, 0, 1, 0, 0, 0, 0, 0, 21, e, 3, 0, a, 0, 0, 0, 0, 0, 27, e, 3, 0, a, 0, 0, 0, 0, 0, 2f, e, 3, 0, 5, 0, 0, 0, 0, 0, 30, e, 3, 0, 4, 0, 0, 0, 0, 0, 3e, e, 3, 0, 2, 0, 0, 0, 0, 0, 3f, e, 3, 0, 2, 0, 0, 0, 0, 0, 40, e, 3, 0, 8, 0, 0, 0, 0, 0, 40, 11, 3, 0, 2, 0, 0, 0, 0, 0, c6, 24, 3, 0, 5, 0, 0, 0, 0, 0, ac, 2d, 3, 0, 14, 0, 0, 0, 0, 0, af, 2d, 3, 0, 1, 0, 0, 0, 0, 0, b0, 2d, 3, 0, 1, 0, 0, 0, 0, 0, 83, 1a, 6, 0, 2, 0, 0, 0, 0, 0, 84, 1a, 6, 0, 4, 0, 0, 0, 0, 0, 93, 1a, 6, 0, 2, 0, 0, 0, 0, 0, ed, 1a, 6, 0, 1, 0, 0, 0, 0, 0, 30, 1b, 6, 0, b, 0, 0, 0, 0, 0, de, 1b, 6, 0, e8, 3, 0, 0, 0, 0, ee, 1b, 6, 0, 2, 0, 0, 0, 0, 0, 13, 1c, 6, 0, 5, 0, 0, 0, 0, 0, 16, 1c, 6, 0, 1, 0, 0, 0, 0, 0, c1, 1e, 6, 0, 3c, 0, 0, 0, 0, 0, 7e, 1f, 6, 0, 4, 0, 0, 0, 0, 0, 84, 1f, 6, 0, 35, 2, 0, 0, 0, 0, 86, 1f, 6, 0, 9f, 0, 0, 0, 0, 0, 88, 1f, 6, 0, 4, 0, 0, 0, 0, 0, 8a, 1f, 6, 0, ab, 0, 0, 0, 0, 0, 8d, 1f, 6, 0, d2, 2, 0, 0, 0, 0, 8e, 1f, 6, 0, 98, 0, 0, 0, 0, 0, 99, 1f, 6, 0, 2c, 0, 0, 0, 0, 0, 21, a1, 7, 0, 1, 0, 0, 0, 0, 0, 68, a2, 7, 0, 1, 0, 0, 0, 0, 0");
        }
        @Override
        public byte[] getExampleBytes() {
            return exampleBytes;
        }
    }

    //www
    static class M1038Parser implements BaseParser{
        @Override
        public void parse(ByteArray param1) {
            HomeInfo homeInfo = new HomeInfo();
            homeInfo.leftFightCount = param1.readUnsignedByte();
            homeInfo.trainingPetInfoVec = new Vector<>();
            int c= param1.readUnsignedInt();
            for (int i = 0; i < c; i++) {
                TrainingPetInfo t = new TrainingPetInfo(param1);
                t.readSixAttr(param1);
                t.readSkillInfo(param1);
                t.setFlag(param1.readUnsignedInt());
                homeInfo.trainingPetInfoVec.push(t);
            }
            homeInfo.garbageCount = param1.readByte();
            c = param1.readUnsignedInt();
            for (int i = 0; i < c; i++) {
                homeInfo.birthPetInfo = new PetInfo();
                homeInfo.birthPetInfo.readPetInfo(param1);
            }
            homeInfo.suleAwardCount = param1.readUnsignedInt();
            //homeInfo.decorateInfo = new DecorateInfo(param1); //do nothing
            homeInfo.semiyaCount = param1.readUnsignedInt();
        }


        private byte[] exampleBytes = Message.trans("14, 0, 0, 0, 0, 5, 1, 0, 0, 0, 8c, b6, 73, 5b, 1, 3e, 0, 33, 1, 0, 0, 33, 1, 0, 0, c3, 0, cc, 0, 4, 1, eb, 0, ba, 0, 72, 2d, 0, 0, 4, 0, f, 0, 0, 0, 0, 2, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 56, 27, 0, 0, 5b, 27, 0, 0, 5c, 27, 0, 0, 61, 27, 0, 0, 5f, 27, 0, 0, 9, 0, 0, 0, 53, 27, 0, 0, 54, 27, 0, 0, 55, 27, 0, 0, 57, 27, 0, 0, 58, 27, 0, 0, 59, 27, 0, 0, 5a, 27, 0, 0, 5d, 27, 0, 0, 5e, 27, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 5e, 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0");
        @Override
        public byte[] getExampleBytes() {
            return exampleBytes;
        }

        class HomeInfo{
            int leftFightCount,garbageCount,suleAwardCount,semiyaCount;
            PetInfo birthPetInfo;
            Vector<TrainingPetInfo> trainingPetInfoVec;
        }
    }

    static  class M1082Parser implements BaseParser{
        @Override
        public void parse(ByteArray param1){
            Vector<PetInfo> petInfos1 = new Vector<>();
            Vector<PetInfo> petInfos2 = new Vector<>();
            UserInfo userInfo1 = new UserInfo();
            UserInfo userInfo2 = new UserInfo();
            int c = param1.readUnsignedInt();
            for (int i = 0; i < c; i++) {
                int actorId = param1.readUnsignedInt();
                param1.readUnsignedInt();
                int c2 = param1.readUnsignedInt();
                for (int j = 0; j < c2; j++) {
                    param1.readUnsignedInt();
                    param1.readUnsignedInt();
                    param1.readUnsignedInt();
                }
                if(i==0)
                {
                    userInfo1.id=param1.readUnsignedInt();
                    userInfo1.readUserInfoFromSex(param1);
                    Message.logger.info("id:"+userInfo1.id +", nick:"+userInfo1.nick);
                }
                else
                {
                    userInfo2.id=param1.readUnsignedInt();
                    userInfo2.readUserInfoFromSex(param1);
                    Message.logger.info("id:"+userInfo2.id +", nick:"+userInfo2.nick);
                }
                c2=param1.readUnsignedInt();
                for (int j = 0; j < c2; j++) {
                    PetInfo petInfo = new PetInfo();
                    petInfo.resourceId = param1.readUnsignedInt();
                    if(i == 0)
                    {
                        petInfos1.push(petInfo);
                    }
                    else
                    {
                        petInfos2.push(petInfo);
                    }
                }

            }
        }

        private static byte[] exampleBytes  = Message.trans("2, 0, 0, 0, 3, 66, f9, 15, dc, 5, 0, 0, 0, 0, 0, 0, 3, 66, f9, 15, 0, e8, 99, 8e, e7, 9a, ae, e7, 8c, ab, e5, a4, a7, e4, ba, ba, 0, 6, 0, 0, 0, b4, 16, 0, 0, 5, 0, 0, 0, fd, 86, 1, 0, ff, 86, 1, 0, 0, 87, 1, 0, 52, 87, 1, 0, 67, 88, 1, 0, 3, 0, 0, 0, c9, 0, 0, 0, c, 0, 0, 0, 32, 0, 0, 0, d5, 6e, a9, 35, dc, 5, 0, 0, 0, 0, 0, 0, d5, 6e, a9, 35, 0, e9, a1, bd, e7, 9a, ae, e7, 9a, 84, e6, 98, 8e, e6, 98, 9f, 0, 0, 0, 0, 0, 9c, 4, 0, 0, 5, 0, 0, 0, a3, 86, 1, 0, d5, 86, 1, 0, d6, 86, 1, 0, d7, 86, 1, 0, 20, 87, 1, 0, 3, 0, 0, 0, 1c, a, 0, 0, 20, a, 0, 0, e7, 3, 0, 0");
        @Override
        public byte[] getExampleBytes() {
            return exampleBytes;
        }
    }

    static class M_1002 {
        static String d = "ca, 44, a8, 11, e9, 87, 8a, e7, 84, b6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 6d, 3, 0, 0, 12, 2, 0, 0, 49, a2, 7, 0, b2, 19, ef, 4e, 7, 0, 0, 0, 24, 87, 1, 0, 25, 87, 1, 0, 26, 87, 1, 0, 27, 87, 1, 0, 85, 88, 1, 0, e6, 88, 1, 0, b6, 8b, 1, 0, 1, 0, 0, 0, b3, 8e, 1, 0, 1, 0, 0, 0, 8f, 18, 9, 5d, 7, a, 0, 0, 0, 64, 13, 0, 0, 0, 13, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, f0, 3, 0, 0, 0, 0, 0, 0, 0, 5, 16, 0, 0, 0, 14, 0, 0, 0, 1c, a3, 7, 0, 0, 0, 0, 0, 32, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 14, 11, 0, 0, 0, 1, 4e, 4f, 4e, 4f, 2e, 33, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 94, 30, 31, 1, 0, 0, 0, 0";

        static String d2 ="bf, 71, bb, f, e9, 82, aa, e7, 81, ab, e2, 98, 85, e5, 87, a4, e5, 87, b0, 0, 8, 0, 0, 0, 11, 2, 0, 0, ab, 1, 0, 0, 4e, a1, 7, 0, ef, ba, 3b, 4e, 6, 0, 0, 0, 5, 87, 1, 0, 21, 87, 1, 0, 1e, 89, 1, 0, 57, 8a, 1, 0, 52, 8b, 1, 0, 87, 8b, 1, 0, 1, 0, 0, 0, 7c, 8e, 1, 0, 1, 0, 0, 0, d1, f0, db, 4f, e1, 0, 0, 0, 2, 64, d, 0, 0, 0, 7, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 16, 0, 0, 0, 14, 0, 0, 0, 1e, a3, 7, 0, 0, 0, 0, 0, 32, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 4c, 15, 0, 0, 0, 1, 4e, 4f, 4e, 4f, 2e, 33, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99, a6, 31, 1, 0, 0, 0, 0";
        public static void main(String[] args) {
            UserInfo userInfo = new UserInfo();
            var b = new ByteArray(Message.trans(d2));
            userInfo.parseEnterMap(b);
            System.out.println(b.readUnsignedByte());
            userInfo.nonoInfo.readData(b);
            System.out.println(b.readUnsignedInt());
            System.out.println(b.readUnsignedInt());
            System.out.println(b.readUnsignedInt());
            System.out.println(b.bytesAvailable());
            System.out.println();
            System.out.println();
        }
    }

    static   class M_10 {
        public static M_10 m_10 = new M_10();
        private ItemUseResultInfo i ;
        static byte[] bytes = Message.trans("1, bf, 71, bb, f, f0, 9d, 6b, 5b, 0, 0, 0, 0, 1, f, 0, 0, 0, f, 0, 0, 0, 28, 0, 0, 6, 6, 6, 6, 6, 1, 0, 0, 0, 6b, 2b, 0, 0, 0, ca, 9a, 3b, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, c7, 6a, 5f, 7e, 5b, 74, 6c, aa, 7f, b9, 7, 0, 3a, 2c, 16, 0, 17, 40, 6d, 35, 34, 21, 5d, e8, 71, ae, 1, f2, 95, 6b, 61, 4, 16, 0, 17, 41, 6d, 34, 34, 21, 5d, 57, 0, 14, e, 2, 8, 14, 3a, 2c, 16, 3d, 73, 32, 5f, eb, 5f, 66, e8, 64, a0, 3, a, 8, 3a, 16, 3a, 16, 17, 57, 92, 28, bb, 1a, 8c, 97, 3c, 4e, 33, c, a, 8, 3a, 16, 3a, 16, 16, 57, 2d, 59, 14, 15, 7c, a, 3b, 70, 74, 61, 77, 6d, 7d, ae, 4f, a7, 7, 0, 17, 6d, 2c, e, 3a, c, 11, 29, 1f, 3e, 11, 4f, 11, 11, 3e, 1c, 9, 0, 17, 6d, 1e, 29, 3a, c, 10, a1, 1d, 3e, 11, c4, 13, 11, 3e, 3f, 8, 0, 11, 6b, 2a, 8, 3c, c, 11, 2b, 1f, 3c, ae, 3f, aa, 1e, ce, 81, 63, 5b, 17, 6d, 2c, e, 3b, c, 11, 2b, 1f, 31, 11, 4e, 11, 75, 3e, 1c, e, 6, 11, 6b, 2a, f, 3a, c, 11, 40, 34, 3e, 11, 4e, db, 8b, 5, 1c, 8, 0, 17, 6d, 2c, e, 3a, d, 11, 2b, 1f, 3e, 11, 4e, 11, 11, 3e, 1c, 8, 64, 17, 6d, 2c, f, 3a, c, 11, 3d, 1e, 3e, 11, c5, 65, 65, 5f, 4b, 65, 7d, b9, 22, 8b, 9, 3a, 1b, 7c, 7, 11, 4, 1d, 5f, 38, e");
        public static void main(String[] args) {
            try {
                ByteArray b = new ByteArray(bytes);
                m_10.parse(b);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        public void parse(ByteArray param1){
            i= new ItemUseResultInfo(param1);
        }
        class ItemUseResultInfo {
            int _side, userId, fighterId, _skillId, _position, _hp, _maxHp, _anger, _atkLevel, _defenceLevel, _specialAtkLevel, _specialDefenceLevel, _speedLevel;
            boolean _isDying;
            Vector<FighterBuffInfo> _buffInfoVec;

            public ItemUseResultInfo(ByteArray param1) {
                this._side = param1.readUnsignedByte();
                this.userId = param1.readUnsignedInt();
                this.fighterId = param1.readUnsignedInt();
                this._skillId = param1.readUnsignedInt();
                this._position = param1.readUnsignedByte();
                this._hp = param1.readUnsignedInt();
                this._maxHp = param1.readUnsignedInt();
                this._anger = param1.readUnsignedShort();
                this._isDying = param1.readUnsignedByte() == 1;
                this._atkLevel = param1.readUnsignedByte();
                this._defenceLevel = param1.readUnsignedByte();
                this._specialAtkLevel = param1.readUnsignedByte();
                this._specialDefenceLevel = param1.readUnsignedByte();
                this._speedLevel = param1.readUnsignedByte();
                this._buffInfoVec = new Vector<>();
                int c = param1.readUnsignedInt();
                for (int i = 0; i < c; i++) {
                    this._buffInfoVec.push(new FighterBuffInfo(param1));
                }
            }
        }


    }

    static class M1028 {
        static String s ="60, 59, f2, 36, 0, e8, 81, aa, e6, 98, 8e, e7, 9a, 84, e9, aa, 91, e5, a3, ab, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, a3, 86, 1, 0, 0, 0, 1, 0, 0, 0";
        public static void main1(String[] args) {
            var b = new ByteArray(Message.trans(s));
            UserInfo u = new UserInfo();
            u.id=b.readUnsignedInt();
            u.readSimpleInfo(b);
            System.out.println(b.bytesAvailable());
            System.out.println();
        }
    }

    static class M1023 {
        public static void main1(String[] args) {
            String s ="3d, 0, 0, 0, b, 49, 17, 8, 0, 0, 91, c1, ce, f, 0, 0, 9f, 6e, 3f, 7, 0, 0, 98, d2, 11, 3, 0, 0, 32, 40, 60, 2, 0, 0, 35, 7e, b9, 5, 0, 0, 37, a, 40, 5, 0, 0, b8, c0, 24, 4, 0, 0, c4, a3, f5, b, 0, 0, d6, 2a, e, 6, 0, 0, d4, d1, 8d, 2, 0, 0, df, cf, 5f, c, 0, 0, e6, 5d, 42, 9, 0, 0, ef, f1, 87, f, 0, 0, f6, bb, c, 0, 0, 0, e8, 5a, be, 0, 0, 0, 7e, f0, cd, 5, 0, 0, f2, 85, 7e, 1f, 0, 0, 3a, 8a, d1, 16, 0, 0, 61, ef, 8c, 1f, 0, 0, 2f, 1c, 49, 11, 0, 0, 18, b4, 31, 1b, 0, 0, 21, 8b, 6f, 16, 0, 0, c4, 8c, 41, 1b, 0, 0, ed, 41, 24, 14, 0, 0, 6d, 43, 61, 1d, 0, 0, 96, 16, 42, 1a, 0, 0, 7d, 6e, 46, 12, 0, 0, 1e, af, 71, 1d, 0, 0, b9, aa, 89, 1d, 0, 0, de, 43, c5, 35, 0, 0, af, 25, 9f, 1d, 0, 0, 34, 7e, c4, 30, 0, 0, 74, 85, 75, 1d, 0, 0, ed, 54, ab, 35, 0, 0, 7e, 16, 51, 18, 0, 0, 7a, 68, 97, 1f, 0, 0, 5d, 71, f4, 2f, 0, 0, 90, 15, 97, 1f, 0, 0, 2c, f9, 80, 1f, 0, 0, 8b, 19, b6, 35, 0, 0, d, 79, 7f, 1f, 0, 0, 32, 78, 76, 15, 0, 0, 4f, cd, 86, 1f, 0, 0, 9f, c, 61, 1e, 0, 0, 6c, 1d, 6e, 1f, 0, 0, da, 7b, af, 1f, 0, 0, aa, ba, 9a, 1f, 0, 0, 3b, ea, 6d, 1f, 0, 0, 9d, 89, d5, 16, 0, 0, d7, 18, b8, 1f, 0, 0, 2f, 87, 16, 15, 0, 0, 12, 23, 47, 13, 0, 0, 14, 82, 27, 21, 0, 0, cd, 24, 30, 17, 0, 0, 41, a9, f5, 1e, 0, 0, a6, c4, 52, 16, 0, 0, db, 91, b5, 24, 0, 0, 5, e, 22, 1a, 0, 0, 37, 77, 57, 16, 0, 0, a4, 9, 89, 18, 0, 0";
            s="3d, 0, 0, 0, 7e, f0, cd, 5, 0, 0, b, 49, 17, 8, 0, 0, 91, c1, ce, f, 0, 0, 98, d2, 11, 3, 0, 0, 9f, 6e, 3f, 7, 0, 0, 32, 40, 60, 2, 0, 0, 37, a, 40, 5, 0, 0, b8, c0, 24, 4, 0, 0, 35, 7e, b9, 5, 0, 0, d4, d1, 8d, 2, 0, 0, c4, a3, f5, b, 0, 0, d6, 2a, e, 6, 0, 0, df, cf, 5f, c, 0, 0, e6, 5d, 42, 9, 0, 0, e8, 5a, be, 0, 0, 0, f6, bb, c, 0, 0, 0, ef, f1, 87, f, 0, 0, d, 79, 7f, 1f, 0, 0, 5d, 71, f4, 2f, 0, 0, 4f, cd, 86, 1f, 0, 0, 8b, 19, b6, 35, 0, 0, 6c, 1d, 6e, 1f, 0, 0, da, 7b, af, 1f, 0, 0, aa, ba, 9a, 1f, 0, 0, 32, 78, 76, 15, 0, 0, 3b, ea, 6d, 1f, 0, 0, 9d, 89, d5, 16, 0, 0, d7, 18, b8, 1f, 0, 0, 2f, 87, 16, 15, 0, 0, 12, 23, 47, 13, 0, 0, 14, 82, 27, 21, 0, 0, cd, 24, 30, 17, 0, 0, 41, a9, f5, 1e, 0, 0, db, 91, b5, 24, 0, 0, 37, 77, 57, 16, 0, 0, a6, c4, 52, 16, 0, 0, a4, 9, 89, 18, 0, 0, f2, 85, 7e, 1f, 0, 0, 5, e, 22, 1a, 0, 0, 3a, 8a, d1, 16, 0, 0, 61, ef, 8c, 1f, 0, 0, 2f, 1c, 49, 11, 0, 0, 7d, 6e, 46, 12, 0, 0, 18, b4, 31, 1b, 0, 0, 21, 8b, 6f, 16, 0, 0, ed, 41, 24, 14, 0, 0, 6d, 43, 61, 1d, 0, 0, 96, 16, 42, 1a, 0, 0, c4, 8c, 41, 1b, 0, 0, 1e, af, 71, 1d, 0, 0, 7e, 16, 51, 18, 0, 0, b9, aa, 89, 1d, 0, 0, de, 43, c5, 35, 0, 0, af, 25, 9f, 1d, 0, 0, 34, 7e, c4, 30, 0, 0, 74, 85, 75, 1d, 0, 0, ed, 54, ab, 35, 0, 0, 7a, 68, 97, 1f, 0, 0, 90, 15, 97, 1f, 0, 0, 9f, c, 61, 1e, 0, 0, 2c, f9, 80, 1f, 0, 0";

            var b = new ByteArray(Message.trans(s));
            int c  = b.readUnsignedInt();
            for (int i = 0; i < c; i++) {
                System.out.println(b.readUnsignedInt()+":"+b.readUnsignedShort());
            }
            System.out.println(b.bytesAvailable());
            s="3d, 0, 0, 0, b, 49, 17, 8, 91, c1, ce, f, 9f, 6e, 3f, 7, 98, d2, 11, 3, 32, 40, 60, 2, 35, 7e, b9, 5, 37, a, 40, 5, b8, c0, 24, 4, c4, a3, f5, b, d6, 2a, e, 6, d4, d1, 8d, 2, df, cf, 5f, c, e6, 5d, 42, 9, ef, f1, 87, f, f6, bb, c, 0, e8, 5a, be, 0, 7e, f0, cd, 5, f2, 85, 7e, 1f, 3a, 8a, d1, 16, 61, ef, 8c, 1f, 2f, 1c, 49, 11, 18, b4, 31, 1b, 21, 8b, 6f, 16, c4, 8c, 41, 1b, ed, 41, 24, 14, 6d, 43, 61, 1d, 96, 16, 42, 1a, 7d, 6e, 46, 12, 1e, af, 71, 1d, b9, aa, 89, 1d, de, 43, c5, 35, af, 25, 9f, 1d, 34, 7e, c4, 30, 74, 85, 75, 1d, ed, 54, ab, 35, 7e, 16, 51, 18, 7a, 68, 97, 1f, 5d, 71, f4, 2f, 90, 15, 97, 1f, 2c, f9, 80, 1f, 8b, 19, b6, 35, d, 79, 7f, 1f, 32, 78, 76, 15, 4f, cd, 86, 1f, 9f, c, 61, 1e, 6c, 1d, 6e, 1f, da, 7b, af, 1f, aa, ba, 9a, 1f, 3b, ea, 6d, 1f, 9d, 89, d5, 16, d7, 18, b8, 1f, 2f, 87, 16, 15, 12, 23, 47, 13, 14, 82, 27, 21, cd, 24, 30, 17, 41, a9, f5, 1e, a6, c4, 52, 16, db, 91, b5, 24, 5, e, 22, 1a, 37, 77, 57, 16, a4, 9, 89, 18";
            b = new ByteArray(Message.trans(s));
            c  = b.readUnsignedInt();
            for (int i = 0; i < c; i++) {
                System.out.println(b.readUnsignedInt());
            }
            System.out.println(b.bytesAvailable());

        }
    }

    static class M3Parser implements BaseParser {

        @Override
        public void parse(ByteArray b) {
            new TurnResultInfo(b);
        }


        private static byte[] exampleBytes = Message.trans("2, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 42, 27, 0, 0, 1, f, 0, 0, 0, f, 0, 0, 0, 23, 0, 0, 6, 6, 6, 6, 6, 0, 0, 0, 0, 2, d5, 6e, a9, 35, 8f, c8, 6b, 5b, 0, 0, 0, 0, 1, 5a, 1, 0, 0, 79, 1, 0, 0, 14, 0, 0, 6, 6, 6, 6, 6, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 64, 0, 0, 0, 1, 0, 0, 0, 2, 0, 0, 0");
        @Override
        public byte[] getExampleBytes() {
            return exampleBytes;
        }

        class TurnResultInfo {
            int notifyIndex, atkTimes, changedHp, skillTypeDelation;
            boolean isCritical;
            List<FighterTurnResultInfo> fighterTurnResultInfoVec;

            TurnResultInfo(ByteArray param1) {
                fighterTurnResultInfoVec = new ArrayList<>();
                int c = param1.readUnsignedInt();
                for (int i = 0; i < c; i++) {
                    fighterTurnResultInfoVec.add(new FighterTurnResultInfo(param1));
                }
                this.notifyIndex = param1.readUnsignedInt();
                this.isCritical = param1.readUnsignedInt() >= 1;
                this.skillTypeDelation = param1.readUnsignedInt();
                this.atkTimes = param1.readUnsignedInt();
                this.changedHp = param1.readUnsignedInt();
                Message.logger.info("notifyIndex:"+notifyIndex+", isCritical:"+isCritical+", skillTypeDelation:"+skillTypeDelation+", atkTimes:"+atkTimes+", changedHp:"+changedHp);
            }
        }
        class FighterTurnResultInfo {
            int userId, catchTime, skillId, position, hp, maxHp, anger, atk, defence, specialAtk, specialDefence, speed;
            boolean isDying, isAtker;
            public static final int CENTERVALUE = 6;
            Vector<FighterBuffInfo> buffInfoVec;

            public FighterTurnResultInfo(ByteArray param1) {
                if (param1.readUnsignedByte() == 1) {
                    this.isAtker = true;
                }
                this.userId = param1.readUnsignedInt();
                this.catchTime = param1.readUnsignedInt();
                this.skillId = param1.readUnsignedInt();
                this.position = param1.readUnsignedByte();
                this.hp = param1.readUnsignedInt();
                this.maxHp = param1.readUnsignedInt();
                this.anger = param1.readUnsignedShort();
                this.isDying = param1.readUnsignedByte() == 1;
                this.atk = param1.readUnsignedByte();
                this.defence = param1.readUnsignedByte();
                this.specialAtk = param1.readUnsignedByte();
                this.specialDefence = param1.readUnsignedByte();
                this.speed = param1.readUnsignedByte();
                this.buffInfoVec = new Vector<>();
                int c = param1.readUnsignedInt();
                for (int i = 0; i < c; i++) {
                    this.buffInfoVec.push(new FighterBuffInfo(param1));
                }
                Message.logger.info("userId:"+userId+", catchTime:"+catchTime+", skillId:"+skillId+", position:"+position+"   "+buffInfoVec);
                Message.logger.info(hp+"/"+maxHp+"   "+anger+"/100"+(isDying?"  dying":"")+"   "+atk+defence+specialAtk+specialDefence+speed);
            }
        }
    }




}
