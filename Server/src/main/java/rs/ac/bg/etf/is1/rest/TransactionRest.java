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
@XmlRootElement(name = "TransactionRest")
public class TransactionRest {
    private int IDTransaction;
    private Date paymentTime;
    private int totalPrice;
    private int IDOrder;

    @Override
    public String toString() {
        return "TransactionRest{" + "IDTransaction=" + IDTransaction + ", paymentTime=" + paymentTime + ", totalPrice=" + totalPrice + ", IDOrder=" + IDOrder + '}';
    }

    public int getIDTransaction() {
        return IDTransaction;
    }

    public void setIDTransaction(int IDTransaction) {
        this.IDTransaction = IDTransaction;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getIDOrder() {
        return IDOrder;
    }

    public void setIDOrder(int IDOrder) {
        this.IDOrder = IDOrder;
    }

    public TransactionRest(int IDTransaction, Date paymentTime, int totalPrice, int IDOrder) {
        this.IDTransaction = IDTransaction;
        this.paymentTime = paymentTime;
        this.totalPrice = totalPrice;
        this.IDOrder = IDOrder;
    }
}
