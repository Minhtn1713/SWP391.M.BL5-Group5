package model;

import java.sql.Date;

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
    private String screen_details;
    private String imgId;
    private String size;
    private String operatingSystem;
    private String battery;
    private int status;
    private int brandId;
    private float weight;
    private String graphic_card;
    private String description;
    private Date release_date;

    public Product() {
    }

//    public Product(int id, String name, float price, String processor, String screen_details,
//            String imgId, String size, String operatingSystem, String battery, int status,
//            int brandId, float weight, String graphic_card, String description, Date release_date) {
//        this.id = id;
//        this.name = name;
//        this.price = price;
//        this.processor = processor;
//        this.screen_details = screen_details;
//        this.imgId = imgId;
//        this.size = size;
//        this.operatingSystem = operatingSystem;
//        this.battery = battery;
//        this.status = status;
//        this.brandId = brandId;
//        this.weight = weight;
//        this.graphic_card = graphic_card;
//        this.description = description;
//        this.release_date = release_date;
//    }

    public Product(int id, String name, float price, String processor, String screen_details, String size, String operatingSystem, String battery, int status, int brandId, float weight, String graphic_card, String description, Date release_date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.processor = processor;
        this.screen_details = screen_details;
//        this.imgId = imgId;
        this.size = size;
        this.operatingSystem = operatingSystem;
        this.battery = battery;
        this.status = status;
        this.brandId = brandId;
        this.weight = weight;
        this.graphic_card = graphic_card;
        this.description = description;
        this.release_date = release_date;
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

    public String getScreen_details() {
        return screen_details;
    }

    public void setScreen_details(String screen_details) {
        this.screen_details = screen_details;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
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

    public String getGraphic_card() {
        return graphic_card;
    }

    public void setGraphic_card(String graphic_card) {
        this.graphic_card = graphic_card;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }
        
}
