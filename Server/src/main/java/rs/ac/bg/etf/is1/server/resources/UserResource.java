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
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import rs.ac.bg.etf.is1.commands.AddMoneyToUserCommand;
import rs.ac.bg.etf.is1.commands.CreateUserCommand;
import rs.ac.bg.etf.is1.entities.User;
import rs.ac.bg.etf.is1.responses.JMSResponse;
import rs.ac.bg.etf.is1.responses.SuccessfulResponse;
import rs.ac.bg.etf.is1.server.JMSCommunicator;

/**
 *
 * @author stoja
 */
@Path("users")
public class UserResource {
    
    @PersistenceContext(name = "prodavnicaArtikalaPU")
    EntityManager em;
    
    @Inject
    JMSCommunicator comm;
    
    @POST
    @Path("create")
    public Response createUser(@FormParam("username") String username, @FormParam("password") String password,
            @FormParam("name") String name, @FormParam("lastname") String lastName, @FormParam("address") String address, 
            @FormParam("IDCity") String IDCity){                        
        
        CreateUserCommand ccc = new CreateUserCommand(username, password, name, lastName, address, IDCity, 0);
        JMSResponse response =  comm.exchange(ccc);
        
        if(response instanceof SuccessfulResponse){
            return Response.status(Response.Status.CREATED).entity("User created!").build();
        }        
        return Response.status(Response.Status.BAD_REQUEST).entity("User with username is taken!").build();
                
    }
    
    @POST
    @Path("updateCity/{IDUser}?{IDCity}&{address}")
    public Response updateUserCityAndAddress(@PathParam("IDCity") int IDCity, @PathParam("address") String address){
        
        System.out.println("rs.ac.bg.etf.is1.server.resources.UserResource.updateUserCityAndAddress()" + IDCity);
        System.out.println("rs.ac.bg.etf.is1.server.resources.UserResource.updateUserCityAndAddress()" + address);
        return Response.status(Response.Status.CREATED).build();
    }
    
    @POST
    @Path("updateMoney")
    public Response updateMonet(@FormParam("IDUser") String IDUser, @FormParam("amount") String amount){                
        
        AddMoneyToUserCommand amtuc = new AddMoneyToUserCommand(amount, IDUser);
        JMSResponse response = comm.exchange(amtuc);
                
        if(response instanceof SuccessfulResponse){
            return Response.status(Response.Status.CREATED).entity("Money added to user!").build();
        }        
        return Response.status(Response.Status.BAD_REQUEST).entity("Money noy added to user!").build();
    }
    
    @GET
    @Path("getAllUsers")
    public List<User> getAllUsers(){
        
        List<User> ret = new ArrayList<>();
        return ret;        
    }
}
