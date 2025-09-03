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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author semih
 */
@Entity
@Table(name = "tbl_Sell")
public class SellProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "date")
    private LocalDate date;
    
    @Column(name= "payType")
    private String payType = "";
    
    @Column(name = "isComplate")
    private boolean complate = false;
    
    @Column(name = "takeMoney")
    private float takeMoney;
    
    @Column(name = "giveMoney")
    private float giveMoney;
    
    @OneToMany(mappedBy = "sell", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SellProductInfo> productInfos = new ArrayList();

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getPayType() {
        return payType;
    }

    public boolean isComplate() {
        return complate;
    }

    public float getTakeMoney() {
        return takeMoney;
    }

    public float getGiveMoney() {
        return giveMoney;
    }

    public List<SellProductInfo> getProductInfos() {
        return productInfos;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public void setComplate(boolean complate) {
        this.complate = complate;
    }

    public void setTakeMoney(float takeMoney) {
        this.takeMoney = takeMoney;
    }

    public void setGiveMoney(float giveMoney) {
        this.giveMoney = giveMoney;
    }

    public void setProductInfos(List<SellProductInfo> productInfos) {
        this.productInfos = productInfos;
    }
    
}
