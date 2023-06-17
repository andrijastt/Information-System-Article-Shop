/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.commands;

/**
 *
 * @author stoja
 */
public class AddMoneyToUserCommand extends Command {

    private final String amountToAdd;
    private final String IDUser;   

    public AddMoneyToUserCommand(String amountToAdd, String IDUser) {
        this.amountToAdd = amountToAdd;
        this.IDUser = IDUser;        
    }   

    public String getAmountToAdd() {
        return amountToAdd;
    }        

    public String getIDUser() {
        return IDUser;
    }
    
    @Override
    public Destination getDestination() {
        return Destination.SUBSYSTEM1;
    }

    @Override
    public Type getType() {
        return Type.ADD_MONEY_TO_USER;
    }
    
}
