/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import config.connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.ModelCategory;
import model.ModelService;
import services.CategoryInterface;

/**
 *
 * @author abdularifin
 */
public class ControllerCategory implements CategoryInterface {

    private final Connection conn;
    private final connection db;

    public ControllerCategory() {
        db = new connection();
        conn = db.connect();
    }
    @Override
 public void addCategory(ModelService service, List<ModelCategory> categories) {
    PreparedStatement pstmt = null;
    PreparedStatement pstmtCheckService = null;
    ResultSet rs = null;

    try {
        // Langkah 1: Cek apakah service dengan nama sudah ada di database
        String queryCheckService = "SELECT id FROM service WHERE name = ?";
        pstmtCheckService = conn.prepareStatement(queryCheckService);
        pstmtCheckService.setString(1, service.getName()); // Nama service yang ingin dicek
        rs = pstmtCheckService.executeQuery();

        int serviceId;

        // Langkah 2: Jika service sudah ada, ambil service_id-nya
        if (rs.next()) {
            serviceId = rs.getInt("id"); // Ambil ID dari service yang ada
            service.setId(serviceId); // Set ID pada model service
            System.out.println("Service sudah ada dengan ID: " + serviceId);
        } else {
            // Langkah 3: Jika service belum ada, tambahkan service baru
            String queryInsertService = "INSERT INTO service (name) VALUES (?)";
            pstmt = conn.prepareStatement(queryInsertService, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, service.getName());
            pstmt.executeUpdate();

            // Ambil ID service yang baru saja ditambahkan
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                serviceId = rs.getInt(1); // Ambil ID yang dihasilkan oleh insert
                service.setId(serviceId); // Set ID pada model service
                System.out.println("Service berhasil ditambahkan dengan ID: " + serviceId);
            } else {
                throw new SQLException("Gagal mendapatkan ID untuk service yang baru ditambahkan");
            }
        }

        // Langkah 4: Menambahkan kategori-kategori terkait dengan service_id yang sudah ada
        String queryInsertCategory = "INSERT INTO category (service_id, name, price) VALUES (?, CAST(? AS category_type), ?)";
        pstmt = conn.prepareStatement(queryInsertCategory);

        // Iterasi melalui list kategori dan memasukkan data ke dalam batch
        for (ModelCategory category : categories) {
            String categoryName = category.getName();

            // Validasi jika categoryName ada dalam ENUM category_type
            if (!isValidCategoryName(categoryName)) {
                System.out.println("Invalid category name: " + categoryName);
                continue; // Lewati kategori yang tidak valid
            }

            // Mengisi parameter untuk insert category
            pstmt.setInt(1, service.getId()); // Menggunakan ID service yang ditemukan atau baru ditambahkan
            pstmt.setString(2, categoryName); // Nama kategori (akan di-cast ke category_type)
            pstmt.setInt(3, category.getPrice()); // Harga kategori

            // Tambahkan ke batch
            pstmt.addBatch();
        }

        // Eksekusi batch untuk menambahkan kategori
        pstmt.executeBatch();
        System.out.println("Semua kategori berhasil ditambahkan!");

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (pstmtCheckService != null) pstmtCheckService.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


// Fungsi untuk memeriksa apakah nama kategori valid sesuai dengan ENUM
private boolean isValidCategoryName(String categoryName) {
    // Memeriksa apakah categoryName termasuk dalam daftar nilai ENUM
    return "Standar".equals(categoryName) || "Premium".equals(categoryName) || "Super Premium".equals(categoryName);
}






    @Override
    public void editCategory(ModelService service, List<ModelCategory> categories) {
    PreparedStatement pstmtService = null;
    PreparedStatement pstmtCategory = null;
    ResultSet rs = null;

    try {
        // First, get the service ID based on the service name
        String queryService = "SELECT id FROM service WHERE name = ?";
        pstmtService = conn.prepareStatement(queryService);
        pstmtService.setString(1, service.getName());
        rs = pstmtService.executeQuery();
        
        // If service exists, retrieve the service ID
        int serviceId = -1;
        if (rs.next()) {
            serviceId = rs.getInt("id");
        }

        if (serviceId == -1) {
            System.out.println("Service not found!");
            return;
        }

        // Now, update categories related to the service
        String queryCategory = "UPDATE category SET name = CAST(? AS category_type), price = ? WHERE service_id = ? AND name = CAST(? AS category_type)";
        pstmtCategory = conn.prepareStatement(queryCategory);

        // Iterate through the categories and update them
        for (ModelCategory category : categories) {
            String categoryName = category.getName();
            int categoryPrice = category.getPrice();

            // Update category if it exists, or insert if it doesn't
            pstmtCategory.setString(1, categoryName);  // category name
            pstmtCategory.setInt(2, categoryPrice);    // category price
            pstmtCategory.setInt(3, serviceId);       // service_id
            pstmtCategory.setString(4, categoryName);  // category name (used in WHERE clause for matching)

            int rowsAffected = pstmtCategory.executeUpdate();
            
            // If no rows are updated, it means this category doesn't exist, so insert it
            if (rowsAffected == 0) {
                // Insert the new category if it doesn't exist already
                String insertQuery = "INSERT INTO category (service_id, name, price) VALUES (?, CAST(? AS category_type), ?)";
                pstmtCategory = conn.prepareStatement(insertQuery);
                pstmtCategory.setInt(1, serviceId);
                pstmtCategory.setString(2, categoryName);
                pstmtCategory.setInt(3, categoryPrice);
                pstmtCategory.executeUpdate();
            }
        }

        System.out.println("Category updated successfully!");

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (pstmtService != null) pstmtService.close();
            if (pstmtCategory != null) pstmtCategory.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}





    @Override
   public void deleteCategory(ModelCategory category) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        // First, check if the category exists in the database by using service_id and category name
        String queryCheckCategory = "SELECT * FROM category WHERE service_id = ? AND name = CAST(? AS category_type)";
        pstmt = conn.prepareStatement(queryCheckCategory);
        pstmt.setInt(1, category.getServiceId().getId());  // Assuming ModelCategory has getServiceId() that returns ModelService object
        pstmt.setString(2, category.getName());  // Category name
        rs = pstmt.executeQuery();

        // If category exists, proceed to delete
        if (rs.next()) {
            // Delete the category from the database
            String deleteQuery = "DELETE FROM category WHERE service_id = ? AND name = CAST(? AS category_type)";
            pstmt = conn.prepareStatement(deleteQuery);
            pstmt.setInt(1, category.getServiceId().getId());
            pstmt.setString(2, category.getName());

            int rowsAffected = pstmt.executeUpdate();
            
            // Check if the delete operation was successful
            if (rowsAffected > 0) {
                System.out.println("Category deleted successfully!");
            } else {
                System.out.println("Category deletion failed!");
            }
        } else {
            System.out.println("Category not found for deletion!");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


    @Override
    public List<ModelCategory> showCategory() {
    List<ModelCategory> categories = new ArrayList<>();
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        String query = "SELECT c.id AS category_id, " +
                       "s.id AS service_id, " +
                       "s.name AS service_name, " +
                       "c.name AS category_name, " +
                       "c.price AS category_price " +
                       "FROM category AS c " +
                       "INNER JOIN service AS s ON c.service_id = s.id";

        pstmt = conn.prepareStatement(query);
        rs = pstmt.executeQuery();

        while (rs.next()) {
            // Membuat instance ModelCategory
            ModelCategory category = new ModelCategory();
            category.setId(rs.getInt("category_id")); // Set ID kategori

            // Membuat instance ModelService untuk serviceId dan serviceName
            ModelService service = new ModelService();
            service.setId(rs.getInt("service_id")); // ID layanan
            service.setName(rs.getString("service_name")); // Nama layanan
            
            category.setServiceId(service); // Set referensi layanan
            category.setServiceName(service); // Set referensi layanan sebagai nama (jika dibutuhkan)
            
            category.setName(rs.getString("category_name")); // Nama kategori
            category.setPrice(rs.getInt("category_price")); // Harga kategori

            categories.add(category); // Tambahkan ke daftar
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return categories; // Kembalikan daftar kategori
        }
    }
    

