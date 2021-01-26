/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.chuyenDe;
import Helpers.Helper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Laptop88
 */
public class ChuyenDeDAO extends EduSysDao<chuyenDe, String> {

    List<chuyenDe> listCD = new ArrayList<>();
    Helper jdbc = new Helper();

    @Override
    public void insert(chuyenDe model) {

        String Insert = "insert into ChuyenDe (MaCD,TenCD,HocPhi,thoiLuong,logo,MoTa) values(?,?,?,?,?,?)";
        jdbc.executeUpdate(Insert, model.getMaCD(), model.getTenCD(), model.getHocPhi(), model.getThoiLuong(), model.getHinhAnh(), model.getMoTa());
    }

    @Override
    public void update(chuyenDe ten) {
        String Update = "update ChuyenDe set TenCD=?,HocPhi=?,thoiLuong=?,logo=?,MoTa=? where MaCD=?";
        jdbc.executeUpdate(Update, ten.getTenCD(), ten.getHocPhi(), ten.getThoiLuong(), ten.getHinhAnh(), ten.getMoTa(), ten.getMaCD());

    }

    @Override
    public void delete(String MaCD) {

        String delete = "{call Sp_XoaChuyenDe (?)}";
        jdbc.executeUpdate(delete, MaCD);
        

    }

    @Override
    public List<chuyenDe> selectAll() {
        try {
            String sql = "select * from ChuyenDe";
            ResultSet rs = jdbc.executeQuery(sql);
            while (rs.next()) {
                chuyenDe cd = new chuyenDe();
                cd.setMaCD(rs.getString(1));
                cd.setTenCD(rs.getString(2));
                cd.setHocPhi(rs.getInt(3));
                cd.setThoiLuong(rs.getInt(4));
                cd.setHinhAnh(rs.getString(5));
                cd.setMoTa(rs.getString(6));
                listCD.add(cd);

            }
            return listCD;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean selectByID(String MaCD) {
        boolean ktra = false;
        try {

            String sql = "select * from ChuyenDe where MaCD=?";
            ResultSet rs = jdbc.executeQuery(sql, MaCD);
            while (rs.next()) {
                ktra = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ktra;
    }

    @Override
    protected List<chuyenDe> selectBySql(String sql, Object... args) {

        try {

            ResultSet rs = jdbc.executeQuery(sql, args);
            while (rs.next()) {
                chuyenDe cd = new chuyenDe();
                cd.setMaCD(rs.getString(1));
                cd.setTenCD(rs.getString(2));
                cd.setHocPhi(rs.getInt(3));
                cd.setThoiLuong(rs.getInt(4));
                cd.setHinhAnh(rs.getString(5));
                cd.setMoTa(rs.getString(6));
                listCD.add(cd);

            }
            return listCD;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
