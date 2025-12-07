package view;

import java.util.Scanner;
import controller.LogistikController;
import model.*;

public class ViewLogistik {
    static Scanner in = new Scanner(System.in);

    public static void menu() {
        System.out.println("\n=== MENU LOGISTIK DALEX ===");
        System.out.println("1. Beli Kendaraan");
        System.out.println("2. Sewa Kendaraan");
        System.out.println("3. Lihat Armada");
        System.out.println("4. Lihat Pembelian");
        System.out.println("5. Lihat Penyewaan");
        System.out.println("6. Keluar");
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
        in.nextLine(); // Konsumsi newline

        if (pilihJenis == 1) {
            // Input khusus Mobil
            System.out.print("Kapasitas Penumpang: ");
            int kapasitas = in.nextInt();
            in.nextLine();
            System.out.print("Tipe Transmisi (Manual/Automatic): ");
            String transmisi = in.nextLine();
            return new Mobil(id, merk, model, hargaJual, tarifSewa, "TERSEDIA", lokasi, tahun, kapasitas, transmisi);

        } else if (pilihJenis == 2) {
            // Input khusus Motor
            System.out.print("Kapasitas Mesin (cc): ");
            double cc = in.nextDouble();
            in.nextLine();
            System.out.print("Ada Box? (true/false): ");
            boolean adaBox = in.nextBoolean();
            in.nextLine();
            return new Motor(id, merk, model, hargaJual, tarifSewa, "TERSEDIA", lokasi, tahun, cc, adaBox);

        } else {
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
                case 1:
                    System.out.println("\n--- BELI KENDARAAN ---");
                    Kendaraan k = inputKendaraan();
                    System.out.print("Masukkan harga pembelian: ");
                    double hargaBeli = in.nextDouble();
                    in.nextLine();
                    LogistikController.beliKendaraan(k, hargaBeli);
                    System.out.println("Kendaraan berhasil dibeli dan ditambahkan ke armada!");
                    break;

                case 2:
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

                case 3:
                    System.out.println("\n--- ARMADA KENDARAAN ---");
                    for (Kendaraan knd : LogistikController.getArmada()) {
                        System.out.println(knd); // toString() sudah di-override di Mobil/Motor
                    }
                    break;

                case 4:
                    System.out.println("\n--- DAFTAR PEMBELIAN ---");
                    for (var p : LogistikController.getPembelianList()) {
                        Kendaraan knd = p.getKendaraan();
                        System.out.println("ID: " + p.getId() +
                                " | Tanggal: " + p.getTanggal() +
                                " | Harga: " + p.getHarga() +
                                " | Kendaraan: " + knd.getMerk() + " " + knd.getModel() +
                                " (" + knd.getJenis() + ")");
                    }
                    break;

                case 5:
                    System.out.println("\n--- DAFTAR PENYEWAAN ---");
                    for (var s : LogistikController.getSewaList()) {
                        Kendaraan knd = s.getKendaraan();
                        System.out.println("ID: " + s.getId() +
                                " | Penyewa: " + s.getPelanggan().getNama() +
                                " | Kendaraan: " + knd.getMerk() + " " + knd.getModel() +
                                " (" + knd.getJenis() + ")" +
                                " | Tanggal: " + s.getMulai() + " s/d " + s.getSelesai() +
                                " | Biaya: " + s.getTotalBiaya());
                    }
                    break;

                case 6:
                    System.out.println("Terima kasih telah menggunakan sistem logistik Dalex!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilih != 6);
    }
}