/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.commands;

/**
 *
 * @author stoja
 */
public class CreateUserCommand extends Command {

    private final String username;
    private final String password;
    private final String name;
    private final String lastname;
    private final String address;
    private final String IDCity;
    private final int money;

    public CreateUserCommand(String username, String password, String name, String lastname, String address, String IDCity, int money) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.IDCity = IDCity;
        this.money = money;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getIDCity() {
        return IDCity;
    }

    public int getMoney() {
        return money;
    }
                    
    @Override
    public Destination getDestination() {
        return Destination.SUBSYSTEM1;
    }

    @Override
    public Type getType() {
        return Type.CREATE_USER;
    }
    
}
