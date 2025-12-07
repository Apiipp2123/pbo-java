package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import model.*;

public class LogistikController {
    private static ArrayList<Kendaraan> armada = new ArrayList<>();
    private static ArrayList<Pembelian> pembelianList = new ArrayList<>(); 
    private static ArrayList<Penyewaan> sewaList = new ArrayList<>();

    // Helper: buat pelanggan sementara
    private static Pelanggan buatPelangganSementara(String nama) {
        return new Pelanggan(0, nama, "-", "N/A");
    }

    public static void beliKendaraan(Kendaraan kendaraan, double harga) {
        Pembelian p = new Pembelian(
            0, 
            LocalDate.now(), 
            harga, 
            kendaraan, 
            buatPelangganSementara("Admin")
        );
        armada.add(kendaraan);
        pembelianList.add(p);
    }

    public static void sewaKendaraan(Kendaraan kendaraan, String namaPenyewa, int hari, double biaya) {
        Penyewaan s = new Penyewaan(
            0,
            LocalDate.now(),
            LocalDate.now().plusDays(hari),
            biaya,
            "DISEWA",
            kendaraan,
            buatPelangganSementara(namaPenyewa)
        );
        sewaList.add(s);
    }

    public static ArrayList<Kendaraan> getArmada() {
        return armada;
    }

    public static void setArmada(ArrayList<Kendaraan> armada) {
        LogistikController.armada = armada;
    }

    public static ArrayList<Pembelian> getPembelianList() {
        return pembelianList;
    }

    public static void setPembelianList(ArrayList<Pembelian> pembelianList) {
        LogistikController.pembelianList = pembelianList;
    }

    public static ArrayList<Penyewaan> getSewaList() {
        return sewaList;
    }

    public static void setSewaList(ArrayList<Penyewaan> sewaList) {
        LogistikController.sewaList = sewaList;
    }

    
    // Getter
    // public static ArrayList<Kendaraan> getKendaraan() {
    //     return armada;
    // }

    // public static ArrayList<Pembelian> getPembelianList() {
    //     return pembelianList;
    // }

    // public static ArrayList<Penyewaan> getSewaList() {
    //     return sewaList;
    // }
}