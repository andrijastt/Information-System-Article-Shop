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
import rs.ac.bg.etf.is1.entities.Cart;
import rs.ac.bg.etf.is1.entities.Incart;
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
        
        Article article = em.find(Article.class, IDArticle);        
        if(article == null){
            return new FailedResponse(capc, "Article with ID " + capc.getIDArticle()+ " doesn't exist");
        }
        
        if(article.getIDUser() != user){
            return new FailedResponse(capc, "User with ID " + capc.getIDUser() + " doesn't sell article with ID " + capc.getIDArticle());
        }
        
        em.getTransaction().begin();
        
        List<Incart> incart = em.createNamedQuery("Incart.findByIDArticle").setParameter("iDArticle", IDArticle).getResultList();
        for(Incart item: incart){            
            Cart cart = em.find(Cart.class, item.getIncartPK().getIDUser());
            cart.setTotalPrice(cart.getTotalPrice() - item.getAmount() * (100 - article.getDiscount()) / 100 * article.getPrice());
        }
        int price = Integer.parseInt(capc.getPrice());
        article.setPrice(price);
        for(Incart item: incart){            
            Cart cart = em.find(Cart.class, item.getIncartPK().getIDUser());
            cart.setTotalPrice(cart.getTotalPrice() + item.getAmount() * (100 - article.getDiscount()) / 100 * price);
        }
        em.getTransaction().commit();
        em.clear();
        
        return new SuccessfulResponse(capc);
    }
    
}
