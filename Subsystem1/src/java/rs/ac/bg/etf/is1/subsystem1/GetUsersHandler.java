/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.subsystem1;

import javax.persistence.EntityManager;
import rs.ac.bg.etf.is1.commands.Command;
import rs.ac.bg.etf.is1.responses.CommandHandler;
import rs.ac.bg.etf.is1.responses.JMSResponse;

/**
 *
 * @author stoja
 */
public class GetUsersHandler extends CommandHandler {   

    public GetUsersHandler(EntityManager em) {
        super(em);
    }

    @Override
    public JMSResponse handle(Command cmd) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
