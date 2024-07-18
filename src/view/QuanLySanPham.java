package view;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
import javax.swing.JButton;
import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.sql.*;
import database.DBConnect;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.Hinhanh;
import model.Imeimolde;
import model.bonhomolde;
import model.cammeramolde;
import model.chipmolde;
import model.dongspmolde;
import model.hedieuhanhmolde;
import model.khoiluongmolde;
import model.manhinhmolde;
import model.mausacmolde;
import model.pinmolde;
import model.sanphamchitietmolde;
import model.sanphamchitietmolde2;
import model.sanphamodle;
import model.tensanphamolde;
import model.xuatxu;
import service.ProductService;
import service.svHinhAnh;
import service.svImei;
import service.svchitietsanpham;
import service.svsanpham;
import service.svxuatxu;
import view.suaHeDieuHanh;
import view.suaImei;
import view.suaXuatXu;
import service.svDongsp;
import service.svHeDieuHanh;
import service.svbonho;
import service.svcammera;
import service.svchip;
import service.svkhoiluong;
import service.svmanhinh;
import service.svmausac;
import service.svpin;
import view.suadongsanpham;
import service.svtensp;
import view.suaPin;
import view.suamanhinh;
import view.suatensp;

/**
 *
 * @author ACER
 */
public class QuanLySanPham extends javax.swing.JFrame {
    
    List<sanphamchitietmolde> listCTSP = new ArrayList<>();
    List<sanphamodle> list = new ArrayList<>();
    svsanpham sv = new svsanpham();
    svxuatxu xx = new svxuatxu();
    svHinhAnh ha = new svHinhAnh();
    svImei im = new svImei();
    svDongsp dsp = new svDongsp();
    svchitietsanpham spct = new svchitietsanpham();
    svHeDieuHanh hdh = new svHeDieuHanh();
    svtensp tsp = new svtensp();
    svpin p = new svpin();
    svcammera cmr = new svcammera();
    svkhoiluong kl = new svkhoiluong();
    svmanhinh mh = new svmanhinh();
    svchip c = new svchip();
    svmausac ms = new svmausac();
    svbonho bn = new svbonho();
    ProductService tk = new ProductService();
    DefaultTableModel model;
    DefaultTableModel data;

    /**
     * Creates new form QuanLi_SanPham
     */
    public QuanLySanPham() {
        initComponents();
        model = (DefaultTableModel) tableSp.getModel();
        data = (DefaultTableModel) tblspct.getModel();
        fillSanPham();
        comboxxuatxu();
        fillChiTietSanPHam();
        fillImei();
        comboxhinhanh();
//        filldongsp();
        fillhedieuhanh();
        filltensp();
        fillPin();
        fillmanhinh();
        fillcammera();
        fillkhoiluong();
        fillchip();
        fillmausac();
        fillbonho();
        fillmasp();
        filldongsp2();
    }
    
    void fillbonho() {
        List<bonhomolde> lst = new ArrayList<>();
        lst = bn.fillall();
        Set<String> uniquemanhinh = new HashSet<>();
        for (bonhomolde manh : lst) {
            uniquemanhinh.add(manh.getBoNho());
        }
        
        cbobonho.removeAllItems();
        
        for (String manhh : uniquemanhinh) {
            cbobonho.addItem(manhh);
        }
    }
    
    void fillmausac() {
        List<mausacmolde> lst = new ArrayList<>();
        lst = ms.fillall();
        Set<String> uniquemanhinh = new HashSet<>();
        
        for (mausacmolde manh : lst) {
            uniquemanhinh.add(manh.getMauSac());
        }
        
        cbomausac.removeAllItems();
        for (String manhh : uniquemanhinh) {
            cbomausac.addItem(manhh);
        }
    }
    
    void fillchip() {
        List<chipmolde> lst = new ArrayList<>();
        lst = c.fillall();
        Set<String> uniquemanhinh = new HashSet<>();
        for (chipmolde manh : lst) {
            uniquemanhinh.add(manh.getChip());
        }
        cbochip.removeAllItems();
        
        for (String manhh : uniquemanhinh) {
            cbochip.addItem(manhh);
        }
    }
    
    void fillkhoiluong() {
        List<khoiluongmolde> lst = new ArrayList<>();
        lst = kl.fillall();
        Set<String> uniquemanhinh = new HashSet<>();
        
        for (khoiluongmolde manh : lst) {
            uniquemanhinh.add(manh.getKhoiLuong());
        }
        
        cbokhoiluong.removeAllItems();
        
        for (String manhh : uniquemanhinh) {
            cbokhoiluong.addItem(manhh);
        }
    }
    
