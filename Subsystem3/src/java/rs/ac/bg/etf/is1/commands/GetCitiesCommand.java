/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.commands;

/**
 *
 * @author stoja
 */
public class GetCitiesCommand extends Command{

    @Override
    public Destination getDestination() {
        return Destination.SUBSYSTEM1;
    }

    @Override
    public Type getType() {
        return Type.GET_CITIES;
    }  
}
