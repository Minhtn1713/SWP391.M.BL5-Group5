/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author FPT
 */
public class ProductFeedbackData {
    private String productName;
    private double averageRating;
    private int numberOfFeedbacks;

    public ProductFeedbackData(String productName, double averageRating, int numberOfFeedbacks) {
        this.productName = productName;
        this.averageRating = averageRating;
        this.numberOfFeedbacks = numberOfFeedbacks;
    }

    // Getters và Setters

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getNumberOfFeedbacks() {
        return numberOfFeedbacks;
    }

    public void setNumberOfFeedbacks(int numberOfFeedbacks) {
        this.numberOfFeedbacks = numberOfFeedbacks;
    }
}

