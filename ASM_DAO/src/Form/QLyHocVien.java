/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import DAO.HocVienDAO;
import DAO.KhoaHocDAO;
import DAO.NhanVienDAO;
import Entity.HocVien;
import Entity.KhoaHoc;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Laptop88
 */
public class QLyHocVien extends javax.swing.JInternalFrame {

    DefaultTableModel modelHV;
    Connection cn;
    HocVienDAO dao = new HocVienDAO();
    KhoaHocDAO DAOKH = new KhoaHocDAO();
    List<KhoaHoc> listKH = new ArrayList<>();
    List<HocVien> listHV = new ArrayList<>();
    String Ma;
    boolean Vtro;

    public QLyHocVien(String MaNV, boolean VT) {
        initComponents();
        Ma = MaNV;
        Vtro = VT;
        cn = Helpers.Helper.KetNoi();
        listHV = new ArrayList<>();
//        String header[]= {"Mã Học viên","Mã Khóa Học","Mã NH","Điểm","Xóa"};
//        String data[][]= null;
//        modelHV = new DefaultTableModel(data, header);
        loadcomBoBoxNH();
        LoadcomBoBoxKH();
        loadALL();
        
        if (rdoAll.isSelected()) {
            loadALL();
        } else if (rdoNo.isSelected()) {
            LoadChuaDiem();
        } else if (rdoYes.isSelected()) {
            LoadCoDiem();
        }

    }

    public void loadALL() {
        try {
            modelHV = (DefaultTableModel) tblHocVien.getModel();
            modelHV.setRowCount(0);
            listHV = new HocVienDAO().selectAll();
            for (HocVien x : listHV) {
                Object[] row = new Object[]{x.getMaHV(), x.getMaKH(), x.getMaNH(), x.getDiemTB()};
                modelHV.addRow(row);
            }
            tblHocVien.getModel();
        } catch (Exception e) {
        }

    }

