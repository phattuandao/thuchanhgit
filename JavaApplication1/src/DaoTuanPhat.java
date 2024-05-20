package BaiKiemTra;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import school_management.connect_mysql.ConnectFormKiemTra;

public final class DaoTuanPhat extends javax.swing.JFrame {

    public DaoTuanPhat() {
        initComponents();
        init();
    }

    public void init() {
        add();
        edit();
        delete();
        themMoi();
        boQua();

        displayInformation();
        displayContent();
        fillComboBox();
        tableEdit();
        ontEditable();
        disableButtons();
        textBoxEdit();
    }

    public void add() {
        btnLuu.addActionListener((ActionEvent e) -> {
            String productCode = txtMaSach.getText();
            String description = txtTieuDe.getText();
            String unitPrice = txtGia.getText();
            String onHandQuantity = txtSoLuong.getText();
            String categoryName = (String) loaiSach.getSelectedItem();
            boolean success = ConnectFormKiemTra.add(productCode, description, unitPrice, onHandQuantity, categoryName);
            if (success) {
                JOptionPane.showMessageDialog(this, "Lưu thông tin thành công!");
                clearFields();
                displayInformation();
            } else {
                JOptionPane.showMessageDialog(this, "Lưu thông tin không thành công!");
            }
        });
    }

    public void edit() {
        btnSua.addActionListener((ActionEvent e) -> {
            String description = txtTieuDe.getText();
            String unitPrice = txtGia.getText();
            String onHandQuantity = txtSoLuong.getText();
            String categoryName = (String) loaiSach.getSelectedItem();
            boolean success = ConnectFormKiemTra.update(description, unitPrice, onHandQuantity, categoryName);
            if (success) {
                JOptionPane.showMessageDialog(this, "Lưu thông tin thành công!");
                clearFields();
                displayInformation();
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thông tin không thành công!");
            }
        });
    }

