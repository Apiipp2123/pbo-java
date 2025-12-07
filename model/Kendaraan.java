package model;

public abstract class Kendaraan {
    protected String id; //id dari kendaraan mobil atau motor.
    protected String merk; // honda, kawasaki, dll, jadi merk itu sendiri adalah brandnya.
    protected String model; // merk honda Brio, Civic, merk kawasaki Ninja ZX-25R, Ninja H2R.
    protected double hargaJual; // harga jual dari mobil dan motor.
    protected double tarifSewaPerHari; // tarif sewa per harinya.
    protected String status; // tersedia, terjual, disewa.
    protected String lokasi; // jakarta pusat, surabaya, bali, dll.
    protected int tahun; // Mobil Toyota Avanza keluaran 2020 → tahun = 2020

    public Kendaraan(String id, String merk, String model, double hargaJual, double tarifSewaPerHari, String status, String lokasi, int tahun) {
        this.id = id;
        this.merk = merk;
        this.model = model;
        this.hargaJual = hargaJual;
        this.tarifSewaPerHari = tarifSewaPerHari;
        this.status = status;
        this.lokasi = lokasi;
        this.tahun = tahun;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(double hargaJual) {
        this.hargaJual = hargaJual;
    }

    public double getTarifSewaPerHari() {
        return tarifSewaPerHari;
    }

    public void setTarifSewaPerHari(double tarifSewaPerHari) {
        this.tarifSewaPerHari = tarifSewaPerHari;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    // === ABSTRAKSI: method abstrak (harus diimplementasi subclass) ===
    public abstract String getJenis(); // misal: "Mobil" atau "Motor"
    public abstract String getInfoSpesifik(); // info khusus tiap jenis
}