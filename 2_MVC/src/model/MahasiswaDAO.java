package model;

import config.Connector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaDAO {
    private Connection connection;

    public MahasiswaDAO() {
        // Mengambil koneksi database dari class Connector
        connection = Connector.getConnection();
    }

    // 1. FUNGSI INSERT (Tambah Data)
    public void insert(Mahasiswa mhs) {
        String sql = "INSERT INTO mahasiswa (nim, nama, jalur, ipk, wawancara, portofolio, score, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            if (connection != null) {
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setString(1, mhs.getNim());
                    ps.setString(2, mhs.getNama());
                    ps.setString(3, mhs.getJalur());
                    ps.setDouble(4, mhs.getIpk());
                    ps.setDouble(5, mhs.getWawancara());
                    ps.setDouble(6, mhs.getPortofolio());
                    ps.setDouble(7, mhs.getScore());
                    ps.setString(8, mhs.getStatus());
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.err.println("DAO Error saat Insert: " + e.getMessage());
        }
    }

    // 2. FUNGSI UPDATE (Ubah Data Berdasarkan NIM)
    public void update(Mahasiswa mhs) {
        // NIM ditaruh paling belakang setelah kata WHERE
        String sql = "UPDATE mahasiswa SET nama=?, jalur=?, ipk=?, wawancara=?, portofolio=?, score=?, status=? WHERE nim=?";
        try {
            if (connection != null) {
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setString(1, mhs.getNama());
                    ps.setString(2, mhs.getJalur());
                    ps.setDouble(3, mhs.getIpk());
                    ps.setDouble(4, mhs.getWawancara());
                    ps.setDouble(5, mhs.getPortofolio());
                    ps.setDouble(6, mhs.getScore());
                    ps.setString(7, mhs.getStatus());
                    ps.setString(8, mhs.getNim()); // Mengisi tanda tanya ke-8 (WHERE nim)
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.err.println("DAO Error saat Update: " + e.getMessage());
        }
    }

    // 3. FUNGSI DELETE (Hapus Data Berdasarkan NIM)
    public void delete(String nim) {
        String sql = "DELETE FROM mahasiswa WHERE nim=?";
        try {
            if (connection != null) {
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setString(1, nim);
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.err.println("DAO Error saat Delete: " + e.getMessage());
        }
    }

    // 4. FUNGSI GET ALL (Mengambil Semua Data untuk Ditampilkan ke JTable)
    public List<Mahasiswa> getAll() {
        List<Mahasiswa> list = new ArrayList<>();
        String sql = "SELECT * FROM mahasiswa";
        try {
            if (connection != null) {
                try (Statement s = connection.createStatement();
                     ResultSet rs = s.executeQuery(sql)) {
                    
                    while (rs.next()) {
                        // Memasukkan hasil query database ke objek Mahasiswa (Menggunakan Constructor ke-2)
                        Mahasiswa mhs = new Mahasiswa(
                            rs.getString("nim"),
                            rs.getString("nama"),
                            rs.getString("jalur"),
                            rs.getDouble("ipk"),
                            rs.getDouble("wawancara"),
                            rs.getDouble("portofolio"),
                            rs.getDouble("score"),
                            rs.getString("status")
                        );
                        list.add(mhs);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("DAO Error saat GetAll (Load Data): " + e.getMessage());
        }
        return list;
    }
}