/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.subsystem2;

import java.util.List;
import javax.persistence.EntityManager;
import rs.ac.bg.etf.is1.commands.ChangeDiscountForArticleCommand;
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
public class ChangeDiscountForArticleHandler extends CommandHandler {

    public ChangeDiscountForArticleHandler(EntityManager em) {
        super(em);
    }

    @Override
    public JMSResponse handle(Command cmd) {
        
        ChangeDiscountForArticleCommand cdfac = (ChangeDiscountForArticleCommand) cmd;
        
        int IDUser = Integer.parseInt(cdfac.getIDUser());
        
        User user = em.find(User.class, IDUser);
        if(user == null){
            return new FailedResponse(cdfac, "User with ID " + cdfac.getIDUser() + " doesn't exist!");
        }
        
        int IDArticle = Integer.parseInt(cdfac.getIDArticle());
        Article article = em.find(Article.class, IDArticle);
        if(article == null){
            return new FailedResponse(cdfac, "Article with ID " + cdfac.getIDArticle() + " doesn't exist!");
        }
        if(article.getIDUser() != user){
            return new FailedResponse(cdfac, "User doesn't sell this article");
        }
        
        // ovde moze da se desi situacija da popust artikla moze da se promeni dok su artikli u korpi, pa ih zato azuriramo tako sto smanjujemo staru cenu i dodajemo novu
        em.getTransaction().begin();
        List<Incart> incart = em.createNamedQuery("Incart.findByIDArticle").setParameter("iDArticle", article.getIDArticle()).getResultList();
        for(Incart item: incart){
            Cart cart = em.find(Cart.class, item.getIncartPK());
            cart.setTotalPrice(cart.getTotalPrice() -item.getAmount() * (100 - article.getDiscount()) * article.getPrice() / 100);
        }
        
        int discount = Integer.parseInt(cdfac.getDiscount());
        article.setDiscount(discount);                
        
        for(Incart item: incart){
            Cart cart = em.find(Cart.class, item.getIncartPK());
            cart.setTotalPrice(cart.getTotalPrice() + item.getAmount() * (100 - article.getDiscount()) * article.getPrice() / 100);
        }
        
        em.getTransaction().commit();
        em.clear();
        
        return new SuccessfulResponse(cdfac);                
    }
    
}
