package laundrykasir;

import java.util.Scanner;
import model.ModelLogin; // Pastikan ModelLogin ada di package ini
import model.ModelLoginResult;
/**
 *
 * @author abdularifin
 */
public class testLogin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Meminta input username dan password dari pengguna
        System.out.print("Masukkan username: ");
        String username = scanner.nextLine();
        
        System.out.print("Masukkan password: ");
        String password = scanner.nextLine();

        // Membuat instance dari ModelLogin
        ModelLogin modelLogin = new ModelLogin();

        // Memanggil metode processLogin
        ModelLoginResult result = modelLogin.processLogin(username, password);
        
        // Menampilkan hasil autentikasi
        if (result.isAuthenticated()) {
            System.out.println("Login berhasil! Selamat datang, " + result.getUsername() + "!");
        } else {
            System.out.println("Login gagal. Username atau password salah.");
        }
        
        scanner.close(); // Menutup scanner
    }
}