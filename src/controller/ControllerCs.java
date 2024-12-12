/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import config.connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.ModelCustomer;
import org.mindrot.jbcrypt.BCrypt;
import services.CustomerInterface;

/**
 *
 * @author abdularifin
 */
public class ControllerCs implements CustomerInterface {

    private Connection conn;
    private connection db;
    public ControllerCs (){
        db = new connection();
        conn = db.connect();
    }

    @Override
    public void addUser(ModelCustomer cs) {
    PreparedStatement pstmt = null;

    try {
        // SQL query to insert a new customer
        String query = "INSERT INTO customers (customer_name, phone_number, address, created_at, updated_at) VALUES (?, ?, ?, NOW(), NOW())";

        // Prepare the statement
        pstmt = conn.prepareStatement(query);

        // Set the parameters
        pstmt.setString(1, cs.getCustomerName());
        pstmt.setString(2, cs.getPhoneNumber());
        pstmt.setString(3, cs.getAddress());

        // Execute the update
        int rowsInserted = pstmt.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Customer added successfully!");
        }
    } catch (Exception e) {
        e.printStackTrace();
    } 
}


        @Override
        public void updateUser(ModelCustomer cs) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void deleteUser(ModelCustomer cs) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public List<ModelCustomer> showUser() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public List<ModelCustomer> findUserByName(String name) {
        List<ModelCustomer> customers = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

     try {
        // Query untuk mencari user berdasarkan nama
        String query = "SELECT * FROM customers WHERE customer_name ILIKE ?";
        
        // Siapkan statement
        pstmt = conn.prepareStatement(query);
        
        // Set parameter pencarian
        pstmt.setString(1, "%" + name + "%");
        
        // Eksekusi query
        rs = pstmt.executeQuery();
        
        // Iterasi hasil pencarian
        while (rs.next()) {
            ModelCustomer customer = new ModelCustomer();
            customer.setId(rs.getInt("id"));
            customer.setCustomerName(rs.getString("customer_name"));
            customer.setPhoneNumber(rs.getString("phone_number"));
            customer.setAddress(rs.getString("address"));
            

            // Tambahkan hasil ke daftar pelanggan
            customers.add(customer);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } 

    return customers;
}

    
}
