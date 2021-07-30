package seer2.hu;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 不废话,仿照as中的ByteArray写成,默认小端编址
 */
public class ByteArray {
    private ByteBuffer byteBuffer;
    public final byte[] bytes;

    public ByteArray(byte[] bytes) {
        this(bytes, ByteOrder.LITTLE_ENDIAN);
    }

    public ByteArray(byte[] bytes, ByteOrder endian) {
        byteBuffer = ByteBuffer.wrap(bytes).order(endian);
        this.bytes = bytes;
    }

    public static ByteArray getInstance(int len) {
        return new ByteArray(new byte[len], ByteOrder.LITTLE_ENDIAN);
    }

    public byte[] getUsedBytes() {
        return Arrays.copyOf(bytes, byteBuffer.position());
    }

    //编址与位置
    public ByteOrder getEndian() {
        return byteBuffer.order();
    }

    public void setEndian(ByteOrder endian) {
        byteBuffer.order(endian);
    }

    public int bytesAvailable() {
        return byteBuffer.remaining();
    }

    public int getPosition() {
        return byteBuffer.position();
    }

    public void setPosition(int position) {
        byteBuffer.position(position);
    }


    //压缩相关
    public void clear() {
        byteBuffer.clear();
        throw new UnsupportedOperationException();
    }

    public void compress() {
        throw new UnsupportedOperationException();
    }

    public void uncompress() {
        throw new UnsupportedOperationException();
    }

    public void deflate() {
        throw new UnsupportedOperationException();
    }

    public void inflate() {
        throw new UnsupportedOperationException();
    }

    //读数据
    public boolean readBoolean() {
        return byteBuffer.get() != 0;
    }

    public byte readByte() {
        return byteBuffer.get();
    }

    public short readShort() {
        return byteBuffer.getShort();
    }

    public int readInt() {
        return byteBuffer.getInt();
    }

    public int readUnsignedByte() {
        return byteBuffer.get() & 0xff;
    }

    public int readUnsignedShort() {
        return byteBuffer.getShort() & 0xffff;
    }

    /**
     * Java不支持32位无符号, 实际返回值为32为有符号
     *
     * @return
     */
    public int readUnsignedInt() {
        return byteBuffer.getInt();
    }

    public byte[] readBytes(int len) {
        byte[] bytes = new byte[len];
        byteBuffer.get(bytes);
        return bytes;
    }

    public void readBytes(byte[] bytes, int off, int len) {
        byteBuffer.get(bytes, off, len);
    }

    public double readDouble() {
        return byteBuffer.getDouble();
    }

    public double readFloat() {
        return byteBuffer.getFloat();
    }

    public String readMultiByte(long length, String charSet) {
        throw new UnsupportedOperationException();
    }

    public Object readObject() {
        throw new UnsupportedOperationException();
    }

    //public String readUTF(){ return readUTFBytes(readUnsignedShort()); }
    public String readUTFBytes(int length) {
        return new String(readBytes(length), StandardCharsets.UTF_8);
    }

    public void toJSON(String k) {
        throw new UnsupportedOperationException();
    }


    //写相关
    public void writeBoolean(boolean value) {
        throw new UnsupportedOperationException();
    }

    public ByteArray writeByte(byte value) {
        byteBuffer.put(value);
        return this;
    }

    public ByteArray writeShort(short value) {
        byteBuffer.putShort(value);
        return this;
    }


    public ByteArray writeInt(int value) {
        byteBuffer.putInt(value);
        return this;
    }

    public void writeUnsignedShort(int value) {
        byteBuffer.putShort((short) value);
    }

    public void writeUnsignedInt(int value) {
        byteBuffer.putInt(value);
    }


    public void writeBytes(ByteArray byteArray, int offset, int length) {
        throw new UnsupportedOperationException();
    }

    public void writeDouble(double value) {
        throw new UnsupportedOperationException();
    }

    public void writeFloat(float value) {
        throw new UnsupportedOperationException();
    }

    public void writeMultiByte(String value, String charSet) {
        throw new UnsupportedOperationException();
    }

    public void writeObject(Object object) {
        throw new UnsupportedOperationException();
    }

    public ByteArray writeBytes(byte[] bytes) {
        byteBuffer.put(bytes);
        return this;
    }

    public void writeUTF(String value) {
        byte[] b = value.getBytes(StandardCharsets.UTF_8);
        byteBuffer.putInt(b.length);
        byteBuffer.put(b);
    }

    public ByteArray writeUTFBytes(String value, int len) {
        byte[] des = new byte[len], src = value.getBytes(StandardCharsets.UTF_8);
        System.arraycopy(src, 0, des, 0, src.length);
        byteBuffer.put(des);
        return this;
    }

    public static byte[] writeToBytes(int size, Object... args) {
        ByteArray b = new ByteArray(new byte[size]);
        b.writeVar(args);
        return b.byteBuffer.remaining() == 0 ? b.bytes : b.getUsedBytes();
    }

    public void writeVar(Object... args) {
        for (Object o : args) {
            if (o == null) throw new NullPointerException("null pointer");
            if (o instanceof Integer) byteBuffer.putInt((int) o);
            else if (o instanceof Short) byteBuffer.putShort((short) o);
            else if (o instanceof Byte) byteBuffer.put((byte) o);
            else if (o instanceof String) throw new UnsupportedOperationException();
            else if (o instanceof Boolean) byteBuffer.put((byte) ((Boolean) o ? 1 : 0));
            else if (o instanceof List) {
                byteBuffer.putInt(((List) o).size());
                for (Object a : (List) o) {
                    writeVar(a);
                }
            } else if (o instanceof Map) {
                byteBuffer.putInt(((Map) o).size());
                for (Object k : ((Map) o).keySet()) {
                    writeVar(k);
                    writeVar(((Map) o).get(k));
                }
            } else throw new UnsupportedOperationException();
        }
    }

}
