/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.ModelCustomer;

/**
 *
 * @author abdularifin
 */
public class tableCustomer extends AbstractTableModel {

   private List<ModelCustomer> list= new ArrayList<>();
    
    public ModelCustomer getCs(int index){
        return list.get(index);
    }
    
    public void clear(){
        list.clear();
        fireTableDataChanged();
    }
    public void insertData(ModelCustomer cs){
        list.add(cs);
        fireTableRowsInserted(list.size() -1, list.size() -1 );
        
}
    public void updatedData(int row, ModelCustomer cs){
     
        list.set(row,cs);
        fireTableRowsUpdated(row, row);
        JOptionPane.showMessageDialog(null,cs.getCustomerName()+ " succesfully updated!");
    }
    public void deletedData(int row, ModelCustomer cs){
        list.remove(row);
        fireTableRowsDeleted(row,row);
        JOptionPane.showMessageDialog(null,cs.getCustomerName()+ " succesfully deleted!");
    }
    
    public void setData(List<ModelCustomer> list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    private final String[] columnNames = {"no", "name", "phone number", "address"};
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
        ModelCustomer cs = list.get(rowIndex);
        switch (columnIndex){
            case 0 :
                return rowIndex +1;
            case 1 :
                return cs.getCustomerName();
            case 2 :
                return cs.getPhoneNumber();
            case 3 :
                return cs.getAddress();
            default :
                return null;
        }
        
    }
    
    @Override
    public String getColumnName (int column){
        return columnNames[column];
    }
    
    
}
