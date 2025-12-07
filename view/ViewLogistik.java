package view;

import controller.LogistikController;
import java.time.LocalDate;
import java.util.Scanner;
import model.*;

public class ViewLogistik {
    static Scanner in = new Scanner(System.in);

    public static void menu() {
        System.out.println("\n=== SISTEM LOGISTIK DALEX ===");
        System.out.println("1. Tambah Kendaraan (Mobil/Motor)");
        System.out.println("2. Lihat Semua Kendaraan");
        System.out.println("3. Jual Kendaraan");
        System.out.println("4. Sewa Kendaraan");
        System.out.println("5. Pengembalian Kendaraan");
        System.out.println("6. Lihat Penjualan");
        System.out.println("7. Lihat Penyewaan");
        System.out.println("8. Lihat Pengembalian");
        System.out.println("9. Keluar");
        System.out.print("Pilih: ");
    }

    public static Kendaraan inputKendaraan() {
        System.out.print("Masukkan ID Kendaraan: ");
        String id = in.nextLine();

        System.out.print("Jenis (1 = Mobil, 2 = Motor): ");
        int pilihJenis = in.nextInt();
        in.nextLine();

        System.out.print("Merk (contoh: Honda, Kawasaki): ");
        String merk = in.nextLine();

        System.out.print("Model (contoh: Brio, Ninja ZX-25R): ");
        String model = in.nextLine();

        System.out.print("Harga Jual (angka): ");
        double hargaJual = in.nextDouble();

        System.out.print("Tarif Sewa per Hari (angka): ");
        double tarifSewa = in.nextDouble();
        in.nextLine();

        System.out.print("Lokasi (contoh: Jakarta, Surabaya): ");
        String lokasi = in.nextLine();

        System.out.print("Tahun Produksi (contoh: 2020): ");
        int tahun = in.nextInt();
        in.nextLine();

        if (pilihJenis == 1) {
            return inputDataMobil(id, merk, model, hargaJual, tarifSewa, lokasi, tahun);
        } else if (pilihJenis == 2) {
            return inputDataMotor(id, merk, model, hargaJual, tarifSewa, lokasi, tahun);
        } else {
            System.out.println("Jenis tidak valid! Default ke Mobil.");
            return new Mobil(id, merk, model, hargaJual, tarifSewa, "TERSEDIA", lokasi, tahun, 5, "Automatic");
        }
    }

    private static Mobil inputDataMobil(String id, String merk, String model, double hargaJual, 
                                        double tarifSewa, String lokasi, int tahun) {
        System.out.print("Kapasitas Penumpang: ");
        int kapasitas = in.nextInt();
        in.nextLine();
        
        System.out.print("Tipe Transmisi (Manual/Automatic): ");
        String transmisi = in.nextLine();
        
        return new Mobil(id, merk, model, hargaJual, tarifSewa, "TERSEDIA", 
                         lokasi, tahun, kapasitas, transmisi);
    }

    private static Motor inputDataMotor(String id, String merk, String model, double hargaJual, 
                                        double tarifSewa, String lokasi, int tahun) {
        System.out.print("Kapasitas Mesin (cc): ");
        double cc = in.nextDouble();
        in.nextLine();
        
        System.out.print("Ada Box? (true/false): ");
        boolean adaBox = in.nextBoolean();
        in.nextLine();
        
        return new Motor(id, merk, model, hargaJual, tarifSewa, "TERSEDIA", 
                         lokasi, tahun, cc, adaBox);
    }

    private static void tampilkanDaftarKendaraan() {
        System.out.println("\n--- DAFTAR KENDARAAN ---");
        if (LogistikController.getArmada().isEmpty()) {
            System.out.println("Belum ada kendaraan dalam armada.");
        } else {
            int no = 1;
            for (Kendaraan knd : LogistikController.getArmada()) {
                System.out.println(no + ". " + knd);
                no++;
            }
        }
    }

