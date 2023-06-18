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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import rs.ac.bg.etf.is1.commands.GetAllOrdersCommand;
import rs.ac.bg.etf.is1.commands.GetAllOrdersForUserCommand;
import rs.ac.bg.etf.is1.entities.Orders;
import rs.ac.bg.etf.is1.responses.DataResponse;
import rs.ac.bg.etf.is1.responses.FailedResponse;
import rs.ac.bg.etf.is1.responses.JMSResponse;
import rs.ac.bg.etf.is1.rest.OrderRest;
import rs.ac.bg.etf.is1.server.JMSCommunicator;

/**
 *
 * @author stoja
 */
@Path("order")
public class OrderResource {
    
    @Inject
    JMSCommunicator comm;
    
    @PersistenceContext(name = "prodavnicaArtikalaPU")
    EntityManager em;
    
    @GET
    @Path("getAllOrdersForUser/{IDUser}")
    public Response getAllOrdersForUser(@PathParam("IDUser") String IDUser){
        GetAllOrdersForUserCommand gaofuc = new GetAllOrdersForUserCommand(IDUser);
        JMSResponse response = comm.exchange(gaofuc);        
        if(response instanceof DataResponse){
            DataResponse<List<Orders>> dataResponse = (DataResponse<List<Orders>>) response;            
            List<OrderRest> orders = new ArrayList<>();            
            for(Orders order: dataResponse.getData()){                
                OrderRest orderRest = new OrderRest(order.getIDOrder(), order.getTotalPrice(), order.getTimeCreated()
                        , order.getAddress(), order.getIDCity().getIDCity(), order.getIDUser().getIDUser());
                orders.add(orderRest);
            }
            return Response.status(Response.Status.OK).entity(orders).build();
        }
        FailedResponse fr = (FailedResponse) response;
        return Response.status(Response.Status.BAD_REQUEST).entity(fr.toString()).build();
    }

    @GET
    @Path("getAllOrders")
    public Response getAllOrders(){
        GetAllOrdersCommand gao = new GetAllOrdersCommand();
        JMSResponse response = comm.exchange(gao);        
        if(response instanceof DataResponse){
            DataResponse<List<Orders>> dataResponse = (DataResponse<List<Orders>>) response;            
            List<OrderRest> orders = new ArrayList<>();            
            for(Orders order: dataResponse.getData()){                
                OrderRest orderRest = new OrderRest(order.getIDOrder(), order.getTotalPrice(), order.getTimeCreated()
                        , order.getAddress(), order.getIDCity().getIDCity(), order.getIDUser().getIDUser());
                orders.add(orderRest);
            }
            return Response.status(Response.Status.OK).entity(orders).build();
        }
        FailedResponse fr = (FailedResponse) response;
        return Response.status(Response.Status.BAD_REQUEST).entity(fr.toString()).build();
    }
    
}
