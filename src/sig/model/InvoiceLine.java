
package sig.model;


public class InvoiceLine {
   private InvoiceHeader header;
    private String itemName;
    private double itemPrice;
    private int count;
     

    public InvoiceLine(String itemName, double itemPrice, int count, InvoiceHeader header) {
        this.header = header;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.count = count;
    }

    public InvoiceLine(String itemName, double itemPrice, int count) {
      this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.count = count;
    }

   

   

  

   
   

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public InvoiceHeader getHeader() {
        return header;
    }
public void setHeader(InvoiceHeader header) {
        this.header = header;
    }
    public void setInvoiceNum(InvoiceHeader header) {
        this.header = header;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
        
    }
     public double getLineTotal() {
        return count * itemPrice;
    }

    @Override
    public String toString() {
        return "InvoiceLine{" + "itemName=" + itemName + ", unitPrice=" + itemPrice + ", count=" + count + ", lineTotal=" + getLineTotal() + '}';
    }


//    public String getAsCSV()) {
//        return header.getNum() + "," + itemName + "," + itemPrice + "," + count;
//    }

//    public String getAsCSV() {
//return header.getNum() + "," + itemName + "," + itemPrice + "," + count;    }

    public String getAsCSV() {
        return header.getNum() + "," + itemName + "," + itemPrice + "," + count;    }

    }

    

