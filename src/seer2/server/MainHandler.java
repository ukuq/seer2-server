//package seer2.server;
//
//import seer2.MainFrame;
//import seer2.hu.ByteArray;
//import seer2.hu.MEncryptTool;
//import seer2.hu.Message;
//import seer2.server.fight.Arena;
//
//import java.io.IOException;
//import java.net.Socket;
//import java.util.*;
//
//public class MainHandler {
//    private Socket socket;
//    public static HashMap<Integer,S2Handler> handlerMap;
//
//    static Arena arena;
//
//    public MainHandler(Socket socket){
//        this.socket=socket;
//        handlerMap=new HashMap<>();
//        handlerMap.put(1001,new S2User(socket));
////        handlerMap.put(1500,new M1500());
//        handlerMap.put(1501,new M1501());
////        handlerMap.put(1502,new M1502());
//        handlerMap.put(1509,new M1509());
//    }
//    public static void main(String[] args) {
//
//        ByteArray ba = new ByteArray(new byte[1024]);
//        byte[] d = new byte[ba.getPosition()];
//        System.arraycopy(ba.bytes,0,d,0,d.length);
//    }
//    public void error(Message m,int code){
//        write(m.cmdId,m.uid,m.sequenceIndex,code,null);
//    }
//    public void write(Message m,byte[] data){
//        write(m.cmdId,m.uid,m.sequenceIndex,0,data);
//    }
//    public void write(int cmdId,int uid,byte[] data){
//        write(cmdId,uid,0,0,data);
//    }
//    public void write(int cmdId,int uid,int sequenceIndex,int statusCode,byte[] data){
//        if(data==null)data = new byte[0];
//        byte[] bytes = new byte[Message.HEAD_LENGTH +data.length];
//        ByteArray b = new ByteArray(bytes);
//        b.writeVar(bytes.length,(short)cmdId,uid,sequenceIndex,statusCode);
//        System.arraycopy(data,0,bytes,Message.HEAD_LENGTH,data.length);
//        bytes = MEncryptTool.MEncrypt(bytes);
//        MainFrame.ProxyHandler.parseMsg(bytes);
//        try{
//            socket.getOutputStream().write(bytes);
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }
//
//
//
//static class M1 implements S2Handler{
//    @Override
//    public void parse(Message m,MainHandler h) {
//        //判定是否可对战
//        ByteArray ba = new ByteArray(new byte[1024]);
//
//        ba.writeVar((byte)1);
//        ba.writeVar((byte)1,m.uid,1,m.uid);
//        ba.writeUTFBytes("用户",16);
//        ba.writeVar(1,0,20,11,(byte)1,(short)100,500,500,1,10001,0,0);
//
//        ba.writeVar((byte)2,0,1,0);
//        ba.writeUTFBytes("系统",16);
//        ba.writeVar(1,0,50,10,(byte)1,(short)2,50000,50000,1,10001,0,0);
//        ba.writeVar((byte)1,(byte)1);
//        byte[] d = new byte[ba.getPosition()];
//        System.arraycopy(ba.bytes,0,d,0,d.length);
//        h.write(1,m.uid,d);
//    }
//}
//
//    static class M1500 implements S2Handler{
//    @Override
//    public void parse(Message m,MainHandler h) {
//        //判定是否可对战
//        h.write(m,null);
//        h.write(4,m.uid,null);
//
//        arena = new S2Arena();
//        handlerMap.put(1,(S2Handler) arena);
//    }
//}
//
//    static class M1501 implements S2Handler{
//    @Override
//    public void parse(Message m,MainHandler h) {
//        //判定是否可对战
//        h.write(m,null);
//        h.write(2,m.uid,null);
//    }
//}
//
//    static class M1502 implements S2Handler{
//    @Override
//    public void parse(Message m,MainHandler h) {
//        //写回技能选择结果
//        byte[] bytes =new byte[8];
//        int skillId = m.data.readUnsignedInt();
//        new ByteArray(bytes).writeVar(0,skillId);
//        h.write(m,bytes);
//
//        //更新精灵信息
//        ByteArray ba = new ByteArray(new byte[32]);
//        ba.writeVar(1,m.uid,0,20);
//        ba.writeVar(1,0,0,20);
//        h.write(15,m.uid,ba.bytes);
//
//        //更新双方怒气
//        ba = new ByteArray(new byte[24]);
//        ba.writeVar(m.uid,0,15);
//        ba.writeVar(0,0,20);
//        h.write(16,m.uid,ba.bytes);
//
//        //计算伤害结果
//        ba = new ByteArray(new byte[1024]);
//        ba.writeVar(2,(byte)1,m.uid,0,skillId,(byte)1,100,500,(short)20,(byte)0,(byte)6,(byte)6,(byte)6,(byte)6,(byte)6,0);
//        ba.writeVar((byte)0,0,0,0,(byte)1,100,500,(short)20,(byte)0,(byte)6,(byte)6,(byte)6,(byte)6,(byte)6,0);
//        ba.writeVar(0,1,125,1,21586);
//        byte[] d = new byte[ba.getPosition()];
//        System.arraycopy(ba.bytes,0,d,0,d.length);
//        h.write(3,m.uid,d);
//
//
//        //更新双方怒气2
//        ba = new ByteArray(new byte[24]);
//        ba.writeVar(m.uid,0,20);
//        ba.writeVar(0,0,100);
//        h.write(16,m.uid,ba.bytes);
//
//        //计算伤害结果2
//        ba = new ByteArray(new byte[1024]);
//        ba.writeVar(2,(byte)0,m.uid,0,0,(byte)1,100,500,(short)20,(byte)0,(byte)6,(byte)6,(byte)6,(byte)6,(byte)6,0);
//        ba.writeVar((byte)1,0,0,skillId,(byte)1,100,500,(short)20,(byte)0,(byte)6,(byte)6,(byte)6,(byte)6,(byte)6,0);
//        ba.writeVar(1,1,125,1,-1);
//        d = new byte[ba.getPosition()];
//        System.arraycopy(ba.bytes,0,d,0,d.length);
//        h.write(3,m.uid,d);
//
//
//        h.write(7,m.uid,new byte[2]);
//
//
//    }
//}
//
//
//    static class M1509 implements S2Handler {
//    @Override
//    public void parse(Message m, MainHandler h) {
//        ByteArray ba = new ByteArray(new byte[4]);
//        ba.writeUnsignedInt(m.uid);
//        h.write(m,ba.bytes);
//        h.write(12,m.uid,ba.bytes);
//        ba = new ByteArray(new byte[8]);
//        ba.writeVar(0,200);
//        h.write(5,m.uid,ba.bytes);
//
//        ba = new ByteArray(new byte[1024]);
//        ba.writeVar((byte)3,(byte)2,0,0,0,0);
//        byte[] d = new byte[ba.getPosition()];
//        System.arraycopy(ba.bytes,0,d,0,d.length);
//        h.write(1507,m.uid,d);
//    }
//}
//}