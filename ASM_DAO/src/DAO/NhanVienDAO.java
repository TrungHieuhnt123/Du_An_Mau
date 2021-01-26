/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.NhanVien;
import Helpers.Helper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Laptop88
 */
public class NhanVienDAO extends EduSysDao<NhanVien, String> {

    List<NhanVien> listNV = new ArrayList<>();
    Helper jdbc = new Helper();

    @Override
    public void insert(NhanVien ten) {
        try {
            String insertNV = "insert into NhanVien(MaNV,MatKhau,HoTen,VaiTro) values(?,?,?,?)";
        jdbc.executeUpdate(insertNV, ten.getMaNV(), ten.getMatKhau(), ten.getHoTen(), ten.isVaiTro());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi thêm");
        }
    }

    @Override
    public void update(NhanVien ten) {
        String UpdateNV = "update NhanVien set MatKhau=?, HoTen=? ,VaiTro=? where MaNV=?";
        jdbc.executeUpdate(UpdateNV, ten.getMatKhau(), ten.getHoTen(), ten.isVaiTro(),ten.getMaNV());
    }

    @Override
    public void delete(String MaNV) {
        String deleteNV = "{call Sp_XoaNhanVien (?) }";
        jdbc.executeUpdate(deleteNV, MaNV);
    }

    @Override
    public List<NhanVien> selectAll() {
        try {
            String selectAllNV = "select * from NhanVien";;
            ResultSet rs = jdbc.executeQuery(selectAllNV);
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString(1));
                nv.setMatKhau(rs.getString(2));
                nv.setHoTen(rs.getString(3));
                nv.setVaiTro(rs.getBoolean(4));
                listNV.add(nv);
            }
            return listNV;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi Selectall");
        }
        return null;
    }

    @Override
    public boolean selectByID(String MaNV) {
        boolean ktra = false;
        try {

            String sql = "select * from NhanVien where MaNV=?";
            ResultSet rs = jdbc.executeQuery(sql, MaNV);
            while (rs.next()) {
                ktra = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi SelectByID");
        }
        return ktra;
    }

    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
        try {

            ResultSet rs = jdbc.executeQuery(sql, args);
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString(1));
                nv.setMatKhau(rs.getString(2));
                nv.setHoTen(rs.getString(3));
                nv.setVaiTro(rs.getBoolean(4));
                listNV.add(nv);
            }
            return listNV;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi SelectBySQL");
        }
        return null;
    }
    public void doiMk(NhanVien MaNV){
        String doimk = "update NhanVien set MatKhau=? where MaNV=?";
        jdbc.executeUpdate(doimk, MaNV);
    }

}
