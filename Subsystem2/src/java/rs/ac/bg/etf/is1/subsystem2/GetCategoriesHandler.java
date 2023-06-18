/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.subsystem2;

import java.util.List;
import javax.persistence.EntityManager;
import rs.ac.bg.etf.is1.commands.Command;
import rs.ac.bg.etf.is1.commands.GetCategoriesCommand;
import rs.ac.bg.etf.is1.entities.Category;
import rs.ac.bg.etf.is1.responses.CommandHandler;
import rs.ac.bg.etf.is1.responses.DataResponse;
import rs.ac.bg.etf.is1.responses.JMSResponse;

/**
 *
 * @author stoja
 */
public class GetCategoriesHandler extends CommandHandler {

    public GetCategoriesHandler(EntityManager em) {
        super(em);
    }

    @Override
    public JMSResponse handle(Command cmd) {        
        GetCategoriesCommand gcc = (GetCategoriesCommand) cmd;
        List<Category> categories = em.createNamedQuery("Category.findAll").getResultList();
        return new DataResponse(gcc, categories);                        
    }
    
}
