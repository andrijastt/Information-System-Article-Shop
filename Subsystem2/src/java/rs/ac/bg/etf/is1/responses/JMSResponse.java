/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.responses;

import java.io.Serializable;
import rs.ac.bg.etf.is1.commands.Command;

/**
 *
 * @author stoja
 */
public abstract class JMSResponse implements Serializable {
    
    protected final Command cmd;

    public JMSResponse(Command cmd) {
        this.cmd = cmd;
    }
    
    public String getID(){
        return cmd.getId();
    }
    
    abstract public boolean isSuccessful();

    @Override
    public String toString() {
        return "Response[id=" + getID() + ", type=" + cmd.getType() + ", from=" + cmd.getDestination() + "]";
    }
            
}
