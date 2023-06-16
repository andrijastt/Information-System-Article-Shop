/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.client;

import java.util.Scanner;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.Response;

/**
 *
 * @author stoja
 */
public class CreateUserRequest extends Request{

    public CreateUserRequest(Client client, Scanner scanner) {
        super(client, scanner);
    }

    @Override
    public String name() {
        return "Create User Request";
    }

    @Override
    public void send() {
        
        String username = this.readString("Username");
        String password = this.readString("Password");
        String name = this.readString("Name");
        String lastname = this.readString("Lastname");
        String address = this.readString("Address");
        String IDCity = this.readString("IDCity");
        
        MultivaluedHashMap<String, String> map = new MultivaluedHashMap<>();
        map.add("username", username);
        map.add("password", password);
        map.add("name", name);
        map.add("lastname", lastname);
        map.add("address", address);
        map.add("IDCity", IDCity);        
        
        Response post = client.target("http://localhost:8080/Server/users/create")                   
                .request()
                .post(Entity.form(map));
        
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