    public void delete() {
        btnXoa.addActionListener((ActionEvent e) -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String productCode = (String) table.getValueAt(selectedRow, 0);
                boolean success = ConnectFormKiemTra.delete(productCode);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Lưu thông tin thành công!");
                    clearFields();
                    displayInformation();
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa thông tin không thành công!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thông tin không thành công!");
            }
        });
    }

    public void themMoi() {
        btnThemMoi.addActionListener((ActionEvent e) -> {
            inEditable();
            disableButtonsThemMoi();
            textBoxEdit();
        });
    }
    
    public void boQua () {
        btnBoQua.addActionListener((ActionEvent e) -> {
            ontEditable();
            disableButtons();
        });
    }

    public void displayInformation() {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);

        List<String[]> listInfo = ConnectFormKiemTra.displayInformation();
        if (listInfo != null) {
            for (String[] row : listInfo) {
                tableModel.addRow(row);
            }
        }
    }

    public void displayContent() {
        table.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String code = (String) table.getValueAt(selectedRow, 0);
                    String[] infoCodeStudent = ConnectFormKiemTra.displayInformationDetail(code);
                    if (infoCodeStudent != null && infoCodeStudent.length == 5) {
                        SwingUtilities.invokeLater(() -> {
                            loaiSach.setToolTipText(infoCodeStudent[0]);
                            txtMaSach.setText(infoCodeStudent[1]);
                            txtTieuDe.setText(infoCodeStudent[2]);
                            txtGia.setText(infoCodeStudent[3]);
                            txtSoLuong.setText(infoCodeStudent[4]);
                        });
                    }
                }
            }
            inEditable();
            enableButtonsThemMoiXoa();
            enableButtonsLuuBoQua();
        });
    }

    public void fillComboBox() {
        List<String> classNames = ConnectFormKiemTra.getCategoryName();
        if (classNames != null) {
            loaiSach.setModel(new DefaultComboBoxModel<>(classNames.toArray(String[]::new)));
        }
    }

    private void clearFields() {
        txtMaSach.setText("");
        txtTieuDe.setText("");
        txtGia.setText("");
        txtSoLuong.setText("");
    }

    public void tableEdit() {
        table.setFont(new java.awt.Font("Times New Roman", Font.PLAIN, 24));
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Times New Roman", Font.BOLD, 24));
        table.setRowHeight(50);
    }

    public void ontEditable() {
        loaiSach.setFocusable(false);
        txtMaSach.setEditable(false);
        txtTieuDe.setEditable(false);
        txtGia.setEditable(false);
        txtSoLuong.setEditable(false);
    }

    public void inEditable() {
        loaiSach.setFocusable(true);
        txtMaSach.setEditable(true);
        txtTieuDe.setEditable(true);
        txtGia.setEditable(true);
        txtSoLuong.setEditable(true);
    }

    private void disableButtons() {
        btnLuu.setEnabled(false);
        btnXoa.setEnabled(false);
        btnSua.setEnabled(false);
        btnBoQua.setEnabled(false);
    }
    
    private void disableButtonsThemMoi() {
        btnSua.setEnabled(false);
        btnBoQua.setEnabled(true);
        btnLuu.setEnabled(true);
        btnXoa.setEnabled(false);
    }

    private void enableButtonsThemMoiXoa() {
        btnXoa.setEnabled(true);
        btnSua.setEnabled(true);
        btnThemMoi.setEnabled(true);
    }
    
    private void enableButtonsLuuBoQua() {
        btnLuu.setEnabled(false);
        btnBoQua.setEnabled(false);
    }
    
    public void textBoxEdit() {
        settingJTextField(txtMaSach, "Nhập mã sách");
        settingJTextField(txtTieuDe, "Nhập tiêu đề");
        settingJTextField(txtGia, "Nhập giá");
        settingJTextField(txtSoLuong, "Nhập số lượng");
    }
    
    public void settingJTextField(JTextField txt, String text) {
        txt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txt.getText().equals(text)) {
                    txt.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txt.getText().isEmpty()) {
                    txt.setText(text);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loaiSach = new javax.swing.JComboBox<>();
        asf = new javax.swing.JLabel();
        maSach = new javax.swing.JLabel();
        soLuong = new javax.swing.JLabel();
        gia = new javax.swing.JLabel();
        tieuDe = new javax.swing.JLabel();
        txtMaSach = new javax.swing.JTextField();
        txtTieuDe = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        btnThemMoi = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnBoQua = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        loaiSach.setBackground(new java.awt.Color(255, 255, 255));
        loaiSach.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        loaiSach.setToolTipText("");
        loaiSach.setAutoscrolls(true);

        asf.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        asf.setText("Loại Sách:");

        maSach.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        maSach.setText("Mã Sách:");

        soLuong.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        soLuong.setText("Số Lượng:");

        gia.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        gia.setText("Giá:");

        tieuDe.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        tieuDe.setText("Tiêu Đề:");

        txtMaSach.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtMaSach.setText("Nhập mã sách");

        txtTieuDe.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtTieuDe.setText("Nhập tiêu đề");

        txtGia.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtGia.setText("Nhập giá");

        txtSoLuong.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtSoLuong.setText("Nhập số lượng");

        btnThemMoi.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnThemMoi.setText("Thêm Mới");
        btnThemMoi.setToolTipText("");

        btnSua.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setToolTipText("");

        btnLuu.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.setToolTipText("");

        btnXoa.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setToolTipText("");

        btnBoQua.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnBoQua.setText("Bỏ Qua");
        btnBoQua.setToolTipText("");

        table.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Sách", "Tiêu Đề", "Giá", "Số Lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(asf)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(maSach)
                                    .addGap(64, 64, 64)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tieuDe)
                                .addGap(71, 71, 71)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(loaiSach, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtMaSach, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)))
                        .addGap(139, 139, 139)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(soLuong)
                            .addComponent(gia))
                        .addGap(76, 76, 76)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGia, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                            .addComponent(txtSoLuong)))
                    .addComponent(jScrollPane1))
                .addGap(155, 155, 155)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBoQua, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(120, 120, 120))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(asf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loaiSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maSach, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gia, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(btnBoQua, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(118, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(DaoTuanPhat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DaoTuanPhat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DaoTuanPhat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DaoTuanPhat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DaoTuanPhat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel asf;
    private javax.swing.JButton btnBoQua;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThemMoi;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel gia;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> loaiSach;
    private javax.swing.JLabel maSach;
    private javax.swing.JLabel soLuong;
    private javax.swing.JTable table;
    private javax.swing.JLabel tieuDe;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMaSach;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTieuDe;
    // End of variables declaration//GEN-END:variables
}
