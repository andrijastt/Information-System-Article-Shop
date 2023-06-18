/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.subsystem2;

import java.util.List;
import javax.persistence.EntityManager;
import rs.ac.bg.etf.is1.commands.AddArticleAmountInCartCommand;
import rs.ac.bg.etf.is1.commands.Command;
import rs.ac.bg.etf.is1.entities.Article;
import rs.ac.bg.etf.is1.entities.Cart;
import rs.ac.bg.etf.is1.entities.Incart;
import rs.ac.bg.etf.is1.entities.IncartPK;
import rs.ac.bg.etf.is1.entities.User;
import rs.ac.bg.etf.is1.responses.CommandHandler;
import rs.ac.bg.etf.is1.responses.FailedResponse;
import rs.ac.bg.etf.is1.responses.JMSResponse;
import rs.ac.bg.etf.is1.responses.SuccessfulResponse;

/**
 *
 * @author stoja
 */
public class AddArticleAmountInCartHandler extends CommandHandler {

    public AddArticleAmountInCartHandler(EntityManager em) {
        super(em);
    }

    @Override
    public JMSResponse handle(Command cmd) {
        
        AddArticleAmountInCartCommand aaacc = (AddArticleAmountInCartCommand) cmd;
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
            return new FailedResponse(aaacc, "User can't buy item from himself!");
        }
        
        em.getTransaction().begin();
        int amount = Integer.parseInt(aaacc.getAmount());
        
        List<Incart> incart = em.createQuery("Select i from Incart i where i.incartPK.iDUser = :IDUser and i.incartPK.iDArticle = :IDArticle")
                .setParameter("IDUser", IDUser)
                .setParameter("IDArticle", IDArticle)
                .getResultList();
        
        Incart incartItem = null;
        if(!incart.isEmpty()){            
            incartItem = incart.get(0);
            incartItem.setAmount(incartItem.getAmount() + amount);            
        } else {
            incartItem = new Incart();
            incartItem.setAmount(amount);
            incartItem.setIncartPK(new IncartPK(IDUser, IDArticle));            
        }
        
        if(incartItem == null) return new FailedResponse(aaacc, "Error while updating/inserting!");
        
        em.persist(incartItem);
        em.flush();

        Cart cart = em.find(Cart.class, IDUser);
        cart.setTotalPrice(cart.getTotalPrice() + amount * article.getPrice() * (100 - article.getDiscount()) / 100);
                        
        em.getTransaction().commit();
        em.clear();                
        
        return new SuccessfulResponse(aaacc);
    }
    
}
