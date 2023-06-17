/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.client;

import java.util.Scanner;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.Response;

/**
 *
 * @author stoja
 */
public class ChangeAddressAndCityForUserRequest extends Request{

    public ChangeAddressAndCityForUserRequest(Client client, Scanner scanner) {
        super(client, scanner);
    }

    @Override
    public String name() {
        return "Change address and city for user";
    }

    @Override
    public Response send() {
        
        String IDCity = this.readString("New IDCity (leave blank if you dont want to change)");
        String address = this.readString("New address (leave blank if you dont want to change)");
        String IDUser = this.readString("IDUser");        
        
        MultivaluedHashMap<String, String> map = new MultivaluedHashMap<>();
        map.add("IDUser", IDUser);
        map.add("IDCity", IDCity);
        map.add("address", address);
        
        Response post = client.target("http://localhost:8080/Server/users")
                .path("/updateAddressAndCity")                           
                .request()
                .post(Entity.form(map));                
                
        System.out.println(post.getEntity());
        return post;                
    }
    
}
