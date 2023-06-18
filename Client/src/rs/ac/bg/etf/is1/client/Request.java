/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.client;

import java.util.Scanner;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

/**
 *
 * @author stoja
 */
public abstract class Request {
    
    public abstract String name();
    public abstract Response send();
    
    protected Client client;    
    protected Scanner scanner;

    public Request(Client client, Scanner scanner) {
        this.client = client;
        this.scanner = scanner;
    }     
       
    protected String readString(String prompt) {
        System.out.println(prompt + ":");
        return scanner.nextLine();
    }
    
}
