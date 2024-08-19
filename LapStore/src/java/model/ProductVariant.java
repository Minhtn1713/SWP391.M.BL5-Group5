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
    private int productId;
    private int ramId;
    private int storageId;
    private int quantity;
    private float variantPrice;
    private int status;
    private int sale_Id;
    

    public ProductVariant() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getRamId() {
        return ramId;
    }

    public void setRamId(int ramId) {
        this.ramId = ramId;
    }

    public int getStorageId() {
        return storageId;
    }

    public void setStorageId(int storageId) {
        this.storageId = storageId;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSale_Id() {
        return sale_Id;
    }

    public void setSale_Id(int sale_Id) {
        this.sale_Id = sale_Id;
    }

    public ProductVariant(int id, int productId, int ramId, int storageId, int quantity, float variantPrice, int status, int sale_Id) {
        this.id = id;
        this.productId = productId;
        this.ramId = ramId;
        this.storageId = storageId;
        this.quantity = quantity;
        this.variantPrice = variantPrice;
        this.status = status;
        this.sale_Id = sale_Id;
    }

}