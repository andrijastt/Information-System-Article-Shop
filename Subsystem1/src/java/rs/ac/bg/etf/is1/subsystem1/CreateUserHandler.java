/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.subsystem1;

import java.util.List;
import javax.persistence.EntityManager;
import rs.ac.bg.etf.is1.commands.Command;
import rs.ac.bg.etf.is1.commands.CreateUserCommand;
import rs.ac.bg.etf.is1.entities.Cart;
import rs.ac.bg.etf.is1.entities.City;
import rs.ac.bg.etf.is1.entities.User;
import rs.ac.bg.etf.is1.responses.CommandHandler;
import rs.ac.bg.etf.is1.responses.FailedResponse;
import rs.ac.bg.etf.is1.responses.JMSResponse;
import rs.ac.bg.etf.is1.responses.SuccessfulResponse;

/**
 *
 * @author stoja
 */
public class CreateUserHandler extends CommandHandler{

    public CreateUserHandler(EntityManager em) {
        super(em);
    }

    @Override
    public JMSResponse handle(Command cmd) {
        
        CreateUserCommand cuc = (CreateUserCommand) cmd;
        
        List<User> users = em.createNamedQuery("User.findByUsername").setParameter("username", cuc.getUsername()).getResultList();
        
        if(!users.isEmpty()){
            return new FailedResponse(cuc, "User already exists");
        }                
        
        List<City> cities = em.createNamedQuery("City.findByIDCity").setParameter("iDCity", Integer.parseInt(cuc.getIDCity())).getResultList();
        if(cities.isEmpty()){
            return new FailedResponse(cuc, "City not found");
        }
        em.getTransaction().begin();
        User user = new User();
        user.setUsername(cuc.getUsername());
        user.setPassword(cuc.getPassword());
        user.setName(cuc.getName());
        user.setLastname(cuc.getLastname());
        user.setAddress(cuc.getAddress());
        user.setIDCity(cities.get(0));
        user.setMoney(0);
        em.persist(user);
        Cart cart = new Cart();
        cart.setTotalPrice(0);
        cart.setUser(user);
        em.persist(cart);
        em.getTransaction().commit();
        em.clear();
                
        return new SuccessfulResponse(cuc);
    }
    
}
