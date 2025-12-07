package view;

import java.util.Scanner;
import controller.LogistikController;
import model.*;

public class ViewLogistik {
    static Scanner in = new Scanner(System.in);

    public static void menu() {
        System.out.println("\n=== SISTEM LOGISTIK DALEX ===");
        System.out.println("1. Tambah Kendaraan (Mobil/Motor)");
        System.out.println("2. Lihat Semua Kendaraan");
        System.out.println("3. Beli Kendaraan");
        System.out.println("4. Jual Kendaraan");
        System.out.println("5. Sewa Kendaraan");
        System.out.println("6. Lihat Pembelian");
        System.out.println("7. Lihat Penyewaan");
        System.out.println("8. Keluar");
        System.out.print("Pilih: ");
    }

    public static Kendaraan inputKendaraan() {
        System.out.print("Masukkan ID Kendaraan: ");
        String id = in.nextLine();

        System.out.print("Jenis (1 = Mobil, 2 = Motor): ");
        int pilihJenis = in.nextInt();
        in.nextLine(); // Konsumsi newline

        System.out.print("Merk (contoh: Honda, Kawasaki): ");
        String merk = in.nextLine();

        System.out.print("Model (contoh: Brio, Ninja ZX-25R): ");
        String model = in.nextLine();

        System.out.print("Harga Jual (angka): ");
        double hargaJual = in.nextDouble();

        System.out.print("Tarif Sewa per Hari (angka): ");
        double tarifSewa = in.nextDouble();

        in.nextLine(); // Konsumsi newline

        System.out.print("Lokasi (contoh: Jakarta, Surabaya): ");
        String lokasi = in.nextLine();

        System.out.print("Tahun Produksi (contoh: 2020): ");
        int tahun = in.nextInt();
        in.nextLine();

        switch (pilihJenis) {
            case 1:
                System.out.print("Kapasitas Penumpang: ");
                int kapasitas = in.nextInt();
                in.nextLine();
                System.out.print("Tipe Transmisi (Manual/Automatic): ");
                String transmisi = in.nextLine();
                return new Mobil(id, merk, model, hargaJual, tarifSewa, "TERSEDIA", lokasi, tahun, kapasitas, transmisi);

            case 2:
                // Input khusus Motor
                System.out.print("Kapasitas Mesin (cc): ");
                double cc = in.nextDouble();
                in.nextLine();
                System.out.print("Ada Box? (true/false): ");
                boolean adaBox = in.nextBoolean();
                in.nextLine();
                return new Motor(id, merk, model, hargaJual, tarifSewa, "TERSEDIA", lokasi, tahun, cc, adaBox);

            default:
                System.out.println("Jenis tidak valid! Default ke Mobil.");
                return new Mobil(id, merk, model, hargaJual, tarifSewa, "TERSEDIA", lokasi, tahun, 5, "Automatic");
        }
    }

    public static void main(String[] args) {
        int pilih;
        do {
            menu();
            pilih = in.nextInt();
            in.nextLine(); // Konsumsi newline

            switch (pilih) {
                case 1: // Tambah Kendaraan
                    System.out.println("\n--- TAMBAH KENDARAAN ---");
                    Kendaraan k = inputKendaraan();
                    LogistikController.tambahKendaraan(k);
                    System.out.println("Kendaraan berhasil ditambahkan ke armada!");
                    break;

                case 2: // Lihat Semua Kendaraan
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
                    break;

                case 3: // Jual Kendaraan
                    System.out.println("\n--- JUAL KENDARAAN ---");
                    System.out.print("Masukkan ID kendaraan yang ingin dijual: ");
                    String idJual = in.nextLine();
                    
                    Kendaraan kendaraanJual = null;
                    for (Kendaraan kendaraan : LogistikController.getArmada()) {
                        if (kendaraan.getId().equals(idJual)) {
                            kendaraanJual = kendaraan;
                            break;
                        }
                    }

                    if (kendaraanJual == null) {
                        System.out.println("Kendaraan tidak ditemukan!");
                        break;
                    }

                    System.out.print("Masukkan harga penjualan: ");
                    double hargaJual = in.nextDouble();
                    in.nextLine();
                    
                    System.out.print("Nama Pembeli: ");
                    String namaPembeliJual = in.nextLine();
                    
                    LogistikController.jualKendaraan(kendaraanJual, hargaJual, namaPembeliJual);
                    System.out.println("Kendaraan berhasil dijual!");
                    break;

                case 4: // Sewa Kendaraan
                    System.out.println("\n--- SEWA KENDARAAN ---");
                    System.out.print("Masukkan ID kendaraan yang ingin disewa: ");
                    String idSewa = in.nextLine();

                    Kendaraan kendaraanDisewa = null;
                    for (Kendaraan kendaraan : LogistikController.getArmada()) {
                        if (kendaraan.getId().equals(idSewa)) {
                            kendaraanDisewa = kendaraan;
                            break;
                        }
                    }

                    if (kendaraanDisewa == null) {
                        System.out.println("Kendaraan tidak ditemukan!");
                        break;
                    }

                    System.out.print("Nama Penyewa: ");
                    String nama = in.nextLine();

                    System.out.print("Jumlah Hari Sewa: ");
                    int hari = in.nextInt();
                    in.nextLine();

                    double biaya = kendaraanDisewa.getTarifSewaPerHari() * hari;
                    LogistikController.sewaKendaraan(kendaraanDisewa, nama, hari, biaya);
                    System.out.println("Kendaraan berhasil disewa! Total: Rp" + biaya);
                    break;

                case 5: // Lihat Penjualan
                    System.out.println("\n--- DAFTAR PENJUALAN ---");
                    if (LogistikController.getPenjualanList().isEmpty()) {
                        System.out.println("Belum ada transaksi penjualan.");
                    } else {
                        for (var p : LogistikController.getPenjualanList()) {
                            Kendaraan knd = p.getKendaraan();
                            System.out.println("ID: " + p.getId() +
                                    " | Tanggal: " + p.getTanggal() +
                                    " | Harga: " + p.getHarga() +
                                    " | Pembeli: " + p.getPelanggan().getNama() +
                                    " | Kendaraan: " + knd.getMerk() + " " + knd.getModel() +
                                    " (" + knd.getJenis() + ")");
                        }
                    }
                    break;

                case 6: // Lihat Penyewaan
                    System.out.println("\n--- DAFTAR PENYEWAAN ---");
                    if (LogistikController.getSewaList().isEmpty()) {
                        System.out.println("Belum ada transaksi penyewaan.");
                    } else {
                        for (var s : LogistikController.getSewaList()) {
                            Kendaraan knd = s.getKendaraan();
                            System.out.println("ID: " + s.getId() +
                                    " | Penyewa: " + s.getPelanggan().getNama() +
                                    " | Kendaraan: " + knd.getMerk() + " " + knd.getModel() +
                                    " (" + knd.getJenis() + ")" +
                                    " | Tanggal: " + s.getMulai() + " s/d " + s.getSelesai() +
                                    " | Biaya: " + s.getTotalBiaya());
                        }
                    }
                    break;

                case 7:
                    System.out.println("Terima kasih telah menggunakan sistem logistik Dalex!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilih != 7);
    }
}