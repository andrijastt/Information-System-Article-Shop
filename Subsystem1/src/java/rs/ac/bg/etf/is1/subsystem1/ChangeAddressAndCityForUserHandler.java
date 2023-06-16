/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.subsystem1;

import javax.persistence.EntityManager;
import rs.ac.bg.etf.is1.commands.ChangeAddressAndCityForUser;
import rs.ac.bg.etf.is1.commands.Command;
import rs.ac.bg.etf.is1.entities.City;
import rs.ac.bg.etf.is1.entities.User;
import rs.ac.bg.etf.is1.responses.CommandHandler;
import rs.ac.bg.etf.is1.responses.FailedResponse;
import rs.ac.bg.etf.is1.responses.JMSResponse;

/**
 *
 * @author stoja
 */
public class ChangeAddressAndCityForUserHandler extends CommandHandler {

    public ChangeAddressAndCityForUserHandler(EntityManager em) {
        super(em);
    }

    @Override
    public JMSResponse handle(Command cmd) {
        
        ChangeAddressAndCityForUser caacfu = (ChangeAddressAndCityForUser) cmd;
        
        int IDUser = Integer.parseInt(caacfu.getIDUser());
        
        User user = em.find(User.class, IDUser);
        
        if(user == null){
            return new FailedResponse(caacfu, "No user found!");
        }
        
        String IDCityString = caacfu.getIDCity();    
        int IDCity = 0;
        City city = new City();
        if(IDCityString != null){
            if(!IDCityString.equals("")){                
                IDCity = Integer.parseInt(IDCityString);                
                city = em.find(City.class, IDCity);                
                if(city == null){
                    return new FailedResponse(caacfu, "No city found!");
                }                
            }
        }
        
        em.getTransaction().begin();
        if(IDCity != 0){
            user.setIDCity(city);
        }
        if(caacfu.getAddress() != null){
            if(!caacfu.getAddress().equals("")){
                user.setAddress(caacfu.getAddress());
            }
        }
        em.getTransaction().commit();
        em.clear();        
        
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
