/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.subsystem2;

import java.util.List;
import javax.persistence.EntityManager;
import rs.ac.bg.etf.is1.commands.Command;
import rs.ac.bg.etf.is1.commands.CreateCategoryCommand;
import rs.ac.bg.etf.is1.entities.Category;
import rs.ac.bg.etf.is1.entities.Subcategory;
import rs.ac.bg.etf.is1.responses.CommandHandler;
import rs.ac.bg.etf.is1.responses.FailedResponse;
import rs.ac.bg.etf.is1.responses.JMSResponse;
import rs.ac.bg.etf.is1.responses.SuccessfulResponse;

/**
 *
 * @author stoja
 */
public class CreateCategoryHandler extends CommandHandler {

    public CreateCategoryHandler(EntityManager em) {
        super(em);
    }

    @Override
    public JMSResponse handle(Command cmd) {
        
        CreateCategoryCommand ccc = (CreateCategoryCommand) cmd;
        
        List<Category> categories = em.createNamedQuery("Category.findByName").setParameter("name", ccc.getName()).getResultList();
        if(!categories.isEmpty()){            
            return new FailedResponse(ccc, "Category name already exists!");           
        }
        
        Category subCategory = null;
        if(!ccc.getIDSubcategory().equals("")){
            subCategory = em.find(Category.class, Integer.parseInt(ccc.getIDSubcategory()));
            if(subCategory == null){
                return new FailedResponse(ccc, "Subcategory doesn't exists!");           
            }
        }                
                
        em.getTransaction().begin();                
        Category category = new Category();
        category.setName(ccc.getName());
        em.persist(category);
        em.flush();
        if(subCategory != null){
            Subcategory subcategory = new Subcategory();
            subcategory.setIDCategory(category.getIDCategory());
            subcategory.setIDSubcategory(subCategory);
            em.persist(subcategory);
        }
        em.getTransaction().commit();
        em.clear();
        
        return new SuccessfulResponse(ccc);        
    }
    
}
