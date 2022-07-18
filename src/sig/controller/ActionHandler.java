
package sig.controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import sig.model.InvoiceHeader;
import sig.model.InvoiceHeaderTableModel;
import sig.model.InvoiceLine;
import sig.model.InvoiceLineTableModel;
import sig.view.InvoiceFrame;
import sig.view.NewInvoiceForm;
import sig.view.NewInvoiceLineForm;


public class ActionHandler  implements ActionListener, ListSelectionListener{
 
    private InvoiceFrame frame;
    

    public ActionHandler(InvoiceFrame frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case "Load File":
                loadFile();
                break;
            case "Save File":
                saveFile();
                break;
            case "Create New Invoice":
                createNewInvoice();
                break;
            case "Delete Invoice":
                deleteInvoice();
                
                break;
            case "New Line":
                newLine();
                break;
            case "Delete Line":
                deleteLine();
                break;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println("valueChanged : Row Selected");
        int selectedRow = frame.getInvHeaderTable().getSelectedRow();
        System.out.println(selectedRow);
        
      
        if(selectedRow < 0 ){
        }else {
            setDataToTextView(frame.getInvoiceHeadersList().get(selectedRow));
            
                 ArrayList<InvoiceLine> lines = frame.getInvoiceHeadersList().get(selectedRow < 0 ? 0 :selectedRow ).getLines();
        frame.getInvLineTable().setModel(new InvoiceLineTableModel(lines));
        frame.setInvoiceLinesList(lines);
        }
   
    }
void setDataToTextView(InvoiceHeader invoiceHeader){
frame.numLabel.setText(invoiceHeader.getNum()+"");
frame.dateLabel.setText(invoiceHeader.getDate()+"");
frame.customerLabel.setText(invoiceHeader.getCustomer()+"");
frame.invTotalTxt.setText(invoiceHeader.getInvoiceTotal()+"");
}
    private void loadFile() {
        try {
            JFileChooser fc = new JFileChooser();
            int result = fc.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fc.getSelectedFile();
                String headerStrPath = headerFile.getAbsolutePath();
                Path headerPath = Paths.get(headerStrPath);
                List<String> headerLines = Files.lines(headerPath).collect(Collectors.toList());
                // ["1,22-11-2020,Ali", "2,13-10-2021,Saleh"]
                ArrayList<InvoiceHeader> invoiceHeadersList = new ArrayList<>();
                for (String headerLine : headerLines) {
                    String[] parts = headerLine.split(",");
                    // parts = ["1", "22-11-2020", "Ali"]
                    // parts = ["2", "13-10-2021", "Saleh"]
                    int id = Integer.parseInt(parts[0]);
                    InvoiceHeader invHeader = new InvoiceHeader(id, parts[2], parts[1]);
                    invoiceHeadersList.add(invHeader);
                }
                System.out.println("check");
                result = fc.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String lineStrPath = fc.getSelectedFile().getAbsolutePath();
                    Path linePath = Paths.get(lineStrPath);
                    List<String> lineLines = Files.lines(linePath).collect(Collectors.toList());
                    // ["1,Mobile,3200,1", "1,Cover,20,2", "1,Headphone,130,1", "2,Laptop,4000,1", "2,Mouse,35,1"]
                    for (String lineLine : lineLines) {
                        String[] parts = lineLine.split(",");
                        // ["1","Mobile","3200","1"]
                        // ["1","Cover","20","2"]
                        // ["1","Headphone","130","1"]
                        // ["2","Laptop","4000","1"]
                        // ["2","Mouse","35","1"]
                        int invNum = Integer.parseInt(parts[0]);
                        double price = Double.parseDouble(parts[2]);
                        int count = Integer.parseInt(parts[3]);
                        InvoiceHeader header = getInvoiceHeaderById(invoiceHeadersList, invNum);
                        
                       InvoiceLine invLine = new InvoiceLine(parts[1], price, count, header);
                       // InvoiceLine invLine = new InvoiceLine(parts[1], price, count, header);
                        header.getLines().add(invLine);
                    }
                    frame.setInvoiceHeadersList(invoiceHeadersList);
                    
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private InvoiceHeader getInvoiceHeaderById(ArrayList<InvoiceHeader> invoices, int num) {
        for (InvoiceHeader invoice : invoices) {
            if (invoice.getNum() == num) {
                return invoice;
            }
        }
        
        return null;
    }
    
    private void createNewInvoice() {
        NewInvoiceForm a=null;
        if(a==null)
     {
         a=new NewInvoiceForm(this.frame);  
     }
    a.setVisible(true);
    }

    private void deleteInvoice() {
        //delete selected invoice
        
       
       int selectedRow = this.frame.getInvHeaderTable().getSelectedRow();
 if(selectedRow>=0)
 {
      ArrayList<InvoiceHeader> a =(this.frame.getInvoiceHeadersList());
      InvoiceHeader x=a.remove(selectedRow);
        this.frame.getInvHeaderTable().setModel(new InvoiceHeaderTableModel( a));
       this.frame.customerLabel.setText("");
       this.frame.dateLabel.setText("");
       this.frame.invTotalTxt.setText("");
       this.frame.numLabel.setText("");
//     DefaultTableModel jtModel= (DefaultTableModel) this.frame.getInvHeaderTable().getModel();
//    jtModel.removeRow(selectedRow);
//     this.frame.getInvHeaderTable().remove(selectedRow);
//    this.frame.getInvHeaderTable().notifyAll();
     JOptionPane.showMessageDialog(null, "Row Deleted");
     
 }else {     JOptionPane.showMessageDialog(null, "Unable To Delete");

 }
      
    }

    private void saveFile() {
        JFileChooser fc = new JFileChooser();
      fc.showSaveDialog(frame);
      File f=fc.getSelectedFile();
        try {
            FileWriter fw =new FileWriter(f);
            JTable str = frame.getInvHeaderTable();
            fw.write(10);
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        
    }

    private void newLine() {
        NewInvoiceLineForm a=null;
         if(a==null)
     {
         a=new NewInvoiceLineForm(this.frame);  
     }
    a.setVisible(true);
      
      
    }

    private void deleteLine() {
        int selectedRow = this.frame.getInvLineTable() .getSelectedRow();
 if(selectedRow>=0)
 {
     ArrayList<InvoiceLine> a =(this.frame.getInvoiceLinesList());
     a.remove(selectedRow);
    //this.frame.getInvLineTable().remove(selectedRow);
            this.frame.getInvLineTable().setModel(new InvoiceLineTableModel(a));

     JOptionPane.showMessageDialog(null, "Row Deleted");
 }else {     JOptionPane.showMessageDialog(null, "Unable To Delete");
 }
 }
//int selectedRow = this.frame.getInvLineTable() .getSelectedRow();
//InvoiceLine line=this.frame.getInvoiceLinesList().get(selectedRow);
//this.frame.getInvoiceLinesList().remove(selectedRow);
////this.frame.fireTbeleDataChanged();
 }
 
 
    
      

