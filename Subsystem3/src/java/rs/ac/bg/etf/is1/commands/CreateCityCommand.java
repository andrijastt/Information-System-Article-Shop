/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.commands;

import java.util.UUID;

/**
 *
 * @author stoja
 */
public class CreateCityCommand extends Command {

    private final String name;    
    public CreateCityCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }    
    
    @Override
    public Destination getDestination() {
        return Destination.SUBSYSTEM1;
    }

    @Override
    public Type getType() {
        return Type.CREATE_CITY;
    }
            
}
