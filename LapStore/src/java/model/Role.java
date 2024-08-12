/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author lords
 */
public class Role {
    private int id;
    private int role_name;

    public Role() {
    }

    public Role(int id, int role_name) {
        this.id = id;
        this.role_name = role_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRole_name() {
        return role_name;
    }

    public void setRole_name(int role_name) {
        this.role_name = role_name;
    }
    
}
