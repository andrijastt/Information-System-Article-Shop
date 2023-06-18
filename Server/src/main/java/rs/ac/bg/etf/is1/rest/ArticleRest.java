/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.rest;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stoja
 */
@XmlRootElement(name = "ArticleRest")
public class ArticleRest {
    private int IDArticle;
    private String name;
    private String description;
    private int price;
    private int discount;
    private int IDUser;
    private int IDCategory;

    @Override
    public String toString() {
        return "ArticleRest{" + "IDArticle=" + IDArticle + ", name=" + name + ", description=" + description + ", price=" + price + ", discount=" + discount + ", IDUser=" + IDUser + ", IDCategory=" + IDCategory + '}';
    }

    public int getIDArticle() {
        return IDArticle;
    }

    public void setIDArticle(int IDArticle) {
        this.IDArticle = IDArticle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getIDUser() {
        return IDUser;
    }

    public void setIDUser(int IDUser) {
        this.IDUser = IDUser;
    }

    public int getIDCategory() {
        return IDCategory;
    }

    public void setIDCategory(int IDCategory) {
        this.IDCategory = IDCategory;
    }

    public ArticleRest(int IDArticle, String name, String description, int price, int discount, int IDUser, int IDCategory) {
        this.IDArticle = IDArticle;
        this.name = name;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.IDUser = IDUser;
        this.IDCategory = IDCategory;
    }
}
