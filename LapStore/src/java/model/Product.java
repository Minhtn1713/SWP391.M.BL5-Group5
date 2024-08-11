/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Represents a product with various attributes.
 * 
 * Author: hieu
 */
public class Product {
    private int id;
    private String name;
    private float price;
    private String processor;
    private String screenSize;
    private String screenResolution;
    private String imgId;
    private String port;
    private String size;
    private String operatingSystem;
    private String battery;
    private int status;
    private int brandId;
    private float weight;
    private String gpu;
    private String description;

    public Product() {
    }

    public Product(int id, String name, float price, String processor, String screenSize, String screenResolution,
            String imgId, String port, String size, String operatingSystem, String battery, int status,
            int brandId, float weight, String gpu, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.processor = processor;
        this.screenSize = screenSize;
        this.screenResolution = screenResolution;
        this.imgId = imgId;
        this.port = port;
        this.size = size;
        this.operatingSystem = operatingSystem;
        this.battery = battery;
        this.status = status;
        this.brandId = brandId;
        this.weight = weight;
        this.gpu = gpu;
        this.description = description;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String getScreenResolution() {
        return screenResolution;
    }

    public void setScreenResolution(String screenResolution) {
        this.screenResolution = screenResolution;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
