/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.commands;

/**
 *
 * @author stoja
 */
public class GetAllTransactionsCommand extends Command {
    @Override
    public Destination getDestination() {
        return Destination.SUBSYSTEM3;
    }

    @Override
    public Type getType() {
        return Type.GET_ALL_TRANSACTIONS;
    }
}
