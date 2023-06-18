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

    private final String IDUser;
    private final String amount;
    private final String IDArticle;

    public AddArticleAmountInCartCommand(String IDUser, String amount, String IDArticle) {
        this.IDUser = IDUser;
        this.amount = amount;
        this.IDArticle = IDArticle;
    }

    public String getIDUser() {
        return IDUser;
    }

    public String getAmount() {
        return amount;
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
        return Type.ADD_ARTICLE_AMOUNT_IN_CART;
    }
    
}
