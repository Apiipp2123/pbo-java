package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import model.*;

public class LogistikController {
    private static ArrayList<Kendaraan> armada = new ArrayList<>();
    private static ArrayList<Kendaraan> armadaTerjual = new ArrayList<>();
    private static ArrayList<Pembelian> pembelianList = new ArrayList<>();
    private static ArrayList<Penjualan> penjualanList = new ArrayList<>();
    private static ArrayList<Penyewaan> sewaList = new ArrayList<>();
    
    private static int pembelianCounter = 1;
    private static int penjualanCounter = 1;
    private static int sewaCounter = 1;

    // Helper: buat pelanggan sementara
    private static Pelanggan buatPelangganSementara(String nama) {
        return new Pelanggan(0, nama, "-", "N/A");
    }

    // 1. Tambah Kendaraan (tanpa transaksi pembelian)
    public static void tambahKendaraan(Kendaraan kendaraan) {
        armada.add(kendaraan);
    }

    // 2. Beli Kendaraan (dari pihak lain ke armada kita)
    public static void beliKendaraan(Kendaraan kendaraan, double harga, String namaPembeli) {
        Pembelian p = new Pembelian(
            pembelianCounter++, 
            LocalDate.now(), 
            harga, 
            kendaraan, 
            buatPelangganSementara(namaPembeli)
        );
        armada.add(kendaraan);
        pembelianList.add(p);
    }

    // 3. Jual Kendaraan (dari armada kita ke pembeli)
    public static void jualKendaraan(Kendaraan kendaraan, double harga, String namaPembeli) {
        // Buat objek penjualan
        Penjualan j = new Penjualan(
            penjualanCounter++,
            LocalDate.now(),
            harga,
            kendaraan,
            buatPelangganSementara(namaPembeli)
        );
        
        // Ubah status kendaraan
        kendaraan.setStatus("TERJUAL");
        
        // Pindahkan dari armada ke armadaTerjual
        armada.remove(kendaraan);
        armadaTerjual.add(kendaraan);
        
        // Tambah ke daftar penjualan
        penjualanList.add(j);
    }

    // 4. Sewa Kendaraan
    public static void sewaKendaraan(Kendaraan kendaraan, String namaPenyewa, int hari, double biaya) {
        Penyewaan s = new Penyewaan(
            sewaCounter++,
            LocalDate.now(),
            LocalDate.now().plusDays(hari),
            biaya,
            "DISEWA",
            kendaraan,
            buatPelangganSementara(namaPenyewa)
        );
        kendaraan.setStatus("DISEWA");
        sewaList.add(s);
    }

    // Getter untuk armada terjual
    public static ArrayList<Kendaraan> getArmadaTerjual() {
        return armadaTerjual;
    }

    // Getter untuk penjualan
    public static ArrayList<Penjualan> getPenjualanList() {
        return penjualanList;
    }

    // Getter lainnya tetap sama...
    public static ArrayList<Kendaraan> getArmada() {
        return armada;
    }

    public static ArrayList<Pembelian> getPembelianList() {
        return pembelianList;
    }

    public static ArrayList<Penyewaan> getSewaList() {
        return sewaList;
    }
}