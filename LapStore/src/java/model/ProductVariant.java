/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Represents a product variant with various attributes.
 * 
 * Author: hieu
 */
public class ProductVariant {
    private int id;
    private int product_Id;
    private String ram;
    private String storage;
    private int quantity;
    private float variantPrice;
    private int sale_Id;
    private int status;

    public ProductVariant() {
    }

    public ProductVariant(int id, int product_Id, String ram, String storage, int quantity, float variantPrice,
            int sale_Id, int status) {
        this.id = id;
        this.product_Id = product_Id;
        this.ram = ram;
        this.storage = storage;
        this.quantity = quantity;
        this.variantPrice = variantPrice;
        this.sale_Id = sale_Id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return product_Id;
    }

    public void setProductId(int productId) {
        this.product_Id = productId;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getVariantPrice() {
        return variantPrice;
    }

    public void setVariantPrice(float variantPrice) {
        this.variantPrice = variantPrice;
    }

    public int getSaleId() {
        return sale_Id;
    }

    public void setSaleId(int saleId) {
        this.sale_Id = saleId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
