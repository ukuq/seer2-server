package seer2.parser;

import seer2.hu.ByteArray;

public class FighterBuffInfo {
    int buffId, round, dummy0,
            dummy1,//药水
            dummy2;//星魂

    public FighterBuffInfo(ByteArray param1) {
        super();
        this.buffId = param1.readUnsignedInt();
        this.round = param1.readInt();
        this.dummy0 = param1.readInt();
        this.dummy1 = param1.readInt();
        this.dummy2 = param1.readInt();
    }

    @Override
    public String toString() {
        return buffId+"@"+round+":"+dummy0+","+dummy1+","+dummy2;
    }
}
