package seer2;

import seer2.hu.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Seer2Session {
    public static byte[] FLASH_POLICY_REQUEST = "<policy-file-request/>\0".getBytes(StandardCharsets.UTF_8);
    public static byte[] FLASH_POLICY_RESPONSE = "<?xml version=\"1.0\"?><!DOCTYPE cross-domain-policy><cross-domain-policy><allow-access-from domain=\"*\" to-ports=\"*\" /></cross-domain-policy>\0".getBytes(StandardCharsets.UTF_8);

    public static void main(String[] args) throws IOException {
        var s = new Socket("118.89.150.23",843);
        s.getOutputStream().write(FLASH_POLICY_REQUEST);
        s.getOutputStream().flush();
        System.out.println(1);
        var b = s.getInputStream().readAllBytes();
        System.out.println(Arrays.toString(b));
        //new Seer2Session().listen();
    }

    public void listen() throws IOException {
        ServerSocket ss = new ServerSocket(5000);
        System.out.println("等待连接 " + ss.getLocalSocketAddress());
        while (!ss.isClosed()) {
            handle(ss.accept());
        }
    }

    static void handle(Socket s) {
        new Thread(new LoginTask(s)).start();
    }

    static class LoginTask implements Runnable {
        Socket socket;

        LoginTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                var in = socket.getInputStream();
                var out = socket.getOutputStream();
                if (Arrays.equals(in.readAllBytes(), FLASH_POLICY_REQUEST)) {
                    System.out.println(1);
                    out.write(FLASH_POLICY_RESPONSE);
                    out.flush();
                }
                while (in.available()==0){}
                System.out.println(Arrays.toString(in.readAllBytes()));
                var m = Message.from(in, false);
                System.out.println(m.parseInfo());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
