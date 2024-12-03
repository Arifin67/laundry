/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.ResultSet;
import config.connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;
import model.ModelLoginResult;


/**
 *
 * @author abdularifin
 */
public class ModelLogin {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String name;
    private String username;
    private String password;
    
    

    public ModelLoginResult processLogin(String username, String password) {
    connection db = new connection();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    boolean isAuthenticated = false;
    String storedUsername = null;
    String storedRole = null;

    try {
        conn = db.connect();
        // Siapkan query untuk mengambil hashed password berdasarkan username
        String query = "SELECT * FROM users WHERE username = ? ";
        pstmt = conn.prepareStatement(query);
        pstmt.setString(1, username);
        
        
        // Eksekusi query
        rs = pstmt.executeQuery();
        System.out.println(pstmt);
        // Cek apakah username ada
        if (rs.next()) {
            storedUsername = rs.getString("username");
            String hashedPassword = rs.getString("password");
            storedRole = rs.getString("role");

            // Cek password yang dimasukkan dengan hashed password
            isAuthenticated = BCrypt.checkpw(password, hashedPassword);
            if(isAuthenticated==true){
            // Menampilkan hasil
            System.out.println("Username: " + storedUsername);
            System.out.println("Password yang di-hash: " + hashedPassword);
            System.out.println("Autentikasi berhasil: " + isAuthenticated);
            }
            else{
                System.err.println("username or password is wrong!.");
            }
        } else {
            System.err.println("username or password is wrong!.");
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Menangani kesalahan SQL
    } finally {
        // Menutup koneksi dan statement
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Menangani kesalahan saat menutup
        }
    }

    return new ModelLoginResult(isAuthenticated, storedUsername, storedRole);// Mengembalikan hasil autentikasi
    }

  }
