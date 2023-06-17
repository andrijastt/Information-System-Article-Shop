/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.subsystem1;

import java.util.List;
import javax.persistence.EntityManager;
import rs.ac.bg.etf.is1.commands.Command;
import rs.ac.bg.etf.is1.commands.GetUsersCommand;
import rs.ac.bg.etf.is1.entities.User;
import rs.ac.bg.etf.is1.responses.CommandHandler;
import rs.ac.bg.etf.is1.responses.DataResponse;
import rs.ac.bg.etf.is1.responses.JMSResponse;

/**
 *
 * @author stoja
 */
public class GetUsersHandler extends CommandHandler {   

    public GetUsersHandler(EntityManager em) {
        super(em);
    }

    @Override
    public JMSResponse handle(Command cmd) {
        GetUsersCommand gcc = (GetUsersCommand) cmd;        
        List<User> users = em.createNamedQuery("User.findAll").getResultList();        
        DataResponse<List<User>> response = new DataResponse(gcc, users);
        return response;
    }
    
}
