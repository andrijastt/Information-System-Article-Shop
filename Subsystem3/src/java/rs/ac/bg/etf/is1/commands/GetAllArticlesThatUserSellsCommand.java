/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.commands;

/**
 *
 * @author stoja
 */
public class GetAllArticlesThatUserSellsCommand extends Command {
    
    private final String IDUser;    

    public GetAllArticlesThatUserSellsCommand(String IDUser) {
        this.IDUser = IDUser;
    }

    public String getIDUser() {
        return IDUser;
    }
    
    @Override
    public Destination getDestination() {
        return Destination.SUBSYSTEM2;
    }

    @Override
    public Type getType() {
        return Type.GET_ALL_ARTICLES_THAT_USER_SELLS;
    }
    
}
