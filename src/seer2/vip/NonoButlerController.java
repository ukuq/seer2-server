package seer2.vip;

import seer2.hu.ByteArray;
import seer2.hu.Vector;

public class NonoButlerController {
    public static final NonoButlerController NONO_BUTLER_CONTROLLER = new NonoButlerController();
    private NonoButlerController(){

    }
    public Vector<NonoTimeInfo> timeVec;
    public void parseData(ByteArray param1){
        createNonoTimeInfos();
        NonoTimeInfo no = null;
        int c1 = param1.readUnsignedInt();
        for (int i = 0; i < c1; i++) {
            no = new NonoTimeInfo();
            no.type = param1.readUnsignedByte();
            no.catchTime = param1.readUnsignedInt();
            no.endTime = param1.readUnsignedInt();
            timeVec.push(no);
        }//end
    }
    private void createNonoTimeInfos(){
        timeVec = new Vector<>();
        NonoTimeInfo no = new NonoTimeInfo();
        no.type=5;
        no.catchTime=0;
        no.endTime=10000;
        timeVec.push(no);
    }
}
