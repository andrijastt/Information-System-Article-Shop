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
public class RemoveArticleAmountInCartRequest extends Request {

    public RemoveArticleAmountInCartRequest(Client client, Scanner scanner) {
        super(client, scanner);
    }

    @Override
    public String name() {
        return "Remove article amount in cart reqeust";
    }

    @Override
    public Response send() {
        
        String IDUser = this.readString("IDUser that is wants to remove items");
        String IDArticle = this.readString("IDArticle that user wants to remove");
        String amount = this.readString("Amount");
        
        MultivaluedHashMap<String, String> map = new MultivaluedHashMap<>();
        map.add("amount", amount);
        map.add("IDUser", IDUser);
        map.add("IDArticle", IDArticle);
        
        Response response = client.target("http://localhost:8080/Server/article/removeArticleAmountInCart")
                .request()
                .post(Entity.form(map));
                        
        System.out.println(response.readEntity(String.class));
        return response;        
    }
    
}