    public void LoadCoDiem() {
        try {
            modelHV = (DefaultTableModel) tblHocVien.getModel();
            modelHV.setRowCount(0);
            String sql = "select * from HocVien where DiemTB>=0 and DiemTB<=10";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] row = new Object[]{rs.getString(1), rs.getInt(2), rs.getString(3), rs.getDouble(4)};
                modelHV.addRow(row);
            }
            tblHocVien.getModel();
        } catch (Exception e) {
        }
    }

    public void LoadChuaDiem() {
        try {
            modelHV = (DefaultTableModel) tblHocVien.getModel();
            modelHV.setRowCount(0);
            String sql = "select * from HocVien where DiemTB<0 and DiemTB>10";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] row = new Object[]{rs.getString(1), rs.getInt(2), rs.getString(3), rs.getDouble(4)};
                modelHV.addRow(row);
            }
            tblHocVien.getModel();
        } catch (Exception e) {
        }
    }

    public void loadcomBoBoxNH() {
        try {
            String sql = "select MaNH,HoTen from NguoiHoc where maNH not in(select h.MaNH from HocVien v inner join NguoiHoc h on v.MaNH = h.MaNH)";
            PreparedStatement pstm = cn.prepareCall(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String maNH = rs.getString(1);
                String Ten = rs.getString(2);
                String tong = maNH + " - " + Ten;
                cboMaNH.addItem(maNH);
            }
            pstm.close();
            rs.close();
        } catch (Exception e) {
        }
    }

    public void LoadcomBoBoxKH() {
        try {
            listKH = new KhoaHocDAO().selectAll();
            for (KhoaHoc x : listKH) {
                cboMaKH.addItem(x.getMaKH() + "");
            }
        } catch (Exception e) {
        }
    }

    public void addHv() {
        try {
            if(txtDiem.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Không được để trống.");
                txtDiem.requestFocus();
                return;
            }
            if(Double.parseDouble(txtDiem.getText())<0 || Double.parseDouble(txtDiem.getText())>10){
                JOptionPane.showMessageDialog(this, "Điểm phải >=0 Hoặc Điểm <=10");
                txtDiem.requestFocus();
                return;
            }
            HocVien hv = new HocVien();
            hv.setMaKH(Integer.parseInt(cboMaKH.getSelectedItem().toString()));
            hv.setMaNH(cboMaNH.getSelectedItem().toString());
            hv.setDiemTB(Double.parseDouble(txtDiem.getText()));
            dao.insert(hv);
            if (rdoAll.isSelected()) {
                loadALL();
            } else if (rdoNo.isSelected()) {
                LoadChuaDiem();
            } else if (rdoYes.isSelected()) {
                LoadCoDiem();
            }
            JOptionPane.showMessageDialog(this, "Thêm thành công.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Điểm phải là số.");
            txtDiem.requestFocus();
            return;
        }
    }
    
    public void Update(){
        try {
            HocVien hv = new HocVien();
            int MAHV = Integer.parseInt(tblHocVien.getValueAt(0,0).toString());
            double DiemTB = Double.parseDouble(tblHocVien.getValueAt(0, 3).toString());
            hv.setMaHV(MAHV);
            
//            hv.setMaKH(Integer.parseInt(tblHocVien.getValueAt(0,1).toString()));
//            hv.setMaNH((String) tblHocVien.getValueAt(0,2));
            hv.setDiemTB(DiemTB);
//            modelHV.removeRow(0);
            dao.update(hv);
            if (rdoAll.isSelected()) {
                loadALL();
            } else if (rdoNo.isSelected()) {
                LoadChuaDiem();
            } else if (rdoYes.isSelected()) {
                LoadCoDiem();
            }
            JOptionPane.showMessageDialog(this, "Sửa thành công.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Sửa Thất bại .");
        }
    }

    public void Delete(){
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cboMaNH = new javax.swing.JComboBox<>();
        txtDiem = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        cboMaKH = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHocVien = new javax.swing.JTable();
        btnCapNhat = new javax.swing.JButton();
        rdoAll = new javax.swing.JRadioButton();
        rdoYes = new javax.swing.JRadioButton();
        rdoNo = new javax.swing.JRadioButton();

        setTitle("QUẢN LÝ HỌC VIÊN ");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("HỌC VIÊN TRONG KHÓA HỌC");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 204));
        jLabel2.setText("HỌC VIÊN KHÁC");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnThem.setText("THÊM");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(cboMaNH, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(cboMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cboMaNH)
                    .addComponent(txtDiem, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cboMaKH))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tblHocVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã HV", "Mã KH", "Mã NH", "Điểm TB", "Xóa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblHocVien);
        if (tblHocVien.getColumnModel().getColumnCount() > 0) {
            tblHocVien.getColumnModel().getColumn(3).setResizable(false);
            tblHocVien.getColumnModel().getColumn(4).setResizable(false);
        }

        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoAll);
        rdoAll.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoAll.setSelected(true);
        rdoAll.setText("Tất cả");
        rdoAll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoAllMouseClicked(evt);
            }
        });

        buttonGroup1.add(rdoYes);
        rdoYes.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoYes.setText("Đã nhập điểm");
        rdoYes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoYesMouseClicked(evt);
            }
        });

        buttonGroup1.add(rdoNo);
        rdoNo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoNo.setText("Chưa có điểm");
        rdoNo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoNoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 921, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(rdoAll, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(rdoNo)
                .addGap(43, 43, 43)
                .addComponent(rdoYes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoAll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdoYes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdoNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(670, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(4, 4, 4))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jLabel2)
                    .addContainerGap(598, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        Update();
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        addHv();
        LoadcomBoBoxKH();
        loadcomBoBoxNH();
    }//GEN-LAST:event_btnThemActionPerformed

    private void rdoAllMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoAllMouseClicked
        // TODO add your handling code here:
        loadALL();
    }//GEN-LAST:event_rdoAllMouseClicked

    private void rdoNoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoNoMouseClicked
        LoadChuaDiem();
    }//GEN-LAST:event_rdoNoMouseClicked

    private void rdoYesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoYesMouseClicked
        LoadCoDiem();
    }//GEN-LAST:event_rdoYesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboMaKH;
    private javax.swing.JComboBox<String> cboMaNH;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoAll;
    private javax.swing.JRadioButton rdoNo;
    private javax.swing.JRadioButton rdoYes;
    private javax.swing.JTable tblHocVien;
    private javax.swing.JTextField txtDiem;
    // End of variables declaration//GEN-END:variables
}
