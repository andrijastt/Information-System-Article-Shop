/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.server.resources;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import rs.ac.bg.etf.is1.entities.User;

/**
 *
 * @author stoja
 */
@Path("users")
public class UserResource {
    
    @PersistenceContext(name = "prodavnicaArtikalaPU")
    EntityManager em;
    
    @POST
    @Path("create")
    public Response createUser(@FormParam("username") String username, @FormParam("password") String password,
            @FormParam("name") String name, @FormParam("lastName") String lastName, @FormParam("address") String address, 
            @FormParam("IDCity") int IDCity){                        
        
        return Response.status(Response.Status.CREATED).build();
    }
    
    @POST
    @Path("updateCity/{IDUser}?{IDCity}&{address}")
    public Response updateUserCityAndAddress(@PathParam("IDCity") int IDCity, @PathParam("address") String address){
        
        System.out.println("rs.ac.bg.etf.is1.server.resources.UserResource.updateUserCityAndAddress()" + IDCity);
        System.out.println("rs.ac.bg.etf.is1.server.resources.UserResource.updateUserCityAndAddress()" + address);
        return Response.status(Response.Status.CREATED).build();
    }
    
    @POST
    @Path("updateMoney/{IDUser}/{amount}")
    public Response updateMonet(@PathParam("IDUser") int IDUser, @PathParam("amount") int amount){                
        
        return Response.status(Response.Status.CREATED).build();
    }
    
    @GET
    @Path("getAllUsers")
    public List<User> getAllUsers(){
        
        List<User> ret = new ArrayList<>();
        return ret;        
    }
}