    void fillmanhinh() {
        List<manhinhmolde> lst = new ArrayList<>();
        lst = mh.fillall();
        Set<String> uniquemanhinh = new HashSet<>();
        
        for (manhinhmolde manh : lst) {
            uniquemanhinh.add(manh.getManHinh());
        }
        
        cbomanhinh.removeAllItems();
        
        for (String manhh : uniquemanhinh) {
            cbomanhinh.addItem(manhh);
        }
    }
    
    void fillcammera() {
        List<cammeramolde> lst = new ArrayList<>();
        lst = cmr.fillall();
        Set<String> uniquemanhinh = new HashSet<>();
        
        for (cammeramolde manh : lst) {
            uniquemanhinh.add(manh.getCamMera());
        }
        
        cbocammera.removeAllItems();
        
        for (String manhh : uniquemanhinh) {
            cbocammera.addItem(manhh);
        }
    }
    
    void fillPin() {
        List<pinmolde> lst = new ArrayList<>();
        lst = p.fillall();
        
        Set<String> uniquepin = new HashSet<>();
        
        for (pinmolde pin : lst) {
            uniquepin.add(pin.getPin());
        }
        
        cbopin.removeAllItems();
        
        for (String pinn : uniquepin) {
            cbopin.addItem(pinn);
        }
    }
    
    void fillhedieuhanh() {
        List<hedieuhanhmolde> lst = new ArrayList<>();
        lst = hdh.fillall();
        
        Set<String> uniqueHeDieuHanh = new HashSet<>();
        
        for (hedieuhanhmolde hedieuh : lst) {
            uniqueHeDieuHanh.add(hedieuh.getHeDieuHanh());
        }
        
        cbohedieuhanh.removeAllItems();
        
        for (String heDieuHanh : uniqueHeDieuHanh) {
            cbohedieuhanh.addItem(heDieuHanh);
        }
    }
    
    void filltensp() {
        List<tensanphamolde> lst = new ArrayList<>();
        lst = tsp.fiall();
        
        Set<String> uniquetensp = new HashSet<>();
        
        for (tensanphamolde tensp : lst) {
            uniquetensp.add(tensp.getTensanpham());
        }
        
        cbotensp.removeAllItems();
        
        for (String tensp : uniquetensp) {
            cbotensp.addItem(tensp);
        }
    }
    
    void comboxhinhanh() {
        List<Hinhanh> lst = new ArrayList<>();
        lst = ha.selectall();
        
        Set<String> hinhanh = new HashSet<>();
        
        for (Hinhanh ha : lst) {
            hinhanh.add(ha.getHinhAnh());
        }
        
        cbohinhanh.removeAllItems();
        
        for (String hinhanha : hinhanh) {
            cbohinhanh.addItem(hinhanha);
        }
    }
    
    void fillImei() {
        List<Imeimolde> lst = new ArrayList<>();
        lst = im.fiall();
        
        for (Imeimolde imeimolde : lst) {
            cboimei.addItem(String.valueOf(imeimolde.getIdImei()));
        }
    }
    
    void fillChiTietSanPHam() {
        data.setRowCount(0);
        listCTSP = spct.selectall();
        Set<String> uniqueBoNho = new HashSet<>();
        Set<String> uniqueMauSac = new HashSet<>();
        
        for (sanphamchitietmolde ct : listCTSP) {
            data.addRow(new Object[]{ct.getTenSanPham(), ct.getPin(), ct.getManHinh(), ct.getCammera(), ct.getKhoiLuong(), ct.getChip(), ct.getMauSac(), ct.getBoNho(), ct.getGiaBan(), ct.getSoLuong(), ct.getMaSanPham(), ct.getMaChiTietSanPham(), ct.getHinhAnh(), ct.getDongSP(), ct.getGiaNhap()});
            uniqueBoNho.add(ct.getBoNho());
            uniqueMauSac.add(ct.getMauSac());
        }
        
        cbobonho.removeAllItems();
        cbomausac.removeAllItems();
        
        for (String value : uniqueBoNho) {
            cbobonho.addItem(value);
        }
        for (String value : uniqueMauSac) {
            cbomausac.addItem(value);
        }
    }
    
    void comboxxuatxu() {
        List<xuatxu> lst = new ArrayList<>();
        lst = xx.fillComboBox();
        
        Set<String> uniqueXuatXu = new HashSet<>();
        
        for (xuatxu xuatXuItem : lst) {
            uniqueXuatXu.add(xuatXuItem.getXuatxu());
        }
        cboxuatxu.removeAllItems();
        
        for (String xuatXu : uniqueXuatXu) {
            cboxuatxu.addItem(xuatXu);
        }
    }
    
