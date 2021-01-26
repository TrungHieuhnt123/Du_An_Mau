/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Laptop88
 */
public class FormChinh extends javax.swing.JFrame {

    Connection cn;
    QLyChuyenDe CD;
    QLNV nv;
    Timer t;
    String MaNhanVien="";
    boolean VTro;
    public FormChinh(String MaNV,boolean VT) {
        initComponents();
        setLocationRelativeTo(null);
        cn = Helpers.Helper.KetNoi();
        Hinh h = new Hinh();
        h.setSize(Dtop1.getWidth() + 2, Dtop1.getHeight() + 22);
        h.setLocation(-1, -21);
        h.setVisible(true);
        Dtop1.add(h);
        DongHo();
        MaNhanVien=MaNV;
        VTro = VT;
    }

    private FormChinh() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void CloseForm() {
        for (JInternalFrame form : Dtop1.getAllFrames()) {
            form.dispose();
        }
    }

    // Đồng hồ
    public void DongHo() {
        t = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                SimpleDateFormat date = new SimpleDateFormat();
                Date now = new Date();
                date.applyPattern("hh:mm:ss aa");
                String time = date.format(now);
                lblThoiGian.setText(time);
            }
        });
        t.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator3 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jToolBar2 = new javax.swing.JToolBar();
        btnDangXuat = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        btnKetThuc = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnQLCD = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        btnQLNH = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        btnQLKH = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnHdan = new javax.swing.JButton();
        Dtop1 = new javax.swing.JDesktopPane();
        jPanel2 = new javax.swing.JPanel();
        lblThoiGian = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jRadioButtonMenuItem5 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem6 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem7 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem8 = new javax.swing.JRadioButtonMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        NguoiHoc = new javax.swing.JRadioButtonMenuItem();
        DiemTheoKhoaHoc = new javax.swing.JRadioButtonMenuItem();
        TongHop = new javax.swing.JRadioButtonMenuItem();
        DoanhThu = new javax.swing.JRadioButtonMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jRadioButtonMenuItem13 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem14 = new javax.swing.JRadioButtonMenuItem();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hệ thống quản lý đào tạo");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jToolBar2.setRollover(true);

        btnDangXuat.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Exit.png"))); // NOI18N
        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.setFocusable(false);
        btnDangXuat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDangXuat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });
        jToolBar2.add(btnDangXuat);
        jToolBar2.add(jSeparator5);

        btnKetThuc.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnKetThuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Stop.png"))); // NOI18N
        btnKetThuc.setText("Kết thúc");
        btnKetThuc.setFocusable(false);
        btnKetThuc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnKetThuc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKetThucActionPerformed(evt);
            }
        });
        jToolBar2.add(btnKetThuc);
        jToolBar2.add(jSeparator1);

        btnQLCD.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnQLCD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Lists.png"))); // NOI18N
        btnQLCD.setText("Chuyên đề");
        btnQLCD.setFocusable(false);
        btnQLCD.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnQLCD.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnQLCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLCDActionPerformed(evt);
            }
        });
        jToolBar2.add(btnQLCD);
        jToolBar2.add(jSeparator4);

        btnQLNH.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnQLNH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Conference.png"))); // NOI18N
        btnQLNH.setText("Người học");
        btnQLNH.setFocusable(false);
        btnQLNH.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnQLNH.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnQLNH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLNHActionPerformed(evt);
            }
        });
        jToolBar2.add(btnQLNH);
        jToolBar2.add(jSeparator6);

        btnQLKH.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnQLKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Certificate.png"))); // NOI18N
        btnQLKH.setText("Khóa Học");
        btnQLKH.setFocusable(false);
        btnQLKH.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnQLKH.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnQLKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLKHActionPerformed(evt);
            }
        });
        jToolBar2.add(btnQLKH);
        jToolBar2.add(jSeparator2);

        btnHdan.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnHdan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Globe.png"))); // NOI18N
        btnHdan.setText("Hướng dẫn");
        btnHdan.setFocusable(false);
        btnHdan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHdan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(btnHdan);

        Dtop1.setBackground(new java.awt.Color(204, 204, 204));
        Dtop1.setRequestFocusEnabled(false);

        javax.swing.GroupLayout Dtop1Layout = new javax.swing.GroupLayout(Dtop1);
        Dtop1.setLayout(Dtop1Layout);
        Dtop1Layout.setHorizontalGroup(
            Dtop1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1096, Short.MAX_VALUE)
        );
        Dtop1Layout.setVerticalGroup(
            Dtop1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 601, Short.MAX_VALUE)
        );

        lblThoiGian.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lblThoiGian.setForeground(new java.awt.Color(204, 0, 0));
        lblThoiGian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThoiGian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Alarm.png"))); // NOI18N
        lblThoiGian.setText("10:15:20 AM");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Unknown person.png"))); // NOI18N
        jLabel1.setText("Hệ quản lý đào tạo");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblThoiGian, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                .addContainerGap())
        );

        jMenuBar1.setBackground(new java.awt.Color(204, 204, 204));

        jMenu1.setText("Hệ thống");

        jRadioButtonMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jRadioButtonMenuItem5.setSelected(true);
        jRadioButtonMenuItem5.setText("Đăng nhập");
        jRadioButtonMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Key.png"))); // NOI18N
        jMenu1.add(jRadioButtonMenuItem5);

        jRadioButtonMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jRadioButtonMenuItem6.setSelected(true);
        jRadioButtonMenuItem6.setText("Đăng xuất");
        jRadioButtonMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Exit.png"))); // NOI18N
        jRadioButtonMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem6);

        jRadioButtonMenuItem7.setSelected(true);
        jRadioButtonMenuItem7.setText("Đổi mật khẩu");
        jRadioButtonMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Refresh.png"))); // NOI18N
        jRadioButtonMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem7);

        jRadioButtonMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F10, 0));
        jRadioButtonMenuItem8.setSelected(true);
        jRadioButtonMenuItem8.setText("Kết thúc");
        jRadioButtonMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Stop.png"))); // NOI18N
        jRadioButtonMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem8);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Quản lý");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Conference.png"))); // NOI18N
        jMenuItem1.setText("Quản lý người học");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Clien list.png"))); // NOI18N
        jMenuItem2.setText("Quản lý học viên");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Lists.png"))); // NOI18N
        jMenuItem3.setText("Quản lý chuyên đề");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Certificate.png"))); // NOI18N
        jMenuItem4.setText("Quản lý khóa học");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_5, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Boy.png"))); // NOI18N
        jMenuItem5.setText("Quản lý nhân viên");
        jMenuItem5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem5MouseClicked(evt);
            }
        });
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Thống kê");

        NguoiHoc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.SHIFT_MASK));
        NguoiHoc.setSelected(true);
        NguoiHoc.setText("Người học từng năm");
        NguoiHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Clien list.png"))); // NOI18N
        NguoiHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NguoiHocActionPerformed(evt);
            }
        });
        jMenu3.add(NguoiHoc);

        DiemTheoKhoaHoc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.SHIFT_MASK));
        DiemTheoKhoaHoc.setSelected(true);
        DiemTheoKhoaHoc.setText("Điểm theo từng khóa học");
        DiemTheoKhoaHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Statistics.png"))); // NOI18N
        DiemTheoKhoaHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DiemTheoKhoaHocActionPerformed(evt);
            }
        });
        jMenu3.add(DiemTheoKhoaHoc);

        TongHop.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.SHIFT_MASK));
        TongHop.setSelected(true);
        TongHop.setText("Điểm chuyên đề");
        TongHop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Card file.png"))); // NOI18N
        TongHop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TongHopActionPerformed(evt);
            }
        });
        jMenu3.add(TongHop);

        DoanhThu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.SHIFT_MASK));
        DoanhThu.setSelected(true);
        DoanhThu.setText("Doanh thu");
        DoanhThu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Dollar.png"))); // NOI18N
        DoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DoanhThuActionPerformed(evt);
            }
        });
        jMenu3.add(DoanhThu);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Trợ giúp");

        jRadioButtonMenuItem13.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jRadioButtonMenuItem13.setSelected(true);
        jRadioButtonMenuItem13.setText("Hướng dẫn sử dụng");
        jRadioButtonMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Globe.png"))); // NOI18N
        jMenu4.add(jRadioButtonMenuItem13);

        jRadioButtonMenuItem14.setSelected(true);
        jRadioButtonMenuItem14.setText("Giới thiệu sản phẩm");
        jRadioButtonMenuItem14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Brick house.png"))); // NOI18N
        jMenu4.add(jRadioButtonMenuItem14);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 1096, Short.MAX_VALUE)
            .addComponent(Dtop1)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Dtop1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new DangNhap().setVisible(true);
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void TongHopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TongHopActionPerformed
        // TODO add your handling code here:
        CloseForm();
        TongHopThongKe Tk = null;
        try {
            Tk = new TongHopThongKe("ChuyenDe");
        } catch (SQLException ex) {
            Logger.getLogger(FormChinh.class.getName()).log(Level.SEVERE, null, ex);
        }
        Tk.setSize(Dtop1.getWidth() + 2, Dtop1.getHeight() + 22);
        Tk.setLocation(-1, -21);
        Tk.setVisible(true);
        Dtop1.add(Tk);
    }//GEN-LAST:event_TongHopActionPerformed

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked

         }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        try {
            CloseForm();
        
        if(VTro==true){
             JOptionPane.showMessageDialog(this, "Nhân viên không thế vào Form này.");  
            nv.setVisible(false);
            Hinh h = new Hinh();
            Dtop1.add(h);
            return;
        }else if(VTro==false){
        nv = new QLNV(MaNhanVien,VTro);
        nv.setSize(Dtop1.getWidth() + 2, Dtop1.getHeight() + 21);
        nv.setLocation(-1, -21);
           nv.setVisible(true);
           Dtop1.add(nv);
        }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void btnKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKetThucActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnKetThucActionPerformed

    private void jRadioButtonMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem8ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jRadioButtonMenuItem8ActionPerformed

    private void jRadioButtonMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem6ActionPerformed
        // TODO add your handling code here:
        CloseForm();
        this.dispose();
        new DangNhap().setVisible(true);
    }//GEN-LAST:event_jRadioButtonMenuItem6ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        CloseForm();
        CD = new QLyChuyenDe(MaNhanVien,VTro);
        CD.setSize(Dtop1.getWidth() + 2, Dtop1.getHeight() + 22);
        CD.setLocation(-1, -21);
        CD.setVisible(true);
        Dtop1.add(CD);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void btnQLCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLCDActionPerformed
        // TODO add your handling code here:
        CloseForm();
        QLyChuyenDe CD = new QLyChuyenDe(MaNhanVien,VTro);
        CD.setSize(Dtop1.getWidth() + 1, Dtop1.getHeight() + 23);
        CD.setLocation(-1, -21);
        CD.setVisible(true);
        Dtop1.add(CD);

    }//GEN-LAST:event_btnQLCDActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        CloseForm();
        QuanLyNguoiHoc NH = new QuanLyNguoiHoc(MaNhanVien,VTro);
        NH.setSize(Dtop1.getWidth() + 1, Dtop1.getHeight() + 23);
        NH.setLocation(-1, -21);
        NH.setVisible(true);
        Dtop1.add(NH);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        CloseForm();
        QLyHocVien HVien = new QLyHocVien(MaNhanVien,VTro);
        HVien.setSize(Dtop1.getWidth() + 2, Dtop1.getHeight() + 22);
        HVien.setLocation(-1, -21);
        HVien.setVisible(true);
        Dtop1.add(HVien);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        CloseForm();
        QLyKhoaHoc KhHoc = new QLyKhoaHoc(MaNhanVien,VTro);
        KhHoc.setSize(Dtop1.getWidth() + 2, Dtop1.getHeight() + 22);
        KhHoc.setLocation(-1, -21);
        KhHoc.setVisible(true);
        Dtop1.add(KhHoc);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void btnQLNHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLNHActionPerformed
        CloseForm();
        QuanLyNguoiHoc NH = new QuanLyNguoiHoc(MaNhanVien,VTro);
        NH.setSize(Dtop1.getWidth() + 2, Dtop1.getHeight() + 22);
        NH.setLocation(-1, -21);
        NH.setVisible(true);
        Dtop1.add(NH);
    }//GEN-LAST:event_btnQLNHActionPerformed

    private void btnQLKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLKHActionPerformed
        // TODO add your handling code here:
        CloseForm();
        QLyKhoaHoc KH = new QLyKhoaHoc(MaNhanVien,VTro);
        KH.setSize(Dtop1.getWidth() + 2, Dtop1.getHeight() + 22);
        KH.setLocation(-1, -21);
        KH.setVisible(true);
        Dtop1.add(KH);
    }//GEN-LAST:event_btnQLKHActionPerformed

    private void NguoiHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NguoiHocActionPerformed
        CloseForm();
        TongHopThongKe Tk = null;
        try {
            Tk = new TongHopThongKe("NguoiHoc");
        } catch (SQLException ex) {
            Logger.getLogger(FormChinh.class.getName()).log(Level.SEVERE, null, ex);
        }
        Tk.setSize(Dtop1.getWidth() + 2, Dtop1.getHeight() + 22);
        Tk.setLocation(-1, -21);
        Tk.setVisible(true);
        Dtop1.add(Tk);
    }//GEN-LAST:event_NguoiHocActionPerformed

    private void jRadioButtonMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem7ActionPerformed
        // TODO add your handling code here:
        CloseForm();
        DoiMatKhau mk = new DoiMatKhau(MaNhanVien,VTro);
        mk.setSize(Dtop1.getWidth() + 2, Dtop1.getHeight() + 22);
        mk.setLocation(-1, -21);
        mk.setVisible(true);
        Dtop1.add(mk);
    }//GEN-LAST:event_jRadioButtonMenuItem7ActionPerformed

    private void DoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DoanhThuActionPerformed
        // TODO add your handling code here:
        CloseForm();
        TongHopThongKe Tk = null;
        try {
            Tk = new TongHopThongKe("DoanhThu");
        } catch (SQLException ex) {
            Logger.getLogger(FormChinh.class.getName()).log(Level.SEVERE, null, ex);
        }
        Tk.setSize(Dtop1.getWidth() + 2, Dtop1.getHeight() + 22);
        Tk.setLocation(-1, -21);
        Tk.setVisible(true);
        Dtop1.add(Tk);
    }//GEN-LAST:event_DoanhThuActionPerformed

    private void DiemTheoKhoaHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DiemTheoKhoaHocActionPerformed
        CloseForm();
        TongHopThongKe Tk = null;
        try {
            Tk = new TongHopThongKe("BangDiem");
        } catch (SQLException ex) {
            Logger.getLogger(FormChinh.class.getName()).log(Level.SEVERE, null, ex);
        }
        Tk.setSize(Dtop1.getWidth() + 2, Dtop1.getHeight() + 22);
        Tk.setLocation(-1, -21);
        Tk.setVisible(true);
        Dtop1.add(Tk);
    }//GEN-LAST:event_DiemTheoKhoaHocActionPerformed

    private void jMenuItem5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem5MouseClicked
        try {
             // TODO add your handling code here:
        CloseForm();
        
        if(VTro==true){
             JOptionPane.showMessageDialog(this, "Nhân viên không thế vào Form này.");  
            nv.setVisible(false);
            Hinh h = new Hinh();
            Dtop1.add(h);
            return;
        }else if(VTro==false){
        nv = new QLNV(MaNhanVien,VTro);
        nv.setSize(Dtop1.getWidth() + 2, Dtop1.getHeight() + 21);
        nv.setLocation(-1, -21);
           nv.setVisible(true);
           Dtop1.add(nv);
        }
        } catch (Exception e) {
        }
        
        
    }//GEN-LAST:event_jMenuItem5MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormChinh().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButtonMenuItem DiemTheoKhoaHoc;
    private javax.swing.JRadioButtonMenuItem DoanhThu;
    private javax.swing.JDesktopPane Dtop1;
    private javax.swing.JRadioButtonMenuItem NguoiHoc;
    private javax.swing.JRadioButtonMenuItem TongHop;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnHdan;
    private javax.swing.JButton btnKetThuc;
    private javax.swing.JButton btnQLCD;
    private javax.swing.JButton btnQLKH;
    private javax.swing.JButton btnQLNH;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem13;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem14;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem5;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem6;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem7;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem8;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JLabel lblThoiGian;
    // End of variables declaration//GEN-END:variables

    static class ActionListenerImpl implements ActionListener {

        public ActionListenerImpl() {
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}