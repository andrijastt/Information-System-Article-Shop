/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rs.ac.bg.etf.is1.client;

import java.util.Scanner;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 *
 * @author stoja
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Client client = ClientBuilder.newClient();

        CreateCityRequest ccr = new CreateCityRequest(client, new Scanner(System.in));
        ccr.send();
        
    }
    
}
