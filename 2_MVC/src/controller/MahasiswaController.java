package controller;

import model.Mahasiswa;
import model.MahasiswaDAO;
import view.BeasiswaView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MahasiswaController {
    private BeasiswaView view;
    private MahasiswaDAO dao;

    public MahasiswaController(BeasiswaView view, MahasiswaDAO dao) {
        this.view = view;
        this.dao = dao;

        // Load data awal dari database saat aplikasi dibuka
        loadData();

        // 1. EVENT TOMBOL ADD
        this.view.btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validasi Input Kosong
                if (view.tfNim.getText().isEmpty() || view.tfNama.getText().isEmpty() || 
                    view.tfIpk.getText().isEmpty() || view.tfWawancara.getText().isEmpty() || 
                    view.tfPortofolio.getText().isEmpty()) {
                    
                    JOptionPane.showMessageDialog(view, "Semua data wajib diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    String nim = view.tfNim.getText();
                    String nama = view.tfNama.getText();
                    String jalur = view.cbJalur.getSelectedItem().toString();
                    double ipk = Double.parseDouble(view.tfIpk.getText());
                    double wawancara = Double.parseDouble(view.tfWawancara.getText());
                    double portofolio = Double.parseDouble(view.tfPortofolio.getText());

                    // Validasi Rentang Nilai
                    if (ipk < 0.0 || ipk > 4.0) {
                        JOptionPane.showMessageDialog(view, "IPK harus berada di rentang 0.0 - 4.0!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (wawancara < 0 || wawancara > 100 || portofolio < 0 || portofolio > 100) {
                        JOptionPane.showMessageDialog(view, "Nilai Wawancara & Portofolio harus berada di rentang 0 - 100!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Instansiasi objek Mahasiswa (Logika hitung Score & Status otomatis berjalan di Constructor Model)
                    Mahasiswa mhs = new Mahasiswa(nim, nama, jalur, ipk, wawancara, portofolio);
                    
                    // Simpan ke Database lewat DAO
                    dao.insert(mhs);
                    
                    JOptionPane.showMessageDialog(view, "Data mahasiswa berhasil ditambahkan!");
                    loadData();
                    clearForm();
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(view, "Format nilai angka tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 2. EVENT TOMBOL UPDATE
        this.view.btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validasi Input Kosong
                if (view.tfNim.getText().isEmpty() || view.tfNama.getText().isEmpty() || 
                    view.tfIpk.getText().isEmpty() || view.tfWawancara.getText().isEmpty() || 
                    view.tfPortofolio.getText().isEmpty()) {
                    
                    JOptionPane.showMessageDialog(view, "Semua data wajib diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    String nim = view.tfNim.getText(); // NIM bertindak sebagai index WHERE di SQL
                    String nama = view.tfNama.getText();
                    String jalur = view.cbJalur.getSelectedItem().toString();
                    double ipk = Double.parseDouble(view.tfIpk.getText());
                    double wawancara = Double.parseDouble(view.tfWawancara.getText());
                    double portofolio = Double.parseDouble(view.tfPortofolio.getText());

                    // Validasi Rentang Nilai
                    if (ipk < 0.0 || ipk > 4.0) {
                        JOptionPane.showMessageDialog(view, "IPK harus berada di rentang 0.0 - 4.0!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (wawancara < 0 || wawancara > 100 || portofolio < 0 || portofolio > 100) {
                        JOptionPane.showMessageDialog(view, "Nilai Wawancara & Portofolio harus berada di rentang 0 - 100!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Mahasiswa mhs = new Mahasiswa(nim, nama, jalur, ipk, wawancara, portofolio);
                    dao.update(mhs);
                    
                    JOptionPane.showMessageDialog(view, "Data mahasiswa berhasil diperbarui!");
                    loadData();
                    clearForm();
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(view, "Format nilai angka tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 3. EVENT TOMBOL DELETE
        this.view.btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nim = view.tfNim.getText();
                if (nim.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Pilih data mahasiswa dari tabel terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(view, "Apakah Anda yakin ingin menghapus data dengan NIM " + nim + "?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    dao.delete(nim);
                    JOptionPane.showMessageDialog(view, "Data mahasiswa berhasil dihapus!");
                    loadData();
                    clearForm();
                }
            }
        });

        // 4. EVENT TOMBOL CLEAR
        this.view.btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        // 5. EVENT KLIK BARIS TABEL (MOUSE LISTENER)
        this.view.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.table.getSelectedRow();
                if (row != -1) {
                    // Pindahkan data dari tabel ke form input kanan
                    view.tfNim.setText(view.table.getValueAt(row, 0).toString());
                    view.tfNama.setText(view.table.getValueAt(row, 1).toString());
                    view.cbJalur.setSelectedItem(view.table.getValueAt(row, 2).toString());
                    view.tfIpk.setText(view.table.getValueAt(row, 3).toString());
                    view.tfWawancara.setText(view.table.getValueAt(row, 4).toString());
                    view.tfPortofolio.setText(view.table.getValueAt(row, 5).toString());

                    // Kunci komponen NIM agar tidak bisa diubah saat proses Update
                    view.tfNim.setEditable(false);
                }
            }
        });
    }

    // Fungsi helper untuk merender ulang isi tabel JTable dari database
    private void loadData() {
        view.tableModel.setRowCount(0); // Bersihkan sisa render baris visual lama
        List<Mahasiswa> list = dao.getAll();
        for (Mahasiswa mhs : list) {
            Object[] rowData = {
                mhs.getNim(),
                mhs.getNama(),
                mhs.getJalur(),
                mhs.getIpk(),
                mhs.getWawancara(),
                mhs.getPortofolio(),
                mhs.getScore(),
                mhs.getStatus()
            };
            view.tableModel.addRow(rowData);
        }
    }

    // Fungsi helper untuk membersihkan kolom input form dan membuka kunci NIM
    private void clearForm() {
        view.tfNim.setText("");
        view.tfNama.setText("");
        view.cbJalur.setSelectedIndex(0);
        view.tfIpk.setText("");
        view.tfWawancara.setText("");
        view.tfPortofolio.setText("");
        
        // Buka kembali kunci NIM agar bisa menginputkan mahasiswa baru lagi
        view.tfNim.setEditable(true);
        view.table.clearSelection();
    }
}