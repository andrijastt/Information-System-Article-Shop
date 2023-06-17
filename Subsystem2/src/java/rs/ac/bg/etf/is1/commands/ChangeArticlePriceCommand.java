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

    private final String price;
    private final String IDUser;
    private final String IDArticle;   

    public ChangeArticlePriceCommand(String price, String IDUser, String IDArticle) {
        this.price = price;
        this.IDUser = IDUser;
        this.IDArticle = IDArticle;
    }        

    public String getPrice() {
        return price;
    }

    public String getIDUser() {
        return IDUser;
    }

    public String getIDArticle() {
        return IDArticle;
    }
    
    @Override
    public Command.Destination getDestination() {
        return Command.Destination.SUBSYSTEM2;
    }

    @Override
    public Command.Type getType() {
        return Command.Type.CHANGE_ARTICLE_PRICE;
    }
    
}
