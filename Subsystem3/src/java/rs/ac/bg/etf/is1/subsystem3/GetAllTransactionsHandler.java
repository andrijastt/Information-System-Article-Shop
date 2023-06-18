/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.subsystem3;

import java.util.List;
import javax.persistence.EntityManager;
import rs.ac.bg.etf.is1.commands.Command;
import rs.ac.bg.etf.is1.commands.GetAllTransactionsCommand;
import rs.ac.bg.etf.is1.entities.Transaction;
import rs.ac.bg.etf.is1.responses.CommandHandler;
import rs.ac.bg.etf.is1.responses.DataResponse;
import rs.ac.bg.etf.is1.responses.JMSResponse;

/**
 *
 * @author stoja
 */
public class GetAllTransactionsHandler extends CommandHandler {

    public GetAllTransactionsHandler(EntityManager em) {
        super(em);
    }

    @Override
    public JMSResponse handle(Command cmd) {
        GetAllTransactionsCommand gatc = (GetAllTransactionsCommand) cmd;
        List<Transaction> transaction = em.createNamedQuery("Transaction.findAll").getResultList();
        return new DataResponse(gatc, transaction);
    }
    
}
