package seer2.hu.handler;

import seer2.hu.ByteArray;
import seer2.hu.Message;
import seer2.manager.GlobalManager;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class M_1156 extends M{

    public static void main(String[] args) {
        new M_1156().test();
    }
    public void test() {

        Message m_c = new Message("17, 0, 0, 0, 84, 4, 63, 19, 82, 5a, cc, ed, ec, 6b, 4e, f9, 4c, 4e, a6, 6a, ed, cb, f");
        System.out.println(m_c.parseInfo());
        parse_c(m_c.data.bytes);

        Message m = new Message("23, 0, 0, 0, 84, 4, 63, 19, 82, 5a, cc, ed, ec, 6b, ae, ac, 4c, 4e, 66, 85, 89, 19, ca, e1, fd, 95, cc, 1a, a6, 5, c9, 95, ba, e0, 3, 17, 0, 0, 0, e5, 4, 63, 19, 82, 5a, 6c, e0, ec, 6b, ae, ac, 4c, 4e, 66, 1b, ed, cb, f");
        System.out.println(m.parseInfo());
        parse(m.data.bytes);

    }
    @Override
    public void parse(byte[] param1){
        ByteArray b = new ByteArray(param1, ByteOrder.BIG_ENDIAN);
        int len = param1.length /4;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(String.format("%08x",b.readInt()));
        }
        GlobalManager.session = sb.toString();
        System.out.println(GlobalManager.session);
    }
    public void parse_c(byte[] param1){
        System.out.println("prodectID:"+ new ByteArray(param1,ByteOrder.LITTLE_ENDIAN).readUnsignedInt());
    }
}
