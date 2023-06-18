/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.server.resources;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import rs.ac.bg.etf.is1.commands.GetAllTransactionsCommand;
import rs.ac.bg.etf.is1.commands.PaymentProcessCommand;
import rs.ac.bg.etf.is1.entities.Transaction;
import rs.ac.bg.etf.is1.responses.DataResponse;
import rs.ac.bg.etf.is1.responses.FailedResponse;
import rs.ac.bg.etf.is1.responses.JMSResponse;
import rs.ac.bg.etf.is1.responses.SuccessfulResponse;
import rs.ac.bg.etf.is1.rest.TransactionRest;
import rs.ac.bg.etf.is1.server.JMSCommunicator;

/**
 *
 * @author stoja
 */
@Path("transaction")
public class TransactionResource {
    @Inject
    JMSCommunicator comm;
    
    @PersistenceContext(name = "prodavnicaArtikalaPU")
    EntityManager em;
    
    @POST
    @Path("paymentProcess/{IDUser}")
    public Response paymentProcess(@PathParam("IDUser") String IDUser){
        PaymentProcessCommand ppc = new PaymentProcessCommand(IDUser);
        JMSResponse response = comm.exchange(ppc);
        if(response instanceof SuccessfulResponse){
            SuccessfulResponse sr = (SuccessfulResponse) response;
            return Response.status(Response.Status.OK).entity(sr.toString()).build();
        }
        FailedResponse fr = (FailedResponse) response;
        return Response.status(Response.Status.BAD_REQUEST).entity(fr.toString()).build();
    }
    
    @GET
    @Path("getAllTransactions")
    public Response getAllTransactions(){
        GetAllTransactionsCommand gatc = new GetAllTransactionsCommand();
        JMSResponse response = comm.exchange(gatc);
        if(response instanceof DataResponse){
            DataResponse<List<Transaction>> dataResponse = (DataResponse<List<Transaction>>) response;
            List<TransactionRest> transactions = new ArrayList<>();
            for(Transaction transaction: dataResponse.getData()){                
                TransactionRest transactionRest = new TransactionRest(transaction.getIDTransaction(), transaction.getPaymentTime()
                        , transaction.getTotalPrice(), transaction.getIDOrder().getIDOrder());
                transactions.add(transactionRest);
            }
            return Response.status(Response.Status.OK).entity(transactions).build();
        }
        FailedResponse fr = (FailedResponse) response;
        return Response.status(Response.Status.BAD_REQUEST).entity(fr.toString()).build();
    }
    
}
