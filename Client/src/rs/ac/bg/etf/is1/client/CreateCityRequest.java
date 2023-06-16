/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.client;

import java.util.Scanner;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author stoja
 */
public class CreateCityRequest extends Request{    

    public CreateCityRequest(Client client, Scanner scanner) {
        super(client, scanner);
    }

    @Override
    public String name() {
        return "Create city request";
    }

    @Override
    public Response send() {
        
        String name = this.readString("City name");
        
        Response post = client.target("http://localhost:8080/Server/city")
                .path("/{cityName}")
                .resolveTemplate("cityName", name)
                .request()
                .post(Entity.entity(name, "application/xml"));
        
        
        System.out.println(post);
        return post;
        
    }            
}
