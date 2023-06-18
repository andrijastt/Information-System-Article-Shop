/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.subsystem3;

import java.util.List;
import javax.persistence.EntityManager;
import rs.ac.bg.etf.is1.commands.Command;
import rs.ac.bg.etf.is1.commands.GetAllOrdersCommand;
import rs.ac.bg.etf.is1.commands.GetAllOrdersForUserCommand;
import rs.ac.bg.etf.is1.entities.Orders;
import rs.ac.bg.etf.is1.responses.CommandHandler;
import rs.ac.bg.etf.is1.responses.DataResponse;
import rs.ac.bg.etf.is1.responses.JMSResponse;

/**
 *
 * @author stoja
 */
public class GetAllOrdersHandler extends CommandHandler {

    public GetAllOrdersHandler(EntityManager em) {
        super(em);
    }

    @Override
    public JMSResponse handle(Command cmd) {
        GetAllOrdersCommand gaoc = (GetAllOrdersCommand) cmd;                       
        List<Orders> orders = em.createNamedQuery("Orders.findAll").getResultList();
        return new DataResponse(gaoc, orders); 
    }
    
}
