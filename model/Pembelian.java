package model;

import java.time.LocalDate;

public class Pembelian {
    private int id;
    private LocalDate tanggal;
    private double harga;
    private Kendaraan kendaraan;
    private Pelanggan pelanggan;

    public Pembelian(int id, LocalDate tanggal, double harga, Kendaraan kendaraan, Pelanggan pelanggan) {
        this.id = id;
        this.tanggal = tanggal;
        this.harga = harga;
        this.kendaraan = kendaraan;
        this.pelanggan = pelanggan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public Kendaraan getKendaraan() {
        return kendaraan;
    }

    public void setKendaraan(Kendaraan kendaraan) {
        this.kendaraan = kendaraan;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public void setPelanggan(Pelanggan pelanggan) {
        this.pelanggan = pelanggan;
    }
    
    
}
