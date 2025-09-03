/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sk_fastfood;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author semih
 */

@Embeddable
public class SellProductInfoId implements Serializable{
    private Integer sellID;
    private Integer productID;

    public SellProductInfoId() {
    }

    public SellProductInfoId(Integer sellID, Integer productID) {
        this.sellID = sellID;
        this.productID = productID;
    }
    
    public Integer getSellID() {
        return sellID;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setSellID(Integer sellID) {
        this.sellID = sellID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    @Override
    public int hashCode() {
        return sellID.hashCode()/* + productID.hashCode()*/;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SellProductInfoId other = (SellProductInfoId) obj;
        if (!Objects.equals(this.sellID, other.sellID)) {
            return false;
        }
        return Objects.equals(this.productID, other.productID);
    }

    
}