    private static void prosesJualKendaraan() {
        System.out.println("\n--- JUAL KENDARAAN ---");
        System.out.print("Masukkan ID kendaraan yang ingin dijual: ");
        String idJual = in.nextLine();
        
        Kendaraan kendaraanJual = cariKendaraanById(idJual);
        if (kendaraanJual == null) {
            System.out.println("Kendaraan tidak ditemukan!");
            return;
        }

        System.out.print("Masukkan harga penjualan: ");
        double hargaJual = in.nextDouble();
        in.nextLine();
        
        System.out.print("Nama Pembeli: ");
        String namaPembeli = in.nextLine();
        
        LogistikController.jualKendaraan(kendaraanJual, hargaJual, namaPembeli);
        System.out.println("Kendaraan berhasil dijual!");
    }

    private static void prosesSewaKendaraan() {
        System.out.println("\n--- SEWA KENDARAAN ---");
        
        // Tampilkan kendaraan yang tersedia
        System.out.println("\nKendaraan yang tersedia:");
        boolean adaTersedia = false;
        for (Kendaraan k : LogistikController.getArmada()) {
            if ("TERSEDIA".equals(k.getStatus())) {
                System.out.println("- ID: " + k.getId() + " | " + k.getMerk() + " " + k.getModel() + 
                                 " | Tarif: Rp" + k.getTarifSewaPerHari() + "/hari");
                adaTersedia = true;
            }
        }
        
        if (!adaTersedia) {
            System.out.println("Tidak ada kendaraan yang tersedia untuk disewa.");
            return;
        }
        
        System.out.print("\nMasukkan ID kendaraan yang ingin disewa: ");
        String idSewa = in.nextLine();

        Kendaraan kendaraanDisewa = cariKendaraanById(idSewa);
        if (kendaraanDisewa == null) {
            System.out.println("Kendaraan tidak ditemukan!");
            return;
        }
        
        if (!"TERSEDIA".equals(kendaraanDisewa.getStatus())) {
            System.out.println("Kendaraan tidak tersedia! Status: " + kendaraanDisewa.getStatus());
            return;
        }

        System.out.print("Nama Penyewa: ");
        String nama = in.nextLine();

        System.out.print("Jumlah Hari Sewa: ");
        int hari = in.nextInt();
        in.nextLine();

        double biaya = kendaraanDisewa.getTarifSewaPerHari() * hari;
        LogistikController.sewaKendaraan(kendaraanDisewa, nama, hari, biaya);
        System.out.println("Kendaraan berhasil disewa! Total: Rp" + biaya);
    }

private static void prosesPengembalianKendaraan() {
    System.out.println("\n--- PENGEMBALIAN KENDARAAN ---");
    
    // Tampilkan kendaraan yang sedang disewa
    System.out.println("\nKendaraan yang sedang disewa:");
    boolean adaDisewa = false;
    for (Penyewaan s : LogistikController.getSewaList()) {
        if ("DISEWA".equals(s.getStatusSewa())) {
            Kendaraan k = s.getKendaraan();
            LocalDate tanggalSelesai = s.getSelesai();
            LocalDate sekarang = LocalDate.now();
            
            String statusTelat = "";
            if (sekarang.isAfter(tanggalSelesai)) {
                long hariTelat = sekarang.toEpochDay() - tanggalSelesai.toEpochDay();
                statusTelat = " (Telat " + hariTelat + " hari)";
            }
            
            System.out.println("- ID Penyewaan: " + s.getId() + 
                             " | ID Kendaraan: " + k.getId() + 
                             " | " + k.getMerk() + " " + k.getModel() +
                             " | Penyewa: " + s.getPelanggan().getNama() +
                             " | Selesai: " + s.getSelesai() + statusTelat);
            adaDisewa = true;
        }
    }
    
    if (!adaDisewa) {
        System.out.println("Tidak ada kendaraan yang sedang disewa.");
        return;
    }
    
    System.out.print("\nMasukkan ID Penyewaan yang akan dikembalikan: ");
    int idSewa = in.nextInt();
    in.nextLine();
    
    // Cari penyewaan berdasarkan ID
    Penyewaan penyewaanDikembalikan = null;
    for (Penyewaan s : LogistikController.getSewaList()) {
        if (s.getId() == idSewa && "DISEWA".equals(s.getStatusSewa())) {
            penyewaanDikembalikan = s;
            break;
        }
    }
    
    if (penyewaanDikembalikan == null) {
        System.out.println("Penyewaan tidak ditemukan atau sudah dikembalikan!");
        return;
    }
    
    // Tampilkan detail penyewaan
    Kendaraan kendaraan = penyewaanDikembalikan.getKendaraan();
    LocalDate tanggalSelesai = penyewaanDikembalikan.getSelesai();
    LocalDate tanggalKembali = LocalDate.now();
    
    System.out.println("\nDetail Penyewaan:");
    System.out.println("Kendaraan: " + kendaraan.getMerk() + " " + kendaraan.getModel());
    System.out.println("Penyewa: " + penyewaanDikembalikan.getPelanggan().getNama());
    System.out.println("Tanggal Selesai: " + tanggalSelesai);
    System.out.println("Tanggal Kembali: " + tanggalKembali);
    
    double denda = 0;
    boolean telat = false;
    
    // Tanya apakah pengembalian telat
    System.out.print("\nApakah pengembalian telat? (y/n): ");
    String jawabanTelat = in.nextLine();
    
    if (jawabanTelat.equalsIgnoreCase("y")) {
        telat = true;
        System.out.print("Masukkan berapa hari telat: ");
        int hariTelat = in.nextInt();
        in.nextLine();
        
        // Hitung denda (10% dari tarif sewa per hari untuk setiap hari keterlambatan)
        double tarifHarian = kendaraan.getTarifSewaPerHari();
        denda = hariTelat * tarifHarian * 0.1;
        
        System.out.println("\n=== PERHITUNGAN DENDA ===");
        System.out.println("Tarif sewa per hari: Rp" + tarifHarian);
        System.out.println("Jumlah hari telat: " + hariTelat + " hari");
        System.out.println("Denda per hari (10%): Rp" + (tarifHarian * 0.1));
        System.out.println("Total denda: Rp" + denda);
        System.out.println("=========================");
    } else {
        System.out.println("Pengembalian tepat waktu, tidak ada denda.");
    }
    
    System.out.print("\nKonfirmasi pengembalian? (y/n): ");
    String konfirmasi = in.nextLine();
    
    if (konfirmasi.equalsIgnoreCase("y")) {
        LogistikController.kembalikanKendaraan(penyewaanDikembalikan, denda);
        System.out.println("Pengembalian berhasil!");
        System.out.println("Status kendaraan: TERSEDIA");
        if (telat) {
            System.out.println("Denda yang dibayar: Rp" + denda);
        }
    } else {
        System.out.println("Pengembalian dibatalkan.");
    }
}

