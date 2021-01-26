/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import DAO.ThongKeDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Laptop88
 */
public class TongHopThongKe extends javax.swing.JInternalFrame {

    Connection cn;
    ThongKeDAO dao = new ThongKeDAO();
    DefaultTableModel modelNguoiHoc;
    DefaultTableModel modelBangDiem, modelDoanhThu, modelTongHop;

    public TongHopThongKe(String a) throws SQLException {
        cn = Helpers.Helper.KetNoi();
        initComponents();
        BangNguoiHoc();
        BangDoanhThu();
        BangDiem();
        BangTongHop();
        //
        loadComBoboxKH();
        loadComBoBoxDoanhThu();
        //
        loadTableBangDiem();
        loadTableNguoiHoc();
        loadTableDiemChuyenDe();
        loadTableDoanhThu();
        if (a.equalsIgnoreCase("NguoiHoc")) {
            form.setSelectedComponent(NguoiHoc);
        }
        if (a.equalsIgnoreCase("ChuyenDe")) {
            form.setSelectedComponent(TongHop);
        }
        if (a.equalsIgnoreCase("BangDiem")) {
            form.setSelectedComponent(BangDiem);
        }  
        if(a.equalsIgnoreCase("DoanhThu")){
            form.setSelectedComponent(DoanhThu);
        }
    }

    public void BangNguoiHoc() {
        String header[] = {"Năm", "Số người học", "Ngày đầu tiên ĐK", "Ngày cuối cùng"};
        String data[][] = null;
        modelNguoiHoc = new DefaultTableModel(data, header);
        tblNguoiHoc.setModel(modelNguoiHoc);
    }

    public void BangDiem() {
        String header[] = {"Mã Người học", "Họ và tên", "Điểm ", "xếp loại"};
        String data[][] = null;
        modelBangDiem = new DefaultTableModel(data, header);
        tblBangDiem.setModel(modelBangDiem);
    }

    public void BangTongHop() {
        String header[] = {"Chuyên đề", "Tổng số HV", "Cao nhất", "Thấp nhất", "Điểm TB"};
        String data[][] = null;
        modelTongHop = new DefaultTableModel(data, header);
        tblTongHop.setModel(modelTongHop);
    }

    public void BangDoanhThu() {
        String header[] = {"chuyên đề", "KHóa Học", "Số HV", "Doanh thu", "Học phí Max", "Học phí Min", "Học phí TB"};
        String data[][] = null;
        modelDoanhThu = new DefaultTableModel(data, header);
        tblDoanhThu.setModel(modelDoanhThu);
    }

