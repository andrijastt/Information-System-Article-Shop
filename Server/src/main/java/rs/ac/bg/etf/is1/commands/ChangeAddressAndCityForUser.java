/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.commands;

/**
 *
 * @author stoja
 */
public class ChangeAddressAndCityForUser extends Command{

    private final int IDUser;
    private final String address;
    private final String city;

    public ChangeAddressAndCityForUser(int IDUser, String address, String city) {
        this.IDUser = IDUser;
        this.address = address;
        this.city = city;
    }

    public int getIDUser() {
        return IDUser;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }        
    
    @Override
    public Destination getDestination() {
        return Destination.SUBSYSTEM1;
    }

    @Override
    public Type getType() {
        return Type.CHANGE_ADDRESS_AND_CITY_FOR_USER;
    }            
}
