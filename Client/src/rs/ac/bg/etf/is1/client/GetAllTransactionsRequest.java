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
import rs.ac.bg.etf.is1.client.rest.TransactionRest;

/**
 *
 * @author stoja
 */
public class GetAllTransactionsRequest extends Request {

    public GetAllTransactionsRequest(Client client, Scanner scanner) {
        super(client, scanner);
    }

    @Override
    public String name() {
        return "Get all transaciotns request";
    }

    @Override
    public Response send() {
        
        Response response = client.target("http://localhost:8080/Server/transaction/getAllTransactions")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();
        
        String temp = response.readEntity(String.class);
        if(temp.contains("Failed Response")){
            System.out.println(temp);
            return response;
        }
        List<TransactionRest> transactions = Arrays.asList(new GsonBuilder().create().fromJson(temp, TransactionRest[].class));
        
        for(TransactionRest trasnsaction: transactions){
            System.out.println(trasnsaction.toString() + "\n");
        }        
        return response;                
    }
    
}
