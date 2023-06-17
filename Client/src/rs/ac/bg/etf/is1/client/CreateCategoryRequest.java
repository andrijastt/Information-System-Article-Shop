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
public class CreateCategoryRequest extends Request{

    public CreateCategoryRequest(Client client, Scanner scanner) {
        super(client, scanner);
    }

    @Override
    public String name() {
        return "Create category Request";
    }

    @Override
    public Response send() {
        
        String name = this.readString("Category name");
        String IDSubcategory = this.readString("Is this category a sub category? If it is enter IDCategory of the category");        
        
        MultivaluedHashMap<String, String> map = new MultivaluedHashMap<>();
        map.add("name", name);
        map.add("IDSubcategory", IDSubcategory);
        
        Response response = client.target("http://localhost:8080/Server/category/create")
                .request()
                .post(Entity.form(map));
                               
        System.out.println(response.readEntity(String.class));
        return response;        
    }
    
}
