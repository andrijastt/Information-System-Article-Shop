/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.subsystem1;

import javax.persistence.EntityManager;
import rs.ac.bg.etf.is1.commands.AddMoneyToUserCommand;
import rs.ac.bg.etf.is1.commands.Command;
import rs.ac.bg.etf.is1.entities.User;
import rs.ac.bg.etf.is1.responses.CommandHandler;
import rs.ac.bg.etf.is1.responses.FailedResponse;
import rs.ac.bg.etf.is1.responses.JMSResponse;
import rs.ac.bg.etf.is1.responses.SuccessfulResponse;

/**
 *
 * @author stoja
 */
public class AddMoneyToUserHandler extends CommandHandler {

    public AddMoneyToUserHandler(EntityManager em) {
        super(em);
    }

    @Override
    public JMSResponse handle(Command cmd) {
        
        AddMoneyToUserCommand amtuc = (AddMoneyToUserCommand) cmd;
        
        int IDUser = Integer.parseInt(amtuc.getIDUser());        
        User user = em.find(User.class, IDUser);
        
        if(user == null){
            return new FailedResponse(amtuc, "User not found");
        }              
        
        int amount = Integer.parseInt(amtuc.getAmountToAdd());
        
        em.getTransaction().begin();
        user.setMoney(user.getMoney() + amount);
        em.getTransaction().commit();
        em.clear();
        
        return new SuccessfulResponse(amtuc);
    }
    
}
