/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helpers.Helper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Laptop88
 */
public class ThongKeDAO {

    Helper jdbc = new Helper();

    public List<Object[]> TongHop() throws SQLException {
        List<Object[]> listTh = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_DiemChuyenDe}";
                rs = jdbc.executeQuery(sql);
                while (rs.next()) {
                    Object[] row = new Object[]{rs.getString(1), rs.getInt(2), rs.getDouble(3), rs.getDouble(4), rs.getDouble(5)};
                    listTh.add(row);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listTh;
    }

    public List<Object[]> LuongNguoiHoc() throws SQLException {

        List<Object[]> listLngH = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "sp_LuongNguoiHoc";
                rs = jdbc.executeQuery(sql);
                while (rs.next()) {
                    Object[] row = new Object[]{rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getDate(4)};
                    listLngH.add(row);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
        }
        return listLngH;
    }

    public List<Object[]> DoanhThu(int Nam) throws SQLException {

        List<Object[]> listDT = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call Sp_DoanhThu (?)}";
                rs = jdbc.executeQuery(sql, Nam);
                while(rs.next()){
                    String tenCD = rs.getString(1);
                String MaCD = rs.getString(2);
                int SoHV = rs.getInt(3);
                int TongHP = rs.getInt(4);
                int min = rs.getInt(5);
                int max = rs.getInt(6);
                int TB = rs.getInt(7);

                listDT.add(new Object[]{tenCD, MaCD, SoHV, TongHP, min, max, TB});
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
        }
        return listDT;
    }

    public List<Object[]> BangDiem(int MaKH) throws SQLException {
        List<Object[]> listBD = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call Sp_BangDiem (?)}";
                rs = jdbc.executeQuery(sql, MaKH);
                while(rs.next()){
                    String MaNH = rs.getString(1);
                String Ten = rs.getString(2);
                double diem = rs.getDouble(3);
                String hocLuc = "";
                if (diem >= 0 && diem < 5) {
                    hocLuc = "Yếu";
                } else if (diem < 6.5) {
                    hocLuc = "TB";
                } else if (diem < 8) {
                    hocLuc = "Khá";
                } else if (diem < 9) {
                    hocLuc = "Giỏi";
                } else if (diem <= 10) {
                    hocLuc = "Xuất sắc";
                } else {
                    hocLuc = "Không xếp";
                }
                
                Object[] row = new Object[]{MaNH,Ten,diem,hocLuc};
                listBD.add(row);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
        }
        return listBD;
    }

}
