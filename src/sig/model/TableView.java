/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sig.model;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import sig.controller.*;
import java.net.URL;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import static java.util.Collections.list;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.table.TableColumn;

/**
 *
 * @author ENG AMR
 */
public class TableView implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
/* javafx.scene.control.TableView<InvoiceHeader>headerTable;
    

    private javafx.scene.control.TableColumn<InvoiceHeader,Integer> num;
        

    private javafx.scene.control.TableColumn<InvoiceHeader,Date> date;
      

     private javafx.scene.control.TableColumn<InvoiceHeader,String> customer;
    //private TableColumn <InvoiceHeader> total; 
ObservableList<InvoiceHeader> list=FXCollections.observableArrayList(
        
        new InvoiceHeader(1,  "22-11-2020","aya"),
         new InvoiceHeader(2, "22-1-2020", "ahm"),
         new InvoiceHeader(3," 5-11-2020", "haron")
  
        );
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
num.setCellValueFactory(new PropertyValueFactory<InvoiceHeader,Integer>("num"));    
date.setCellValueFactory(new PropertyValueFactory<InvoiceHeader,Date>("date"));    
customer.setCellValueFactory(new PropertyValueFactory<InvoiceHeader,String>("customer"));    }
   headerTable.setItems(InvoiceHeader);*/
   
}
