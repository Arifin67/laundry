/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.ModelUser;

/**
 *
 * @author abdularifin
 */
public class tableUser extends AbstractTableModel {

    private List<ModelUser> list= new ArrayList<>();
    
    public ModelUser getUser(int index){
        return list.get(index);
    }
    
    public void clear(){
        list.clear();
        fireTableDataChanged();
    }
    public void insertData(ModelUser user){
        list.add(user);
        fireTableRowsInserted(list.size() -1, list.size() -1 );
        
}
    public void updatedData(int row, ModelUser user){
     
        list.set(row,user);
        fireTableRowsUpdated(row, row);
        JOptionPane.showMessageDialog(null,user.getName() + " succesfully updated!");
    }
    public void deletedData(int row, ModelUser user){
        list.remove(row);
        fireTableRowsDeleted(row,row);
        JOptionPane.showMessageDialog(null,user.getName() + " succesfully deleted!");
    }
    
    public void setData(List<ModelUser> list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    private final String[] columnNames = {"no", "name", "username", "role"};
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
        ModelUser user = list.get(rowIndex);
        switch (columnIndex){
            case 0 :
                return rowIndex +1;
            case 1 :
                return user.getName();
            case 2 :
                return user.getUsername();
            case 3 :
                return user.getRole();
            default :
                return null;
        }
        
    }
    
    @Override
    public String getColumnName (int column){
        return columnNames[column];
    }
}
