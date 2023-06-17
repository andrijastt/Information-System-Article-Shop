/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.commands;

/**
 *
 * @author stoja
 */
public class PaymentProcessCommand extends Command {

    private final String username;

    public PaymentProcessCommand(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
    
    @Override
    public Destination getDestination() {
        return Destination.SUBSYSTEM3;
    }

    @Override
    public Type getType() {
        return Type.PAYMENT_PROCESS;
    }
    
}
