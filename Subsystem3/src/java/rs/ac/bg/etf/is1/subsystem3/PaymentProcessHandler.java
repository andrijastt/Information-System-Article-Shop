/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.subsystem3;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import rs.ac.bg.etf.is1.commands.Command;
import rs.ac.bg.etf.is1.commands.PaymentProcessCommand;
import rs.ac.bg.etf.is1.entities.Article;
import rs.ac.bg.etf.is1.entities.Cart;
import rs.ac.bg.etf.is1.entities.Incart;
import rs.ac.bg.etf.is1.entities.Item;
import rs.ac.bg.etf.is1.entities.Orders;
import rs.ac.bg.etf.is1.entities.Transaction;
import rs.ac.bg.etf.is1.entities.User;
import rs.ac.bg.etf.is1.responses.CommandHandler;
import rs.ac.bg.etf.is1.responses.FailedResponse;
import rs.ac.bg.etf.is1.responses.JMSResponse;
import rs.ac.bg.etf.is1.responses.SuccessfulResponse;

/**
 *
 * @author stoja
 */
public class PaymentProcessHandler extends CommandHandler {

    public PaymentProcessHandler(EntityManager em) {
        super(em);
    }

    @Override
    public JMSResponse handle(Command cmd) {
        PaymentProcessCommand ppc = (PaymentProcessCommand) cmd;
        
        int IDUser = Integer.parseInt(ppc.getIDUser());
        User user = em.find(User.class, IDUser);
        
        if(user == null){
            return new FailedResponse(ppc, "User with ID " + ppc.getIDUser() + " doesn't exist!");
        }
        
        Cart cart = em.find(Cart.class, IDUser);
        
        if(user.getMoney() < cart.getTotalPrice()){
            return new FailedResponse(ppc, "User with ID " + ppc.getIDUser() + " doesn't have enough money to pay for order!");
        }
        
        List<Incart> incartItems = em.createNamedQuery("Incart.findByIDUser").setParameter("iDUser", IDUser).getResultList();
        
        if(incartItems.isEmpty()){
            return new FailedResponse(ppc, "No items in cart to pay");
        }
        
        em.getTransaction().begin();
        
        Orders order = new Orders();        
        order.setTotalPrice(cart.getTotalPrice());                        
        order.setTimeCreated(new Date());
        order.setAddress(user.getAddress());
        order.setIDUser(user);
        order.setIDCity(user.getIDCity());
        em.persist(order);
        em.flush();
        
        for(Incart item: incartItems){            
            Article article = item.getArticle();            
            Item newItem = new Item();
            newItem.setAmount(item.getAmount());
            newItem.setTotalArticlePrice(item.getAmount() * article.getPrice() * (100 - article.getDiscount()) / 100);
            newItem.setIDOrder(order);
            newItem.setIDArticle(article);
            em.persist(newItem);
            em.remove(item);
        }
        em.flush();
        
        Transaction transaction = new Transaction();
        transaction.setIDOrder(order);
        transaction.setPaymentTime(new Date());
        transaction.setTotalPrice(cart.getTotalPrice());  
        em.persist(transaction);
        
        cart.setTotalPrice(0);
        user.setMoney(user.getMoney() - order.getTotalPrice());
        em.getTransaction().commit();
        em.clear();
        return new SuccessfulResponse(ppc);
    }
    
    
}
