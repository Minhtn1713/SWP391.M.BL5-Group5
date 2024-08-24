/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Represents a product variant with various attributes.
 * 
 * Author: lords
 */
public class ProductVariant {
    private int id;
    private String name;
    private int productId;
    private int ramId;
    private int storageId;
    private int quantity;
    private float variantPrice;
    private int status;
    private float productPrice;
    private int saleId;
    private String ram;
    private String storage;
    private String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public ProductVariant(int id, int productId, int ramId, int storageId, int quantity, float variantPrice, int status, int saleId) {
        this.id = id;
        this.productId = productId;
        this.ramId = ramId;
        this.storageId = storageId;
        this.quantity = quantity;
        this.variantPrice = variantPrice;
        this.status = status;
        this.saleId = saleId;
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
        this.productId = product_Id;
        this.ram = ram;
        this.storage = storage;
        this.quantity = quantity;
        this.variantPrice = variantPrice;
        this.saleId = sale_Id;
        this.status = status;
    }
    public ProductVariant(int id, int product_Id, String ram, String storage, int quantity, float variantPrice,
            int sale_Id, int status, String productName) {
        this.id = id;
        this.productId = product_Id;
        this.ram = ram;
        this.storage = storage;
        this.quantity = quantity;
        this.variantPrice = variantPrice;
        this.saleId = sale_Id;
        this.status = status;
        this.productName = productName;
    }
}