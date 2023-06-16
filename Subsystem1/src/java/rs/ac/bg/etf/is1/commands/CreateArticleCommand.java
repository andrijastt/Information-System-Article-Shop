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
    private final int price;
    private final int discount;
    private final String username;
    private final String categoryName;

    public CreateArticleCommand(String name, String description, int price, int discount, String username, String categoryName) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.username = username;
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }

    public String getUsername() {
        return username;
    }

    public String getCategoryName() {
        return categoryName;
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
