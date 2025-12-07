package model;

public class Pelanggan {
    private int id;
    private String nama;
    private String kontak;
    private String alamat;

    public Pelanggan(int id, String nama, String kontak, String alamat) {
    this.id = id;
    this.nama = nama;
    this.kontak = kontak;
    this.alamat = alamat;
    }

    public int getId() {
    return id;
    }

    public void setId(int id) {
    this.id = id;
    }

    public String getNama() {
    return nama;
    }

    public void setNama(String nama) {
    this.nama = nama;
    }

    public String getKontak() {
    return kontak;
    }

    public void setKontak(String kontak) {
    this.kontak = kontak;
    }

    public String getAlamat() {
    return alamat;
    }

    public void setAlamat(String alamat) {
    this.alamat = alamat;
    }
}