/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.server.resources;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.etf.is1.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author stoja
 */
@Path("city")
public class CityResource {
    
    @PersistenceContext(name = "prodavnicaArtikalaPU")
    EntityManager em;
    
    @POST
    @Path("{cityName}")
    public Response createCity(@PathParam("cityName") String name){
        
        List<City> cities = em.createNamedQuery("City.findByName", City.class).setParameter("name", name).getResultList();
        if(!cities.isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).entity("Grad sa imenom " + name + " vec postoji!").build();
        }
        City city = new City();
        city.setName(name);
        em.persist(city);
        return Response.status(Response.Status.CREATED).entity("Grad sa imenom " + name + " je kreiran!").build();
    }
    
    @GET
    @Path("getAllCities")
    public List<City> getAllCities(){
        
        List<City> ret = new ArrayList<>();
        
        
        
        return ret;        
    }
}
