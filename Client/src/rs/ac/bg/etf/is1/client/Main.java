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

        while(true){
            
            System.out.println("Enter number for option (1 - 19)");
            
            Scanner scanner = new Scanner(System.in);
            
            int number = Integer.parseInt(scanner.nextLine());
            Request req;
            switch(number){                                
                case 1:
                    req =  new CreateCityRequest(client, new Scanner(System.in));
                    req.send();
                    break;
                case 2:
                    req =  new CreateUserRequest(client, new Scanner(System.in));
                    req.send();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    break;
                case 12:
                    break;
                case 13:
                    break;
                case 14:
                    break;
                case 15:
                    break;
                case 16:
                    break;
                case 17:
                    break;
                case 18:
                    break;
                case 19:
                    break;
                
                default:
                    System.out.println("Wrong number input! Try again.");                    
                    break;
            }                        

        }
        
        
        
    }
    
}
