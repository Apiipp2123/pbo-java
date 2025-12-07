package model;

import java.time.LocalDate;

public class Penyewaan {
   private int id;
   private LocalDate mulai;
   private LocalDate selesai;
   private double totalBiaya;
   private String statusSewa; // DISEWA atau SELESAI
   private Kendaraan kendaraan;
   private Pelanggan pelanggan;

   public Penyewaan(int id, LocalDate mulai, LocalDate selesai, double totalBiaya, 
                    String statusSewa, Kendaraan kendaraan, Pelanggan pelanggan) {
    this.id = id;
    this.mulai = mulai;
    this.selesai = selesai;
    this.totalBiaya = totalBiaya;
    this.statusSewa = statusSewa;
    this.kendaraan = kendaraan;
    this.pelanggan = pelanggan;
   }

   // Getter dan Setter
   public int getId() { return id; }
   public void setId(int id) { this.id = id; }

   public LocalDate getMulai() { return mulai; }
   public void setMulai(LocalDate mulai) { this.mulai = mulai; }

   public LocalDate getSelesai() { return selesai; }
   public void setSelesai(LocalDate selesai) { this.selesai = selesai; }

   public double getTotalBiaya() { return totalBiaya; }
   public void setTotalBiaya(double totalBiaya) { this.totalBiaya = totalBiaya; }

   public String getStatusSewa() { return statusSewa; }
   public void setStatusSewa(String statusSewa) { this.statusSewa = statusSewa; }

   public Kendaraan getKendaraan() { return kendaraan; }
   public void setKendaraan(Kendaraan kendaraan) { this.kendaraan = kendaraan; }

   public Pelanggan getPelanggan() { return pelanggan; }
   public void setPelanggan(Pelanggan pelanggan) { this.pelanggan = pelanggan; }
}