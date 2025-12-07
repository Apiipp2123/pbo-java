package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import model.*;

public class LogistikController {
    private static ArrayList<Kendaraan> armada = new ArrayList<>();
    private static ArrayList<Kendaraan> armadaTerjual = new ArrayList<>();
    private static ArrayList<Penjualan> penjualanList = new ArrayList<>();
    private static ArrayList<Penyewaan> sewaList = new ArrayList<>();
    private static ArrayList<Pengembalian> pengembalianList = new ArrayList<>();
    
    private static int penjualanCounter = 1;
    private static int sewaCounter = 1;
    private static int pengembalianCounter = 1;

    // Helper: buat pelanggan sementara
    private static Pelanggan buatPelangganSementara(String nama) {
        return new Pelanggan(0, nama, "-", "N/A");
    }

    // 1. Tambah Kendaraan (tanpa transaksi pembelian)
    public static void tambahKendaraan(Kendaraan kendaraan) {
        armada.add(kendaraan);
    }

    // 2. Jual Kendaraan (dari armada kita ke pembeli)
    public static void jualKendaraan(Kendaraan kendaraan, double harga, String namaPembeli) {
        Penjualan j = new Penjualan(
            penjualanCounter++,
            LocalDate.now(),
            harga,
            kendaraan,
            buatPelangganSementara(namaPembeli)
        );
        
        kendaraan.setStatus("TERJUAL");
        armada.remove(kendaraan);
        armadaTerjual.add(kendaraan);
        penjualanList.add(j);
    }

    // 3. Sewa Kendaraan
    public static void sewaKendaraan(Kendaraan kendaraan, String namaPenyewa, int hari, double biaya) {
        Penyewaan s = new Penyewaan(
            sewaCounter++,
            LocalDate.now(),
            LocalDate.now().plusDays(hari),
            biaya,
            "DISEWA",  // Status awal: masih disewa
            kendaraan,
            buatPelangganSementara(namaPenyewa)
        );
        kendaraan.setStatus("DISEWA");
        sewaList.add(s);
    }

// 4. Pengembalian Kendaraan
public static void kembalikanKendaraan(Penyewaan penyewaan, double denda) {
    // Update status penyewaan menjadi SELESAI
    penyewaan.setStatusSewa("SELESAI");
    
    // Update status kendaraan kembali menjadi TERSEDIA
    penyewaan.getKendaraan().setStatus("TERSEDIA");
    
    // Catat pengembalian dengan referensi ke penyewaan
    Pengembalian pengembalian = new Pengembalian(
        pengembalianCounter++,
        denda,
        LocalDate.now(),
        penyewaan  // Menyimpan referensi ke penyewaan
    );
    pengembalianList.add(pengembalian);
}
    // Getter methods
    public static ArrayList<Kendaraan> getArmada() {
        return armada;
    }

    public static ArrayList<Kendaraan> getArmadaTerjual() {
        return armadaTerjual;
    }

    public static ArrayList<Penjualan> getPenjualanList() {
        return penjualanList;
    }

    public static ArrayList<Penyewaan> getSewaList() {
        return sewaList;
    }

    public static ArrayList<Pengembalian> getPengembalianList() {
        return pengembalianList;
    }

}