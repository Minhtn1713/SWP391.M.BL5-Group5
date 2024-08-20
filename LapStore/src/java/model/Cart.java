/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Cart {
    private List<Item> items;
    
    public Cart(){
        items= new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public int getQuantityById(int id){
        return getItemById(id).getQuantity();
    }
    
    private Item getItemById(int id){
        for(Item i: items){
            if (i.getProductVariant().getId() == id){
                return i;
            }
        }
        return null;
    }
    
    public void addItem(Item t){
        if(getItemById(t.getProductVariant().getId())!=null){
            Item m=getItemById(t.getProductVariant().getId());
            m.setQuantity(m.getQuantity()+t.getQuantity());
        }else{
            items.add(t);
        }
    }
    
    public void removeItem(int id){
        if(getItemById(id)!=null){
            items.remove(getItemById(id));
        }
    }
    
    public float getTotalMoney(){
        float t=0;
        for(Item i:items){
            t+=(i.getQuantity()*i.getPrice());
        }
        return t;
    }
    
    private ProductVariant getProductById(int id,List<ProductVariant> list){
        for(ProductVariant p : list){
            if(p.getId()==id){
                return p;
            }
        }
        return null;
    }
    
    public Cart(String txt,List<ProductVariant> list){
        items = new ArrayList<>();
        try{
        if(txt !=null && txt.length() !=0){
            String[] s=txt.split("-");
            for(String i:s){
                String[] n = i.split(":");
                int id=Integer.parseInt(n[0]);
                int quantity = Integer.parseInt(n[1]);
                ProductVariant p = getProductById(id,list);
                Item t= new Item(p,quantity,p.getProductPrice()+p.getVariantPrice());
                addItem(t);
            }
        }
        }catch(NumberFormatException e){
            
        }
    }
}