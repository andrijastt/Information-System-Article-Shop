/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.subsystem2;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import rs.ac.bg.etf.is1.commands.Command;
import rs.ac.bg.etf.is1.commands.GetAllItemsInCartForUserCommand;
import rs.ac.bg.etf.is1.entities.Article;
import rs.ac.bg.etf.is1.entities.Incart;
import rs.ac.bg.etf.is1.entities.User;
import rs.ac.bg.etf.is1.responses.CommandHandler;
import rs.ac.bg.etf.is1.responses.DataResponse;
import rs.ac.bg.etf.is1.responses.FailedResponse;
import rs.ac.bg.etf.is1.responses.JMSResponse;

/**
 *
 * @author stoja
 */
public class GetAllItemsInCartForUserHandler extends CommandHandler {

    public GetAllItemsInCartForUserHandler(EntityManager em) {
        super(em);
    }

    @Override
    public JMSResponse handle(Command cmd) {
        
        GetAllItemsInCartForUserCommand  gaiicfuc = (GetAllItemsInCartForUserCommand) cmd;
        int IDUser = Integer.parseInt(gaiicfuc.getIDUser());
        User user = em.find(User.class, IDUser);
        
        if(user == null){
            return new FailedResponse(gaiicfuc, "User with ID " + gaiicfuc.getIDUser() + " doesn't exist");
        }
        
        List<Incart> incart = em.createNamedQuery("Incart.findByIDUser").setParameter("iDUser", IDUser).getResultList();        
        return new DataResponse(gaiicfuc, incart);        

//        List<Article> articles = new ArrayList<>();        
//        for(Incart cart: incart){
//            articles.add(cart.getArticle());                        
//        }
//        return new DataResponse(gaiicfuc, articles);        
    }
    
}
