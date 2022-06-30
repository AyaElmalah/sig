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
public class InvoiceLineTableModel extends AbstractTableModel {

    private ArrayList<InvoiceLine> data;
    private String[] cols = {"Item Name", "Item Price", "Count","Line Total"};

    public InvoiceLineTableModel(ArrayList<InvoiceLine> data) {
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
        InvoiceLine line = data.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return line.getItemName();
            case 1:
                return line.getItemPrice();
            case 2:
                return line.getCount();
            case 3:
                return line.getLineTotal();
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        return cols[column];
    }
    
    {
    
}}
