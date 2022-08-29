package sig.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import sig.model.InvoiceHeader;
import sig.model.InvoiceHeaderTableModel;
import sig.model.InvoiceLine;
import sig.model.InvoiceLineTableModel;
import sig.view.InvoiceFrame;
import sig.view.NewInvoiceForm;
import sig.view.NewInvoiceLineForm;

public class ActionHandler implements ActionListener, ListSelectionListener {

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

        if (selectedRow < 0) {
        } else {
            setDataToTextView(frame.getInvoiceHeadersList().get(selectedRow));

            ArrayList<InvoiceLine> lines = frame.getInvoiceHeadersList().get(selectedRow < 0 ? 0 : selectedRow).getLines();
            frame.getInvLineTable().setModel(new InvoiceLineTableModel(lines));
            frame.setInvoiceLinesList(lines);
        }

    }

    void setDataToTextView(InvoiceHeader invoiceHeader) {
        frame.numLabel.setText(invoiceHeader.getNum() + "");
        frame.dateLabel.setText(invoiceHeader.getDate() + "");
        frame.customerLabel.setText(invoiceHeader.getCustomer() + "");
        frame.invTotalTxt.setText(invoiceHeader.getInvoiceTotal() + "");
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
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "WRONG FILE FORMATE");

        } catch (NoSuchFileException ex) {
            JOptionPane.showMessageDialog(null, "FILE NOT FOUND");

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
        NewInvoiceForm a = null;
        if (a == null) {
            a = new NewInvoiceForm(this.frame);
        }
        a.setVisible(true);
    }

    private void deleteInvoice() {
        //delete selected invoice

        int selectedRow = this.frame.getInvHeaderTable().getSelectedRow();
        if (selectedRow >= 0) {
            ArrayList<InvoiceHeader> a = (this.frame.getInvoiceHeadersList());
            InvoiceHeader x = a.remove(selectedRow);
            this.frame.getInvHeaderTable().setModel(new InvoiceHeaderTableModel(a));
            this.frame.customerLabel.setText("");
            this.frame.dateLabel.setText("");
            this.frame.invTotalTxt.setText("");
            this.frame.numLabel.setText("");

            JOptionPane.showMessageDialog(null, "Row Deleted");

        } else {
            JOptionPane.showMessageDialog(null, "Unable To Delete");

        }
    }

//    private void saveFile() {
//
//        ArrayList<InvoiceHeader> invoices = frame.getInvoiceHeadersList();
//        // ArrayList<InvoiceLine> liness = frame.getInvoiceLinesList();
//
//        String headers = "";
//        String lines = "";
//        for (InvoiceHeader invoice : invoices) {
//            String invCSV = invoice.getAsCSVfile();
//            headers += invCSV;
//            headers += "\n";
//
//            for (InvoiceLine line : invoice.getLines()) {
//                String lineCSV = line.getAsCSV();
//                lines += lineCSV;
//                lines += "\n";
//            }
//        }
//        System.out.println("Check point");
//
//        try {
//            JFileChooser fc = new JFileChooser();
//            //new
//            fc.setFileFilter(new FileNameExtensionFilter("csv,txt", "csv", "txt"));
//            fc.setAcceptAllFileFilterUsed(false);
//
//            int result = fc.showSaveDialog(frame);
//            if (result == JFileChooser.APPROVE_OPTION) {
//                File headerFile = fc.getSelectedFile();
//                FileWriter hfw = new FileWriter(headerFile);
//                hfw.write(headers);
//                hfw.flush();
//                hfw.close();
//                result = fc.showSaveDialog(frame);
//                JOptionPane.showMessageDialog(frame, "FILE SAVED SUCCUSSFULLY");
//                if (result == JFileChooser.APPROVE_OPTION) {
//                    File lineFile = fc.getSelectedFile();
//                    FileWriter lfw = new FileWriter(lineFile);
//                    lfw.write(lines);
//                    lfw.flush();
//                    lfw.close();
//                }
//            }
//        } catch (FileNotFoundException ee) {
//            JOptionPane.showMessageDialog(frame, "WRONG FILE FORMATE,PLEASE SELECT VALID FORMATE.");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
    private void saveFile() {
         ArrayList<InvoiceHeader> invoiceT = frame.getInvoiceHeadersList();
          String headerData = "";
       String linesData = "";
   
    
      for (InvoiceHeader invoices : invoiceT) {
            String invCSV = invoices.getAsCSVfile();
            headerData+= invCSV;
            headerData += "\n";

            for (InvoiceLine line : invoices.getLines()) {
                String lineCSV = line.getAsCSV();
               linesData += lineCSV;
               linesData += "\n";
            }
        }
        try {
              JFileChooser fc = new JFileChooser();
             
                        

             int res = fc.showSaveDialog(frame);
             if (res==JFileChooser.APPROVE_OPTION) {
                 File file1 = fc.getSelectedFile();
     
            FileWriter fw = new FileWriter(file1);
                fw.write(headerData);
                fw.flush();
                 fw.close();
                 res = fc.showSaveDialog(frame);
                JOptionPane.showMessageDialog(frame, "FILE SAVED SUCCUSSFULLY");
                if (res == JFileChooser.APPROVE_OPTION) {
                    File file2 = fc.getSelectedFile();
                    FileWriter fw2 = new FileWriter(file2);
                 fw2.write(linesData);
                fw2.flush();
                 fw2.close();   
            }}
        } 
        catch (FileNotFoundException ee) {
          JOptionPane.showMessageDialog(frame, "WRONG FILE FORMATE,PLEASE SELECT VALID FORMATE.");
        }catch (IOException ex) {
            Logger.getLogger(ActionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }

    private void newLine() {
        NewInvoiceLineForm a = null;
        int selectedRow = this.frame.getInvHeaderTable().getSelectedRow();
        if (a == null) {
            a = new NewInvoiceLineForm(this.frame, frame.getInvoiceHeadersList().get(selectedRow));
        }
        a.setVisible(true);

    }

    private void deleteLine() {
        int selectedRow = this.frame.getInvLineTable().getSelectedRow();
        if (selectedRow >= 0) {
            ArrayList<InvoiceLine> a = (this.frame.getInvoiceLinesList());
            a.remove(selectedRow);
            //this.frame.getInvLineTable().remove(selectedRow);
            this.frame.getInvLineTable().setModel(new InvoiceLineTableModel(a));

            JOptionPane.showMessageDialog(null, "Row Deleted");
        } else {
            JOptionPane.showMessageDialog(null, "Unable To Delete");
        }
    }
//int selectedRow = this.frame.getInvLineTable() .getSelectedRow();
//InvoiceLine line=this.frame.getInvoiceLinesList().get(selectedRow);
//this.frame.getInvoiceLinesList().remove(selectedRow);
////this.frame.fireTbeleDataChanged();

}
