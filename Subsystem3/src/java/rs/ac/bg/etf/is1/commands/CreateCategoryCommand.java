/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.commands;

/**
 *
 * @author stoja
 */
public class CreateCategoryCommand extends Command {

    private final String name;
    private final String IDSubcategory;

    public CreateCategoryCommand(String name, String IDSubcategory) {
        this.name = name;
        this.IDSubcategory = IDSubcategory;
    }

    public String getIDSubcategory() {
        return IDSubcategory;
    }    

    public String getName() {
        return name;
    }
    
    @Override
    public Destination getDestination() {
        return Destination.SUBSYSTEM2;
    }

    @Override
    public Type getType() {
        return Type.CREATE_CATEGORY;
    }
    
}
