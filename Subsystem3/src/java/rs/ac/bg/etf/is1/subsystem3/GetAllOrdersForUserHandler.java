/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.subsystem3;

import java.util.List;
import javax.persistence.EntityManager;
import rs.ac.bg.etf.is1.commands.Command;
import rs.ac.bg.etf.is1.commands.GetAllOrdersForUserCommand;
import rs.ac.bg.etf.is1.entities.Orders;
import rs.ac.bg.etf.is1.entities.User;
import rs.ac.bg.etf.is1.responses.CommandHandler;
import rs.ac.bg.etf.is1.responses.DataResponse;
import rs.ac.bg.etf.is1.responses.FailedResponse;
import rs.ac.bg.etf.is1.responses.JMSResponse;

/**
 *
 * @author stoja
 */
public class GetAllOrdersForUserHandler extends CommandHandler {

    public GetAllOrdersForUserHandler(EntityManager em) {
        super(em);
    }

    @Override
    public JMSResponse handle(Command cmd) {
        GetAllOrdersForUserCommand gaofuc = (GetAllOrdersForUserCommand) cmd;
        
        int IDUser = Integer.parseInt(gaofuc.getIDUser());
        User user = em.find(User.class, IDUser);
        if(user == null){
            return new FailedResponse(gaofuc, "User with ID " + IDUser + " doesn't exist");
        }
        
        List<Orders> orders = em.createQuery("Select o from Orders o where o.iDUser = :user").setParameter("user", user).getResultList();
        return new DataResponse(gaofuc, orders);        
    }
    
}
