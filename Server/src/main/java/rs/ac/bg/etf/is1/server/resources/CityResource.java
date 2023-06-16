/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.server.resources;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rs.ac.bg.etf.is1.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import rs.ac.bg.etf.is1.commands.Command;
import rs.ac.bg.etf.is1.commands.CreateCityCommand;
import rs.ac.bg.etf.is1.responses.JMSResponse;
import rs.ac.bg.etf.is1.responses.SuccessfulResponse;
import rs.ac.bg.etf.is1.server.JMSCommunicator;

/**
 *
 * @author stoja
 */
@Path("city")
public class CityResource {
    
    @Inject
    JMSCommunicator comm;
    
    @PersistenceContext(name = "prodavnicaArtikalaPU")
    EntityManager em;
    
    @POST
    @Path("{cityName}")
    @Consumes("application/xml")
    public Response createCity(@PathParam("cityName") String name){        
        CreateCityCommand ccc = new CreateCityCommand(name);        
        JMSResponse response =  comm.exchange(ccc);
        
        if(response instanceof SuccessfulResponse){
            return Response.status(Response.Status.CREATED).entity("Grad sa imenom " + name + " je kreiran!").build();
        }        
        return Response.status(Response.Status.BAD_REQUEST).entity("Grad sa imenom " + name + " nije kreiran!").build();
        
    }
    
    @GET
    @Path("getAllCities")
    public List<City> getAllCities(){
        
        List<City> ret = new ArrayList<>();
        
        
        
        return ret;        
    }
}
