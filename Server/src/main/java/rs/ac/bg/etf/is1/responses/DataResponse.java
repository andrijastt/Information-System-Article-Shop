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
public class DataResponse<T> extends SuccessfulResponse {
    private final T data;
    
    public DataResponse(Command cmd, T data) {
        super(cmd);
        this.data = data;
    }

    public T getData() {
        return data;
    }        
}
