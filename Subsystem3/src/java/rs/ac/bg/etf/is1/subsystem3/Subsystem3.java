/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rs.ac.bg.etf.is1.subsystem3;

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
public class Subsystem3 {

    @Resource(lookup = "jms/__defaultConnectionFactory")
    private static ConnectionFactory myConnectionFactory;
    
    @Resource(lookup = "subsystem3Queue")
    private static Queue subsystem3Queue;
    
    @Resource(lookup = "serverQueue")
    private static Queue serverQueue;
    
    private static final Map<Command.Type, CommandHandler> handlers = assignHandlers();
    
    private static Map<Command.Type, CommandHandler> assignHandlers(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Subsystem3PU");
        EntityManager em = emf.createEntityManager();
        Map<Command.Type, CommandHandler> map = new EnumMap<>(Command.Type.class);
        map.put(Command.Type.PAYMENT_PROCESS, new PaymentProcessHandler(em));      
        map.put(Command.Type.GET_ALL_ORDERS_FOR_USER, new GetAllOrdersForUserHandler(em));
        map.put(Command.Type.GET_ALL_ORDERS, new GetAllOrdersHandler(em));
        map.put(Command.Type.GET_ALL_TRANSACTIONS, new GetAllTransactionsHandler(em));
        return map;
    }
    
    public static void main(String[] args) {
        
        JMSContext context = myConnectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        JMSConsumer consumer = context.createConsumer(subsystem3Queue);
        Message msg;
        msg = consumer.receiveNoWait();
        while(msg != null){
            msg = consumer.receiveNoWait();
        }
        
        System.out.println("Subsystem 3 started!");        
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
                Logger.getLogger(Subsystem3.class.getName()).log(Level.SEVERE, null, ex);
            }                        
        }
    }
    
}
