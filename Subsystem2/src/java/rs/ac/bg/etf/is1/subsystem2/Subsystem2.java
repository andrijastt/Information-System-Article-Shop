/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rs.ac.bg.etf.is1.subsystem2;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import rs.ac.bg.etf.is1.commands.Command;
import rs.ac.bg.etf.is1.responses.CommandHandler;
import rs.ac.bg.etf.is1.responses.JMSResponse;

/**
 *
 * @author stoja
 */
public class Subsystem2 {

    @Resource(lookup = "jms/__defaultConnectionFactory")
    private static ConnectionFactory myConnectionFactory;
    
    @Resource(lookup = "subsystem2Queue")
    private static Queue subsystem2Queue;
    
    @Resource(lookup = "serverQueue")
    private static Queue serverQueue;
    
    private static final Map<Command.Type, CommandHandler> handlers = assignHandlers();
    
    private static Map<Command.Type, CommandHandler> assignHandlers(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Subsystem2PU");
        EntityManager em = emf.createEntityManager();
        Map<Command.Type, CommandHandler> map = new EnumMap<>(Command.Type.class);
        map.put(Command.Type.CREATE_CATEGORY, new CreateCategoryHandler(em));
        map.put(Command.Type.CREATE_ARTICLE, new CreateArticleHandler(em));
        map.put(Command.Type.CHANGE_ARTICLE_PRICE, new ChangeArticlePriceHandler(em));
        map.put(Command.Type.CHANGE_DISCOUNT_FOR_ARTICLE, new ChangeDiscountForArticleHandler(em));
        map.put(Command.Type.ADD_ARTICLE_AMOUNT_IN_CART, new AddArticleAmountInCartHandler(em));
        map.put(Command.Type.REMOVE_ARTICLE_AMOUNT_IN_CART, new RemoveArticleAmountInCartHandler(em));
        map.put(Command.Type.GET_CATEGORIES, new GetCategoriesHandler(em));
        map.put(Command.Type.GET_ALL_ARTICLES_THAT_USER_SELLS, new GetAllArticlesThatUserSellsHandler(em));
        map.put(Command.Type.GET_ALL_ITEMS_IN_CART_FOR_USER, new GetAllItemsInCartForUserHandler(em));
        return map;
    }
    
    public static void main(String[] args) {
        
        JMSContext context = myConnectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        JMSConsumer consumer = context.createConsumer(subsystem2Queue);
        Message msg;
        msg = consumer.receiveNoWait();
        while(msg != null){
            msg = consumer.receiveNoWait();
        }
        
        System.out.println("Subsystem 2 started!");        
        while(true){
            try {
                msg = consumer.receive();
                
                Destination dest = msg.getJMSReplyTo();
                if(!(dest instanceof Queue)){
                    System.err.println("Destination is not an Queue");
                    continue;
                }
                
                if(!(msg instanceof ObjectMessage)){
                    System.err.println("Message is not an instance of ObjectMessage");
                    continue;
                }
                ObjectMessage objMsg = (ObjectMessage) msg;
                
                Serializable obj = (objMsg).getObject();
                if(!(obj instanceof Command)){
                    System.err.println("Didn't receive command");
                    continue;
                }
                
                Command cmd = (Command) obj;
                CommandHandler handler = handlers.get(cmd.getType());                
                if(handler == null){
                    System.err.println("No adequate handler");
                    continue;
                }
                
                JMSResponse response = handler.handle(cmd);
                ObjectMessage msgSent = context.createObjectMessage(response);
                msgSent.setJMSCorrelationID(msg.getJMSCorrelationID());
                System.out.println("Sending response " + response);
                producer.send(dest, msgSent);
                
            } catch (JMSException ex) {
                Logger.getLogger(Subsystem2.class.getName()).log(Level.SEVERE, null, ex);
            }                        
        }
    }
    
}
