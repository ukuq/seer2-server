package seer2.fight;

import seer2.hu.ByteArray;
import seer2.hu.Message;

public class ArenaResourceLoadCMD {

    public ArenaDataInfo arenaData = new ArenaDataInfo();

    public static void main(String[] args) {
        String s = "ce, 0, 0, 0, 1, 0, 23, f4, c1, 18, ab, ac, ec, 6b, ae, ac, 4c, 4e, 86, 4b, 4d, 11, 42, 31, a8, 2e, ec, ad, d, 76, c1, de, 88, 91, f8, b9, ba, b8, 18, 97, fc, 54, 92, fd, 3d, b1, 5e, bf, cc, 6b, ae, ac, ac, 10, b7, 5, 66, c9, 6f, 84, ce, 4e, ec, ad, 8d, 2c, e0, ab, 92, ac, 4c, 8e, da, 6b, ed, 6b, 6f, 84, 8e, ae, 99, a8, ad, ac, 9b, 6e, ae, ec, e6, 49, e6, b, 9b, ce, 6f, c4, 3e, 29, ec, ad, ad, ac, ec, 6b, ae, ac, 4c, e, e6, 6b, ed, cb, 4f, 84, 8e, 2e, ec, ad, ad, ac, 6c, 68, ae, ac, 4c, 4a, e6, 6b, 6d, f1, 3e, 28, 72, d1, e3, ad, 8d, ac, ec, 6b, 8e, ac, 4c, 4e, 66, 69, ed, cb, 4f, 86, 8e, 2e, cc, 2d, af, ac, f5, 6b, ae, ac, 55, 4e, e6, 8b, ed, cb, 6f, 4, 62, 2a, ec, d, 41, a8, ec, ab, 42, a8, 4c, ae, a, 6f, ed, cb, 82, 80, 8e, e, 1, a9, ad, ec, 1, 6f, ae, ac, 4c, 4e, e6, 6b, ed, cb, 6f, 64, 8e, e";
        Message m = new Message(s);
        System.out.println(m.parseInfo());
        new ArenaResourceLoadCMD().onGetResourceInfo(m.data);
    }

    public void onGetResourceInfo(ByteArray param1) {
        this.arenaData.fightMode = param1.readUnsignedByte();
        TeamInfo t1 = new TeamInfo(param1, this.arenaData.fightMode);
        TeamInfo t2 = new TeamInfo(param1, this.arenaData.fightMode);
        this.arenaData.fightWeather = param1.readUnsignedByte();
        this.arenaData.canCatch = param1.readUnsignedByte() == 0;
        if(t1.serverSide == 1)//FightSide.LEFT
        {
            this.arenaData.leftTeam = new FighterTeam(t1);
            this.arenaData.rightTeam = new FighterTeam(t2);
        }
        else
        {
            this.arenaData.rightTeam = new FighterTeam(t2);
            this.arenaData.leftTeam = new FighterTeam(t1);
        }
        System.out.println(param1.bytesAvailable());
    }
}
