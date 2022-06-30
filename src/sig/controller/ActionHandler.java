
package sig.controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import sig.model.InvoiceHeader;
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
        System.out.println("Row Selected");
        int selectedRow = frame.getInvHeaderTable().getSelectedRow();
        System.out.println(selectedRow);
        ArrayList<InvoiceLine> lines = frame.getInvoiceHeadersList().get(selectedRow).getLines();
        frame.getInvLineTable().setModel(new InvoiceLineTableModel(lines));
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
         a=new NewInvoiceForm();  
     }
    a.setVisible(true);
    }

    private void deleteInvoice() {
        //delete selected invoice
        
       
       int selectedRow = this.frame.getInvHeaderTable().getSelectedRow();
 if(selectedRow>=0)
 {
    this.frame.getInvHeaderTable().remove(selectedRow);
     JOptionPane.showMessageDialog(null, "Row Deleted");
 }else {     JOptionPane.showMessageDialog(null, "Unable To Delete");

 }
      
    }

    private void saveFile() {
        JFileChooser fc = new JFileChooser();
        
    }

    private void newLine() {
        NewInvoiceLineForm a=null;
         if(a==null)
     {
         a=new NewInvoiceLineForm();  
     }
    a.setVisible(true);
      
      
    }

    private void deleteLine() {
        int selectedRow = this.frame.getInvLineTable() .getSelectedRow();
 if(selectedRow>=0)
 {
    this.frame.getInvLineTable().remove(selectedRow);
     JOptionPane.showMessageDialog(null, "Row Deleted");
 }else {     JOptionPane.showMessageDialog(null, "Unable To Delete");

 }
    }
      
}
