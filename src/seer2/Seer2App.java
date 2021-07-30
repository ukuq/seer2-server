package seer2;

import seer2.cmd.Command;
import seer2.hu.ByteArray;
import seer2.hu.Message;
import seer2.login.MainServerLoginInfo;
import seer2.login.OnlineServerListInfo;
import seer2.login.ServerInfo;
import seer2.message.LoginInfo;
import seer2.server.S2Handler;
import seer2.server.S2User;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Seer2App {
    public static byte[] FLASH_POLICY_REQUEST = "<policy-file-request/>\0".getBytes(StandardCharsets.UTF_8);
    public static byte[] FLASH_POLICY_RESPONSE = "<?xml version=\"1.0\"?><!DOCTYPE cross-domain-policy><cross-domain-policy><allow-access-from domain=\"*\" to-ports=\"*\" /></cross-domain-policy>\0".getBytes(StandardCharsets.UTF_8);
    public static byte[] arr = new byte[]{-38, 4, 0, 0, -23, 3, 35, -12, -63, 24, 11, -72, -20, 107, -82, -84, 76, 78, 70, -79, -64, 126, -119, -114, -124, 46, -20, -83, -83, -84, -20, 107, -82, -84, 76, 78, -26, 107, -19, -53, 111, -124, -82, 108, -20, -83, -51, -103, -20, 107, -82, -84, 76, 78, -122, -44, -68, -90, -92, -124, -114, 46, -116, 121, -99, -84, 76, -79, -98, -84, -116, -108, -42, 107, 13, 17, 95, -124, 110, -35, -36, -83, -19, -121, -35, 107, -82, -84, 76, 78, -26, 11, -53, 114, 111, -60, -123, 46, -20, -83, -83, -84, -20, 107, 56, -84, 76, -50, 42, -21, 92, -38, -61, -127, -114, 46, -52, -83, -83, -84, -116, 107, -82, -84, -52, 78, -26, 107, 77, -53, 111, -124, 78, 46, -20, -83, 77, -84, -20, 107, -82, -83, 76, 78, -58, 106, -19, -53, 47, -123, -114, 46, -116, -84, -83, -84, 108, 106, -82, -84, -20, 79, -26, 107, 45, -54, 111, -124, 46, 44, -20, -83, 109, -82, -20, 107, 78, -82, 76, 78, -58, 104, -19, -53, 47, -121, -114, 46, -116, -82, -83, -84, 108, 104, -82, -84, -20, 77, -26, 107, 45, -56, 111, -124, 110, 45, -20, -83, -83, -88, -20, 107, -114, -88, 76, 78, -90, 111, -19, -53, 15, -128, -114, 46, 108, -87, -83, -84, 76, 111, -82, -84, -20, 73, -26, 107, 45, -52, 111, -124, 110, 41, -20, -83, -51, -91, -20, 107, -50, -96, 76, 78, 102, 103, -19, -53, -49, -120, -114, 46, 44, -95, -83, -84, -52, -119, -86, -84, 44, -84, -30, 107, 77, 41, 107, -124, 110, -42, -24, -83, 77, -84, -23, 107, -114, 0, 66, 78, -90, -57, -29, -53, 15, 40, -128, 46, -20, -83, -83, -84, -84, 106, -82, -84, -52, 73, -26, 107, 13, -57, 111, -124, -50, -52, -24, -83, 77, 78, -24, 107, -114, 75, 72, 78, 70, -125, -23, -53, 79, 119, -118, 46, 44, -83, -88, -84, 12, -52, -96, -84, -20, -28, -24, 107, -115, -53, 111, -124, 110, 63, -107, -64, -26, 44, -32, 107, -108, -84, 76, 78, -36, 107, -19, -21, 94, -124, -93, -82, -59, 109, -124, -52, -50, 107, -82, -84, 76, 46, -26, -21, -20, -53, 111, -92, -98, 46, -20, 77, -82, -84, -20, 107, -82, -84, 76, 78, -26, 107, -19, -53, 111, 36, -114, 46, -20, -51, 69, -88, -20, 107, 68, -88, 76, 110, 12, 111, -19, -21, -122, -128, -114, -18, 5, -87, -83, 44, -19, 107, -82, -20, -92, 74, -26, -21, 5, -49, 111, 36, 102, 42, -20, 109, 69, -88, -20, -117, 70, -88, 76, 78, 15, 111, -19, -117, -122, -128, -114, 78, 5, -87, -83, 44, 5, 111, -82, 12, -91, 74, -26, -117, 4, -49, 111, -60, 100, 42, -20, -83, -83, -84, -20, 107, -82, -84, 76, 78, -26, 107, -19, -53, 111, -124, -114, 46, -20, -83, -83, -84, -20, 107, -82, -84, 76, 78, -26, 107, -19, -53, 111, -124, -114, 46, -20, -51, -95, -20, -23, 107, -82, -84, 76, 78, -26, 107, -19, -53, 111, -124, -114, 46, -20, -83, -83, -84, -20, 107, -82, -84, 76, 14, 101, 76, 56, -32, -17, -120, -50, 96, -20, -83, -19, -30, -20, 107, 46, -97, 12, 112, -26, 92, -19, -10, -81, -67, -114, 46, -20, -83, 45, -83, -20, 23, -82, -84, 108, 78, -26, 107, -83, -53, 111, -124, -114, 46, -20, -83, -83, -84, -20, 107, -82, -84, -20, 78, -26, 107, 77, 102, 103, -124, 78, -125, -28, -83, -83, 3, -28, 107, 78, 2, 68, 78, -58, -60, -27, -53, 111, -123, -114, 46, 12, 0, -91, -84, -20, -59, -90, -84, 108, -32, -18, 107, -83, 101, 103, -124, -18, -128, -28, -83, 45, 2, -28, 107, 14, 2, 68, 78, 38, -59, -27, -53, 15, -121, -114, 46, -20, -83, -83, -84, 108, 102, -82, -84, -52, 67, -26, 107, 109, -58, 111, -124, 14, 35, -20, -83, 45, -95, -20, 107, 46, -95, 76, 78, -26, 107, -19, -53, 15, -108, 78, 38, -20, -83, -83, -84, -20, 107, -82, -84, 76, 78, -26, 107, -19, -53, 111, -124, -114, 46, -20, -83, -83, -84, 44, -54, -84, 72, 103, 46, -25, 43, -21, -53, 111, -60, -120, 46, -20, -83, -85, -84, -22, 107, -88, -84, 74, -114, -29, 75, -29, -53, 111, 4, -116, 78, -25, -83, -83, 76, -20, 107, -82, -84, 76, 78, -26, 107, -19, -53, 111, -124, -114, 46, -20, -83, -83, -52, -20, 107, -82, -20, -92, 74, -26, -53, 1, -49, 111, -124, -83, 43, -20, -83, -83, -84, -20, -85, -84, -84, 76, 78, -26, 107, -19, -53, 100, -124, -114, 46, -25, -83, -83, -84, -25, 107, -82, -84, 71, 78, -26, 107, -26, -53, 111, -124, -123, 46, -20, -83, -83, -84, -20, 11, -86, 76, 76, -82, 102, -7, -19, -53, 111, -124, -114, 46, -20, -83, -83, -84, -20, 107, -82, -84, 76, 78, -26, 107, -19, -21, 111, -124, -114, 46, 21, 85, 122, -25, 108, 106, 14, -86, 76, 78, 70, 109, -19, -53, -81, -126, -50, 40, -52, -85, -115, -86, -84, 110, 78, -81, 76, 78, -122, 107, -51, -56, 111, -124, -82, 46, -27, -83, -115, -84, -20, 107, -82, -84, 76, 78, -26, 107, -19, -53, 111, -124, 14, 46, -20, -83, 109, 88, -24, 107, 78, 88, 72, 78, -26, -98, -23, -53, -17, -125, -117, 46, -20, -83, -83, -84, -52, 104, -82, -84, 76, 78, -26, 107, 109, -57, 111, -124, 14, 34, -20, -83, 45, -96, -20, 107, 46, -96, 76, 78, 102, 103, -19, -53, -17, -120, -114, 46, -20, -83, -83, -84, -84, 111, -114, -83, 76, 78, -26, 107, -19, -53, 111, -124, -114, 46, -20, -83, -83, -84, -20, 107, -82, -84, 76, 78, -26, 107, 109, -53, 111, -124, 14, -61, -67, -64, 70, -84, -20, 107, -82, -82, 76, 78, -26, 107, 45, 63, 62, -23, -27, 46, -20, -83, 45, -89, -20, 107, -82, -84, 108, 94, -76, 6, 38, -53, 111, -124, -114, 40, -20, -83, -83, -84, 76, 87, -104, 127, 103, 78, -26, 107, -51, -54, 111, -124, -114, 46, -20, 103, -91, -84, 12, -19, -90, -84, 76, 78, -26, 107, -51, -53, 111, -124, -114, 46, -20, -83, -83, -84, -20, 107, -82, 39, 20, 78, 70, -105, 59, 127, 115, -46, -65, -109, 121, 31, -79, -69, -3, 107, -82, -84, 76, 78, -26, 107, -19, -53, 111, -124, -114, -18, -20, -83, -83, 108, -19, 107, -82, -84, -52, -73, -7, 107, -19, -53, 111, -92, -18, 45, -20, -83, -51, -92, -20, 107, -18, -84, 76, 78, -26, 107, -19, -53, 111, -124, -82, 45, -20, -83, -83, -84, -20, 107, -82, -84, 76, 78, -26, 107, -19, -53, 111, -124, -114, 46, 108, -81, -83, -84, -20, 107, -82, -84, 108, -114, 15, -94, 4, 2, 10, -126, -114, 46, -20, -83, -83, -84, -20, 107, -82, -84, 76, 78, -26, 107, -19, -53, 111, -124, -114, 46, -20, -83, -83, -84, -20, 11, -89, -84, 76, -18, -26, 107, -19, -53, 111, -124, -114, 46, -20, -83, -83, -20, -54, 109, 105, -119, 106, -87, 32, 78, 75, -83, -87, 65, 72, 40, -20, -83, -83, -84, -20, 107, -82, -84, 76, 14};

    public static final int PORT = 5201;
    public static final int LOGIN_PORT = 5002;
    public static final int FLASH_PORT = 843;

    static Map<Integer, byte[]> sessionMap = new HashMap<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(FLASH_PORT);
                System.out.println("监听flash:" + serverSocket.getLocalPort());
                while (true) {
                    Socket socket = serverSocket.accept();
                    handleFlash(socket);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(PORT);
                System.out.println("监听:" + serverSocket.getLocalPort());
                while (true) {
                    Socket socket = serverSocket.accept();
                    handleSocket(socket);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(LOGIN_PORT);
                System.out.println("监听登录:" + serverSocket.getLocalPort());
                while (true) {
                    Socket socket = serverSocket.accept();
                    handleLogin(socket);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }


    private static byte[] readMessage(InputStream in) throws IOException {
        var pis = new PushbackInputStream(in, 4);
        byte[] b1 = new byte[4];
        int length;
        while (true) {
            if (pis.read(b1) < 4) continue;
            length = ByteBuffer.wrap(b1).order(ByteOrder.LITTLE_ENDIAN).getInt();
            if (length > 0) break;
        }
        pis.unread(b1);
        return pis.readNBytes(length);
    }

    private static void handleFlash(Socket socket) {
        new Thread(() -> {
            try (InputStream in = socket.getInputStream(); OutputStream out = socket.getOutputStream()) {
                var b = in.readNBytes(FLASH_POLICY_REQUEST.length);
                var s = new String(b, StandardCharsets.US_ASCII);
                System.out.println("flash: " + socket.getRemoteSocketAddress() + ", " + s);
                out.write(FLASH_POLICY_RESPONSE);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void handleLogin(Socket socket) {
        new Thread(() -> {
            try (InputStream in = socket.getInputStream(); OutputStream out = socket.getOutputStream()) {
                while (true) {
                    Message m = new Message(readMessage(in), false);
                    if (m.cmdId == 0) continue;
                    System.out.println();
                    System.out.println(m.parseInfo());
                    ByteArray ba;
                    byte[] data = switch (Command.findById(m.cmdId)) {
                        case LOGIN_103 -> ByteArray.getInstance(4 + 16 + 4).writeInt(0).writeBytes(new byte[16]).writeInt(1).bytes;
                        case GET_RECOMMEND_SERVER_LIST_105 -> {
                            ba = ByteArray.getInstance(1024);
                            ba.writeInt(1).writeInt(1);
                            ba.writeShort((short) 1).writeUTFBytes("127.0.0.1", 16).writeShort((short) PORT).writeInt(0).writeByte((byte) 0).writeByte((byte) 0);
                            ba.writeByte((byte) 0);
                            ba.writeInt(0).writeInt(0);//friend info
                            yield ba.getUsedBytes();
                        }
                        case CHECK_HAS_ROLE_111 -> ByteArray.getInstance(4).writeInt(1).bytes;
                        default -> new byte[0];
                    };
                    out.write(ByteArray.getInstance(18 + data.length).writeInt(18 + data.length).writeShort((short) m.cmdId).writeInt(123456).writeInt(0).writeInt(0).writeBytes(data).bytes);
                    out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void handleSocket(Socket socket) {
        new Thread(() -> {
            try (InputStream in = socket.getInputStream(); OutputStream out = socket.getOutputStream()) {
                S2User s2User = null;
                while (true) {
                    Message m = new Message(readMessage(in), true);
                    System.out.println();
                    System.out.println(m.parseInfo());

                    if (s2User == null) {
//                       if(m.cmdId!=1001 || sessionMap.get(m.uid) == null)break;
//                       m.data.readUnsignedInt();
//                       byte[] session = m.data.readBytes(20);
//                       if(!Arrays.equals(session,sessionMap.get(m.uid)))break;
                        s2User = new S2User(socket, m.uid);
                    }
                    S2Handler s2Handler = s2User.handlerMap.get(m.cmdId);
                    if (s2Handler != null) s2Handler.parse(m, s2User);
                    else {
                        byte[] bytes = Seer2App.lieSome(m);
                        if (bytes != null) s2User.write(m.cmdId, bytes);
                        else if (m.cmdId == 1004) {
                            m.cmdId = 1103;
                            s2User.write(m, Seer2App.lieSome(m));
                        } else s2User.error(m, 500);
                    }
                    out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    static void show(ByteArray b) {
        System.out.println();
        while (b.bytesAvailable() > 0) {
            System.out.print(b.readUnsignedInt());
        }
        System.out.println();
    }

    public static byte[] lieSome(Message m) {
        byte[] bx;
        byte[] b4 = new byte[4];
        byte[] b8 = new byte[8];
        ByteArray ba;
        int c;
        switch (m.cmdId) {
            case 1011:
                new ByteArray(b4).writeUnsignedInt(m.data.readUnsignedInt());
                return b4;
            case 1065:
                var t = m.data.readUnsignedInt();
                System.out.println(t);
                new ByteArray(b8).writeUnsignedInt(t);
                return b8;
            case 1156:
            case 1521:
                return new byte[16];
            case 1202:
            case 1253:
                bx = new byte[4];
                new ByteArray(bx).writeUnsignedInt(12345600);
                return bx;
            case 1003:
                bx = new byte[6];
                new ByteArray(bx).writeUnsignedInt(m.uid);
                return bx;
            case 1075:
                bx = new byte[20];
                new ByteArray(bx).writeUnsignedInt(m.uid);
                System.arraycopy(m.data.bytes, Message.HEAD_LENGTH, bx, 4, 16);
                return bx;
            case 1005:
            case 1022:
            case 1004:
            case 1213:
            case 1234:
            case 1255:
            case 1007:
            case 1268:
                return b4;
            case 1058:
                return new byte[200];
            case 1062://长度不定, 按需调试
                bx = new byte[54];
                new ByteArray(bx).writeUnsignedInt(m.data.readUnsignedInt());
                return bx;
            case 1010:
                // bx = S2User.getMore();
                //new ByteArray(bx).writeUnsignedInt(m.data.readUnsignedInt());
                //return bx;
                return null;
            case 1142:
                ba = ByteArray.getInstance(1024);
                c = m.data.readUnsignedInt();
                ba.writeUnsignedInt(c);
                for (int i = 0; i < c; i++) {
                    m.data.readUnsignedInt();
                    ba.writeUnsignedInt(0);
                }
                return ba.getUsedBytes();
            case 1500:
                return new byte[0];
            case 1002:
                //return S2User.getEnter();
                return null;
            case 1262:
                return new byte[12];
            case 1055:
                bx = new byte[28];//4+4+8+12
                return bx;
            case 1101:
                bx = new byte[12];
                ba = new ByteArray(bx);
                ba.writeUnsignedInt(m.cmdId);
                ba.writeUnsignedInt(m.data.readUnsignedInt());
                ba.writeUnsignedInt(m.data.readUnsignedInt());
                return bx;
            case 1030:
                bx = new byte[m.data.bytes.length - Message.HEAD_LENGTH];
                System.arraycopy(m.data.bytes, Message.HEAD_LENGTH, bx, 0, bx.length);
                return bx;
            case 1052:
                return new byte[12];
            case 1522:
                return new byte[40];
            case 1103:
                bx = new byte[49];
                ba = new ByteArray(bx);
                ba.writeUnsignedInt(3);
                for (int i = 0; i < 3; i++) {
                    ba.writeVar(i, 500, (short) 2, 0, (byte) 0);
                }
                return bx;
            case 1015:
                ba = ByteArray.getInstance(128);
                ba.writeUnsignedInt(m.data.readUnsignedInt());
                ba.writeByte(m.data.readByte());
                ba.writeVar(0, 0, 0, 0);
                return ba.getUsedBytes();
            case 1241:
                ba = ByteArray.getInstance(256);
                c = m.data.readUnsignedInt();
                ba.writeUnsignedInt(c);
                for (int i = 0; i < c; i++) {
                    ba.writeUnsignedInt(m.data.readUnsignedInt());
                    ba.writeUnsignedInt(0);
                }
                return ba.getUsedBytes();
            case 1251:
                System.out.println(1251 + ":" + m.data.readUnsignedInt() + " " + m.data.readUnsignedInt());
                return new byte[0];
            case 1079:
                System.out.println(1079 + ":" + m.data.readUnsignedInt() + " " + m.data.readUnsignedInt());
                return new byte[0];
            case 1080:
                return new byte[0];
            case 1244:
                ba = ByteArray.getInstance(256);
                ba.writeUnsignedInt(m.data.readUnsignedInt());
                c = m.data.readUnsignedInt();
                ba.writeUnsignedInt(c);
                for (int i = 0; i < c; i++) {
                    ba.writeUnsignedInt(0);
                }
                return ba.getUsedBytes();
            case 1219:
                return ByteArray.writeToBytes(8, 0, 0);
            case 1516:
                return ByteArray.writeToBytes(4, 3600 * 3);
        }
        return null;
    }

    public static class A {
       // public static void main(String[] args) {
        //      LoginInfo.loginInfo.setFromOnline(new Message(arr, true).data);
        //  }
    }
}