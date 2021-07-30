package seer2.hu;


import hu.util.NumUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class MessageHandler {
    //src/com/taomee/seer2/app/MainEntry.as
    //Connection.send(CommandSet.ONLINE_LOGIN_1001,GlobalsManager.fromGame,LoginInfo.session,this.getTopLeftTmcid());
    static final int MSG_FIRST_TOKEN_LEN = 4;
    Vector<Message> messageQueue = new Vector<>();
    byte[] zeroBuffer = new byte[0];
    byte[] tempBuffer = zeroBuffer;
    byte[] chunkBuffer = zeroBuffer;
    byte[] inBuffer = zeroBuffer;

    void onSocketData(InputStream in) throws IOException {
        inBuffer = new byte[in.available()];
        in.read(inBuffer);
        chunkBuffer = NumUtil.byteMerger(tempBuffer,inBuffer);
        tempBuffer = zeroBuffer;
        if(chunkBuffer.length > 0){
            if(chunkBuffer.length > MSG_FIRST_TOKEN_LEN){
                int msgLen = ByteBuffer.wrap(chunkBuffer).order(ByteOrder.LITTLE_ENDIAN).getInt();
                if(msgLen > chunkBuffer.length){
                    tempBuffer = NumUtil.byteMerger(tempBuffer,chunkBuffer);
                    chunkBuffer =  zeroBuffer;
                }else{
                    messageQueue.push(new Message(new ByteArray(chunkBuffer)));
                    chunkBuffer = zeroBuffer;
                }
            }else{
                tempBuffer = NumUtil.byteMerger(tempBuffer,chunkBuffer);
                chunkBuffer =  zeroBuffer;
            }
        }

    }
}
