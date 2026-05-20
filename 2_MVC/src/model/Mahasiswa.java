package model;

public class Mahasiswa {
    private String nim;
    private String nama;
    private String jalur;
    private double ipk;
    private double wawancara;
    private double portofolio;
    private double score;
    private String status;

    // CONSTRUCTOR 1: Dipakai saat kita menambah (Add) atau mengubah (Update) data dari Form Input
    public Mahasiswa(String nim, String nama, String jalur, double ipk, double wawancara, double portofolio) {
        this.nim = nim;
        this.nama = nama;
        this.jalur = jalur;
        this.ipk = ipk;
        this.wawancara = wawancara;
        this.portofolio = portofolio;
        
        // LOGIKA BISNIS: Hitung rata-rata otomatis
        // Nilai IPK (0.0 - 4.0) dikalikan 25 dulu agar skalanya sama-sama 0 - 100
        this.score = ((this.ipk * 25) + this.wawancara + this.portofolio) / 3;
        
        // ATURAN KELULUSAN: Batas minimal kelulusan score adalah 80.0
        if (this.score >= 80.0) {
            this.status = "LOLOS BEASISWA";
        } else {
            this.status = "TIDAK LOLOS";
        }
    }

    // CONSTRUCTOR 2: Overloading, dipakai oleh DAO saat mengambil data yang sudah jadi dari Database
    public Mahasiswa(String nim, String nama, String jalur, double ipk, double wawancara, double portofolio, double score, String status) {
        this.nim = nim;
        this.nama = nama;
        this.jalur = jalur;
        this.ipk = ipk;
        this.wawancara = wawancara;
        this.portofolio = portofolio;
        this.score = score;
        this.status = status;
    }

    // ==========================================
    // GETTER & SETTER (Wajib untuk Controller & DAO)
    // ==========================================
    public String getNim() { return nim; }
    public void setNim(String nim) { this.nim = nim; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getJalur() { return jalur; }
    public void setJalur(String jalur) { this.jalur = jalur; }

    public double getIpk() { return ipk; }
    public void setIpk(double ipk) { this.ipk = ipk; }

    public double getWawancara() { return wawancara; }
    public void setWawancara(double wawancara) { this.wawancara = wawancara; }

    public double getPortofolio() { return portofolio; }
    public void setPortofolio(double portofolio) { this.portofolio = portofolio; }

    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}