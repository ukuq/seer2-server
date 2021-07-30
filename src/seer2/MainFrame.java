package seer2;

import hu.util.NumUtil;
import seer2.hu.ByteArray;
import seer2.hu.Message;
import seer2.login.OnlineServerListInfo;
import seer2.login.ServerInfo;
import seer2.login.MainServerLoginInfo;
import seer2.message.LoginInfo;
import seer2.parser.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;
import java.util.List;

//33, 0, 0, 0, 0, 0, 63, 19, 82, 5a, 2c, 20, ec, 6b, 2e, fe,
// 4c, 4e, e6, 6b, ed, cb, 6f, 84, 8e, 2e, ec, ad, ad, ac, ec, 6b, ae, ac,
// 4c, 4e, e6, 6b, ed, cb, 6f, 84, 8e, 2e, ec, ad, ad, ac, ec, 6b, e


//  异常部分; s-c-4-1611:[ae, ac, 4c, 4e, e6, 6b, ed, c3, cf, 84, 8e, 2e, 2c, 67, a8, ac, 4c, a2, ab, ac, ec, 84, e3, 6b, 8d, 2, 6a, 84, 8e, e5, e9, ad, 8d, ae, ec, 6b, ae, 65, 49, 4e, c6, a2, e8, cb, 2f, 4d, 8b, 2e, 6c, 64, a8, ac, 2c, a2, ab, ac, ac, 87, e3, 6b, ed, 1, 6a, 84, ae, e4, e9, ad, ed, 66, e9, 6b, ce, 66, 49, 4e, 66, a1, e8, cb, 8f, 4e, 8b, 2e, 8c, 38, aa, ac, 6c, fe, a9, ac, ec, db, e1, 6b, 2d, 5e, 68, 84, 6e, bb, eb, ad, 4d, af, ec, 6b, ae, ac, 4c, 4e, e6, 64, ed, cb, 6f, 8b, 8e, 2e, ec, a2, ad, ac, ec, 64, ae, ac, 4c, 41, e6, 6b, ed, c4, 6f, 84, 2e, 2e, ec, ad, cd, ac, ec, 6b, 8e, ac, 4c, 4e, 86, 6b, ed, cb, 4f, 84, 8e, 2e, 8c, ad, ad, ac, cc, 6b, ae, ac, 2c, 4e, e6, 6b, cd, cb, 6f, 84, ee, 2e, ec, ad, 8d, ac, ec, 6b, 4e, a2, 2c, 44, c6, e6, 7f, cb, 6f, 84, 8e, 2e, ec, ad, ad, ac, ec, 6b, ae, ac, 4c, 4e, e6, 6b, ed, cb, cf, f0, 53, 53, c6, 2d, a1, ec, a6, 6b, ae, ec, 6, 4e, e6, 8b, de, 8b, 2f, c4, b5, ee, d6, ad, 95, ac, ec, 6b, ae, ac, 4d, e, f9, 6b, ed, eb, 6f, 84, 8e, e, ec, ad, ad, ac, ec, 6b, ae, ac, 4c, 4e, e6, 6b, ed, 6b, 6f, 84, 8e, ee, 4a, a8, ad, 4c, 4a, 6e, ae, cc, eb, 4b, e6, cb, 4b, ce, 6f, 44, 29, 2b, ec, ad, ac, ac, ec, 2b, 48, a8, 4c, 2e, b1, 6e, ed, 4b, c9, 81, 8e, 2e, 4b, a8, ad, 8c, 4b, 6e, ae, ec, eb, 4b, e6, eb, 4a, ce, 6f, 24, 29, 2b, ec, 8d, ac, ac, ec, 6b, ae, ac, 4c, 4e, e9, 6b, ed, cb, 60, 84, 8e, 2e, e3, ad, ad, ac, e3, 6b, ae, ac, 43, 4e, e6, 6b, e2, cb, 6f, 84, 8e, 2e, ec, d, a6, 2c, e4, 6b, ae, ac, 4c, 4e, e6, 6b, ed, cb, 6f, 84, 8e, 2e, ec, ad, ad, ac, ec, 6b, ae, ac, 4c, 8e, 67, 24, 18, 81, ef, 88, ee, 7f, ec, ad, cd, fd, ec, 6b, 6e, 96, 6c, 3, a6, 52, 4d, f8, 4f, b8, 8e, 2e, ec, ad, ed, ad, 2c, 51, af, ac, 6c, 5e, e6, 6b, ed, cb, af, 9d, 8e, 2e, 6c, b2, ad, ac, ec, 6b, 2e, aa, ec, 4e, e6, 6b, 2d, 50, 68, 84, 4e, ea, e4, ad, d, 68, e4, 6b, 8e, 69, 44, 4e, 6, f7, ea, cb, 4f, 86, 8e, 2e, ac, 45, a9, ac, 4c, 87, aa, ac, 4c, 6d, e3, 6b, cd, e8, 6a, 84, ce, d, e9, ad, cd, 8f, e9, 6b, e, 37, 4b, 4e, 6, f0, ea, cb, 6f, 18, 89, 2e, cc, 31, aa, ac, ac, f7, a9, ac, 2c, d2, e1, 6b, 6d, 57, 68, 84, 2e, b2, eb, ad, 6d, 30, eb, 6b, 4e, 68, 44, 4e, e6, ae, e5, cb, f, 87, 8e, 2e, ec, ad, ad, ac, ec, 64, ae, ac, 4c, 41, e6, 6b, ed, c4, 6f, 84, 8e, 21, ec, ad, ad, a3, ec, 6b, ae, a3, 4c, 4e, 46, 6b, ed, cb, f, 84, 8e, 2e, 2c, ad, ad, ac, 8c, 6b, ae, ac, 8c, 4e, e6, 6b, 8d, cb, 6f, 84, 4e, 2e, ec, ad, cd, ac, ec, 6b, 6e, ac, 4c, 4e, 86, 6b, ed, cb, af, 84, 8e, 2e, 2c, a5, 6d, a8, ec, d8, 3c, ac, 4c, 4e, e6, 6b, ed, cb, 6f, 84, 8e, 2e, ec, ad, ad, ac, ec, 6a, ae, ac, c, ed, a3, 3a, a6, 4b, 63, a4, d7, 2e, ec, 8d, f4, ac, ec, 8b, 90, 2c, c, ce, db, eb, d0, eb, 2b, 84, 8e, 2e, ec, ad, af, ac, a8, 6a, ae, 4c, 4c, 4e, e6, 6b, ed, 2b, 70, 64, 91, 2e, ec, ad, ad, ac, ec, 6b, ae, c, 4c, 4e, e6, ab, da, c2, 6f, a4, b7, 27, ec, ad, 94, a5, ec, 8b, 96, a5, 4c, 8e, de, 62, ed, 4b, 6e, 84, 8e, 6e, db, a4, ad, cc, db, 62, ae, 2c, 7b, 47, e6, cb, da, c2, 6f, 64, b9, 27, ec, ad, 95, a5, ec, 4b, 96, a5, 4c, e, de, 62, ed, ab, 57, 8d, 8e, ae, d4, a4, ad, c, d4, 62, ae, ec, 75, 47, e6, cb, ed, cb, 6f, 84, 8e, 2e, ec, ad, a2, ac, ec, 6b, a1, ac, 4c, 4e, e9, 6b, ed, cb, 60, 84, 8e, 2e, e3, ad, ad, ac, e3, 6b, ae, c, 4c, 4e, e6, b, ed, cb, 6f, 44, 8e, 2e, ec, cd, ad, ac, ec, ab, ae, ac, 4c, e, e6, 6b, ed, 6b, 6f, 84, 8e, 4e, ec, ad, ad, 6c, ec, 6b, ae, cc, 4c, 4e, e6, 2b, ed, cb, 6f, 64, aa, ce, e5, ad, ad, ac, ec, 6b, ae, c, ab, 1b, e7, 6b, ed, cb, 6f, 84, 8e, 2e, ec, d, ad, ac, ec, 4b, 55, 0, 23, 65, 66, 67, 4d, 9a, 6f, 84, 2e, 7f, ec, ad, 4d, eb, ac, 29, 2e, 9e, c, 76, 46, 50, ed, cb, 6f, 84, ae, 2f, 8c, da, ad, ac, cc, 6b, ae, ac, 4c, 4e, 66, 74, 6d, d4, 6f, 84, 8e, 2e, ec, ad, 6d, ac, 4c, 6b, ae, ac, ac, 2, e0, 6b, ad, 86, 69, 84, ee, 52, e4, ad, ed, e0, ea, 6b, 2e, e1, 4a, 4e, 66, 6a, ed, cb, ef, cf, 88, 2e, 2c, e6, ab, ac, c, 20, a8, ac, 4c, 2, e0, 6b, cd, 87, 69, 84, e, 62, ea, ad, 6d, e0, ea, 6b, 8e, e1, 4a, 4e, 86, 26, eb, cb, 6f, c9, 88, 2e, cc, e0, ab, ac, 8c, 26, a8, ac, 2c, 4c, e6, 6b, ed, cb, 6f, 84, 8e, 21, ec, ad, ad, a3, ec, 6b, ae, a3, 4c, 4e, e6, 64, ed, cb, 6f, 8b, 8e, 2e, ec, a2, ad, ac, 4c, 6b, ae, ac, 2c, 4e, e6, 6b, 2d, cb, 6f, 84, ee, 2e, ec, ad, 8d, ac, ec, 6b, ce, ac, 4c, 4e, c6, 6b, ed, cb, f, 84, 8e, 2e, cc, ad, ad, ac, 8c, 6b, ae, ac, 6c, 4e, e6, 6b, ad, db, 6f, 83, 8e, 2e, ec, ad, ad, ac, ec, 6b, ae, ac, 4c, 4e, e6, 6b, ed, cb, 6f, 84, 4e, 53, ec, ad, 8d, ac, ec, 6b, 2e, 16, b6, b0, cf, eb, e1, ab, 25, 84, 8e, 4e, a6, ad, ad, 8c, aa, 6b, 95, 2c, 7e, 4e, db, eb, ac, cb, 6f, 84, 8e, 8e, ed, ed, a6, ac, ec, 4b, aa, a4, 4c, 4e, e6, eb, e9, 2b, 70, 84, 8e, 2e, ec, ad, ad, cc, f7, cb, ae, ac, 4c, 2e, 90, 6e, ed, b, 19, 81, 8e, ae, 99, a8, ad, ac, 9b, 6e, ae, cc, e0, 4b, e6, cb, ec, cb, 6f, a4, fb, 2b, ec, ed, d8, a9, ec, b, db, a9, 4c, ee, 93, 6e, ed, b, 1a, 81, 8e, ce, 99, a8, ad, ac, 9a, 6e, ae, 8c, 3a, 4b, e6, 2b, 9b, ce, 6f, 4, f8, 2b, ec, d, db, a9, ec, 8b, d8, a9, 4c, ae, 90, 6e, ed, 2b, 6c, 84, 8e, 2e, ec, ad, ad, ac, e3, 6b, ae, ac, 43, 4e, e6, 6b, e2, cb, 6f, 84, 81, 2e, ec, ad, a2, ac, ec, 6b, a1, ac, 4c, ee, e6, 6b, ed, 8b, 6f, 84, 8e, e, ec, ad, ad, ec, ec, 6b, ae, 8c, 4c, 4e, e6, b, ed, cb, 6f, a4, 8e, 2e, ec, cd, ad, ac, ec, 4b, ae, ac, 4c, 2e, e6, 6b, ed, eb, 6f, 84, 8e, e, fc, 8d, a8, ac, ec, 6b, ae, ac, 4c, 4e, e6, 6b, ed, cb, 6f, 84, 8e, 2e, ec, ad, ad, 8c, 91, 6b, ae, 6c, 4c, 4e, e6, 4b, 9f, bc, a8, 4d, 8e, 2e, ec, 2d, a1, ac, e8, 6b, ae, 6c, a1, 24, 2c, 42, ec, cb, 6f, 4, 82, 2e, e8, ad, ad, ec, 22, d9, 70, c5, 4c, 4e, e6, eb, e1, cb, 6b, 84, 8e, 6e, 9e, 3, 31, 86, ec, 6b, ae, ec, 4d, 4e, e6, 6b, ed, 2b, 72, 21, 2e, 4, ec, ad, ad, ec, ed, 6b, ae, ac, 4c, e, 53, 2, 45, e1, 6f, 84, 8e, 6e, ed, ad, ad, ac, ec, 6b, 64, a4, 4c, 6e, 50, 63, ed, cb, 6f, 84, 8e, e, ec, ad, ad, cc, ec, 6b, ae, ac, 4c, 4e, e6, 4b, d, 9c, 6f, a4, f3, 19, 5e, 51, da, dc, d0, bb, 1b, 90, 3d, be, da, 1d, fb, cb, 6f, 84, 8e, 2e, ec, ad, ad, ac, 8c, 6a, ae, ac, 6c, 4c, e6, 6b, d, 34, 90, 9b, 8e, 2e, ec, ad, ed, 6c, e8, 6b, ae, 6c, 48, 4e, e6, b, f6, cb, 6f, 84, 8e, 2e, ec, ad, ed, 8c, 94, 6b, ae, ac, 4c, 4e, e6, 6b, ed, cb, 6f, 84, 8e, 2e, ec, ad, ad, ac, ec, eb, ac, ac, 4c, 6e, a6, cb, ec, eb, af, 6d, 47, c7, 25, c8, ab, ac, ec, 6b, ae, ac, 4c, 4e, e6, 6b, ed, cb, 6f, 84, 8e, 2e, ec, ad, ad, ac, ec, 6b, ae, ac, 4c, 4e, a6, 40, ed, cb, 6f, 84, 8e, 2e, cc, ab, ad, ac, cc, b8, 9a, 8a, cc, 8, 20, 2e, 8b, ed, a8, 1, 8, e8, e9, 8a, aa, ac, ec, 6b, ae, ac, 4c, 4e, e6, 6b, ed, cb, f]
public class MainFrame {
    static int idd = 0;
    public static String[] SERVER_DESC = {"网通","电信"};
    public static String[] SERVER_HOSTS = {"118.89.150.23","118.89.150.43"};
    public static int[] SERVER_PORTS = {1863,1863};
    public static int[] SERVER_LOCAL_PORTS = {5000,5001};
    public static Map<String, ServerInfo> sessionMap = new HashMap<>();
    public static final byte[] _105 = {39, 2, 0, 0, 105, 0, -65, 113, -69, 15, 0, 0, 0, 0, 0, 0, 0, 0, 100, 0, 0, 0, 10, 0, 0, 0, 84, 0, 49, 49, 56, 46, 56, 57, 46, 49, 52, 57, 46, 49, 56, 57, 0, -35, 4, 5, 0, 0, 0, 0, 0, 0, 1, 0, 49, 50, 55, 46, 48, 46, 48, 46, 49, 0, 0, 0, 0, 0, 0, -35, -79, 4, 0, 0, 0, 0, 1, 0, 2, 0, 49, 49, 49, 46, 50, 51, 48, 46, 50, 51, 51, 46, 49, 51, 54, -35, -79,4, 0, 0, 0, 0, 0, 0, 94, 0, 49, 49, 56, 46, 56, 57, 46, 49, 52, 57, 46, 49, 56, 57, 0, -35, 14, 5, 0, 0, 0, 0, 0, 0, 93, 0, 49, 49, 56, 46, 56, 57, 46, 49, 52, 57, 46, 49, 56, 57, 0, -35, 13, 5, 0, 0, 0, 0, 0, 0, 91, 0, 49, 49, 56, 46, 56, 57, 46, 49, 52, 57, 46, 49, 56, 57, 0, -35, 11, 5, 0, 0, 0, 0, 0, 0, 90, 0, 49, 49, 56, 46, 56, 57, 46, 49, 52, 57, 46, 49, 56, 57, 0, -35, 10, 5, 0, 0, 0, 0, 0, 0, 88, 0, 49, 49, 56, 46, 56, 57, 46, 49, 52, 57, 46, 49, 56, 57, 0, -35, 8, 5, 0, 0, 0, 0, 0, 0, 85, 0, 49, 49, 56, 46, 56, 57, 46, 49, 52, 57, 46, 49, 56, 57, 0, -35, 5, 5, 0, 0, 0, 0, 0, 0, 82, 0, 49, 49, 56, 46, 56, 57, 46, 49, 52, 57, 46, 49, 56, 57, 0, -35, 2, 5, 0, 0, 0, 0, 0, 0, 0, 61, 0, 0, 0, -26, 93, 66, 9, 18, 35, 71, 19, -33, -49, 95, 12, -51, 36, 48, 23, -90, -60, 82, 22, -104, -46, 17, 3, 53, 126, -71, 5, 11, 73, 23, 8, 55, 10, 64, 5, -111, -63, -50, 15, -72, -64, 36, 4, -97, 110, 63, 7, 5, 14, 34, 26, -42, 42, 14, 6, -92, 9, -119, 24, 58, -118, -47, 22, 50, 64, 96, 2, 47, 28, 73, 17, -17, -15, -121, 15, 24, -76, 49, 27, -60, -116, 65, 27, 126, -16, -51, 5, -10, -69, 12, 0, 109, 67, 97, 29, 30, -81, 113, 29, -71, -86, -119, 29, -81, 37, -97, 29, 116, -123, 117, 29, 122, 104, -105, 31, -112, 21, -105, 31, 44, -7, -128, 31, 13, 121, 127, 31, 79, -51, -122, 31, 108, 29, 110, 31, -38, 123, -81, 31, -86, -70, -102, 31, 59, -22, 109, 31, -99, -119, -43, 22, -41, 24, -72, 31, 47, -121, 22, 21, 20, -126, 39, 33, 65, -87, -11, 30, -37, -111, -75, 36, 55, 119, 87, 22, -14, -123, 126, 31, 97, -17, -116, 31, 125, 110, 70, 18, -60, -93, -11, 11, 33, -117, 111, 22, -19, 65, 36, 20, -106, 22, 66, 26, 126, 22, 81, 24, -44, -47, -115, 2, -34, 67, -59, 53, 52, 126, -60, 48, -19, 84, -85, 53, 93, 113, -12, 47, -97, 12, 97, 30, -117, 25, -74, 53, 50, 120, 118, 21, -24, 90, -66, 0, 3, 0, 0, 0, 96, -67, -22, 8, -127, 86, 39, 27, 3, 81, 67, 39};
    static final byte[] _103 = {42, 0, 0, 0, 103, 0, -65, 113, -69, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 49, 3, -118, -85, 109, -27, -61, -78, 28, 65, 16, -13, 96, 8, -1, -128, 1, 0, 0, 0};
    static final byte[] _111 = {22, 0, 0, 0, 111, 0, -65, 113, -69, 15, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0};

