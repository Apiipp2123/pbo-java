package model;

public class Mobil extends Kendaraan {
    private int kapasitasPenumpang;
    private String tipeTransmisi; // Manual / Automatic

    public Mobil(String id, String merk, String model, double hargaJual,
                 double tarifSewaPerHari, String status, String lokasi, int tahun,
                 int kapasitasPenumpang, String tipeTransmisi) {
        super(id, merk, model, hargaJual, tarifSewaPerHari, status, lokasi, tahun);
        this.kapasitasPenumpang = kapasitasPenumpang;
        this.tipeTransmisi = tipeTransmisi;
    }

    public int getKapasitasPenumpang() { return kapasitasPenumpang; }
    public void setKapasitasPenumpang(int kapasitas) { this.kapasitasPenumpang = kapasitas; }

    public String getTipeTransmisi() { return tipeTransmisi; }
    public void setTipeTransmisi(String transmisi) { this.tipeTransmisi = transmisi; }

    @Override
    public String getJenis() {
        return "MOBIL";
    }

    @Override
    public String getInfoSpesifik() {
        return "Kapasitas: " + kapasitasPenumpang + " orang, Transmisi: " + tipeTransmisi;
    }

    @Override
    public String toString() {
        return "Mobil {" + "id='" + id + '\'' + ", merk='" + merk + '\'' +
                ", model='" + model + '\'' + ", tahun=" + tahun +
                ", status='" + status + '\'' + ", " + getInfoSpesifik() + '}';
    }
}