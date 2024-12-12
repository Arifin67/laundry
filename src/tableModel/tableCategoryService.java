package tableModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.ModelService;
import model.ModelCategory;

/**
 * Table model untuk menampilkan layanan dan kategori dalam form transaksi.
 */
public class tableCategoryService extends AbstractTableModel {

    private List<Object[]> data = new ArrayList<>();
    private final String[] columnNames = {"Service", "Category"};

    /**
     * Menambahkan baris baru ke tabel.
     * 
     * @param service  layanan yang dipilih
     * @param category kategori yang dipilih
     */
    public void addRow(ModelService service, ModelCategory category) {
        data.add(new Object[]{service.getName(), category.getName()});
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    /**
     * Menghapus semua data dalam tabel.
     */
    public void clear() {
        data.clear();
        fireTableDataChanged();
    }

    /**
     * Mendapatkan jumlah baris dalam tabel.
     */
    @Override
    public int getRowCount() {
        return data.size();
    }

    /**
     * Mendapatkan jumlah kolom dalam tabel.
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Mendapatkan nilai pada baris dan kolom tertentu.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }

    /**
     * Mendapatkan nama kolom berdasarkan indeks.
     */
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
