/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.client.rest;

/**
 *
 * @author stoja
 */
public class InCartRest {
    private int IDUser;
    private int IDArticle;
    private int amount;

    @Override
    public String toString() {
        return "InCartRest{" + "IDArticle=" + IDArticle + ", amount=" + amount + '}';
    }

    public int getIDUser() {
        return IDUser;
    }

    public void setIDUser(int IDUser) {
        this.IDUser = IDUser;
    }

    public int getIDArticle() {
        return IDArticle;
    }

    public void setIDArticle(int IDArticle) {
        this.IDArticle = IDArticle;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public InCartRest(int IDUser, int IDArticle, int amount) {
        this.IDUser = IDUser;
        this.IDArticle = IDArticle;
        this.amount = amount;
    }
    
}
