/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.server;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import rs.ac.bg.etf.is1.commands.Command;
import static rs.ac.bg.etf.is1.commands.Command.Destination.SUBSYSTEM1;
import rs.ac.bg.etf.is1.responses.DataResponse;
import rs.ac.bg.etf.is1.responses.FailedResponse;
import rs.ac.bg.etf.is1.responses.JMSResponse;
import rs.ac.bg.etf.is1.responses.SuccessfulResponse;

/**
 *
 * @author stoja
 */
public class JMSCommunicator {
    
    @Resource(lookup = "jms/__defaultConnectionFactory")
    private static ConnectionFactory myConnectionFactory;
    
    @Resource(lookup = "serverQueue")
    private static Queue serverQueue;
    
    @Resource(lookup = "subsystem1Queue")
    private static Queue subsystem1Queue;
    
    @Resource(lookup = "subsystem2Queue")
    private static Queue subsystem2Queue;
    
    @Resource(lookup = "subsystem3Queue")
    private static Queue subsystem3Queue;
    
    private Queue getQueue(Command.Destination destination){
        
        switch(destination){            
            case SUBSYSTEM1: return subsystem1Queue;
            case SUBSYSTEM2: return subsystem2Queue;
            case SUBSYSTEM3: return subsystem3Queue;            
        }
        return null;
        
    }
    
    public JMSResponse exchange(Command cmd){
        
        try {
            JMSContext context = myConnectionFactory.createContext();
            JMSConsumer consumer = context.createConsumer(serverQueue, "JMSCorrelationID = '" + cmd.getId() + "'");
            JMSProducer producer = context.createProducer();
            
            ObjectMessage objMsg = context.createObjectMessage(cmd);            
            objMsg.setJMSReplyTo(serverQueue);
            objMsg.setJMSCorrelationID(cmd.getId());
            
            Message msg = consumer.receive(5000);
            if(!(msg instanceof ObjectMessage)){
                System.err.println("Message is not an object message"  + msg);
            }            
            Serializable obj = ((ObjectMessage)msg).getObject();
            if(!(obj instanceof JMSResponse)){
                System.err.println("JMSResponse not received"  + msg);
                return new FailedResponse(cmd, "JMSResponse not received");
            }                        
            
            System.out.println("Response: " + obj);
            if(obj instanceof SuccessfulResponse) return (SuccessfulResponse) obj;
            else return (DataResponse) obj;
            
        } catch (JMSException ex) {
            Logger.getLogger(JMSCommunicator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new FailedResponse(cmd, "Messaging error");
    }
}
