/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author abdularifin
 */
public class connection {
    // URL database, username, dan password
    private final String url = "jdbc:postgresql://localhost:4321/LaundryNetbeans"; // ganti dengan nama database kamu
    private final String user = "postgres"; // ganti dengan username PostgreSQL kamu
    private final String password = "postgres"; // ganti dengan password PostgreSQL kamu
    
    // Method untuk mendapatkan koneksi ke database
    public Connection connect() {
        Connection conn = null;
        try {
            // Memuat driver PostgreSQL
            Class.forName("org.postgresql.Driver");
            
            // Membuat koneksi
            conn = DriverManager.getConnection(url, user, password);
            
            System.out.println("Koneksi berhasil!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver tidak ditemukan: " + e.getMessage());
        }
        
        return conn;
    }
}
