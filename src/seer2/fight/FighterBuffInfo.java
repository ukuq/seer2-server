package seer2.fight;


import seer2.hu.ByteArray;
import seer2.hu.Vector;

public class FighterBuffInfo{
    private static final int TYPE_PERMANENT = 1000000000;
    private static final int TYPE_NEXT_TIME = 1000000101;
    private static final int TYPE_NEXT_ROUND = 1000010001;
    private static final int[] ITEM_LIST = {3427,3428,3429,3430,3431};
    private static final int[] EQUIP_LIST = {3442,3443,3444,3445,3446 };
    public int buffId;
    public int round;
    public int dummy0;
    public int dummy1;
    public int dummy2;
    public FighterBuffInfo(ByteArray param1){
        this.buffId = param1.readUnsignedInt();
        this.round = param1.readInt();
        this.dummy0 = param1.readInt();
        this.dummy1 = param1.readInt();
        this.dummy2 = param1.readInt();
    }
}
