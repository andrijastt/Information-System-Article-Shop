/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.commands;

/**
 *
 * @author stoja
 */
public class AddArticleAmountInCartCommand extends Command {

    private final String username;
    private final int amount;
    private final int IDArticle;

    public AddArticleAmountInCartCommand(String username, int amount, int IDArticle) {
        this.username = username;
        this.amount = amount;
        this.IDArticle = IDArticle;
    }

    public String getUsername() {
        return username;
    }

    public int getAmount() {
        return amount;
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
        return Type.ADD_ARTICLE_AMOUNT_IN_CART;
    }
    
}
