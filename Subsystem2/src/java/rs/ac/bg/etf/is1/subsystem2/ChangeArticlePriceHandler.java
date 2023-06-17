/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.subsystem2;

import java.util.List;
import javax.persistence.EntityManager;
import rs.ac.bg.etf.is1.commands.ChangeArticlePriceCommand;
import rs.ac.bg.etf.is1.commands.Command;
import rs.ac.bg.etf.is1.entities.Article;
import rs.ac.bg.etf.is1.entities.User;
import rs.ac.bg.etf.is1.responses.CommandHandler;
import rs.ac.bg.etf.is1.responses.FailedResponse;
import rs.ac.bg.etf.is1.responses.JMSResponse;
import rs.ac.bg.etf.is1.responses.SuccessfulResponse;

/**
 *
 * @author stoja
 */
public class ChangeArticlePriceHandler extends CommandHandler {

    public ChangeArticlePriceHandler(EntityManager em) {
        super(em);
    }

    @Override
    public JMSResponse handle(Command cmd) {
        
        ChangeArticlePriceCommand capc = (ChangeArticlePriceCommand) cmd;
                
        int IDUser = Integer.parseInt(capc.getIDUser());        
        User user = em.find(User.class, IDUser);
        if(user == null){
            return new FailedResponse(capc, "User with this ID doesn't exist!");
        }
        
        int IDArticle = Integer.parseInt(capc.getIDArticle());
        List<Article> articles = em.createQuery("Select a from Article a where IDarticle = :id and IDUser = :id1").setParameter("id", IDArticle).setParameter("id1", user).getResultList();
        
        if(articles.isEmpty()){
            return new FailedResponse(capc, "User with ID " + capc.getIDUser() + " doesn't sell article with ID " + capc.getIDArticle());
        }
        
        Article article = articles.get(0);
        
        em.getTransaction().begin();
        article.setPrice(Integer.parseInt(capc.getPrice()));
        em.getTransaction().commit();
        em.clear();
        
        return new SuccessfulResponse(capc);
    }
    
}
