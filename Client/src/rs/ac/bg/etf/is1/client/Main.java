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
                    req =  new AddMoneyToUserRequest(client, new Scanner(System.in));
                    req.send();
                    break;
                case 4:
                    req =  new ChangeAddressAndCityForUserRequest(client, new Scanner(System.in));
                    req.send();
                    break;
                case 5:
                    req =  new CreateCategoryRequest(client, new Scanner(System.in));
                    req.send();
                    break;
                case 6:
                    req =  new CreateArticleRequest(client, new Scanner(System.in));
                    req.send();
                    break;
                case 7:
                    req =  new ChangeArticlePriceRequest(client, new Scanner(System.in));
                    req.send();
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
                    req = new GetCitiesRequest(client, new Scanner(System.in));
                    req.send();
                    break;
                case 13:
                    req = new GetUsersRequest(client, new Scanner(System.in));
                    req.send();
                    break;
                case 14:
                    req = new GetCategoriesRequest(client, new Scanner(System.in));
                    req.send();
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
//        client.close();
    }
    
}