    JFrame frame = new JFrame("Seer2Proxy");
    JPanel buttonPanel = new JPanel();
    JPanel inputPanel = new JPanel();

    JButton proxyButton = new JButton("proxy");
    JButton loginButton = new JButton("login");
    JButton saveButton = new JButton("saveFile");

    JTextField byteText = new JTextField();

    JTextArea textArea = new JTextArea();
    ScrollPane scrollPane = new ScrollPane();

    JTable table = new JTable();

    Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 20);

    static List<byte[]> list = new ArrayList<>();
    static Map<Integer,Integer> map = new HashMap<>();
    static OutputStream myOut;

    static Map<Integer,byte[]> loginSession = new HashMap<>();


    public MainFrame() {
        init();
        //this(1);
    }

    public MainFrame(int a) {


        frame.setSize(1000, 800);
        frame.setLocation(200, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textArea.setFont(font);

        loginButton.setFont(font);
        proxyButton.setFont(font);
        saveButton.setFont(font);

        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(loginButton);
        buttonPanel.add(proxyButton);
        buttonPanel.add(saveButton);

        byteText.setFont(font);
        byteText.setColumns(20);

        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(byteText);

        scrollPane.add(textArea);

        proxyButton.addActionListener((p)->{
            try {
                String ss [] = byteText.getText().split(" ");
                int s0 = Integer.parseInt(ss[1]);
                int s1 = Integer.parseInt(ss[1]);
                map.put(new Message(new ByteArray(list.get(s0))).sequenceIndex,s1);
                for (int i = 0; i < s1; i++) {
                    myOut.write(list.get(s0));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(inputPanel, BorderLayout.SOUTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);

        init();
    }

    private void init() {
      //  exec = Executors.newCachedThreadPool();
        new Thread(new LoginListener(0)).start();
        new Thread(new LoginListener(1)).start();
        new Thread(new ProxyListener()).start();
    }

    public static void main(String[] args) {
        new MainFrame();
    }

    class LoginListener implements Runnable {

        int type;

        LoginListener(int type){
            this.type=type;
        }

        @Override
        public void run() {
            try {
                ServerSocket loginSocket = new ServerSocket(SERVER_LOCAL_PORTS[type]);
                System.out.println("等待连接 "+SERVER_DESC[type] +" "+SERVER_LOCAL_PORTS[type]+" ---");
                while (true) {
                    Socket socket = loginSocket.accept();
                    new Thread(new LoginHandler(type,socket)).start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class LoginHandler implements Runnable {
        Socket loginSocket;
        int type;
        MainServerLoginInfo mainServerLoginInfo;

        LoginHandler(int type, Socket socket) {
            this.type=type;
            loginSocket = socket;
        }

        @Override
        public void run() {
            Socket socket2=null;
            try {
                socket2 = new Socket(SERVER_HOSTS[type], SERVER_PORTS[type]);
                InputStream in = loginSocket.getInputStream(), in2 = socket2.getInputStream();
                OutputStream out = socket2.getOutputStream(), out2 = loginSocket.getOutputStream();
                Message m;
                boolean flag = true;
                while (flag) {
                    if (in.available() != 0) {
                        byte[] bytes = new byte[in.available()];
                        in.read(bytes);
                        m = new Message(bytes,false);
                        System.out.println(m.parseInfo());
                        System.out.println("login-c:" + (++idd) + ":" + NumUtil.bytesToHex(bytes));
                        System.out.println();
                        out.write(bytes);
                        out.flush();
                    }
                    if (in2.available() != 0) {
                        byte[] bytes = new byte[in2.available()];
                        in2.read(bytes);
                        m = new Message(bytes,false);
                        System.out.println(m.parseInfo());
                        System.out.println("login-s:" + (++idd) + ":" + NumUtil.bytesToHex(bytes));
                        switch (bytes[4]) {
                            case 103:
                                mainServerLoginInfo = new MainServerLoginInfo(m.data);
                                System.out.println(mainServerLoginInfo);
                                break;
                            case 105:
                                var serverListInfo = new OnlineServerListInfo(m.data);
                                System.out.println(serverListInfo);
                                 sessionMap.put(Arrays.toString(mainServerLoginInfo.session),serverListInfo.serverInfos.get(1));
                                bytes = _105;
                                flag=false;
                                break;
                            case 111:
                                break;
                        }
                        System.out.println();
                        out2.write(bytes);
                        out2.flush();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try{
                    if(socket2!=null){
                        socket2.close();
                    }
                    System.out.println("已退出 ----");
                }catch (IOException e1){
                    e1.printStackTrace();
                }
            }
        }
    }

    class ProxyListener implements Runnable {
        @Override
        public void run() {
            try {
                ServerSocket proxySocket = new ServerSocket(1201);
                System.out.println("等待连接1201---");
                while (true) {
                    Socket socket = proxySocket.accept();
                    new Thread(new ProxyHandler(socket)).start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class ProxyHandler implements Runnable {
        private Socket proxySocket;

        ProxyHandler(Socket socket) {
            proxySocket = socket;
        }

        @Override
        public void run() {
            Socket socket2= null;
            try {
                InputStream in = proxySocket.getInputStream();
                OutputStream out = null;
                InputStream in2 = null;
                OutputStream out2 = proxySocket.getOutputStream();
                byte[] chunk = new byte[0];
                while (true) {
                    if (in.available() != 0) {
                        byte[] bytes = new byte[in.available()];
                        in.read(bytes);
                        list.add(bytes);
                        System.out.println("lid:"+(list.size()-1));
                        Message m = new Message(new ByteArray(bytes));

                        System.out.println(m.parseInfo());
                        System.out.println("proxy-c:" + (++idd) + ":" + NumUtil.bytesToHex(Arrays.copyOfRange(m.data.bytes, Message.HEAD_LENGTH, m.data.bytes.length)));
                        if (m.cmdId == 1101) {
                            System.out.println("(" + m.data.readUnsignedInt() + "," + m.data.readUnsignedInt() + ")");
                        }else if(m.cmdId==1017){
                            System.out.println(m.data.readUnsignedInt());
                        }else if(m.cmdId==1030) {
                            System.out.print(m.data.readUnsignedInt() + ":");
                            int c = m.data.readUnsignedInt();
                            for (int i = 0; i < c; i++) {
                                System.out.print(m.data.readUnsignedInt() + ",");
                            }
                        }else if(m.cmdId ==1001){
                            System.out.println(NumUtil.bytesToHex(bytes));
                            m.data.readUnsignedInt();
                            ServerInfo serverInfo = sessionMap.get( Arrays.toString(m.data.readBytes(16)));
                            //serverInfo=null;
                            if(serverInfo ==null){
                                throw new RuntimeException("session 不存在");
                            }else{
                                socket2 = new Socket(serverInfo.getServerIp(),1218);
                                out = socket2.getOutputStream();
                                in2 = socket2.getInputStream();
                                myOut=out2;
                                System.out.println(serverInfo.toString());
                            }
                        }else if(m.cmdId == 1241 || m.cmdId == 1055 || m.cmdId==1079){
                            System.out.println();
                            while(m.data.bytesAvailable()>=4){
                                System.out.print(m.data.readUnsignedInt()+" ");
                            }
                            System.out.println();
                        }
                        System.out.println();
                        if(socket2!=null){
                            out.write(bytes);
                            out.flush();
                        }
                    }
                    if (socket2!=null && in2.available() != 0) {
                        byte[] bytes = new byte[chunk.length+in2.available()];
                        if(chunk.length!=0)System.arraycopy(chunk,0,bytes,0,chunk.length);
                        in2.read(bytes,chunk.length,bytes.length-chunk.length);
                        int len = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getInt();
                        if(len>bytes.length){//未读完
                            chunk=bytes;
                        }else{//包含多个信息
                            int i=0;
                            while (len<=bytes.length-i) {
                                byte[] tmpBytes = new byte[len];
                                System.arraycopy(bytes,i,tmpBytes,0,len);
                                int cmdId = ByteBuffer.wrap(tmpBytes).order(ByteOrder.LITTLE_ENDIAN).position(4).getShort();
                                parseMsg(tmpBytes);
                                int seq = new Message(new ByteArray(tmpBytes)).sequenceIndex;
                                if(map.getOrDefault(seq,0)==0)out2.write(tmpBytes);
                                else map.put(seq,map.get(seq)-1);
                                System.out.println();
                                i+=len;
                                if(i+4<bytes.length)len = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).position(i).getInt();
                                else break;
                            }
                            chunk=new byte[bytes.length-i];
                            if(chunk.length!=0)System.arraycopy(bytes,i,chunk,0,chunk.length);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("连接中断---");
            }finally {
                try{
                    if(socket2!=null){
                        socket2.close();
                    }
                }catch (IOException e1){
                    e1.printStackTrace();
                }
            }
        }
        public static byte[] parseMsg(byte[] bytes){
            Message m = new Message(new ByteArray(bytes));
            System.out.println(m.parseInfo());
            if(m.cmdId==1001){
                LoginInfo.loginInfo.setFromOnline(m.data);
//                var b = m.data;
//                b.setPosition(36+Message.HEAD_LENGTH);
//                System.out.println("createDate:"+new Date(b.readUnsignedInt() * 1000L));
//                b.setPosition(36+Message.HEAD_LENGTH);
//                b.writeUnsignedInt(1587696661);
//                b.setPosition(bytes.length-25);
//                System.out.println("ip:"+b.readUTFBytes(20));
//                System.out.println("isYearVip:"+(b.readUnsignedInt()!=0));
//                b.setPosition(bytes.length-5);
//                b.writeUnsignedInt(1);

            }else if (m.cmdId == 15) {
                var b =m.data;
                int c = b.readUnsignedInt();
                for (int i = 0; i < c; i++) {
                    int t1 =b.readUnsignedInt(),
                            t2=b.readUnsignedInt(),
                            t3=b.readUnsignedInt();
                    System.out.println("left:"+t1+", petCatchTime:"+t2+", angerValue:"+t3+", position:"+(i+1));
                }
                c = b.readUnsignedInt();
                for (int i = 0; i < c; i++) {
                    int t1 =b.readUnsignedInt(),
                            t2=b.readUnsignedInt(),
                            t3=b.readUnsignedInt();
                    System.out.println("right:"+t1+", petCatchTime:"+t2+", angerValue:"+t3+", position:"+(i+1));
                }
            }else if (m.cmdId == 16) {
                var b =m.data;
                System.out.println(b.readUnsignedInt()+","+b.readUnsignedInt()+":"+b.readUnsignedInt());
                System.out.println(b.readUnsignedInt()+","+b.readUnsignedInt()+":"+b.readUnsignedInt());
            }else if (m.cmdId == 1100) {
                Date date = new Date(m.data.readUnsignedInt() * 1000L);
                System.out.println(date);
            } else if (m.cmdId == 1101) {
                System.out.println(m.data.readUnsignedInt() + ":(" + m.data.readUnsignedInt() + "," + m.data.readUnsignedInt() + ")");
            } else if (m.cmdId == 1103) {
                var b = m.data;
                var c = b.readUnsignedInt();
                for (int i = 0; i < c; i++) {
                    System.out.println("positionIndex:" + b.readUnsignedInt() + ", resourceId:" + b.readUnsignedInt() + ", level:" + b.readUnsignedShort());
                    b.readUnsignedInt();
                    b.readUnsignedByte();
                }
            } else if (MessageParser.get(m.cmdId)!=null) {
                MessageParser.get(m.cmdId).parse(m.data);
            }else{
                System.out.println("proxy-s:" + (++idd) + ":" + NumUtil.bytesToHex(Arrays.copyOfRange(m.data.bytes, Message.HEAD_LENGTH, m.data.bytes.length)));
            }
            return bytes;
        }
    }

}















