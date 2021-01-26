/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Entity.KhoaHoc;
import Entity.NguoiHoc;
import Helpers.Helper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author Laptop88
 */
public class NguoiHocDAO extends EduSysDao<NguoiHoc, String>{

    Helper jdbc = new Helper();
    List<NguoiHoc> listNH = new ArrayList<>();
    @Override
    public void insert(NguoiHoc ten) {
        String sql =  "insert into NguoiHoc(MaNH,HoTen,NgSinh,gt,SDT,Email,GhiChu,MaNV,NgayDK) values(?,?,?,?,?,?,?,?,?)";
        jdbc.executeUpdate(sql, ten.getMaNH(),ten.getHoTen(),ten.getNgaySinh(),ten.getGt(),ten.getSdt(),ten.getEmail(),ten.getGhiChu(),ten.getMaNV(),ten.getNgayDK());
    }

    @Override
    public void update(NguoiHoc ten) {
        String updateNH = "update NguoiHoc set HoTen=?,NgSinh=?,gt=?,SDT=?,Email=?,GhiChu=? where MaNH=?";
        jdbc.executeUpdate(updateNH, ten.getHoTen(),ten.getNgaySinh(),ten.getGt(),ten.getSdt(),ten.getEmail(),ten.getGhiChu(),ten.getMaNH());
    }

    @Override
    public void delete(String MaNH) {
        String DeleteNH = "{call Sp_XoaNguoiHoc (?)}";
        jdbc.executeUpdate(DeleteNH, MaNH);
    }

    @Override
    public List<NguoiHoc> selectAll() {
        try {
            String sql = "select * from NguoiHoc";
            ResultSet rs = jdbc.executeQuery(sql);
            while(rs.next()){
                NguoiHoc nh = new NguoiHoc();
                nh.setMaNH(rs.getString(1));
                nh.setHoTen(rs.getString(2));
                nh.setNgaySinh(rs.getString(3));
                nh.setGt(rs.getString(4));
                nh.setSdt(rs.getString(5));
                nh.setEmail(rs.getString(6));
                nh.setGhiChu(rs.getString(7));
                nh.setMaNV(rs.getString(8));
                nh.setNgayDK(rs.getString(9));
                listNH.add(nh);
            }
            return listNH;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi SelectAll");
        }
        return null;
    }

    @Override
    public boolean selectByID(String MaNH) {
        boolean kt = false;
        try {
            String sql = "select * from NguoiHoc where MaNH=?";
            ResultSet rs = jdbc.executeQuery(sql, MaNH);
            while(rs.next()){
             kt = true;   
            }
        } catch (Exception e) {
        }
        return kt;
    }

    @Override
    protected List<NguoiHoc> selectBySql(String sql, Object... args) {
        try {
            ResultSet rs = jdbc.executeQuery(sql, args);
            while(rs.next()){
             NguoiHoc nh = new NguoiHoc();
             nh.setMaNH(rs.getString(1));
             nh.setHoTen(rs.getString(2));
             nh.setNgaySinh(rs.getString(3));
             nh.setGt(rs.getString(4));
             nh.setSdt(rs.getString(5));
             nh.setEmail(rs.getString(6));
             nh.setGhiChu(rs.getString(7));
             nh.setMaNV(rs.getString(8));
             nh.setNgayDK(rs.getString(9));
             
             listNH.add(nh);
            }
            return listNH;
        } catch (Exception e) {
        }
        return null;
    }
    
}
