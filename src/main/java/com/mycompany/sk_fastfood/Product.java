/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sk_fastfood;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author semih
 */
@Entity
@Table(name = "tbl_Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "name")
    private String name = "";
    
    @Column(name = "contant")
    private String contant = "";
    
    @Column(name = "price")
    private float price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SellProductInfo> sellInfos = new ArrayList();
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContant() {
        return contant;
    }

    public float getPrice() {
        return price;
    }

    public List<SellProductInfo> getSellInfos() {
        return sellInfos;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContant(String contant) {
        this.contant = contant;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setSellInfos(List<SellProductInfo> sellInfos) {
        this.sellInfos = sellInfos;
    }
    
    public boolean chkSetId(String id) {
        try{
            this.id = Integer.parseInt(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean chkSetName(String name) {
        if(name.matches(".{1,50}") && !name.matches(".*\\|.*")){
            this.name = name.toUpperCase();
            return true;
        }
        return false;
    }

    public boolean chkSetContant(String contant) {
         if(contant.matches(".{1,500}") && !contant.matches(".*\\|.*")){
            this.contant = contant;
            return true;
        }
        return false;
    }

    public boolean chkSetPrice(String price) {
        try{
            this.price = Float.parseFloat(price);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
