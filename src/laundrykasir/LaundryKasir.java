package laundrykasir;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;
import config.connection;

/**
 *
 * @author abdularifin
 */
public class LaundryKasir {

    public static void main(String[] args) {
        // Membuat instance dari class connection
        connection db = new connection();
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Mendapatkan objek Connection dengan memanggil method connect()
            conn = db.connect();
            String name = "admin";
            String username = "admin";
            String password = "admin"; // Password asli
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            // Menyiapkan statement untuk menyimpan pengguna
            String query = "INSERT INTO users (name, username, password, created_at, updated_at) VALUES (?, ?, ?, NOW(), NOW())";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, username);
            stmt.setString(3, hashedPassword);
            stmt.executeUpdate();

            // Memeriksa apakah koneksi berhasil atau gagal
            if (conn != null) {
                System.out.println("Koneksi berhasil dibuat.");
            } else {
                System.out.println("Koneksi gagal.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Menangani kesalahan SQL
        } finally {
            // Menutup koneksi dan statement
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Menangani kesalahan saat menutup
            }
        }
    }
}