    void fillmasp() {
        List<sanphamodle> lst = new ArrayList<>();
        lst = sv.fiall(); 
        Set<String> uniqueXuatXu = new HashSet<>();
        for (sanphamodle xuatXuItem : lst) {
            uniqueXuatXu.add(xuatXuItem.getMaSp());
        }
        
        cbomasp.removeAllItems();
        
        for (String xuatXu : uniqueXuatXu) {
            cbomasp.addItem(xuatXu);
        }
    }
    
    void filldongsp2() {
        List<sanphamodle> lst = new ArrayList<>();
        lst = sv.fiall();
        
        Set<String> uniqueXuatXu = new HashSet<>();
        
        for (sanphamodle xuatXuItem : lst) {
            uniqueXuatXu.add(xuatXuItem.getDongsp());
        }
        cbodongsp2.removeAllItems();
        for (String xuatXu : uniqueXuatXu) {
            cbodongsp2.addItem(xuatXu);
        }
    }
    
    public void fillSanPham() {
        model.setRowCount(0);
        list = sv.fiall();
        
        Set<String> uniqueHeDieuHanh = new HashSet<>();
        Set<String> uniqueXuatXu = new HashSet<>();
        for (sanphamodle sp : list) {
            model.addRow(new Object[]{
                sp.getMaSp(), sp.getHang(), sp.getDongsp(), sp.getHeDieuHanh(), sp.getXuatXu()
            });
            uniqueHeDieuHanh.add(sp.getHeDieuHanh());
            uniqueXuatXu.add(sp.getXuatXu());
        }
        
        cbohedieuhanh.removeAllItems();
        cboxuatxu.removeAllItems();
        
        for (String value : uniqueHeDieuHanh) {
            cbohedieuhanh.addItem(value);
        }
        for (String value : uniqueXuatXu) {
            cboxuatxu.addItem(value);
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

        MainGiaoDien = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        txthang = new javax.swing.JTextField();
        txtmasp = new javax.swing.JTextField();
        txtdongsp = new javax.swing.JTextField();
        cbohedieuhanh = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        cboxuatxu = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSp = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblspct = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        cbotensp = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtgianhap = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        soluong2 = new javax.swing.JLabel();
        txtgiaban = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txttimkiem = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        cbopin = new javax.swing.JComboBox<>();
        cbomanhinh = new javax.swing.JComboBox<>();
        cbokhoiluong = new javax.swing.JComboBox<>();
        cbocammera = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        cbochip = new javax.swing.JComboBox<>();
        cbomausac = new javax.swing.JComboBox<>();
        cbobonho = new javax.swing.JComboBox<>();
        txtmactsp = new javax.swing.JTextField();
        cbohinhanh = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txtsoluong = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        cbomasp = new javax.swing.JComboBox<>();
        cbodongsp2 = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        cboimei = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1165, 683));
        setResizable(false);

        MainGiaoDien.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        MainGiaoDien.setPreferredSize(new java.awt.Dimension(1428, 771));

        jPanel3.setPreferredSize(new java.awt.Dimension(1414, 719));

