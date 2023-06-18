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
                        
            System.out.println("0. Exit");
            System.out.println("1. Create City");
            System.out.println("2. Create user");
            System.out.println("3. Add money to user");
            System.out.println("4. Change address and city for user");
            System.out.println("5. Create category");
            System.out.println("6. Create article");
            System.out.println("7. Change article price");
            System.out.println("8. Change discount for article");
            System.out.println("9. Add article amount in cart");
            System.out.println("10. Remove article amount in cart");
            System.out.println("11. Paymnet process");
            System.out.println("12. Get cities");
            System.out.println("13. Get users");
            System.out.println("14. Get categories");
            System.out.println("15. Get all articles that user sells");
            System.out.println("16. Get all items in car for user");
            System.out.println("17. Get all orders for user");
            System.out.println("18. Get all orders");
            System.out.println("19. Get all transactions");
            System.out.println("Enter number for option (0 - 19)");
            Scanner scanner = new Scanner(System.in);
            
            int number = Integer.parseInt(scanner.nextLine());
            Request req;
            switch(number){       
                case 0:
                    System.out.println("Exiting");                    
                    break;
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
                    req =  new ChangeDiscountForArticleRequest(client, new Scanner(System.in));
                    req.send();
                    break;
                case 9:
                    req =  new AddArticleAmountInCartRequest(client, new Scanner(System.in));
                    req.send();
                    break;
                case 10:
                    req =  new RemoveArticleAmountInCartRequest(client, new Scanner(System.in));
                    req.send();
                    break;
                case 11:
                    req =  new PaymentProcessRequest(client, new Scanner(System.in));
                    req.send();
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
                    req =  new GetAllArticlesThatUserSellsRequest(client, new Scanner(System.in));
                    req.send();
                    break;
                case 16:
                    req =  new GetAllItemsInCartForUserRequest(client, new Scanner(System.in));
                    req.send();
                    break;
                case 17:
                    req =  new GetAllOrdersForUserRequest(client, new Scanner(System.in));
                    req.send();
                    break;
                case 18:
                    req =  new GetAllOrdersRequest(client, new Scanner(System.in));
                    req.send();
                    break;                    
                case 19:
                    req =  new GetAllTransactionsRequest(client, new Scanner(System.in));
                    req.send();
                    break;                                
                default:
                    System.out.println("Wrong number input! Try again.");                    
                    break;
            }                        
            if(number == 0) break;
        }
        client.close();
    }
    
}
