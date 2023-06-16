/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.commands;

/**
 *
 * @author stoja
 */
public class ChangeArticlePriceCommand extends Command {

    private final int price;
    private final String username;
    private final int IDArticle;   

    public ChangeArticlePriceCommand(int price, String username, int IDArticle) {
        this.price = price;
        this.username = username;
        this.IDArticle = IDArticle;
    }        

    public int getPrice() {
        return price;
    }

    public String getUsername() {
        return username;
    }

    public int getIDArticle() {
        return IDArticle;
    }
    
    @Override
    public Destination getDestination() {
        return Destination.SUBSYSTEM2;
    }

    @Override
    public Type getType() {
        return Type.CHANGE_ARTICLE_PRICE;
    }
    
}
