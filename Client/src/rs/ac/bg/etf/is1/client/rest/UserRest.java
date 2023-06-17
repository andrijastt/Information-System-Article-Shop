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

    public UserRest() {
    }
    

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

    public int getIDUser() {
        return IDUser;
    }

    public void setIDUser(int IDUser) {
        this.IDUser = IDUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIDCity() {
        return IDCity;
    }

    public void setIDCity(int IDCity) {
        this.IDCity = IDCity;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
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
