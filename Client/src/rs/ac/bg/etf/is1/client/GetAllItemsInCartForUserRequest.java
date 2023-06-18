/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import rs.ac.bg.etf.is1.client.rest.ArticleRest;

/**
 *
 * @author stoja
 */
public class GetAllItemsInCartForUserRequest extends Request {

    public GetAllItemsInCartForUserRequest(Client client, Scanner scanner) {
        super(client, scanner);
    }

    @Override
    public String name() {
        return "Get All Items In Cart For User Request";
    }

    @Override
    public Response send() {
        
        String IDUser = this.readString("IDUser");
        
        Response response = client.target("http://localhost:8080/Server/article/getAllItemsInCartForUser").
                path("/{IDUser}")
                .resolveTemplate("IDUser", IDUser)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();
        
        String temp = response.readEntity(String.class);                       
        Gson gson = new Gson();        
        List<ArticleRest> articles = Arrays.asList(new GsonBuilder().create().fromJson(temp, ArticleRest[].class));
        
        for(ArticleRest article: articles){
            System.out.println(article.toString() + "\n");
        }        
        return response;                
    }
    
}
