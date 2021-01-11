package dao;

/**
 *
 * @author Keith A Graham
 */
public class DAOProduct_tbl {
    private int ProductID;
    private String Description;
    private Double Price;

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double Price) {
        this.Price = Price;
    }
    
    
}