    private static void tampilkanDaftarPenjualan() {
        System.out.println("\n--- DAFTAR PENJUALAN ---");
        if (LogistikController.getPenjualanList().isEmpty()) {
            System.out.println("Belum ada transaksi penjualan.");
        } else {
            for (var penjualan : LogistikController.getPenjualanList()) {
                tampilkanDetailPenjualan(penjualan);
            }
        }
    }

    private static void tampilkanDetailPenjualan(Penjualan penjualan) {
        Kendaraan kendaraan = penjualan.getKendaraan();
        System.out.println("ID: " + penjualan.getId() +
                " | Tanggal: " + penjualan.getTanggal() +
                " | Harga: Rp" + penjualan.getHarga() +
                " | Pembeli: " + penjualan.getPelanggan().getNama() +
                " | Kendaraan: " + kendaraan.getMerk() + " " + kendaraan.getModel() +
                " (" + kendaraan.getJenis() + ")");
    }

    private static void tampilkanDaftarPenyewaan() {
        System.out.println("\n--- DAFTAR PENYEWAAN ---");
        if (LogistikController.getSewaList().isEmpty()) {
            System.out.println("Belum ada transaksi penyewaan.");
        } else {
            for (var penyewaan : LogistikController.getSewaList()) {
                tampilkanDetailPenyewaan(penyewaan);
            }
        }
    }

