/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.subsystem2;

import java.util.List;
import javax.persistence.EntityManager;
import rs.ac.bg.etf.is1.commands.Command;
import rs.ac.bg.etf.is1.commands.RemoveArticleAmountInCartCommand;
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
public class RemoveArticleAmountInCartHandler extends CommandHandler {

    public RemoveArticleAmountInCartHandler(EntityManager em) {
        super(em);
    }

    @Override
    public JMSResponse handle(Command cmd) {
        
        RemoveArticleAmountInCartCommand aaacc = (RemoveArticleAmountInCartCommand) cmd;
        int IDUser = Integer.parseInt(aaacc.getIDUser());
        User user = em.find(User.class, IDUser);
        if(user == null){
            return new FailedResponse(aaacc, "User with ID " + aaacc.getIDUser() + " doesn't exist!");
        }
        
        int IDArticle = Integer.parseInt(aaacc.getIDArticle());
        Article article = em.find(Article.class, IDArticle);
        if(article == null){
            return new FailedResponse(aaacc, "Article with ID " + aaacc.getIDArticle() + " doesn't exist!");
        }
        if(article.getIDUser() == user){
            return new FailedResponse(aaacc, "User can't remove item from himself!");
        }
        
        em.getTransaction().begin();
        int amount = Integer.parseInt(aaacc.getAmount());
        
        List<Incart> incart = em.createQuery("Select i from Incart i where i.incartPK.iDUser = :IDUser and i.incartPK.iDArticle = :IDArticle")
                .setParameter("IDUser", IDUser)
                .setParameter("IDArticle", IDArticle)
                .getResultList();
        
        Incart incartItem = null;
        Cart cart = em.find(Cart.class, IDUser);
        if(!incart.isEmpty()){            
            incartItem = incart.get(0);            
            if(incartItem.getAmount() - amount <= 0){
                cart.setTotalPrice(cart.getTotalPrice() - incartItem.getAmount() * article.getPrice() * (100 - article.getDiscount()) / 100);
                em.remove(incartItem);                
            } else {
                cart.setTotalPrice(cart.getTotalPrice() - amount * article.getPrice() * (100 - article.getDiscount()) / 100);
                incartItem.setAmount(incartItem.getAmount() - amount);
            }                        
        } else {
            return new FailedResponse(aaacc, "There is no item to be removed!");
        }                                                     
        
        em.getTransaction().commit();
        em.clear();                
        
        return new SuccessfulResponse(aaacc);                
    }
    
}
