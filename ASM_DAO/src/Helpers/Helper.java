/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Helper {

    public static Connection KetNoi() {
        try {
            String user = "sa";
            String Pass = "123";
            String url = "jdbc:sqlserver://localhost:1433;databaseName=ASM_DuAnMau";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection cn = DriverManager.getConnection(url, user, Pass);
            return cn;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /*
        Xây dựng PreparedStatement
        sql là câu lệnh sql có thể chứa tham số . Nó có thể là 1 lời gọi
        args là danh sách các giá trị được cung cấp cho các tham số trong câu lệnh 
     */
    public static PreparedStatement PreparedStatement(String sql, Object... args) throws SQLException {
//        KetNoi();
        PreparedStatement pstm;
        CallableStatement call;
        if (sql.trim().startsWith("{")) {
            call = KetNoi().prepareCall(sql);// dùng cho Proc
            for (int i = 0; i < args.length; i++) {
                call.setObject(i + 1, args[i]);
            }
            return call;
        } else {
            pstm = KetNoi().prepareStatement(sql); // câu lệnh SQL
            for (int i = 0; i < args.length; i++) {
                pstm.setObject(i + 1, args[i]);
            }
            return pstm;
        }
//        return null;
    }

    /*
         Thực hiện câu lệnh SQL  thao tác ( Insert , Update , Delete) hoặc thủ tục lưu thao tác 
     */
    public static void executeUpdate(String sql, Object... args) {
        try {
            PreparedStatement pstm = PreparedStatement(sql, args);
            try {
                pstm.executeUpdate();
            } finally {
                pstm.getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
        Thực hiện câu lệnh truuy vấn (Select) hoặc thủ tục lưu truy vấn dữ liệu
     */
    public static ResultSet executeQuery(String sql, Object... args) throws SQLException {
        try {
            PreparedStatement pstm = PreparedStatement(sql, args);
            return pstm.executeQuery();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
