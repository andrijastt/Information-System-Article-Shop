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

    private final int amountToAdd;
    private final String username;   

    public AddMoneyToUserCommand(int amountToAdd, String username) {
        this.amountToAdd = amountToAdd;
        this.username = username;        
    }   

    public int getAmountToAdd() {
        return amountToAdd;
    }        

    public String getUsername() {
        return username;
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
