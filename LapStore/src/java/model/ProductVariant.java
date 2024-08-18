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
    private int ram;
    private int storage;
    private int quantity;
    private float variantPrice;
    private int sale_Id;
    private int status;
    private int imgId;

    public ProductVariant() {
    }

    public ProductVariant(int id, int product_Id, int ram, int storage, int quantity, float variantPrice,  int status, int sale_Id) {
        this.id = id;
        this.product_Id = product_Id;
        this.ram = ram;
        this.storage = storage;
        this.quantity = quantity;
        this.variantPrice = variantPrice;
        this.status = status;
        this.sale_Id = sale_Id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_Id() {
        return product_Id;
    }

    public void setProduct_Id(int product_Id) {
        this.product_Id = product_Id;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
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

    public int getSale_Id() {
        return sale_Id;
    }

    public void setSale_Id(int sale_Id) {
        this.sale_Id = sale_Id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImg(int img) {
        this.imgId = imgId;
    }

}