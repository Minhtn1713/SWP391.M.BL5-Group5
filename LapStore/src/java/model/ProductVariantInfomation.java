/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kienk
 */
public class ProductVariantInfomation {
    private int id;
    private String name;
    private String processor;
    private String screenDetails;
    private String operatingSystem;
    private String battery;
    private String graphicCard;
    private String url;
    private String ram;
    private String storage;
    private int quantity;
    private float variantPrice;
    private int status;
    private float sale;

    public ProductVariantInfomation() {
    }

    public ProductVariantInfomation(int id, String name, String processor, String screenDetails, String operatingSystem, String battery, String graphicCard, String url, String ram, String storage, int quantity, float variantPrice, int status, float sale) {
        this.id = id;
        this.name = name;
        this.processor = processor;
        this.screenDetails = screenDetails;
        this.operatingSystem = operatingSystem;
        this.battery = battery;
        this.graphicCard = graphicCard;
        this.url = url;
        this.ram = ram;
        this.storage = storage;
        this.quantity = quantity;
        this.variantPrice = variantPrice;
        this.status = status;
        this.sale = sale;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getScreenDetails() {
        return screenDetails;
    }

    public void setScreenDetails(String screenDetails) {
        this.screenDetails = screenDetails;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getGraphicCard() {
        return graphicCard;
    }

    public void setGraphicCard(String graphicCard) {
        this.graphicCard = graphicCard;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getSale() {
        return sale;
    }

    public void setSale(float sale) {
        this.sale = sale;
    }

}