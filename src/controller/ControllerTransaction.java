/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import config.connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import model.ModelCategory;
import model.ModelCustomer;
import model.ModelService;
import model.ModelTransaction;
import model.ModelUser;
import services.TransactionInterface;

/**
 *
 * @author abdularifin
 */
public class ControllerTransaction implements TransactionInterface {
    
    private Connection conn;
    private connection db;
    public ControllerTransaction (){
        db = new connection();
        conn = db.connect();
    }

    @Override
    public int addTransaction(ModelService service, ModelCategory category, ModelUser user, ModelCustomer customer, ModelTransaction transaction) {
        PreparedStatement pstmt = null;
        int transactionId = 0;
        
        

    try {
        // SQL query to insert a new customer
        String query = "INSERT INTO transaction (category_id, customer_id, status, weight, price, created_at)"
               + " VALUES ("
               + "(SELECT id FROM category WHERE name::text = ? and service_id = (select id from service where name = ?)),"
               + "(SELECT id FROM customers WHERE customer_name = ?),"
               + "false, ?, ?, NOW()) RETURNING id";

        
        // Prepare the statement
        pstmt = conn.prepareStatement(query);
        

        // Set the parameters
        pstmt.setString (1, category.getName());
        pstmt.setString (2, service.getName());
        pstmt.setString (3, customer.getCustomerName());
//        pstmt.setString (3, user.getName());
        pstmt.setDouble(4, transaction.getWeight());
        pstmt.setDouble(5, transaction.getPrice());
        System.out.println("Executing Query: " + pstmt.toString());
        // Execute the update
        
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            transactionId = rs.getInt("id");
        }
    } catch (Exception e) {
        e.printStackTrace();
        }
     return transactionId;
    }

    @Override
    public void editTransaction(ModelTransaction model) {
        PreparedStatement pstmt = null;
        
        
        try {
            String query = "UPDATE transaction SET status=true, updated_at = NOW() WHERE id = ?";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, model.getId()); // Assuming the id is a unique identifier
            
            pstmt.executeUpdate();
            System.out.println("Executing Query: " + pstmt.toString());
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTransaction(ModelService service, ModelCategory category, ModelUser user, ModelCustomer customer, ModelTransaction transaction) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ModelTransaction> showTransaction() {
    List<ModelTransaction> transactions = new ArrayList<>();
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        // SQL query untuk mengambil data transaksi
       String query = "SELECT t.id, "
                + "c.id as customer_id, c.customer_name, "
                + "cat.id as cat_id, cat.name AS category_name, "
                + "s.id as service_id, s.name AS service_name, " // Added missing comma here
                + "t.weight, t.price, t.status, t.created_at, t.updated_at "
                + "FROM transaction t "
                + "JOIN customers c ON t.customer_id = c.id "
                + "JOIN category cat ON t.category_id = cat.id "
                + "JOIN service s ON cat.service_id = s.id";


        // Siapkan statement
        pstmt = conn.prepareStatement(query);

        System.out.println("Executing Query: " + pstmt.toString());
        // Eksekusi query
        rs = pstmt.executeQuery();

        // Iterasi hasil dan tambahkan ke daftar transaksi
        while (rs.next()) {
            ModelTransaction transaction = new ModelTransaction();
            ModelCustomer customer = new ModelCustomer();
            ModelCategory category = new ModelCategory();
            ModelService service = new ModelService();
            // Set nilai dari hasil query ke model
            transaction.setId(rs.getInt("id"));
            //set customer
            customer.setId(rs.getInt("customer_id"));
            customer.setCustomerName(rs.getString("customer_name"));
            transaction.setCustomerId(customer);
            transaction.setCustomerName(customer);
            //set category
            category.setId(rs.getInt("cat_id"));
            category.setName(rs.getString("category_name"));
            category.setServiceId(service);
            category.setServiceName(service);
            transaction.setCategoryId(category);
            transaction.setCategoryName(category);
            
            service.setId(rs.getInt("service_id"));
            service.setName(rs.getString("service_name"));
            
            transaction.setWeight(rs.getInt("weight"));
            transaction.setPrice(rs.getInt("price"));
            transaction.setStatus(rs.getBoolean("status"));
            transaction.setCreated_at(rs.getTimestamp("created_at"));
            transaction.setUpdated_at(rs.getTimestamp("updated_at"));

            // Tambahkan ke daftar
            transactions.add(transaction);
        }

    } catch (Exception e) {
        System.err.println("SQL Error: " + e.getMessage());
        e.printStackTrace();
    } finally {
        // Tutup resources
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    return transactions;
}
    
@Override
public List<ModelTransaction> showTransactionByNow() {
    List<ModelTransaction> transactions = new ArrayList<>();
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        // SQL query untuk mengambil data transaksi
      String query = "SELECT t.id, "
                + "c.id as customer_id, c.customer_name, "
                + "cat.id as cat_id, cat.name AS category_name, "
                + "s.id as service_id, s.name AS service_name, "
                + "t.weight, t.price, t.status, t.created_at, t.updated_at "
                + "FROM transaction t "
                + "JOIN customers c ON t.customer_id = c.id "
                + "JOIN category cat ON t.category_id = cat.id "
                + "JOIN service s ON cat.service_id = s.id "
                + "WHERE DATE(t.created_at) = CURRENT_DATE";



        // Siapkan statement
        pstmt = conn.prepareStatement(query);
        System.out.println("Executing Query: " + pstmt.toString());
        // Eksekusi query
        rs = pstmt.executeQuery();

        // Iterasi hasil dan tambahkan ke daftar transaksi
        while (rs.next()) {
            ModelTransaction transaction = new ModelTransaction();
            ModelCustomer customer = new ModelCustomer();
            ModelCategory category = new ModelCategory();
            ModelService service = new ModelService();
            // Set nilai dari hasil query ke model
            transaction.setId(rs.getInt("id"));
            //set customer
            customer.setId(rs.getInt("customer_id"));
            customer.setCustomerName(rs.getString("customer_name"));
            transaction.setCustomerId(customer);
            transaction.setCustomerName(customer);
            //set category
            category.setId(rs.getInt("cat_id"));
            category.setName(rs.getString("category_name"));
            category.setServiceId(service);
            category.setServiceName(service);
            transaction.setCategoryId(category);
            transaction.setCategoryName(category);
            
            service.setId(rs.getInt("service_id"));
            service.setName(rs.getString("service_name"));
            
            transaction.setWeight(rs.getInt("weight"));
            transaction.setPrice(rs.getInt("price"));
            transaction.setStatus(rs.getBoolean("status"));
            transaction.setCreated_at(rs.getTimestamp("created_at"));
            transaction.setUpdated_at(rs.getTimestamp("updated_at"));

            // Tambahkan ke daftar
            transactions.add(transaction);
        }

    } catch (Exception e) {
        System.err.println("SQL Error: " + e.getMessage());
        e.printStackTrace();
    } finally {
        // Tutup resources
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    return transactions;
}
    
@Override
    public double showTransactionForProfit() {
    double totalPrice = 0;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        // SQL query to calculate the total price for transactions with status true and updated_at today
        String query = "SELECT SUM(t.price) AS total_price "
                     + "FROM transaction t "
                     + "WHERE t.status = true "
                     + "AND DATE(t.updated_at) = CURRENT_DATE";

        // Prepare the statement
        pstmt = conn.prepareStatement(query);
        System.out.println("Executing Query: " + pstmt.toString());
        // Execute the query
        rs = pstmt.executeQuery();

        // If result exists, retrieve the total price
        if (rs.next()) {
            totalPrice = rs.getDouble("total_price");
        }

    } catch (Exception e) {
        System.err.println("SQL Error: " + e.getMessage());
        e.printStackTrace();
    } finally {
        // Close resources
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    return totalPrice;
}


}
