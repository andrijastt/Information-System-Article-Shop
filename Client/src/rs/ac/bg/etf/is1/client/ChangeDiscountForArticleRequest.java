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
public class ChangeDiscountForArticleRequest extends Request {

    public ChangeDiscountForArticleRequest(Client client, Scanner scanner) {
        super(client, scanner);
    }

    @Override
    public String name() {
        return "Change discount for article request";
    }

    @Override
    public Response send() {
        
        String discount = "";
        int dis = -1;
        do {            
            
            discount = this.readString("Enter discount (0 - 99)");
            dis = Integer.parseInt(discount);
            
        } while (dis < 0 || dis >= 100);
        
        String IDUser = this.readString("IDUser");
        String IDArticle = this.readString("IDArticle");
        
        MultivaluedHashMap<String, String> map = new MultivaluedHashMap<>();
        map.add("discount", discount);
        map.add("IDUser", IDUser);
        map.add("IDArticle", IDArticle);
        
        Response response = client.target("http://localhost:8080/Server/article/changeDiscountForArticle")
                .request()
                .post(Entity.form(map));
                        
        System.out.println(response.readEntity(String.class));
        return response;
    }
    
}
