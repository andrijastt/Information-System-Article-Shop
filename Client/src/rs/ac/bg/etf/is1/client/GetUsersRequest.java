/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.client;

import java.util.List;
import java.util.Scanner;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import rs.ac.bg.etf.is1.client.rest.UserRest;

/**
 *
 * @author stoja
 */
public class GetUsersRequest extends Request {

    public GetUsersRequest(Client client, Scanner scanner) {
        super(client, scanner);
    }

    @Override
    public String name() {
        return "Get users request";
    }

    @Override
    public Response send() {
        
        Response response = client.target("http://localhost:8080/Server/users")
                .path("getAllUsers")
                .request(MediaType.APPLICATION_XML)
                .get();
        
        System.out.println(response.toString());                                      
        System.out.println(response.getEntity());                  
        List<UserRest> users = (List<UserRest>)response.readEntity(List.class);
        
//        List<User> users = client.target("http://localhost:8080/Server/users")
//                .path("getAllUsers")
//                .request(MediaType.APPLICATION_XML)
//                .get(new GenericType<List<User>>(){});
        
        for(UserRest user: users){
            System.out.println(user.toString() + "/n");
        }
        
        return Response.status(Response.Status.OK).build();
    }
    
}
