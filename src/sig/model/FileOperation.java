
package sig.model;


import sig.controller.*;
import sig.model.InvoiceHeader;
import sig.model.InvoiceLine;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class FileOperation {
    
    private ArrayList<InvoiceHeader> invoiceHeader;
      
    public ArrayList<InvoiceHeader> read(){
        
        
        JFileChooser fc = new JFileChooser();

        try {
            JOptionPane.showMessageDialog(null, "Select Invoice Header File",
                    "Invoice Header", JOptionPane.INFORMATION_MESSAGE);
            int result = fc.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fc.getSelectedFile();
                Path headerPath = Paths.get(headerFile.getAbsolutePath());
                List<String> headerLines = Files.readAllLines(headerPath);
                System.out.println("Invoices have been read");
                // 1,22-11-2020,Ali
                // 2,13-10-2021,Saleh
                // 3,09-01-2019,Ibrahim
                ArrayList<InvoiceHeader> invoicesArray = new ArrayList<>();
                for (String headerLine : headerLines) {
                    try {
                        String[] headerParts = headerLine.split(",");
                        int invoiceNum = Integer.parseInt(headerParts[0]);
                        String invoiceDate = headerParts[1];
                        String customerName = headerParts[2];

                        InvoiceHeader invoice = new InvoiceHeader(invoiceNum, invoiceDate, customerName);
                        invoicesArray.add(invoice);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error in line format", "Error", JOptionPane.ERROR_MESSAGE);
                        //Reminder to load only Line CSV file and error popup appear when try to choose fault file type
                    }
                }
                System.out.println("Check point");
                JOptionPane.showMessageDialog(null, "Select Invoice Line File",
                        "Invoice Line", JOptionPane.INFORMATION_MESSAGE);
                result = fc.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File lineFile = fc.getSelectedFile();
                    Path linePath = Paths.get(lineFile.getAbsolutePath());
                    List<String> lineLines = Files.readAllLines(linePath);
                    System.out.println("Lines have been read");
                    for (String lineLine : lineLines) {
                        try {
                            String lineParts[] = lineLine.split(",");
                            int invoiceNum = Integer.parseInt(lineParts[0]);
                            String itemName = lineParts[1];
                            double itemPrice = Double.parseDouble(lineParts[2]);
                            int count = Integer.parseInt(lineParts[3]);
                            InvoiceHeader inv = null;
                            for (InvoiceHeader invoice : invoicesArray) {
                                if (invoice.getNum() == invoiceNum) {
                                    inv = invoice;
                                    break;
                                }
                            }

                            InvoiceLine line = new InvoiceLine(itemName, itemPrice, count, inv);
                            inv.getLines().add(line);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error in line format", "Error", JOptionPane.ERROR_MESSAGE);
                                //Reminder to load only CSV file and error popup appear when try to choose fault file type
                           }
                        }
                    
                    System.out.println("Check point");
                    
                  }
              
                this.invoiceHeader = invoicesArray;  // store invoices array in the class variable
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot read file", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
        return invoiceHeader;
    }
    
    
    
    
    
    public void write(ArrayList<InvoiceHeader> invoices)
    {
        for(InvoiceHeader inv : invoices)
      {
          int invId = inv.getNum();
          String date = inv.getDate();
          String customer = inv.getCustomer();
          System.out.println("\n Invice " + invId + "\n {\n " + date + "," + customer);
          ArrayList<InvoiceLine> lines = inv.getLines();
          
          for(InvoiceLine line : lines)
          {
              System.out.println( line.getItemName() + "," + line.getItemPrice() + "," + line.getCount());
          }
          
          System.out.println(" } \n");
      }
        
    }
    
    
    
    
    
     public static void main(String[] args)
   {
       FileOperation fo = new FileOperation();
       ArrayList<InvoiceHeader> invoices = fo.read();
       fo.write(invoices);
              
   }
    
     
}
