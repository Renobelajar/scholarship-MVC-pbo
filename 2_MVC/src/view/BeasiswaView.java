package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BeasiswaView extends JFrame {
    public JTable table;
    public DefaultTableModel tableModel;
    public JTextField tfNim, tfNama, tfIpk, tfWawancara, tfPortofolio;
    public JComboBox<String> cbJalur;
    public JButton btnAdd, btnUpdate, btnDelete, btnClear;

    public BeasiswaView() {
        setTitle("Sistem Seleksi Beasiswa Universitas");
        setSize(950, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- TABEL DATA (KIRI/CENTER) ---
        String[] columns = {"NIM", "Nama", "Jalur", "IPK", "Wawancara", "Portofolio", "Score", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // --- FORM INPUT & BUTTONS (KANAN/EAST) ---
        JPanel panelRight = new JPanel();
        panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
        panelRight.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelRight.setPreferredSize(new Dimension(250, 500));

        tfNim = new JTextField();
        tfNama = new JTextField();
        String[] jalurs = {"Prestasi", "Kurang Mampu"};
        cbJalur = new JComboBox<>(jalurs);
        tfIpk = new JTextField();
        tfWawancara = new JTextField();
        tfPortofolio = new JTextField();

        panelRight.add(new JLabel("NIM (Primary Key)"));
        panelRight.add(tfNim);
        panelRight.add(Box.createVerticalStrut(5));
        panelRight.add(new JLabel("Nama Mahasiswa"));
        panelRight.add(tfNama);
        panelRight.add(Box.createVerticalStrut(5));
        panelRight.add(new JLabel("Jalur Beasiswa"));
        panelRight.add(cbJalur);
        panelRight.add(Box.createVerticalStrut(5));
        panelRight.add(new JLabel("IPK (0.0 - 4.0)"));
        panelRight.add(tfIpk);
        panelRight.add(Box.createVerticalStrut(5));
        panelRight.add(new JLabel("Nilai Wawancara (0-100)"));
        panelRight.add(tfWawancara);
        panelRight.add(Box.createVerticalStrut(5));
        panelRight.add(new JLabel("Nilai Portofolio (0-100)"));
        panelRight.add(tfPortofolio);
        panelRight.add(Box.createVerticalStrut(15));

        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");

        Dimension btnSize = new Dimension(230, 30);
        btnAdd.setMaximumSize(btnSize);
        btnUpdate.setMaximumSize(btnSize);
        btnDelete.setMaximumSize(btnSize);
        btnClear.setMaximumSize(btnSize);

        panelRight.add(btnAdd);
        panelRight.add(Box.createVerticalStrut(5));
        panelRight.add(btnUpdate);
        panelRight.add(Box.createVerticalStrut(5));
        panelRight.add(btnDelete);
        panelRight.add(Box.createVerticalStrut(5));
        panelRight.add(btnClear);

        add(panelRight, BorderLayout.EAST);
    }
}