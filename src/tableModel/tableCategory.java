/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.ModelCategory;

/**
 *
 * @author abdularifin
 */
public class tableCategory extends AbstractTableModel {

private List<ModelCategory> list= new ArrayList<>();
    
    public ModelCategory getCategory(int index){
        return list.get(index);
    }
    
    public void clear(){
        list.clear();
        fireTableDataChanged();
    }
  public void insertData(List<ModelCategory> categories) {
    // Loop untuk menambahkan setiap kategori dalam list
    for (ModelCategory category : categories) {
        list.add(category); // Menambahkan kategori ke dalam list
    }
    // Memberitahu tabel bahwa baris telah ditambahkan
    fireTableRowsInserted(list.size() - categories.size(), list.size() - 1);
}

    public void updatedData(int row, ModelCategory category){
     
        list.set(row,category);
        fireTableRowsUpdated(row, row);
        JOptionPane.showMessageDialog(null,category.getName() + " succesfully updated!");
    }
    public void deletedData(int row, ModelCategory category){
        list.remove(row);
        fireTableRowsDeleted(row,row);
        JOptionPane.showMessageDialog(null,category.getName() + " succesfully deleted!");
    }
    
    public void setData(List<ModelCategory> list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    private final String[] columnNames = {"no", "service name", "category", "price"};
    @Override
    public int getRowCount() {
           return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ModelCategory category = list.get(rowIndex);
        switch (columnIndex){
            case 0 :
                return rowIndex +1;
            case 1 :
                return category.getServiceName().getName();
            case 2 :
                return category.getName();
            case 3 :
                return category.getPrice();
            default :
                return null;
        }
        
    }
    
    @Override
    public String getColumnName (int column){
        return columnNames[column];
    }
}
