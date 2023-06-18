/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.rest;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stoja
 */
@XmlRootElement(name = "OrderRest")
public class OrderRest {
    
    private int IDOrder;
    private int totalPrice;
    private Date timeCreated;
    private String address;
    private int IDCity;
    private int IDUser;   

    @Override
    public String toString() {
        return "OrderRest{" + "IDOrder=" + IDOrder + ", totalPrice=" + totalPrice + ", timeCreated=" + timeCreated + ", address=" + address + ", IDCity=" + IDCity + ", IDUser=" + IDUser + '}';
    }

    public int getIDUser() {
        return IDUser;
    }

    public void setIDUser(int IDUser) {
        this.IDUser = IDUser;
    }

    public int getIDOrder() {
        return IDOrder;
    }

    public void setIDOrder(int IDOrder) {
        this.IDOrder = IDOrder;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIDCity() {
        return IDCity;
    }

    public void setIDCity(int IDCity) {
        this.IDCity = IDCity;
    }

    public OrderRest(int IDOrder, int totalPrice, Date timeCreated, String address, int IDCity, int IDUser) {
        this.IDOrder = IDOrder;
        this.totalPrice = totalPrice;
        this.timeCreated = timeCreated;
        this.address = address;
        this.IDCity = IDCity;
        this.IDUser = IDUser;
    }    
    
}
