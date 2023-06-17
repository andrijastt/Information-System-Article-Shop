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
import rs.ac.bg.etf.is1.client.rest.CityRest;
import rs.ac.bg.etf.is1.client.rest.UserRest;

/**
 *
 * @author stoja
 */
public class GetCitiesRequest extends Request {

    public GetCitiesRequest(Client client, Scanner scanner) {
        super(client, scanner);
    }   

    @Override
    public String name() {
        return "Get cities request";
    }

    @Override
    public Response send() {
        
        Response response = client.target("http://localhost:8080/Server/city")
                .path("getAllCities")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();                        
        
        String temp = response.readEntity(String.class);                       
        Gson gson = new Gson();        
        List<CityRest> cities = Arrays.asList(new GsonBuilder().create().fromJson(temp, CityRest[].class));
        
        for(CityRest city: cities){
            System.out.println(city.toString() + "\n");
        }        
        return Response.status(Response.Status.OK).build();                
    }
    
}
