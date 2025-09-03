/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sk_fastfood;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

/**
 *
 * @author semih
 */
@Entity
@Table(name = "tbl_SellProductInfo")
public class SellProductInfo {
    @EmbeddedId
    private SellProductInfoId id;
    
    @ManyToOne
    @MapsId("sellID")
    @JoinColumn(name = "sell_ID")
    private SellProduct sell;
    
    @ManyToOne
    @MapsId("productID")
    @JoinColumn(name = "product_ID")
    private Product product;
    
    @Column(name = "productPrice")
    private float productPrice;
    
    @Column(name = "prodect_Comment", nullable = true)
    private String comment = "";
    
    @Column(name = "amount")
    private int amount;

    public SellProductInfoId getId() {
        return id;
    }

    public SellProduct getSell() {
        return sell;
    }

    public Product getProduct() {
        return product;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public String getComment() {
        return comment;
    }

    public int getAmount() {
        return amount;
    }

    public void setId(SellProductInfoId id) {
        this.id = id;
    }

    public void setSell(SellProduct sell) {
        this.sell = sell;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean chkSetComment(String comment) {
        if(comment.matches(".{0,500}") && !comment.matches(".*\\|.*")){
            this.comment = comment;
            return true;
        }
        
        return false;
    }
}
