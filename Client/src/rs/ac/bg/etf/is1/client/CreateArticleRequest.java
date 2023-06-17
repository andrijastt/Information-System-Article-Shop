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
public class CreateArticleRequest extends Request {

    public CreateArticleRequest(Client client, Scanner scanner) {
        super(client, scanner);
    }

    @Override
    public String name() {
        return "Create article request";
    }

    @Override
    public Response send() {
        
        String name = this.readString("Article name");
        String description = this.readString("Description");
        String price = this.readString("Price");
        String IDUser = this.readString("IDUser");
        String IDCategory = this.readString("IDCategory");
        
        MultivaluedHashMap<String, String> map = new MultivaluedHashMap<>();
        map.add("name", name);
        map.add("description", description);
        map.add("price", price);
        map.add("IDUser", IDUser);
        map.add("IDCategory", IDCategory);
        
        Response response = client.target("http://localhost:8080/Server/article/create")
                .request()
                .post(Entity.form(map));
        
        System.out.println(response.readEntity(String.class));
        return response;        
    }        
}
