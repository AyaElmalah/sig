
package sig.controller;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import sig.view.InvoiceFrame;


public class ActionHandler extends JFrame  implements ActionListener{
 

    @Override
    public void actionPerformed(ActionEvent e) {
       
        switch(e.getActionCommand()){
            case "Create New Invoice":
                System.out.println("Create New Invoice");
                newInv();
                break;
                case "Delete Invoice":
                System.out.println("Delete Invoice");
                deletInv();
                break;
                case "New Line":
                System.out.println("New Line");
                newLine();
                break;
                 case "Delete Line":
                System.out.println("Delete Line");
                deleteLine();
                break;
                 case "Load File":
                System.out.println("Load File");
                loadFile();
                break;
                 case "Save File":
                System.out.println("Save File");
                saveFile();
                break;
                
        
        }
    }

    private void newInv() {
    }

    private void deletInv() {

         
}
    private void newLine() {
        
 JTextField itemName;
        JTextField itemCount;
        JTextField itemPrice;
        JButton  btn;
        JButton btn2;
        
        
//super("Ui Componnent"); 
setLayout(new FlowLayout());
    itemName=new  JTextField(15);
    JLabel userl=new JLabel("Item Name");
    add(userl);
    add(itemName);
     // setSize(300,200);
     //setLocation(200,300);  
      itemCount=new  JTextField(15);
    JLabel user2=new JLabel("Item Count");
    add(user2);
    add(itemCount);
     // setSize(300,200);
    // setLocation(200,400);  
;
          itemPrice=new  JTextField(15);
    JLabel user3=new JLabel("Item Price");
    add(user3);
    add(itemPrice);
    //  setSize(300,200);
    // setLocation(200,500);  
     setVisible(true);

     btn=new JButton("OK");
     add(btn);
     btn.setActionCommand("a");
     
   //  MyLisner l=new MyLisner();
    btn.addActionListener((ActionListener) this);
      btn2=new JButton("Cancle");
     add(btn2);
          btn2.setActionCommand("b");

      btn2.addActionListener((ActionListener) this);
     setSize(300,200);
     setLocation(200,800);
      setVisible(true);
  
    
    }

    private void deleteLine() {
        
      
    }

    private void loadFile() {
       JFileChooser fc= new JFileChooser();
       int result= fc.showOpenDialog(fc);
       //دا جديد
        BufferedReader reader=null;
        String line="";
         String file=""
                 + ""
                 + "+-6"
                 + "662. ]0,0 b ";
        if(result==JFileChooser.APPROVE_OPTION){
       String path= fc.getSelectedFile().getPath();
       FileInputStream fis=null;
       try
       {
      fis =new FileInputStream(path);
          
      //جديد
      reader=new BufferedReader(new FileReader(file));
       int size=fis.available();
       byte []  b=new byte[size];
       fis.read(b);
     //      JTableHeader tableHeader = null;
//       headerTable.setTableHeader(tableHeader);
      
       //هنا كود ta.settext(b);
      
       }
       catch (FileNotFoundException e){
               
              e.printStackTrace();
               }
       catch(IOException e){
           e.printStackTrace();
       } finally{
              try{ fis.close();}catch(IOException e){
              }
       }
        }
    }

    private void saveFile() {
    }
    
}
