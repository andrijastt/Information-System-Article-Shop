/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.client;

import com.google.gson.GsonBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import rs.ac.bg.etf.is1.client.rest.CategoryRest;

/**
 *
 * @author stoja
 */
public class GetCategoriesRequest extends Request {

    public GetCategoriesRequest(Client client, Scanner scanner) {
        super(client, scanner);
    }

    @Override
    public String name() {
        return "Get cateogories request";
    }

    @Override
    public Response send() {
        
        Response response = client.target("http://localhost:8080/Server/category")
                .path("getCategories")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();                        
        
        String temp = response.readEntity(String.class);
        if(temp.contains("Failed Response")){
            System.out.println(temp);
            return response;
        }
        List<CategoryRest> categories = Arrays.asList(new GsonBuilder().create().fromJson(temp, CategoryRest[].class));
        
        for(CategoryRest category: categories){
            System.out.println(category.toString() + "\n");
        }        
        return response;                
    }
    
}
