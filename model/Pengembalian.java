package model;

import java.time.LocalDate;

public class Pengembalian {
    private int id;
    private double denda;
    private LocalDate tgl_pengembalian;
    private Penyewaan penyewaan; // Tambahkan referensi ke penyewaan
    
    // Constructor lama untuk kompatibilitas
    public Pengembalian(int id, double denda, LocalDate tgl_pengembalian) {
        this.id = id;
        this.denda = denda;
        this.tgl_pengembalian = tgl_pengembalian;
    }
    
    // Constructor baru dengan penyewaan
    public Pengembalian(int id, double denda, LocalDate tgl_pengembalian, Penyewaan penyewaan) {
        this.id = id;
        this.denda = denda;
        this.tgl_pengembalian = tgl_pengembalian;
        this.penyewaan = penyewaan;
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
    
    public Penyewaan getPenyewaan() {
        return penyewaan;
    }
    
    public void setPenyewaan(Penyewaan penyewaan) {
        this.penyewaan = penyewaan;
    }
}