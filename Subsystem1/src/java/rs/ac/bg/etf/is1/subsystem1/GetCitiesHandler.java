/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.subsystem1;

import java.util.List;
import javax.persistence.EntityManager;
import rs.ac.bg.etf.is1.commands.Command;
import rs.ac.bg.etf.is1.commands.GetCitiesCommand;
import rs.ac.bg.etf.is1.entities.City;
import rs.ac.bg.etf.is1.responses.CommandHandler;
import rs.ac.bg.etf.is1.responses.DataResponse;
import rs.ac.bg.etf.is1.responses.JMSResponse;

/**
 *
 * @author stoja
 */
public class GetCitiesHandler extends CommandHandler {   

    public GetCitiesHandler(EntityManager em) {
        super(em);
    }

    @Override
    public JMSResponse handle(Command cmd) {        
        GetCitiesCommand gcc = (GetCitiesCommand) cmd;        
        List<City> cities = em.createNamedQuery("City.findAll").getResultList();        
        DataResponse<List<City>> response = new DataResponse(gcc, cities);
        return response;                
    }
    
}
