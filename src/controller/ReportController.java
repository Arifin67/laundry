/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.sun.jdi.connect.spi.Connection;
import config.connection;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import services.ReportInterface;

/**
 *
 * @author abdularifin
 */
public class ReportController implements ReportInterface {
    private java.sql.Connection conn;
    private connection db;
    
    public ReportController (){
        db = new connection();
        conn = db.connect();
    }
    @Override
    public void PrintStruk(int id) {
    try {
        // Path ke file laporan Jasper
        String reportPath = "src/report/cashierLaundry.jasper";
        
        // Parameter yang akan diberikan ke laporan
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("transaction_id", id);
        
        // Muat file laporan
//        JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportPath);
            
        // Buat koneksi ke database
        
        
        // Isi laporan dengan data dan parameter
        JasperPrint print = JasperFillManager.fillReport(reportPath, parameters,conn);
        
        // Tampilkan laporan menggunakan JasperViewer
        JasperViewer viewer = new JasperViewer(print, false);
        viewer.setTitle("Print Struk");
        viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
        viewer.setVisible(true);
        
        // Tutup koneksi
        conn.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}

    
}
