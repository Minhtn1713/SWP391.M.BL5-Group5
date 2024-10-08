/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kienk
 */
public class Ram {
    private int id;
    private String name;
    private float priceBonus;
    private int status;

    public Ram() {
    }

    public Ram(int id, String name, float priceBonus, int status) {
        this.id = id;
        this.name = name;
        this.priceBonus = priceBonus;
        this.status = status;
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

    public float getPriceBonus() {
        return priceBonus;
    }

    public void setPriceBonus(float priceBonus) {
        this.priceBonus = priceBonus;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
    
}
