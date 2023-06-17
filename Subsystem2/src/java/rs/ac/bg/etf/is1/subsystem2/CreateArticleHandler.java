/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.subsystem2;

import java.util.List;
import javax.persistence.EntityManager;
import rs.ac.bg.etf.is1.commands.Command;
import rs.ac.bg.etf.is1.commands.CreateArticleCommand;
import rs.ac.bg.etf.is1.entities.Article;
import rs.ac.bg.etf.is1.entities.Category;
import rs.ac.bg.etf.is1.entities.User;
import rs.ac.bg.etf.is1.responses.CommandHandler;
import rs.ac.bg.etf.is1.responses.FailedResponse;
import rs.ac.bg.etf.is1.responses.JMSResponse;
import rs.ac.bg.etf.is1.responses.SuccessfulResponse;

/**
 *
 * @author stoja
 */
public class CreateArticleHandler extends CommandHandler {

    public CreateArticleHandler(EntityManager em) {
        super(em);
    }

    @Override
    public JMSResponse handle(Command cmd) {
        
        CreateArticleCommand cac = (CreateArticleCommand) cmd;                
        
        int IDUser = Integer.parseInt(cac.getIDUser());                
        User user = em.find(User.class, IDUser);
        if(user == null){
            return new FailedResponse(cac, "User with this ID doesn't exist!");
        }
        
        int IDCategory = Integer.parseInt(cac.getIDCategory());
        Category category = em.find(Category.class, IDCategory);
        if(category == null){
            return new FailedResponse(cac, "Category with this ID doesn't exist!");
        }
        
        List<Article> articles = em.createNamedQuery("Article.findByName").setParameter("name", cac.getName()).getResultList();
        if(!articles.isEmpty()){            
            for(Article art: articles){                
                if(art.getIDUser().getIDUser() == IDUser && art.getIDCategory().getIDCategory() == IDCategory){
                    return new FailedResponse(cac, "Already selling an article with that name and category!");
                }                
            }            
        }
        
        em.getTransaction().begin();        
        Article article = new Article();
        article.setName(cac.getName());
        article.setDescription(cac.getDescription());
        article.setIDUser(user);
        article.setDiscount(0);
        article.setIDCategory(category);
        article.setPrice(Integer.parseInt(cac.getPrice()));
        em.persist(article);
        em.getTransaction().commit();
        em.clear();
        
        return new SuccessfulResponse(cac);
    }
    
    
}
