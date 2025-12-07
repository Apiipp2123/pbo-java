import view.ViewLogistik;

public class Main {
    public static void main(String[] args) {
        // ViewLogistik.main(args);
        try {
            var conn = DatabaseConnection.getConnection();
            System.out.println("✅ Koneksi database SUKSES!");
            conn.close();
        } catch (Exception e) {
            System.err.println("❌ Gagal koneksi:");
            e.printStackTrace();
        }
    }
}