    private static void tampilkanDetailPenyewaan(Penyewaan penyewaan) {
        Kendaraan kendaraan = penyewaan.getKendaraan();
        String status = "SELESAI".equals(penyewaan.getStatusSewa()) ? 
                       "(Sudah Dikembalikan)" : "(Masih Disewa)";
        
        System.out.println("ID: " + penyewaan.getId() + " " + status +
                " | Penyewa: " + penyewaan.getPelanggan().getNama() +
                " | Kendaraan: " + kendaraan.getMerk() + " " + kendaraan.getModel() +
                " (" + kendaraan.getJenis() + ")" +
                " | Tanggal: " + penyewaan.getMulai() + " s/d " + penyewaan.getSelesai() +
                " | Biaya: Rp" + penyewaan.getTotalBiaya() +
                " | Status: " + penyewaan.getStatusSewa());
    }

    private static void tampilkanDaftarPengembalian() {
        System.out.println("\n--- DAFTAR PENGEMBALIAN ---");
        if (LogistikController.getPengembalianList().isEmpty()) {
            System.out.println("Belum ada transaksi pengembalian.");
        } else {
            for (var pengembalian : LogistikController.getPengembalianList()) {
                tampilkanDetailPengembalian(pengembalian);
            }
        }
    }

private static void tampilkanDetailPengembalian(Pengembalian pengembalian) {
    System.out.println("ID Pengembalian: " + pengembalian.getId() +
            " | Tanggal Pengembalian: " + pengembalian.getTgl_pengembalian() +
            " | Denda: Rp" + pengembalian.getDenda());
    
    // Jika ada informasi penyewaan
    if (pengembalian.getPenyewaan() != null) {
        Penyewaan penyewaan = pengembalian.getPenyewaan();
        Kendaraan kendaraan = penyewaan.getKendaraan();
        System.out.println("   Penyewaan ID: " + penyewaan.getId() +
                " | Kendaraan: " + kendaraan.getMerk() + " " + kendaraan.getModel() +
                " | Penyewa: " + penyewaan.getPelanggan().getNama());
    }
}

    private static Kendaraan cariKendaraanById(String id) {
        for (Kendaraan kendaraan : LogistikController.getArmada()) {
            if (kendaraan.getId().equals(id)) {
                return kendaraan;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int pilih;
        
        do {
            menu();
            pilih = in.nextInt();
            in.nextLine();

            if (pilih == 1) {
                System.out.println("\n--- TAMBAH KENDARAAN ---");
                Kendaraan k = inputKendaraan();
                LogistikController.tambahKendaraan(k);
                System.out.println("Kendaraan berhasil ditambahkan ke armada!");
                
            } else if (pilih == 2) {
                tampilkanDaftarKendaraan();
                
            } else if (pilih == 3) {
                prosesJualKendaraan();
                
            } else if (pilih == 4) {
                prosesSewaKendaraan();
                
            } else if (pilih == 5) {
                prosesPengembalianKendaraan();
                
            } else if (pilih == 6) {
                tampilkanDaftarPenjualan();
                
            } else if (pilih == 7) {
                tampilkanDaftarPenyewaan();
                
            } else if (pilih == 8) {
                tampilkanDaftarPengembalian();
                
            } else if (pilih == 9) {
                System.out.println("Terima kasih telah menggunakan sistem logistik Dalex!");
                
            } else {
                System.out.println("Pilihan tidak valid!");
            }
            
        } while (pilih != 9);
        
        in.close();
    }
}