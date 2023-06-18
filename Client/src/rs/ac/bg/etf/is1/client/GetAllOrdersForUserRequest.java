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
import rs.ac.bg.etf.is1.client.rest.OrderRest;

/**
 *
 * @author stoja
 */
public class GetAllOrdersForUserRequest extends Request {

    public GetAllOrdersForUserRequest(Client client, Scanner scanner) {
        super(client, scanner);
    }

    @Override
    public String name() {
        return "Get all orders for user request";
    }

    @Override
    public Response send() {
        
        String IDUser = this.readString("IDUser");
        
        Response response = client.target("http://localhost:8080/Server/order/getAllOrdersForUser")
                .path("{IDUser}")
                .resolveTemplate("IDUser", IDUser)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();
        
        String temp = response.readEntity(String.class);                       
        Gson gson = new Gson();        
        List<OrderRest> orders = Arrays.asList(new GsonBuilder().create().fromJson(temp, OrderRest[].class));
        
        for(OrderRest order: orders){
            System.out.println(order.toString() + "\n");
        }        
        return response;
    }
    
}
