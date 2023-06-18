/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.commands;

/**
 *
 * @author stoja
 */
public class RemoveArticleAmountInCartCommand extends Command {

    final private String IDUser;
    final private String IDArticle;
    final private String amount;        

    public RemoveArticleAmountInCartCommand(String IDUser, String IDArticle, String amount) {
        this.IDUser = IDUser;
        this.IDArticle = IDArticle;
        this.amount = amount;
    }

    public String getIDUser() {
        return IDUser;
    }   

    public String getIDArticle() {
        return IDArticle;
    }

    public String getAmount() {
        return amount;
    }
    
    @Override
    public Destination getDestination() {
        return Destination.SUBSYSTEM2;
    }

    @Override
    public Type getType() {
        return Type.REMOVE_ARTICLE_AMOUNT_IN_CART;
    }
    
}
