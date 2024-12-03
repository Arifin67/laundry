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
import model.ModelTransaction;

/**
 *
 * @author abdularifin
 */
public class tableTransaction extends AbstractTableModel {

    private List<ModelTransaction> list= new ArrayList<>();
    
    public ModelTransaction getTransaction(int index){
        return list.get(index);
    }
    
    public void clear(){
        list.clear();
        fireTableDataChanged();
    }
public void insertData(List<ModelTransaction> transactions) {
    int initialSize = list.size(); // Get the initial size of the list

    list.addAll(transactions); // Add all elements from the given list to the existing list

    // Notify the table that new rows were added
    fireTableRowsInserted(initialSize, list.size() - 1);
}


    public void updatedData(int row, ModelTransaction transaction){
     
        list.set(row,transaction);
        fireTableRowsUpdated(row, row);
        JOptionPane.showMessageDialog(null,transaction.getCategoryId() + " succesfully updated!");
    }
    public void deletedData(int row, ModelCategory category){
        list.remove(row);
        fireTableRowsDeleted(row,row);
        JOptionPane.showMessageDialog(null,category.getName() + " succesfully deleted!");
    }
    
    public void setData(List<ModelTransaction> list){
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
        ModelTransaction transaction = list.get(rowIndex);
        switch (columnIndex){
            case 0 :
                return rowIndex +1;
            case 1 :
                return transaction.getCustomerName().getCustomerName();
            case 2 :
                return transaction.getCategoryName().getServiceName().getName();
            case 3 :
                return transaction.getCategoryName().getName();
            case 4 :
                return transaction.getWeight();
            case 5 :
                return transaction.getPrice();
            default :
                return null;
        }
        
    }
    
    @Override
    public String getColumnName (int column){
        return columnNames[column];
    }

    
}
