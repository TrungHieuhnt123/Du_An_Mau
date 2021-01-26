/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Entity.HocVien;
import Helpers.Helper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Laptop88
 */
public class HocVienDAO extends EduSysDao<HocVien, String>{

    List<HocVien> listHv = new ArrayList<>();
    Helper jdbc = new Helper();
    @Override
    public void insert(HocVien ten) {
        String insertHV = "insert into HocVien(MaKH,MaNH,DiemTB) values(?,?,?)";
        jdbc.executeUpdate(insertHV,ten.getMaKH(),ten.getMaNH(),ten.getDiemTB());
    }

    @Override
    public void update(HocVien ten) {
        String Update = "update HocVien set DiemTB=? where MaHV=?";
        jdbc.executeUpdate(Update, ten.getDiemTB(),ten.getMaHV());
    }

    @Override
    public void delete(String MaHV) {
        String DeleteHV = "delete HocVien where MaHV=?";
        jdbc.executeUpdate(DeleteHV, MaHV);
    }

    @Override
    public List<HocVien> selectAll() {
        try {
            String sql = "select * from HocVien";
            ResultSet rs = jdbc.executeQuery(sql);
            while(rs.next()){
                HocVien hv = new HocVien();
                hv.setMaHV(rs.getInt(1));
                hv.setMaKH(rs.getInt(2));
                hv.setMaNH(rs.getString(3));
                hv.setDiemTB(rs.getDouble(4));
                listHv.add(hv);
            }
            return listHv;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public boolean selectByID(String MaKH) {
        boolean kt = false;
        try {
            String sql = "select * from HocVien where MaHV=?";
            ResultSet rs = jdbc.executeQuery(sql, MaKH);
            while(rs.next()){
             kt = true;   
            }
        } catch (Exception e) {
        }
        return kt;
    }

    @Override
    protected List<HocVien> selectBySql(String sql, Object... args) {
        try {
            ResultSet rs = jdbc.executeQuery(sql, args);
            while(rs.next()){
                HocVien hv = new HocVien();
                hv.setMaHV(rs.getInt(1));
                hv.setMaKH(rs.getInt(2));
                hv.setMaNH(rs.getString(3));
                hv.setDiemTB(rs.getDouble(4));
                listHv.add(hv); 
            }
            return listHv;
        } catch (Exception e) {
        }
        return null;
    }



}
