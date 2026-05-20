/*
 * KELAS   : IF-F
 * NIM     : 123240248
 * NAMA    : Reno Miftahudin
 */

import model.MahasiswaDAO;
import view.BeasiswaView;
import controller.MahasiswaController;

public class Main {
    public static void main(String[] args) {
        // Mengubah Look and Feel UI agar tombolnya modern soft-blue/light mirip mockup
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Gagal memuat LookAndFeel: " + e.getMessage());
        }

        // Instansiasi komponen MVC
        BeasiswaView view = new BeasiswaView();
        MahasiswaDAO model = new MahasiswaDAO();
        new MahasiswaController(view, model);

        // Menampilkan Window Form GUI ke layar
        view.setVisible(true);
    }
}