
package sig.model;


public class InvoiceLine {
   private InvoiceHeader invoiceNo;
    private String itemName;
    private double itemPrice;
    private int count;
     private int lineTotal;

    public InvoiceLine(InvoiceHeader invoiceNo, String itemName, double itemPrice, int count) {
        this.invoiceNo = invoiceNo;
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

    public InvoiceHeader getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(InvoiceHeader invoiceNo) {
        this.invoiceNo = invoiceNo;
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

    public int getItamTotal() {
        return lineTotal;
    }

    public void setItamTotal(int lineTotal) {
        this.lineTotal = (int) (itemPrice*count);
    }
    
    
}
