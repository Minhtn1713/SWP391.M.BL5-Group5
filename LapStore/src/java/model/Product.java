package model;

import java.sql.Date;

public class Product {
    private int id;
    private String name;
    private String img;
    private int brandId;
    private float price;
    private String processor;
    private String screen_details;
    private String size;
    private String operatingSystem;
    private String battery;
    private int status;
    private float weight;
    private String graphic_card;
    private String description;
    private String brandName;  

    public Product(int id, String name, String img, int brandId,float price, String processor, String graphic_card, String screen_details,
                   String size, float weight, String operatingSystem, String battery, String description, String brandName, int status) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.price = price;
        this.processor = processor;
        this.screen_details = screen_details;
        this.size = size;
        this.operatingSystem = operatingSystem;
        this.battery = battery;
        this.status = status;
        this.brandId = brandId;
        this.weight = weight;
        this.graphic_card = graphic_card;
        this.description = description;
        this.brandName = brandName; 
    }
       public Product(int id, String name, String img, int brandId , float price, String processor, String graphic_card, String screen_details,
                   String size, float weight, String operatingSystem, String battery,  String description, int status) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.price = price;
        this.processor = processor;
        this.screen_details = screen_details;
        this.size = size;
        this.operatingSystem = operatingSystem;
        this.battery = battery;
        this.status = status;
        this.brandId = brandId;
        this.weight = weight;
        this.graphic_card = graphic_card;
        this.description = description; 
    }

    public Product() {
    }

    

    // Getters and setters for all fields, including brandName
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}