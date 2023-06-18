/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.commands;

/**
 *
 * @author stoja
 */
public class ChangeDiscountForArticleCommand extends Command {

    private final String discount;
    private final String IDUser;
    private final String IDArticle;   

    public ChangeDiscountForArticleCommand(String discount, String IDUser, String IDArticle) {
        this.discount = discount;
        this.IDUser = IDUser;
        this.IDArticle = IDArticle;
    }        

    public String getDiscount() {
        return discount;
    }

    public String getIDUser() {
        return IDUser;
    }

    public String getIDArticle() {
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
