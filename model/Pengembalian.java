package model;

import java.time.LocalDate;

public class Pengembalian {
    private int id;
    private double denda;
    private LocalDate tgl_pengembalian;
    private Kendaraan kendaraan;
    private Pelanggan pelanggan;

    public Pengembalian(int id, double denda, LocalDate tgl_pengembalian, Kendaraan kendaraan, Pelanggan pelanggan) {
        this.id = id;
        this.denda = denda;
        this.tgl_pengembalian = tgl_pengembalian;
        this.kendaraan = kendaraan;
        this.pelanggan = pelanggan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDenda() {
        return denda;
    }

    public void setDenda(double denda) {
        this.denda = denda;
    }

    public LocalDate getTgl_pengembalian() {
        return tgl_pengembalian;
    }

    public void setTgl_pengembalian(LocalDate tgl_pengembalian) {
        this.tgl_pengembalian = tgl_pengembalian;
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
