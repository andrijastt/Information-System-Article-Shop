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

    private final String IDUser;
    private final String address;
    private final String IDCity;

    public ChangeAddressAndCityForUser(String IDUser, String address, String IDCity) {
        this.IDUser = IDUser;
        this.address = address;
        this.IDCity = IDCity;
    }

    public String getIDUser() {
        return IDUser;
    }

    public String getAddress() {
        return address;
    }

    public String getIDCity() {
        return IDCity;
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
