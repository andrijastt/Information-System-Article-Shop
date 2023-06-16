/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.responses;

import javax.persistence.EntityManager;
import rs.ac.bg.etf.is1.commands.Command;

/**
 *
 * @author stoja
 */
public abstract class CommandHandler {
    protected final EntityManager em;

    public CommandHandler(EntityManager em) {
        this.em = em;
    }
    abstract public JMSResponse handle(Command cmd);
}
