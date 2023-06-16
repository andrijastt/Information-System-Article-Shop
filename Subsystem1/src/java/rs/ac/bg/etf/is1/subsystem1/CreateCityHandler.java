/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.subsystem1;

import java.util.List;
import javax.persistence.EntityManager;
import rs.ac.bg.etf.is1.commands.Command;
import rs.ac.bg.etf.is1.commands.CreateCityCommand;
import rs.ac.bg.etf.is1.entities.City;
import rs.ac.bg.etf.is1.responses.CommandHandler;
import rs.ac.bg.etf.is1.responses.FailedResponse;
import rs.ac.bg.etf.is1.responses.JMSResponse;
import rs.ac.bg.etf.is1.responses.SuccessfulResponse;

/**
 *
 * @author stoja
 */
public class CreateCityHandler extends CommandHandler {

    public CreateCityHandler(EntityManager em) {
        super(em);
    }   
    
    @Override
    public JMSResponse handle(Command cmd) {
        
        CreateCityCommand cityCmd = (CreateCityCommand) cmd;
        
        List<City> cities = em.createNamedQuery("City.findByName").setParameter("name", cityCmd.getName()).getResultList();
        
        if(!cities.isEmpty()){
            return new FailedResponse(cmd, "City already exists");
        }
        
        City city = new City();
        city.setName(cityCmd.getName());
        em.getTransaction().begin();
        em.persist(city);
        em.getTransaction().commit();
        em.clear();
        return new SuccessfulResponse(cmd);

    }
    
}
