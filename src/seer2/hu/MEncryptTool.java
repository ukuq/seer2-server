package seer2.hu;

public class MEncryptTool {

    private static final int[] EN_KEY = {0xe6, 0x6b, 0xed, 0xcb, 0x6f, 0x84, 0x8e, 0x2e, 0xec, 0xad, 0xad, 0xac, 0xec, 0x6b, 0xae, 0xac, 0x4c, 0x4e};
    private static final int EN_KEY_LEN = EN_KEY.length;
    private static final int EN_DEFAULT_NUM = 0x68;
    private static final byte[] KEY = "taomee_seer2_k_~#t".getBytes();//utf-8
    private static int NO_ENCRYPT_LEN = 6;

    public static byte[] MEncrypt(byte[] bytes) {
        byte[] outBytes = new byte[bytes.length + 1];
        int before = EN_DEFAULT_NUM,now;//b'1101000
        for (int i = NO_ENCRYPT_LEN; i < bytes.length; i++) {
            now = bytes[i] & 0xff;
            outBytes[i] = (byte) (((((now << 5) + (before >>> 3)) ^ EN_KEY[i % EN_KEY_LEN]) & 0xf0) +
                    (((before >>> 3) ^ (EN_KEY[i % EN_KEY_LEN])) & 0xf));
            before = now;
        }
        outBytes[bytes.length] = (byte) (((((0x20) + (before >>> 3)) ^ EN_KEY[bytes.length % EN_KEY_LEN]) & 0xf0) +
                (((before >>> 3) ^ (EN_KEY[bytes.length % EN_KEY_LEN])) & 0xf));
        writeLen(outBytes,bytes);
        return outBytes;
    }

    public static byte[] MDecrypt(byte[] bytes) {
        byte[] outBytes = new byte[bytes.length - 1];
        for (int i = NO_ENCRYPT_LEN; i < outBytes.length; i++) {
            int t2_0 = ((bytes[i] ^ EN_KEY[i % EN_KEY_LEN]) & 0xe0) >>> 5; // i [YYYx xxxx] -> [xxxx xxxYYY] i
            int t7 = ((bytes[i + 1] ^ EN_KEY[(i + 1) % EN_KEY_LEN]) & 0x10) << 3;//i+1 [xxxY xxxx] -> [Yxxx xxxx] i
            int t6_3 = ((bytes[i + 1] ^ EN_KEY[(i + 1) % EN_KEY_LEN]) & 0xf) << 3;//i+1 [xxxx YYYY] -> [xYYY Yxxx] i
            outBytes[i] = (byte) (t2_0 + t7 + t6_3);
        }
        writeLen(outBytes,bytes);
        return outBytes;
    }

    public static byte[] encrypt1(byte[] bytes) {
        int offset = 4+2;
        byte[] outBytes = new byte[bytes.length + 1];
        int i = 0;
        for (int j = offset;j<bytes.length;j++) {
            if(i==KEY.length)i=0;
            outBytes[j] = (byte) (KEY[i++] ^ bytes[j]);
        }
        for (int j = outBytes.length-1; j >offset; j--) {
            outBytes[j] = (byte)((outBytes[j])|((outBytes[j-1]&0xff)>>3));
            outBytes[j-1]<<=5;
        }
        outBytes[offset]|=3;
        writeLen(outBytes,bytes);
        return outBytes;
    }

    public static byte[] decrypt1(byte[] bytes) {
        int offset = 4+2;
        byte[] outBytes = new byte[bytes.length - 1];
        int i = 0;
        for (int j = offset;j<outBytes.length;j++) {
            if(i==KEY.length)i=0;
            outBytes[j] = (byte)(( (bytes[j]&0xff) >> 5) | (bytes[j+1] <<3));
            outBytes[j] ^=KEY[i++];
        }
        writeLen(outBytes,bytes);
        return outBytes;
    }

    private static void writeLen(byte[] outBytes,byte[] bytes){
        outBytes[0]= (byte) (outBytes.length&0xff);
        outBytes[1]= (byte) ((outBytes.length>>8)&0xff);
        outBytes[2]= (byte) ((outBytes.length>>16)&0xff);
        outBytes[3]= (byte) ((outBytes.length>>24)&0xff);
        outBytes[4]=bytes[4];
        outBytes[5]=bytes[5];
    }
}
