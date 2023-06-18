/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.subsystem2;

import java.util.List;
import javax.persistence.EntityManager;
import rs.ac.bg.etf.is1.commands.Command;
import rs.ac.bg.etf.is1.commands.GetAllArticlesThatUserSellsCommand;
import rs.ac.bg.etf.is1.entities.Article;
import rs.ac.bg.etf.is1.entities.User;
import rs.ac.bg.etf.is1.responses.CommandHandler;
import rs.ac.bg.etf.is1.responses.DataResponse;
import rs.ac.bg.etf.is1.responses.FailedResponse;
import rs.ac.bg.etf.is1.responses.JMSResponse;

/**
 *
 * @author stoja
 */
public class GetAllArticlesThatUserSellsHandler extends CommandHandler {

    public GetAllArticlesThatUserSellsHandler(EntityManager em) {
        super(em);
    }

    @Override
    public JMSResponse handle(Command cmd) {
        
        GetAllArticlesThatUserSellsCommand gaatusc = (GetAllArticlesThatUserSellsCommand) cmd;
        
        int IDUser = Integer.parseInt(gaatusc.getIDUser());
        User user = em.find(User.class, IDUser);
        
        if(user == null){
            return new FailedResponse(gaatusc, "User with ID " + gaatusc.getIDUser() + " doesn't exist");
        }
        
        List<Article> articles = em.createQuery("Select a from Article where IDUser = :id").setParameter("id", user).getResultList();
        return new DataResponse(gaatusc, articles);
        
    }
    
}
