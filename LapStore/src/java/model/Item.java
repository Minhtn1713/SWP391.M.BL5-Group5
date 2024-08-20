
package model;


public class Item {
    private ProductVariant productVariant;
    private int quantity;
    private float price;

    public Item(ProductVariant productVariant, int quantity, float price) {
        this.productVariant = productVariant;
        this.quantity = quantity;
        this.price = price;
    }

    public Item() {
    }

    public ProductVariant getProductVariant() {
        return productVariant;
    }

    public void setProductVariant(ProductVariant productVariant) {
        this.productVariant = productVariant;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
}