/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import config.connection;
import java.util.List;
import model.ModelUser;
import services.UserInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;




/**
 *
 * @author abdularifin
 */
public class ControllerUser implements UserInterface {

    private Connection conn;
    private connection db;
    public ControllerUser (){
        db = new connection();
        conn = db.connect();
    }
    @Override
    public void addUser(ModelUser model) {
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;


        
        String hashedPass =BCrypt.hashpw(model.getPassword(), BCrypt.gensalt(10));
        model.setPassword(hashedPass);
        
        String role ="user";
        model.setRole(role);
        
        try{
        
        String checkedName = "Select name from users where name = ?";
        pstmt1 = conn.prepareStatement(checkedName);
        pstmt1.setString(1, model.getName());
        ResultSet rs = pstmt1.executeQuery();

        // If name exists, exit
        if (rs.next()) {
            
            JOptionPane.showMessageDialog(null, "User already exists!");
            return;
        }
        
        
        
        
        String query = "INSERT INTO users(name,username,password,role,created_at,updated_at) VALUES(?,?,?,?,NOW(),NOW())";
        pstmt2 = conn.prepareStatement(query);
        pstmt2.setString(1, model.getName());
        pstmt2.setString(2, model.getUsername());
        pstmt2.setString(3, model.getPassword());
        pstmt2.setString(4, model.getRole());
        
        pstmt2.executeUpdate();
        
        pstmt2.close();
        pstmt1.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(ModelUser model) {
        PreparedStatement pstmt = null;
        String hashedPass = BCrypt.hashpw(model.getPassword(), BCrypt.gensalt(10));
        model.setPassword(hashedPass);
        try {
            String query = "UPDATE users SET name = ?, username = ?, password = ?, updated_at = NOW() WHERE id = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, model.getName());
            pstmt.setString(2, model.getUsername());
            pstmt.setString(3, model.getPassword());
            pstmt.setInt(4, model.getId()); // Assuming the id is a unique identifier
            
            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(ModelUser model) {
        PreparedStatement pstmt = null;
        try {
            String query = "DELETE FROM users WHERE id = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, model.getId()); // Assuming id is the unique identifier
            
            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ModelUser> showUser() {
        List<ModelUser> users = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM users";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
            ModelUser user = new ModelUser();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            user.setCreatedAt(rs.getDate("created_at")); // Use camelCase
            user.setUpdatedAt(rs.getDate("updated_at")); // Use camelCase
                
                users.add(user);
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
    
}
