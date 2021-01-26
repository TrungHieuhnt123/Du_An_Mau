/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import DAO.ChuyenDeDAO;
import DAO.KhoaHocDAO;
import Entity.KhoaHoc;
import Entity.chuyenDe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class QLyKhoaHoc extends javax.swing.JInternalFrame {

    Connection cn;
    List<KhoaHoc> listKH = new ArrayList<>();
    DefaultTableModel modelKH;
    int index;
    String Ma = "";
    boolean vt;
    LocalDate date;
    KhoaHocDAO dao = new KhoaHocDAO();
    ChuyenDeDAO cd = new ChuyenDeDAO();
    List<chuyenDe> listcd = new ArrayList<>();
    public QLyKhoaHoc(String MaNV, boolean VT) {
        initComponents();
        cn = Helpers.Helper.KetNoi();
        Ma = MaNV;
        vt = VT;
        String header[] = {"Mã Khóa học", " Mã Chuyên đề", "Học phí", "Thời lượng", "Ngày khai giảng", "tạo bởi", "Ngày tạo"};
        String data[][] = null;
        modelKH = new DefaultTableModel(data, header);
        tblKhoaHoc.setModel(modelKH);
        loadTable();
        loadComBox();
        txtHocPhi.setEnabled(false);
        txtThoiLuong.setEnabled(false);
        txtMaKH.setEnabled(false);
        txtNguoiTao.setEnabled(false);
        if(vt==false){
            btnDelete.setEnabled(true);
        }else{
            btnDelete.setEnabled(false);
        }
        HienThi();

    }

    public void loadTable() {
        try {
           listKH = new KhoaHocDAO().selectAll();
           modelKH.setRowCount(0);
           for(KhoaHoc x: listKH){
            Object[] row = new Object[]{x.getMaKH(),x.getMaCD(),x.getHocPhi(),x.getThoiLuong(),x.getNgayKhaiGiang(),x.getMaNV(),x.getNgayTao()};
            modelKH.addRow(row);
           }
           
        } catch (Exception e) {
        }
    }

    public void loadComBox() {
        try {
            
            listcd = new ChuyenDeDAO().selectAll();
            for(chuyenDe x: listcd){
                cboChuyenDe.addItem(x.getMaCD());
            }
        } catch (Exception e) {
        }
    }

    public void ShowDaetail(int ind) {
        KhoaHoc kh = listKH.get(index);
        txtMaKH.setText(tblKhoaHoc.getValueAt(index, 0).toString());
        txtGhiChu.setText(kh.getGhiChu());
        txtHocPhi.setText(kh.getHocPhi() + "");
        txtNgayKhaiGiang.setText(tblKhoaHoc.getValueAt(index, 4).toString());
        txtNguoiTao.setText(kh.getMaNV());
        txtThoiLuong.setText(kh.getThoiLuong() + "");
        cboChuyenDe.setSelectedItem(kh.getMaCD());
    }

    // Thêm vào danh sách
    public void AddKhoaHoc() {
        try {
            
            if (txtHocPhi.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "KHông được để trống");
                txtHocPhi.requestFocus();
                return;
            }
            if (txtThoiLuong.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "KHông được để trống");
                txtThoiLuong.requestFocus();
                return;
            } else if (Integer.parseInt(txtThoiLuong.getText()) < 0) {
                JOptionPane.showMessageDialog(this, "Thời lượng phải là số.");
                txtThoiLuong.requestFocus();
                return;
            }
            if (txtNgayKhaiGiang.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "KHông được để trống");
                txtNgayKhaiGiang.requestFocus();
                return;
            } else {
                if (!txtNgayKhaiGiang.getText().matches("\\d*\\-[0,1]{0,1}\\d{1}-[0,1,2]{1}\\d{1}") ) {
                    JOptionPane.showMessageDialog(this, "Ngày không đúng định dạng");
                    txtNgayKhaiGiang.requestFocus();
                    return;
                }
            }

            try {
                KhoaHoc kh = new KhoaHoc();
                kh.setMaCD(cboChuyenDe.getSelectedItem().toString());
                for(chuyenDe x: listcd){
                    if(cboChuyenDe.getSelectedItem().toString().equalsIgnoreCase(x.getMaCD())){
                        kh.setHocPhi(x.getHocPhi());
                        kh.setThoiLuong(x.getThoiLuong());
                    }
                }
                
                
                kh.setNgayKhaiGiang(txtNgayKhaiGiang.getText());
                kh.setGhiChu(txtGhiChu.getText());
                kh.setMaNV(Ma);
                kh.setNgayTao(date.now().toString());
                dao.insert(kh);
                loadTable();
                JOptionPane.showMessageDialog(this, "THêm thành công");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "THêm Thất bại");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UpDate() {
        try {
            
            if (txtHocPhi.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "KHông được để trống");
                txtHocPhi.requestFocus();
                return;
            }
            if (txtThoiLuong.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "KHông được để trống");
                txtThoiLuong.requestFocus();
                return;
            } else if (Integer.parseInt(txtThoiLuong.getText()) < 0) {
                JOptionPane.showMessageDialog(this, "Thời lượng phải là số.");
                txtThoiLuong.requestFocus();
                return;
            }
            if (txtNgayKhaiGiang.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "KHông được để trống");
                txtNgayKhaiGiang.requestFocus();
                return;
            } else {
                if (!txtNgayKhaiGiang.getText().matches("\\d*\\-[0,1]{0,1}\\d{1}-[0,1,2]{1}\\d{1}")) {
                    JOptionPane.showMessageDialog(this, "Ngày không đúng định dạng");
                    txtNgayKhaiGiang.requestFocus();
                    return;
                }
            }
            
            try {
                KhoaHoc kh = new KhoaHoc();
                kh.setMaCD(cboChuyenDe.getSelectedItem().toString());
                for(chuyenDe x: listcd){
                    if(cboChuyenDe.getSelectedItem().toString().equalsIgnoreCase(x.getMaCD())){
                        kh.setHocPhi(x.getHocPhi());
                        kh.setThoiLuong(x.getThoiLuong());
                    }
                }
                kh.setNgayKhaiGiang(txtNgayKhaiGiang.getText());
                kh.setGhiChu(txtGhiChu.getText());
                kh.setMaKH(Integer.parseInt(txtMaKH.getText()));
                dao.update(kh);
                loadTable();
                JOptionPane.showMessageDialog(this, "Sửa thành công.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Sửa thất bại.");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sửa thất bại");
            e.printStackTrace();
        }
    }
    public void Reset(){
        index = -1;
        txtMaKH.setText("");
        txtGhiChu.setText("");
        txtHocPhi.setText("");
        txtNgayKhaiGiang.setText("");
        txtNguoiTao.setText("");
        txtThoiLuong.setText("");
        cboChuyenDe.setSelectedIndex(0);
    }
    public void Delete(){
        try {
            boolean ktra = false;
            ktra = dao.selectByID(txtMaKH.getText());
            if(!ktra){
                JOptionPane.showMessageDialog(this, "Chưa tồn tại mã khóa học: " + txtMaKH.getText());
                return;
            }
            dao.delete(txtMaKH.getText());
            loadTable();
            Reset();
            JOptionPane.showMessageDialog(this, "Xóa thành công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Không xóa được");
            e.printStackTrace();
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
        Pane = new javax.swing.JTabbedPane();
        CapNhat = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtThoiLuong = new javax.swing.JTextField();
        cboChuyenDe = new javax.swing.JComboBox<>();
        txtNguoiTao = new javax.swing.JTextField();
        txtHocPhi = new javax.swing.JTextField();
        txtMaKH = new javax.swing.JTextField();
        txtNgayKhaiGiang = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        btnPrev = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        DanhSach = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhoaHoc = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("QUẢN LÝ KHÓA HỌC");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Học phí");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Chuyên đề");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Ngày khai giảng");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Ghi chú");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Người tạo");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Thời lượng(giờ)");

        cboChuyenDe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboChuyenDeMouseClicked(evt);
            }
        });

        txtNguoiTao.setEditable(false);

        txtMaKH.setEditable(false);

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane1.setViewportView(txtGhiChu);

        btnPrev.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPrev.setText("<<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnFirst.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnFirst.setText("|<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnLast.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLast.setText(">|");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnNext.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnAdd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Mã Khóa học");

        javax.swing.GroupLayout CapNhatLayout = new javax.swing.GroupLayout(CapNhat);
        CapNhat.setLayout(CapNhatLayout);
        CapNhatLayout.setHorizontalGroup(
            CapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CapNhatLayout.createSequentialGroup()
                .addGroup(CapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CapNhatLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(CapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(CapNhatLayout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1))
                            .addGroup(CapNhatLayout.createSequentialGroup()
                                .addGroup(CapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(CapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CapNhatLayout.createSequentialGroup()
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cboChuyenDe, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(CapNhatLayout.createSequentialGroup()
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtHocPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(CapNhatLayout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtNguoiTao)))
                                .addGap(31, 31, 31)
                                .addGroup(CapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(CapNhatLayout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(CapNhatLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNgayKhaiGiang, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(CapNhatLayout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtThoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(34, 34, 34))))
                    .addGroup(CapNhatLayout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CapNhatLayout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        CapNhatLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8});

        CapNhatLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboChuyenDe, txtHocPhi, txtMaKH, txtNgayKhaiGiang, txtNguoiTao, txtThoiLuong});

        CapNhatLayout.setVerticalGroup(
            CapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CapNhatLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(CapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboChuyenDe, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(CapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtThoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(CapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHocPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayKhaiGiang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(CapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(CapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(CapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        CapNhatLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboChuyenDe, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, txtHocPhi, txtMaKH, txtNgayKhaiGiang, txtNguoiTao, txtThoiLuong});

        Pane.addTab("CẬP NHẬT", CapNhat);

        tblKhoaHoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblKhoaHoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhoaHocMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKhoaHoc);

        javax.swing.GroupLayout DanhSachLayout = new javax.swing.GroupLayout(DanhSach);
        DanhSach.setLayout(DanhSachLayout);
        DanhSachLayout.setHorizontalGroup(
            DanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1028, Short.MAX_VALUE)
        );
        DanhSachLayout.setVerticalGroup(
            DanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
        );

        Pane.addTab("DANH SÁCH", DanhSach);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(Pane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(Pane))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        Reset();

    }//GEN-LAST:event_btnResetActionPerformed

    private void tblKhoaHocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhoaHocMouseClicked
        // TODO add your handling code here:
        if (listKH.size() > 0) {
            index = tblKhoaHoc.getSelectedRow();
            Pane.setSelectedComponent(CapNhat);
            ShowDaetail(index);
        }
    }//GEN-LAST:event_tblKhoaHocMouseClicked

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        try {
            if (listKH.size() > 0) {
                index--;
                ShowDaetail(index);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        try {
            if (listKH.size() > 0) {
                index = 0;
                ShowDaetail(index);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        try {
            if (listKH.size() > 0) {
                index = listKH.size() - 1;
                ShowDaetail(index);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        try {
            if (listKH.size() > 0 && index < listKH.size()) {
                index++;
                ShowDaetail(index);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        AddKhoaHoc();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        UpDate();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

        Delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void cboChuyenDeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboChuyenDeMouseClicked
        // TODO add your handling code here:
        HienThi();
    }//GEN-LAST:event_cboChuyenDeMouseClicked
    public void HienThi(){
        for(chuyenDe x: listcd){
                    if(cboChuyenDe.getSelectedItem().toString().equalsIgnoreCase(x.getMaCD())){
                        txtHocPhi.setText(x.getHocPhi()+"");
                        txtThoiLuong.setText(x.getThoiLuong()+"");
                    }
                }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CapNhat;
    private javax.swing.JPanel DanhSach;
    private javax.swing.JTabbedPane Pane;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cboChuyenDe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblKhoaHoc;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtHocPhi;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtNgayKhaiGiang;
    private javax.swing.JTextField txtNguoiTao;
    private javax.swing.JTextField txtThoiLuong;
    // End of variables declaration//GEN-END:variables
}
