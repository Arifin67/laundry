/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import config.connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
 
        try{
        String query = "INSERT INTO customers(customer_name,phone_number,address,created_at,updated_at) VALUES(?,?,?,NOW(),NOW())";
        pstmt2 = conn.prepareStatement(query);
        pstmt2.setString(1, cs.getCustomerName());
        pstmt2.setString(2, cs.getPhoneNumber());
        pstmt2.setString(3, cs.getAddress());
        
        
        pstmt2.executeUpdate();
        
        pstmt2.close();
        pstmt1.close();
        }catch (Exception e){
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

    
}
