/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.commands;

/**
 *
 * @author stoja
 */
public class CreateArticleCommand extends Command {

    private final String name;
    private final String description;
    private final String price;
    private final int discount;
    private final String IDUser;
    private final String IDCategory;

    public CreateArticleCommand(String name, String description, String price, int discount, String IDUser, String IDCategory) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.IDUser = IDUser;
        this.IDCategory = IDCategory;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }

    public String getIDUser() {
        return IDUser;
    }

    public String getIDCategory() {
        return IDCategory;
    }    
    
    @Override
    public Destination getDestination() {
        return Destination.SUBSYSTEM2;
    }

    @Override
    public Type getType() {
        return Type.CREATE_ARTICLE;
    }
    
}
