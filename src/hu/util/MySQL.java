package hu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class MySQL {
    private static String driver="com.mysql.cj.jdbc.Driver";
    private static String url="jdbc:mysql://111.230.233.136:3306/hu";
    private static String user="s2";
    private static String password="dZ7bTfEDCXE22zmX";
    public static Connection conn ;
    public Statement stmt = null;
    public ResultSet rs = null;
    public PreparedStatement pstmt = null;
    public void init(){
        try{
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url,user,password);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void exec(String sqlSrting){
        try{
            stmt = conn.createStatement();
            if (stmt.execute(sqlSrting)) {
                rs = stmt.getResultSet();
            }
        }catch (SQLException ex){
            printE(ex);
        }

    }
    public void execPre(String preparedSql, Object[] param){
        try {
            pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    pstmt.setObject(i + 1, param[i]); // 为预编译sql设置参数
                }
            }
            rs = pstmt.executeQuery();
        }catch (SQLException e){
            printE(e);
        }
    }
    public void close(){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException sqlEx) { } // ignore

            rs = null;
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException sqlEx) { } // ignore

            stmt = null;
        }
    }
    public static void printE(SQLException ex){
        // handle any errors
        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("VendorError: " + ex.getErrorCode());
    }
    public static void main(String[] args) {

        MySQL mySQL = new MySQL();
        mySQL.init();
        mySQL.exec("show tables");
        System.out.println(mySQL.rs);
    }
}
