
package sig.model;

import com.sun.org.apache.bcel.internal.generic.Type;
import static java.nio.file.Files.lines;
import java.util.ArrayList;
import java.util.Date;


public class InvoiceHeader {
    private int num;
    private String customer;
    private String date;
   
    private ArrayList<InvoiceLine> lines;

   public InvoiceHeader(int num, String customer, String date) {
        this.num = num;
        this.customer = customer;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

     public void setDate(String date) {
        this.date = date;
    }


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
     public double getInvoiceTotal() {
        double total = 0;
        for (InvoiceLine line : getLines()) {
            total += line.getLineTotal();
        }
        return total;
    }
      public ArrayList<InvoiceLine> getLines() {
        if (lines == null) {
            lines = new ArrayList<>();
        }
        return lines;
    }
     
     @Override
    public String toString() {
        return "InvoiceHeader{" + "num=" + num + ", customer=" + customer + ", date=" + date + '}';
    }


   

    public String getCSVHeaderFile() {
return num+ "," + date + "," + customer;    }

  

    public String getAsCSVfile() {
return num+ "," + date + "," + customer;     }
    }

    

