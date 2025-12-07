package model;

public class Motor extends Kendaraan {
    private double kapasitasMesin; // cc
    private boolean adaBox; // apakah punya box belakang

    public Motor(String id, String merk, String model, double hargaJual,
                 double tarifSewaPerHari, String status, String lokasi, int tahun,
                 double kapasitasMesin, boolean adaBox) {
        super(id, merk, model, hargaJual, tarifSewaPerHari, status, lokasi, tahun);
        this.kapasitasMesin = kapasitasMesin;
        this.adaBox = adaBox;
    }

    public double getKapasitasMesin() { return kapasitasMesin; }
    public void setKapasitasMesin(double cc) { this.kapasitasMesin = cc; }

    public boolean isAdaBox() { return adaBox; }
    public void setAdaBox(boolean adaBox) { this.adaBox = adaBox; }

    @Override
    public String getJenis() {
        return "MOTOR";
    }

    @Override
    public String getInfoSpesifik() {
        return "Mesin: " + kapasitasMesin + " cc, Box: " + (adaBox ? "Ya" : "Tidak");
    }

    @Override
    public String toString() {
        return "Motor{" +
                "id='" + id + '\'' +
                ", merk='" + merk + '\'' +
                ", model='" + model + '\'' +
                ", tahun=" + tahun +
                ", status='" + status + '\'' +
                ", " + getInfoSpesifik() +
                '}';
    }
}