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
    private String name;
    private int product_Id;
    private String ram;
    private String storage;
    private int quantity;
    private float variantPrice;
    private int sale_Id;
    private int status;
    private float productPrice;
    public ProductVariant() {
    }

    public int getProduct_Id() {
        return product_Id;
    }

    public void setProduct_Id(int product_Id) {
        this.product_Id = product_Id;
    }

    public int getSale_Id() {
        return sale_Id;
    }

    public void setSale_Id(int sale_Id) {
        this.sale_Id = sale_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public ProductVariant(int id, String name, String ram, String storage, int quantity, float variantPrice, float productPrice) {
        this.id = id;
        this.name = name;
        this.ram = ram;
        this.storage = storage;
        this.quantity = quantity;
        this.variantPrice = variantPrice;
        this.productPrice = productPrice;
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
