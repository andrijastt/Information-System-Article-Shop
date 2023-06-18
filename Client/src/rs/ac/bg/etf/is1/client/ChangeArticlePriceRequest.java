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
public class ChangeArticlePriceRequest extends Request {

    public ChangeArticlePriceRequest(Client client, Scanner scanner) {
        super(client, scanner);
    }

    @Override
    public String name() {
        return "Change article price request";
    }

    @Override
    public Response send() {
        
        String price = this.readString("New price");
        String IDArticle = this.readString("IDArticle");
        String IDUser = this.readString("IDUser");
        
        MultivaluedHashMap<String, String> map = new MultivaluedHashMap<>();
        map.add("price", price);
        map.add("IDArticle", IDArticle);
        map.add("IDUser", IDUser);
        
        Response response = client.target("http://localhost:8080/Server/article")
                .path("changeArticlePrice")
                .request()
                .post(Entity.form(map));
        
        System.out.println(response.readEntity(String.class));
        return response;
        
    }
    
}
