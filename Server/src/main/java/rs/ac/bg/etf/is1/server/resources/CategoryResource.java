/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.server.resources;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import rs.ac.bg.etf.is1.commands.CreateCategoryCommand;
import rs.ac.bg.etf.is1.commands.GetCategoriesCommand;
import rs.ac.bg.etf.is1.entities.Category;
import rs.ac.bg.etf.is1.responses.DataResponse;
import rs.ac.bg.etf.is1.responses.FailedResponse;
import rs.ac.bg.etf.is1.responses.JMSResponse;
import rs.ac.bg.etf.is1.responses.SuccessfulResponse;
import rs.ac.bg.etf.is1.rest.CategoryRest;
import rs.ac.bg.etf.is1.server.JMSCommunicator;

/**
 *
 * @author stoja
 */
@Path("category")
public class CategoryResource {
    
    @Inject
    JMSCommunicator comm;
    
    @PersistenceContext(name = "prodavnicaArtikalaPU")
    EntityManager em;
    
    @POST
    @Path("create")
    public Response createCategory(@FormParam("name") String name, @FormParam("IDSubcategory") String IDSubcategory){        
        CreateCategoryCommand ccc = new CreateCategoryCommand(name, IDSubcategory);
        JMSResponse response = comm.exchange(ccc);
        if(response instanceof SuccessfulResponse){
            SuccessfulResponse sr = (SuccessfulResponse)response;
            return Response.status(Response.Status.CREATED).entity(sr.toString()).build();
        }
        FailedResponse fr = (FailedResponse) response;
        return Response.status(Response.Status.BAD_REQUEST).entity(fr.toString()).build();
    }
    
    @GET
    @Path("getCategories")
    public Response getCategories(){
        
        GetCategoriesCommand gcc = new GetCategoriesCommand();
        JMSResponse response = comm.exchange(gcc);
        
        if(response instanceof DataResponse){            
            DataResponse<List<Category>> dataresp = (DataResponse<List<Category>>) response;
            List<CategoryRest> categoriesRest = new ArrayList<>();
            for(Category cat: dataresp.getData()){
                CategoryRest categoryRest = new CategoryRest(cat.getIDCategory(), cat.getName());
                categoriesRest.add(categoryRest);
            }
            return Response.status(Response.Status.OK).entity(categoriesRest).build();
        }
        
        FailedResponse fr = (FailedResponse) response;
        return Response.status(Response.Status.BAD_REQUEST).entity(fr.toString()).build();
    }
}
