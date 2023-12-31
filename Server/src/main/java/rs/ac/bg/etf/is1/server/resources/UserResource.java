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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import rs.ac.bg.etf.is1.commands.AddMoneyToUserCommand;
import rs.ac.bg.etf.is1.commands.ChangeAddressAndCityForUser;
import rs.ac.bg.etf.is1.commands.CreateUserCommand;
import rs.ac.bg.etf.is1.commands.GetUsersCommand;
import rs.ac.bg.etf.is1.entities.User;
import rs.ac.bg.etf.is1.responses.DataResponse;
import rs.ac.bg.etf.is1.responses.FailedResponse;
import rs.ac.bg.etf.is1.responses.JMSResponse;
import rs.ac.bg.etf.is1.responses.SuccessfulResponse;
import rs.ac.bg.etf.is1.rest.UserRest;
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
            SuccessfulResponse sr = (SuccessfulResponse) response;
            return Response.status(Response.Status.CREATED).entity(sr.toString()).build();
        }        
        FailedResponse fr = (FailedResponse) response;
        return Response.status(Response.Status.BAD_REQUEST).entity(fr.toString()).build();
                
    }
    
    @POST
    @Path("updateAddressAndCity")
    public Response updateUserAddressAndCity(@FormParam("IDCity") String IDCity, @FormParam("address") String address, @FormParam("IDUser") String IDUser){
        
        ChangeAddressAndCityForUser caacfu = new ChangeAddressAndCityForUser(IDUser, address, IDCity);        
         JMSResponse response =  comm.exchange(caacfu);
        
        if(response instanceof SuccessfulResponse){
            SuccessfulResponse sr = (SuccessfulResponse) response;
            return Response.status(Response.Status.ACCEPTED).entity(sr.toString()).build();
        }        
        FailedResponse fr = (FailedResponse) response;
        return Response.status(Response.Status.BAD_REQUEST).entity(fr.toString()).build();
    }
    
    @POST
    @Path("updateMoney")
    public Response updateMonet(@FormParam("IDUser") String IDUser, @FormParam("amount") String amount){                
        
        AddMoneyToUserCommand amtuc = new AddMoneyToUserCommand(amount, IDUser);
        JMSResponse response = comm.exchange(amtuc);
                
        if(response instanceof SuccessfulResponse){
            SuccessfulResponse sr = (SuccessfulResponse) response;
            return Response.status(Response.Status.ACCEPTED).entity(sr.toString()).build();
        }        
        FailedResponse fr = (FailedResponse) response;
        return Response.status(Response.Status.BAD_REQUEST).entity(fr.toString()).build();
    }
    
    @GET
    @Path("getAllUsers")        
    public Response getAllUsers(){        
        GetUsersCommand gus = new GetUsersCommand();
        JMSResponse response = comm.exchange(gus);        
        if(response instanceof DataResponse){
            DataResponse<List<User>> dataResponse = (DataResponse<List<User>>) response;
            List<UserRest> users = new ArrayList<>();
           
            for(User user: dataResponse.getData()){                
                UserRest userrest = new UserRest(user.getIDUser(), user.getUsername(), user.getPassword(), user.getName(), 
                        user.getLastname(), user.getAddress(), user.getIDCity().getIDCity(), user.getMoney());
                users.add(userrest);
            }
            return Response.status(Response.Status.OK).entity(users).build();
        }        
        return Response.status(Response.Status.BAD_REQUEST).entity("Couldn't get all users!").build();
    }
}
