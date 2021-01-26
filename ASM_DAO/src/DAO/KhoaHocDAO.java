/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.KhoaHoc;
import Helpers.Helper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Laptop88
 */
public class KhoaHocDAO extends EduSysDao<KhoaHoc, String> {

    Helper jdbc = new Helper();
    List<KhoaHoc> listKH = new ArrayList<>();

    @Override
    public void insert(KhoaHoc ten) {
        String insertKH = "insert into KhoaHoc(MaCD,HocPhi,ThoiLuong,NgayKhaiGiang,GhiChu,MaNV,NgayTao) values(?,?,?,?,?,?,?)";
        jdbc.executeUpdate(insertKH, ten.getMaCD(), ten.getHocPhi(), ten.getThoiLuong(), ten.getNgayKhaiGiang(), ten.getGhiChu(), ten.getMaNV(), ten.getNgayTao());
    }

    @Override
    public void update(KhoaHoc ten) {
        String UpdateKH = "update KhoaHoc set MaCD=?, HocPhi=?,ThoiLuong=?,NgayKhaiGiang=?,GhiChu=? where MaKH=?";
        jdbc.executeUpdate(UpdateKH, ten.getMaCD(), ten.getHocPhi(), ten.getThoiLuong(), ten.getNgayKhaiGiang(), ten.getGhiChu(), ten.getMaKH());
    }

    @Override
    public void delete(String MaKH) {
        String deleteKH = "{call Sp_XoaKhoaHoc (?)}";
        jdbc.executeUpdate(deleteKH, MaKH);
    }

    @Override
    public List<KhoaHoc> selectAll() {
        try {
            String selectAllKH = "select * from KhoaHoc";
            ResultSet rs = jdbc.executeQuery(selectAllKH);
            while (rs.next()) {
                KhoaHoc kh = new KhoaHoc();
                kh.setMaKH(rs.getInt(1));
                kh.setMaCD(rs.getString(2));
                kh.setHocPhi(rs.getInt(3));
                kh.setThoiLuong(rs.getInt(4));
                kh.setNgayKhaiGiang(rs.getString(5));
                kh.setGhiChu(rs.getString(6));
                kh.setMaNV(rs.getString(7));
                kh.setNgayTao(rs.getString(8));
                listKH.add(kh);
            }
            return listKH;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public boolean selectByID(String MaKH) {
        boolean kt = false;
        try {
            String selectIdKH = "select * from KhoaHoc where MaKH=?";
            ResultSet rs = jdbc.executeQuery(selectIdKH, MaKH);
            while (rs.next()) {
                kt = true;
            }
        } catch (Exception e) {
        }
        return kt;
    }

    @Override
    protected List<KhoaHoc> selectBySql(String sql, Object... args) {
        try {
            ResultSet rs = jdbc.executeQuery(sql, args);
            while (rs.next()) {
                KhoaHoc kh = new KhoaHoc();
                kh.setMaKH(rs.getInt(1));
                kh.setMaCD(rs.getString(2));
                kh.setHocPhi(rs.getInt(3));
                kh.setThoiLuong(rs.getInt(4));
                kh.setNgayKhaiGiang(rs.getString(5));
                kh.setGhiChu(rs.getString(6));
                kh.setMaNV(rs.getString(7));
                kh.setNgayTao(rs.getString(8));
                listKH.add(kh);
            }
            return listKH;
        } catch (Exception e) {
        }
        return null;
    }

}
