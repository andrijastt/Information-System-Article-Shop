/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.subsystem2;

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
        
        Incart incart = new Incart();
        incart.setAmount(amount);
        incart.setIncartPK(new IncartPK(IDUser, IDArticle));
        em.persist(incart);
        em.flush();
        
        Cart cart = em.find(Cart.class, user);
        cart.setTotalPrice(cart.getTotalPrice() + amount * article.getPrice() * (100 - article.getDiscount()) / 100);
        
        em.getTransaction().commit();
        em.clear();                
        
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
