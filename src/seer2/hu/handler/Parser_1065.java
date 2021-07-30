package seer2.hu.handler;

import seer2.hu.ByteArray;

public class Parser_1065 {
    public int id;
    public int count;
    public Parser_1065(byte[] bytes) {
        ByteArray byteArray = new ByteArray(bytes);
        this.id = byteArray.readUnsignedInt();
        this.count = byteArray.readUnsignedInt();
    }
}
