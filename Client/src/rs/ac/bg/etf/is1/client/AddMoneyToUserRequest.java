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
public class AddMoneyToUserRequest extends Request {

    public AddMoneyToUserRequest(Client client, Scanner scanner) {
        super(client, scanner);
    }

    @Override
    public String name() {
        return "Add monet to user request";
    }

    @Override
    public Response send() {
        
        int temp = 0;
        String amount = "";
        do {            
            amount = this.readString("Amount of money to add to account (must be > 0)");
            temp = Integer.parseInt(amount);
        } while (temp <= 0);
                
        String IDUser = this.readString("IDUser");        
        
        MultivaluedHashMap<String, String> map = new MultivaluedHashMap<>();
        map.add("IDUser", IDUser);
        map.add("amount", amount);
        
        Response post = client.target("http://localhost:8080/Server/users")
                .path("/updateMoney")                           
                .request()
                .post(Entity.form(map));                
                
        System.out.println(post.readEntity(String.class));
        return post;                
    }
    
}
