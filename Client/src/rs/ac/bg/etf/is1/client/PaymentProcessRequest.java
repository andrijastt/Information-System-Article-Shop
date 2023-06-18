/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.client;

import java.util.Scanner;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

/**
 *
 * @author stoja
 */
public class PaymentProcessRequest extends Request {

    public PaymentProcessRequest(Client client, Scanner scanner) {
        super(client, scanner);
    }

    @Override
    public String name() {
        return "Payment process request";
    }

    @Override
    public Response send() {
        
        String IDUser = this.readString("IDUser");
        
        Response response = client.target("http://localhost:8080/Server/transaction/paymentProcess")
                .path("{IDUser}")
                .resolveTemplate("IDUser", IDUser)
                .request()
                .post(Entity.entity(IDUser, "application/xml"));
        
        System.out.println(response.readEntity(String.class));
        return response;        
    }
    
}
