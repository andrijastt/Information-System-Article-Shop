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
public class FailedResponse extends JMSResponse {
    
    private final String reason;    
    public FailedResponse(Command cmd, String reason) {
        super(cmd);
        this.reason = reason;
    }

    @Override
    public boolean isSuccessful() {
        return true;
    }
    
    public String toString() {
        return "Failed Response[id=" + getID() + ", type=" + cmd.getType() + ", from=" + cmd.getDestination() + ", reason = " + reason +" ]";
    }
}
