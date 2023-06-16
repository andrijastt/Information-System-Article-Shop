/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.responses;

import rs.ac.bg.etf.is1.commands.Command;

/**
 *
 * @author stoja
 */
public class SuccessfulResponse extends JMSResponse {

    public SuccessfulResponse(Command cmd) {
        super(cmd);
    }

    @Override
    public boolean isSuccessful() {
        return true;
    }
    
    public String toString() {
        return "Successful Response[id=" + getID() + ", type=" + cmd.getType() + ", from=" + cmd.getDestination() + "]";
    }
}