        txtmasp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmaspActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setText("Thông tin sản phẩm ");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Mã Sản Phẩm: ");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Hãng: ");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Dòng Sản Phẩm:");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Hệ Điều Hành: ");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Xuất Xứ:");

        tableSp.setBackground(new java.awt.Color(255, 255, 204));
        tableSp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Sản Phẩm ", "Hãng", "Dòng Sản Phẩm ", "Hệ Điều Hành ", "Xuất Xứ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableSp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSpMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableSp);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Oxygen-Icons.org-Oxygen-Actions-document-edit.16.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Oxygen-Icons.org-Oxygen-Actions-document-edit.16.png"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton3.setBackground(new java.awt.Color(255, 204, 0));
        jButton3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton3.setText("Xóa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 204, 0));
        jButton4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton4.setText("Reset");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 204, 0));
        jButton1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton1.setText("Thêm ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 204, 0));
        jButton2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton2.setText("Sửa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cbohedieuhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cboxuatxu, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtmasp, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                .addComponent(txthang)
                                .addComponent(txtdongsp)))
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(328, 341, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(85, 85, 85)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel2)
                                            .addComponent(txtmasp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(25, 25, 25)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txthang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3))
                                        .addGap(25, 25, 25)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel4)
                                            .addComponent(txtdongsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(25, 25, 25)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cboxuatxu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6)))
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cbohedieuhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5))
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(358, Short.MAX_VALUE))
        );

        MainGiaoDien.addTab("Sản Phẩm ", jPanel3);

        tblspct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tên Sản Phẩm ", "Pin", "Màn Hình ", "Cammera", "Khối Lượng ", "Chip", "Màu Sắc", "Bộ Nhớ", "Giá Bán ", "Số Lượng ", "Mã Sản Phẩm", "Mã chi tiết SP"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblspct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblspctMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblspct);

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Tên Sản Phẩm: ");

        cbotensp.setEditable(true);

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Giá Nhập:");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setText("Giá Bán: ");

        txtgianhap.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtgianhap.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.yellow, java.awt.Color.orange, null, null));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Danh sách Imei:");

        soluong2.setText("sl");

        txtgiaban.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtgiaban.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.yellow, java.awt.Color.orange, null, null));

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setText("Tìm Kiếm theo tên ");

        jLabel15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel15.setText("Dòng Sản Phẩm:");

        jLabel16.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel16.setText("Mã sản phẩm Chi tiết ");

        jLabel17.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel17.setText("Mã Sản Phẩm");

        jLabel18.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel18.setText("Pin");

        jLabel19.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel19.setText("Cammera");

        jLabel20.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel20.setText("Màn Hình ");

        cbopin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        cbomanhinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbomanhinhActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel21.setText("Khối Lượng ");

        jLabel22.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel22.setText("Chip");

        jLabel24.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel24.setText("Màu Sắc");

        jLabel25.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel25.setText("Bộ Nhớ ");

        cbochip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbochipActionPerformed(evt);
            }
        });

        cbomausac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "." }));
        cbomausac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbomausacActionPerformed(evt);
            }
        });

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Oxygen-Icons.org-Oxygen-Actions-document-edit.16.png"))); // NOI18N
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel23MouseExited(evt);
            }
        });

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Oxygen-Icons.org-Oxygen-Actions-document-edit.16.png"))); // NOI18N
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel27MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel27MouseExited(evt);
            }
        });

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Oxygen-Icons.org-Oxygen-Actions-document-edit.16.png"))); // NOI18N
        jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel28MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel28MouseExited(evt);
            }
        });

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Oxygen-Icons.org-Oxygen-Actions-document-edit.16.png"))); // NOI18N
        jLabel29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel29MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel29MouseExited(evt);
            }
        });

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Oxygen-Icons.org-Oxygen-Actions-document-edit.16.png"))); // NOI18N
        jLabel30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel30MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel30MouseExited(evt);
            }
        });

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Oxygen-Icons.org-Oxygen-Actions-document-edit.16.png"))); // NOI18N
        jLabel31.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel31MouseDragged(evt);
            }
        });
        jLabel31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel31MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel31MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel31MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel31MousePressed(evt);
            }
        });

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Oxygen-Icons.org-Oxygen-Actions-document-edit.16.png"))); // NOI18N
        jLabel34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel34MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel34MouseExited(evt);
            }
        });

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Oxygen-Icons.org-Oxygen-Actions-document-edit.16.png"))); // NOI18N
        jLabel36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel36MouseClicked(evt);
            }
        });

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Oxygen-Icons.org-Oxygen-Actions-document-edit.16.png"))); // NOI18N
        jLabel37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel37MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel37MouseExited(evt);
            }
        });

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Oxygen-Icons.org-Oxygen-Actions-document-edit.16.png"))); // NOI18N
        jLabel38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel38MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel38MouseExited(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(255, 204, 0));
        jButton10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton10.setText("tìm kiếm ");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setText("So Luong:");

        cbodongsp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbodongsp2ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 204, 0));
        jButton5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton5.setText("Add");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(255, 204, 0));
        jButton7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton7.setText("Delete");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(255, 204, 0));
        jButton6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton6.setText("Update");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(255, 204, 0));
        jButton8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton8.setText("Reset");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jButton5)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addGap(18, 18, 18)
                .addComponent(jButton8)
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton10))
                    .addComponent(jLabel14)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 914, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtmactsp)
                                                    .addComponent(cbokhoiluong, 0, 124, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel34))
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel21))
                                        .addGap(40, 40, 40)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel17)
                                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(cbomasp, 0, 123, Short.MAX_VALUE)
                                                    .addComponent(cbochip, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel31))
                                            .addComponent(jLabel10)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(cbotensp, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel38))))
                                    .addComponent(jLabel15))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtgianhap, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbopin, javax.swing.GroupLayout.Alignment.LEADING, 0, 124, Short.MAX_VALUE)
                                            .addComponent(cbomausac, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel27)
                                            .addComponent(jLabel23)))
                                    .addComponent(jLabel11))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cbobonho, 0, 124, Short.MAX_VALUE)
                                            .addComponent(cbomanhinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel20)
                                            .addComponent(txtgiaban))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel28)
                                            .addComponent(jLabel29)))
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(cbodongsp2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cbocammera, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel30))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cboimei, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel37)))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(155, 155, 155)
                                .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(soluong2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(293, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbohinhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(287, 287, 287))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(txtsoluong)
                                .addGap(29, 29, 29))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel17)
                                .addComponent(jLabel16)
                                .addComponent(jLabel20)
                                .addComponent(jLabel18)
                                .addComponent(jLabel19)
                                .addComponent(jLabel13)
                                .addComponent(soluong2)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtmactsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbomasp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbopin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbomanhinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbocammera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbobonho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cboimei, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(277, 277, 277))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel22)
                                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel24))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbomausac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbochip, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cbokhoiluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel12)
                                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel15)
                                                        .addComponent(jLabel10)
                                                        .addComponent(jLabel11)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(cbodongsp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(cbotensp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(txtgianhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtgiaban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(cbohinhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGap(317, 317, 317))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        MainGiaoDien.addTab("Chi Tiết Sản Phẩm ", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MainGiaoDien, javax.swing.GroupLayout.DEFAULT_SIZE, 1425, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MainGiaoDien, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtmaspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmaspActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmaspActionPerformed

    private void tableSpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSpMouseClicked
        // TODO add your handling code here:
        int row = tableSp.getSelectedRow();
        if (row < 0) {
            return;
        }
        sanphamodle sp = list.get(row);
        txtmasp.setText(sp.getMaSp());
        txthang.setText(sp.getHang());
        txtdongsp.setText(sp.getDongsp());
        cbohedieuhanh.setSelectedItem(sp.getHeDieuHanh());
        cboxuatxu.setSelectedItem(sp.getXuatXu());
    }//GEN-LAST:event_tableSpMouseClicked
    sanphamodle getFromdata() {
        String masp = txtmasp.getText();
        String Hang = txthang.getText();
        String Dongsp = txtdongsp.getText();
        String hedieuhanh = (String) cbohedieuhanh.getSelectedItem();
        String xuatxu = (String) cboxuatxu.getSelectedItem();
        return new sanphamodle(
                masp, Hang, Dongsp, hedieuhanh, xuatxu);
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            sanphamodle sp = getFromdata();
            if (sv.themSanPham(sp)) {
                fillSanPham();
                comboxxuatxu();
                JOptionPane.showMessageDialog(this, "Thêm thành công");
            }
        } catch (Exception e) {
            e.printStackTrace(); // In stack trace để xem lỗi chi tiết
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int row = tableSp.getSelectedRow();
        if (row < 0) {
            return;
        }
        sanphamodle sp = getFromdata();
        String id = list.get(row).getMaSp();
        sp.setMaSp(id);
        if (sv.suaSanPham(sp)) {
            fillSanPham();
            comboxxuatxu();
            
            JOptionPane.showMessageDialog(this, "Cập nhật thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật không thành công");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int row = tableSp.getSelectedRow();
        if (row < 0) {
            return;
        }
        String ma = list.get(row).getMaSp();
        
        if (sv.xoaSanPham(ma)) {
            fillSanPham();
            comboxxuatxu();
            
            JOptionPane.showMessageDialog(this, "Xóa thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Xóa không thành công");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        suaXuatXu suaxxFrame = new suaXuatXu();
        JFrame frame = new JFrame("Sua Xuat Xu Frame");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Đóng chỉ frame này khi đóng
        frame.add(suaxxFrame);
        frame.setSize(400, 300); // Thay đổi kích thước tùy theo nhu cầu
        frame.setVisible(true);
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        txtgianhap.setText("");
        txtgiaban.setText("");
        txtmactsp.setText("");
        txthang.setText("");
        txtdongsp.setText("");
        cbohedieuhanh.setSelectedItem(1);
        cboxuatxu.setSelectedItem(1);
        fillSanPham();
        comboxxuatxu();
        fillmasp();
        

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        suaHeDieuHanh suahdhFrame = new suaHeDieuHanh();
        // Thêm SuaMauSac vào một JFrame mới
        JFrame frame = new JFrame("Sua He Dieu hanh Frame");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Đóng chỉ frame này khi đóng
        frame.add(suahdhFrame);
        // Đặt kích thước và hiển thị frame
        frame.setSize(400, 300); // Thay đổi kích thước tùy theo nhu cầu
        frame.setVisible(true);
    }//GEN-LAST:event_jLabel9MouseClicked
    sanphamchitietmolde getFromdataa() {
        String machitietsanpham = txtmactsp.getText();
        String masp = (String) cbomasp.getSelectedItem();
        String tensp = (String) cbotensp.getSelectedItem();
        String Pin = (String) cbopin.getSelectedItem();
        String ManHinh = (String) cbomanhinh.getSelectedItem();
        String cammera = (String) cbocammera.getSelectedItem();
        String khoiluong = (String) cbokhoiluong.getSelectedItem();
        String chip = (String) cbochip.getSelectedItem();
        String hinhanh = (String) cbohinhanh.getSelectedItem();
        String mausac = (String) cbomausac.getSelectedItem();
        String bonho = (String) cbobonho.getSelectedItem();
        Double gianhap = Double.parseDouble(txtgianhap.getText());
        Double giabnan = Double.parseDouble(txtgiaban.getText());
        return new sanphamchitietmolde(masp, tensp, machitietsanpham, tensp, Pin, ManHinh, cammera, khoiluong, chip, hinhanh, mausac, bonho, gianhap, giabnan, WIDTH, SOMEBITS);
    }
    
    ;
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        try {
            sanphamchitietmolde spctt = getFromdataa();
            if (spct.themSanPham(spctt)) {
                fillSanPham();
                comboxxuatxu();
                fillChiTietSanPHam();
                fillImei();
                comboxhinhanh();
                fillhedieuhanh();
                filltensp();
                fillPin();
                fillmanhinh();
                fillcammera();
                fillkhoiluong();
                fillchip();
                fillmausac();
                fillbonho();
                fillSanPham();
                
                JOptionPane.showMessageDialog(this, "Thêm thành công");
            }
        } catch (Exception e) {
            e.printStackTrace(); // In stack trace để xem lỗi chi tiết
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        

    }//GEN-LAST:event_jButton5ActionPerformed

    private void cbomanhinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbomanhinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbomanhinhActionPerformed

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
        // TODO add your handling code here:

        suaPin suapFrame = new suaPin();
        // Thêm SuaMauSac vào một JFrame mới
        JFrame frame = new JFrame("Sua pin Frame");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Đóng chỉ frame này khi đóng
        frame.add(suapFrame);
        // Đặt kích thước và hiển thị frame
        frame.setSize(400, 300); // Thay đổi kích thước tùy theo nhu cầu
        frame.setVisible(true);
    }//GEN-LAST:event_jLabel23MouseClicked

    private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseClicked
        // TODO add your handling code here:
        suamausac suapFrame = new suamausac();
        // Thêm SuaMauSac vào một JFrame mới
        JFrame frame = new JFrame("Sua mausac Frame");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Đóng chỉ frame này khi đóng
        frame.add(suapFrame);
        // Đặt kích thước và hiển thị frame
        frame.setSize(400, 300); // Thay đổi kích thước tùy theo nhu cầu
        frame.setVisible(true);
        

    }//GEN-LAST:event_jLabel27MouseClicked

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
        // TODO add your handling code here:
        suamanhinh suamhFrame = new suamanhinh();

        // Thêm SuaMauSac vào một JFrame mới
        JFrame frame = new JFrame("Sua pin Frame");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Đóng chỉ frame này khi đóng
        frame.add(suamhFrame);
        // Đặt kích thước và hiển thị frame
        frame.setSize(400, 300); // Thay đổi kích thước tùy theo nhu cầu
        frame.setVisible(true);
        

    }//GEN-LAST:event_jLabel28MouseClicked

    private void jLabel29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MouseClicked
        // TODO add your handling code here:
        suabonho suamhFrame = new suabonho();

        // Thêm SuaMauSac vào một JFrame mới
        JFrame frame = new JFrame("Sua pin Frame");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Đóng chỉ frame này khi đóng
        frame.add(suamhFrame);
        // Đặt kích thước và hiển thị frame
        frame.setSize(400, 300); // Thay đổi kích thước tùy theo nhu cầu
        frame.setVisible(true);

    }//GEN-LAST:event_jLabel29MouseClicked

    private void jLabel30MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MouseClicked
        // TODO add your handling code here:
        suacammera suamhFrame = new suacammera();

        // Thêm SuaMauSac vào một JFrame mới
        JFrame frame = new JFrame("Sua cammera Frame");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Đóng chỉ frame này khi đóng
        frame.add(suamhFrame);
        // Đặt kích thước và hiển thị frame
        frame.setSize(400, 300); // Thay đổi kích thước tùy theo nhu cầu
        frame.setVisible(true);

    }//GEN-LAST:event_jLabel30MouseClicked

    private void jLabel31MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MouseClicked
        suachip suamhFrame = new suachip();

        // Thêm SuaMauSac vào một JFrame mới
        JFrame frame = new JFrame("Sua chip Frame");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Đóng chỉ frame này khi đóng
        frame.add(suamhFrame);
        // Đặt kích thước và hiển thị frame
        frame.setSize(400, 300); // Thay đổi kích thước tùy theo nhu cầu
        frame.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel31MouseClicked

    private void jLabel34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseClicked
        // TODO add your handling code here:

        suakhoiluong suamhFrame = new suakhoiluong();

        // Thêm SuaMauSac vào một JFrame mới
        JFrame frame = new JFrame("Sua kl Frame");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Đóng chỉ frame này khi đóng
        frame.add(suamhFrame);
        // Đặt kích thước và hiển thị frame
        frame.setSize(400, 300); // Thay đổi kích thước tùy theo nhu cầu
        frame.setVisible(true);
        

    }//GEN-LAST:event_jLabel34MouseClicked

    private void jLabel37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseClicked
        // TODO add your handling code here
        suaImei suaImPanel = new suaImei();

        // Hiển thị JPanel trong một hộp thoại không có tiêu đề và không có các nút điều khiển khác
        JOptionPane.showMessageDialog(null, suaImPanel, "Imei", JOptionPane.PLAIN_MESSAGE);
        

    }//GEN-LAST:event_jLabel37MouseClicked

    private void jLabel38MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel38MouseClicked
        // TODO add your handling code here:
        suatensp tsp = new suatensp();
        // Thêm SuaMauSac vào một JFrame mới
        JFrame frame = new JFrame("Sua ten San Pham Frame");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Đóng chỉ frame này khi đóng
        frame.add(tsp);

        // Đặt kích thước và hiển thị frame
        frame.setSize(600, 400); // Thay đổi kích thước tùy theo nhu cầu
        frame.setVisible(true);
    }//GEN-LAST:event_jLabel38MouseClicked

    private void tblspctMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblspctMouseClicked
        // TODO add your handling code here:  
        int row = tblspct.getSelectedRow();
        if (row < 0) {
            return;
        }
        sanphamchitietmolde sp = listCTSP.get(row);

        // Thực hiện fill dữ liệu vào table từ đối tượng sp
        loadFieldsFromObject(sp);
        
    }
    
    private void loadFieldsFromObject(sanphamchitietmolde sp) {
        // Làm điều gì đó với đối tượng sp, chẳng hạn load dữ liệu vào các trường trong giao diện
        // Ví dụ giả sử txtmactsp, txtmasp2 là JTextField, cbotensp là JComboBox, v.v.
        txtmactsp.setText(sp.getMaChiTietSanPham());
        cbopin.setSelectedItem(sp.getPin());
        cbomanhinh.setSelectedItem(sp.getManHinh());
        cbocammera.setSelectedItem(sp.getManHinh());
        cbokhoiluong.setSelectedItem(sp.getKhoiLuong());
        cbochip.setSelectedItem(sp.getChip());
        cbomausac.setSelectedItem(sp.getMauSac());
        cbobonho.setSelectedItem(sp.getBoNho());
        txtgianhap.setText(String.valueOf(sp.getGiaNhap()));
        txtgiaban.setText(String.valueOf(sp.getGiaBan()));
        soluong2.setText(String.valueOf(sp.getSoLuong()));
        cbocammera.setSelectedItem(sp.getCammera());
        cbotensp.setSelectedItem(sp.getTenSanPham());
        cbohinhanh.setSelectedItem(sp.getHinhAnh());
        cbodongsp2.setSelectedItem(sp.getDongSP());
        cbomasp.setSelectedItem(sp.getMaSanPham());
    }//GEN-LAST:event_tblspctMouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        txtmactsp.setText("");
        txtgianhap.setText("");
        txtgiaban.setText("");
        fillSanPham();
        comboxxuatxu();
        fillChiTietSanPHam();
        fillImei();
        comboxhinhanh();
        fillhedieuhanh();
        filltensp();
        fillPin();
        fillmanhinh();
        fillcammera();
        fillkhoiluong();
        fillchip();
        fillmausac();
        fillbonho();
        filldongsp2();
        fillmasp();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        int row = tblspct.getSelectedRow();
        if (row < 0) {
            return;
        }
        sanphamchitietmolde sp = getFromdataa();
        String id = listCTSP.get(row).getMaChiTietSanPham();
        sp.setMaChiTietSanPham(id);
        if (spct.suaSanPham(sp)) {
            fillSanPham();
            comboxxuatxu();
            fillChiTietSanPHam();
            fillImei();
            comboxhinhanh();
            fillhedieuhanh();
            filltensp();
            fillPin();
            fillmanhinh();
            fillcammera();
            fillkhoiluong();
            fillchip();
            fillmausac();
            fillbonho();
            JOptionPane.showMessageDialog(this, "Cập nhật thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật không thành công");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        int row = tblspct.getSelectedRow();
        if (row < 0) {
            return;
        }
        
        String id = listCTSP.get(row).getMaChiTietSanPham(); // Giả sử mã sản phẩm là kiểu int

        if (spct.xoaSanPham(id)) { // Gọi phương thức xoaSanPham() với mã sản phẩm kiểu int
            fillSanPham();
            comboxxuatxu();
            fillChiTietSanPHam();
            fillImei();
            comboxhinhanh();
            fillhedieuhanh();
            filltensp();
            fillPin();
            fillmanhinh();
            fillcammera();
            fillkhoiluong();
            fillchip();
            fillmausac();
            fillbonho();
            JOptionPane.showMessageDialog(this, "Xóa thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Xóa không thành công");
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        String timkiem = txttimkiem.getText();
        Set<String> uniqueBoNho = new HashSet<>();
        Set<String> uniqueMauSac = new HashSet<>();

        // Xóa hết dữ liệu cũ
        DefaultTableModel model = (DefaultTableModel) tblspct.getModel();
        model.setRowCount(0); // Xóa hết dữ liệu

        List<sanphamchitietmolde> listtk = tk.ProductSearch(timkiem);
        for (sanphamchitietmolde ct : listtk) {
            model.addRow(new Object[]{ct.getTenSanPham(), ct.getPin(), ct.getManHinh(), ct.getCammera(), ct.getKhoiLuong(), ct.getChip(), ct.getMauSac(), ct.getBoNho(), ct.getGiaBan(), ct.getSoLuong(), ct.getMaSanPham(), ct.getMaChiTietSanPham(), ct.getHinhAnh(), ct.getDongSP(), ct.getGiaNhap()});
            uniqueBoNho.add(ct.getBoNho());
            uniqueMauSac.add(ct.getMauSac());
        }
        
        cbobonho.removeAllItems();
        cbomausac.removeAllItems();
        
        for (String value : uniqueBoNho) {
            cbobonho.addItem(value);
        }
        for (String value : uniqueMauSac) {
            cbomausac.addItem(value);
        }

    }//GEN-LAST:event_jButton10ActionPerformed

    private void cbochipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbochipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbochipActionPerformed

    private void cbomausacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbomausacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbomausacActionPerformed

    private void jLabel23MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseExited
        // TODO add your handling code here:
        fillPin();
    }//GEN-LAST:event_jLabel23MouseExited

    private void jLabel31MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MouseExited
        // TODO add your handling code here:
        fillchip();
    }//GEN-LAST:event_jLabel31MouseExited

    private void jLabel31MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel31MouseEntered

    private void jLabel31MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel31MousePressed

    private void jLabel31MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel31MouseDragged

    private void jLabel28MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseExited
        // TODO add your handling code here:
        fillmanhinh();
    }//GEN-LAST:event_jLabel28MouseExited

    private void jLabel30MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MouseExited
        // TODO add your handling code here:
        fillcammera();
    }//GEN-LAST:event_jLabel30MouseExited

    private void jLabel34MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseExited
        // TODO add your handling code here:
        fillkhoiluong();
    }//GEN-LAST:event_jLabel34MouseExited

    private void jLabel27MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseExited
        // TODO add your handling code here:
        fillmausac();
    }//GEN-LAST:event_jLabel27MouseExited

    private void jLabel29MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MouseExited
        // TODO add your handling code here:
        fillbonho();
    }//GEN-LAST:event_jLabel29MouseExited

    private void jLabel37MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseExited
        // TODO add your handling code here:
        fillImei();
    }//GEN-LAST:event_jLabel37MouseExited

    private void jLabel38MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel38MouseExited
        // TODO add your handling code here:
        filltensp();
    }//GEN-LAST:event_jLabel38MouseExited

    private void cbodongsp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbodongsp2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbodongsp2ActionPerformed

    private void jLabel36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel36MouseClicked

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
            java.util.logging.Logger.getLogger(QuanLySanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLySanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLySanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLySanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLySanPham().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane MainGiaoDien;
    private javax.swing.JComboBox<String> cbobonho;
    private javax.swing.JComboBox<String> cbocammera;
    private javax.swing.JComboBox<String> cbochip;
    private javax.swing.JComboBox<String> cbodongsp2;
    private javax.swing.JComboBox<String> cbohedieuhanh;
    private javax.swing.JComboBox<String> cbohinhanh;
    private javax.swing.JComboBox<String> cboimei;
    private javax.swing.JComboBox<String> cbokhoiluong;
    private javax.swing.JComboBox<String> cbomanhinh;
    private javax.swing.JComboBox<String> cbomasp;
    private javax.swing.JComboBox<String> cbomausac;
    private javax.swing.JComboBox<String> cbopin;
    private javax.swing.JComboBox<String> cbotensp;
    private javax.swing.JComboBox<String> cboxuatxu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel soluong2;
    private javax.swing.JTable tableSp;
    private javax.swing.JTable tblspct;
    private javax.swing.JTextField txtdongsp;
    private javax.swing.JTextField txtgiaban;
    private javax.swing.JTextField txtgianhap;
    private javax.swing.JTextField txthang;
    private javax.swing.JTextField txtmactsp;
    private javax.swing.JTextField txtmasp;
    private javax.swing.JLabel txtsoluong;
    private javax.swing.JTextField txttimkiem;
    // End of variables declaration//GEN-END:variables
}