    public void loadComBoboxKH() {
        try {
            String sql = "select * from KhoaHoc";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cboKhoaHoc.addItem(rs.getString(1));
            }
            st.close();
            rs.close();
        } catch (Exception e) {
        }
    }
    //

    public void loadComBoBoxDoanhThu() {
        try {
            String sql = "select DISTINCT(year(NgayKhaiGiang)) from KhoaHoc";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                cboDoanhThu.addItem(rs.getString(1));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
        }
    }

    //
    public void loadTableBangDiem() throws SQLException {
        List<Object[]> listBD = new ArrayList<>();
            listBD = dao.BangDiem(Integer.parseInt((String) cboKhoaHoc.getSelectedItem()));
            modelBangDiem.setRowCount(0);
            for(Object[] x: listBD){
                modelBangDiem.addRow(x);
            }
    }

    // load lên bảng người học bằng cách gọi hàm thủ tục sp_LuongNguoiHoc
    public void loadTableNguoiHoc() throws SQLException {
            List<Object[]> listLNgH = new ArrayList<>();
            listLNgH = dao.LuongNguoiHoc();
            modelNguoiHoc.setRowCount(0);
            for(Object[] x: listLNgH){
                modelNguoiHoc.addRow(x);
            }
        
    }

    public void loadTableDiemChuyenDe() throws SQLException {
        List<Object[]> ListCd = new ArrayList<>();
        ListCd = dao.TongHop();
        modelTongHop.setRowCount(0);
        for(Object[] x: ListCd){
         modelTongHop.addRow(x);
        }
    }

    //
    public void loadTableDoanhThu() throws SQLException {
        List<Object[]> list = new ArrayList<>();
        list = dao.DoanhThu(Integer.parseInt((String) cboDoanhThu.getSelectedItem()));
        modelDoanhThu.setRowCount(0);
        for(Object[] x: list){
            modelDoanhThu.addRow(x);
        }
    }
    public void LoadComBoBox(){
        try {
            String sql = "select DISTINCT(year(NgayKhaiGiang)) from KhoaHoc";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                cboDoanhThu.setSelectedItem(rs.getString(1));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        form = new javax.swing.JTabbedPane();
        NguoiHoc = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblNguoiHoc = new javax.swing.JTable();
        BangDiem = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblBangDiem = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cboKhoaHoc = new javax.swing.JComboBox<>();
        TongHop = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTongHop = new javax.swing.JTable();
        DoanhThu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDoanhThu = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        cboDoanhThu = new javax.swing.JComboBox<>();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("TỔNG HỢP THỐNG KÊ");

        tblNguoiHoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tblNguoiHoc);

        javax.swing.GroupLayout NguoiHocLayout = new javax.swing.GroupLayout(NguoiHoc);
        NguoiHoc.setLayout(NguoiHocLayout);
        NguoiHocLayout.setHorizontalGroup(
            NguoiHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1060, Short.MAX_VALUE)
        );
        NguoiHocLayout.setVerticalGroup(
            NguoiHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
        );

        form.addTab("Người học", NguoiHoc);

        tblBangDiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tblBangDiem);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel2.setText("KHÓA HỌC:");

        cboKhoaHoc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboKhoaHocItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout BangDiemLayout = new javax.swing.GroupLayout(BangDiem);
        BangDiem.setLayout(BangDiemLayout);
        BangDiemLayout.setHorizontalGroup(
            BangDiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1060, Short.MAX_VALUE)
            .addGroup(BangDiemLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(cboKhoaHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 792, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BangDiemLayout.setVerticalGroup(
            BangDiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BangDiemLayout.createSequentialGroup()
                .addGroup(BangDiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboKhoaHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE))
        );

        form.addTab("Bảng điểm", BangDiem);

        tblTongHop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblTongHop);

        javax.swing.GroupLayout TongHopLayout = new javax.swing.GroupLayout(TongHop);
        TongHop.setLayout(TongHopLayout);
        TongHopLayout.setHorizontalGroup(
            TongHopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1060, Short.MAX_VALUE)
        );
        TongHopLayout.setVerticalGroup(
            TongHopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
        );

        form.addTab("Tổng hợp điểm ", TongHop);

        tblDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblDoanhThu);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel3.setText("NĂM:");

        cboDoanhThu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboDoanhThuItemStateChanged(evt);
            }
        });
        cboDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDoanhThuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DoanhThuLayout = new javax.swing.GroupLayout(DoanhThu);
        DoanhThu.setLayout(DoanhThuLayout);
        DoanhThuLayout.setHorizontalGroup(
            DoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1060, Short.MAX_VALUE)
            .addGroup(DoanhThuLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(cboDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 786, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        DoanhThuLayout.setVerticalGroup(
            DoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DoanhThuLayout.createSequentialGroup()
                .addGroup(DoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        form.addTab("Doanh thu", DoanhThu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(form)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(form))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboKhoaHocItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboKhoaHocItemStateChanged
        // TODO add your handling code here:
        try {
            loadTableBangDiem();
        } catch (Exception e) {
        }

    }//GEN-LAST:event_cboKhoaHocItemStateChanged

    private void cboDoanhThuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboDoanhThuItemStateChanged
        // TODO add your handling code here:
        try {
            loadTableDoanhThu();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cboDoanhThuItemStateChanged

    private void cboDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDoanhThuActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cboDoanhThuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BangDiem;
    private javax.swing.JPanel DoanhThu;
    private javax.swing.JPanel NguoiHoc;
    private javax.swing.JPanel TongHop;
    private javax.swing.JComboBox<String> cboDoanhThu;
    private javax.swing.JComboBox<String> cboKhoaHoc;
    private javax.swing.JTabbedPane form;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tblBangDiem;
    private javax.swing.JTable tblDoanhThu;
    private javax.swing.JTable tblNguoiHoc;
    private javax.swing.JTable tblTongHop;
    // End of variables declaration//GEN-END:variables
}
