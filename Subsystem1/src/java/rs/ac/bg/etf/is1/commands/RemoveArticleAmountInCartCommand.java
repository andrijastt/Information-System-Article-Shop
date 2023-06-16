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

    final private String username;
    final private int IDArticle;
    final private int amount;        

    public RemoveArticleAmountInCartCommand(String username, int IDArticle, int amount) {
        this.username = username;
        this.IDArticle = IDArticle;
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }   

    public int getIDArticle() {
        return IDArticle;
    }

    public int getAmount() {
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
