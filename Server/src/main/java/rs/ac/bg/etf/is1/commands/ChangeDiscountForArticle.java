/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.commands;

/**
 *
 * @author stoja
 */
public class ChangeDiscountForArticle extends Command {

    private final int discount;
    private final String username;
    private final int IDArticle;   

    public ChangeDiscountForArticle(int discount, String username, int IDArticle) {
        this.discount = discount;
        this.username = username;
        this.IDArticle = IDArticle;
    }        

    public int getDiscount() {
        return discount;
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
        return Type.CHANGE_DISCOUNT_FOR_ARTICLE;
    }
    
}
