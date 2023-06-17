/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.client.rest;

/**
 *
 * @author stoja
 */
public class UserRest {
 
    private int IDUser;
    private String username;
    private String password;
    private String name;
    private String lastname;
    private String address;
    private int IDCity;
    private int money;

    public UserRest(int IDUser, String username, String password, String name, String lastname, String address, int IDCity, int money) {
        this.IDUser = IDUser;
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.IDCity = IDCity;
        this.money = money;
    }        

    @Override
    public String toString() {
        return "IDUser: " + IDUser + "\n" +
                "username: " + username + "\n" +
                "password: " + password + "\n" +
                "name: " + name + "\n" +
                "lastname: " + lastname + "\n" +
                "address: " + address + "\n" +
                "IDCity: " + IDCity + "\n" +
                "money: " + money + "\n";
    }
    
    
    
}
