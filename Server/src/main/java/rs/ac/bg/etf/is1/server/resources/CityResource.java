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
import rs.ac.bg.etf.is1.commands.GetCitiesCommand;
import rs.ac.bg.etf.is1.responses.DataResponse;
import rs.ac.bg.etf.is1.responses.FailedResponse;
import rs.ac.bg.etf.is1.responses.JMSResponse;
import rs.ac.bg.etf.is1.responses.SuccessfulResponse;
import rs.ac.bg.etf.is1.rest.CityRest;
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
            SuccessfulResponse sr = (SuccessfulResponse) response;
            return Response.status(Response.Status.CREATED).entity(sr.toString()).build();
        }        
        FailedResponse fr = (FailedResponse) response;
        return Response.status(Response.Status.BAD_REQUEST).entity(fr.toString()).build();
        
    }
    
    @GET
    @Path("getAllCities")
    public Response getAllCities(){        
        GetCitiesCommand gcc = new GetCitiesCommand();
        JMSResponse response = comm.exchange(gcc);
        if(response instanceof DataResponse){
            DataResponse<List<City>> dataResponse = (DataResponse<List<City>>) response;
            
            List<CityRest> cities = new ArrayList<>();
            for(City city: dataResponse.getData()){
                CityRest cityrest = new CityRest(city.getIDCity(), city.getName());
                cities.add(cityrest);
            }
            
            return Response.status(Response.Status.OK).entity(cities).build();
        }        
        return Response.status(Response.Status.BAD_REQUEST).entity("Couldn't get all cities!").build();          
    }
}
