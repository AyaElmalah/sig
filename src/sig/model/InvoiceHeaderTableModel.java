/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sig.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ENG AMR
 */
public class InvoiceHeaderTableModel extends AbstractTableModel  {
        private ArrayList<InvoiceHeader> data;
   private String[] cols = {"num", "Customer ", " Date" ,"Invoice Total"};

    public InvoiceHeaderTableModel(ArrayList<InvoiceHeader> data) {
        this.data = data;
    }
    
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader header = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return header.getNum();
            case 1:
                return header.getCustomer();
            case 2:
                return header.getDate();
            case 3:
                return header.getInvoiceTotal();
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        return cols[column];
    }
    
    
    
    
}
